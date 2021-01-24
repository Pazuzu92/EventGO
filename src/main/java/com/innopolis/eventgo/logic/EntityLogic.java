package com.innopolis.eventgo.logic;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.BreakIterator;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EntityLogic {

    public static class FilterBuilder<E> {

        private final Class<E> entityClass;

        private final E entityExample;

        @SneakyThrows
        public FilterBuilder(Class<E> entityClass) {
            this.entityClass = entityClass;
            entityExample = entityClass.getDeclaredConstructor().newInstance();
        }

        @SneakyThrows
        public <T> void field(String field, Optional<T> value) {
            if (value.isPresent()) {
                String methodName = "set" + UCharacter.toTitleCase(field, BreakIterator.getWordInstance());
                final Method method = Arrays.stream(entityClass.getDeclaredMethods())
                        .filter(m -> m.getName().equals(methodName))
                        .findFirst()
                        .orElseThrow(RuntimeException::new);
                method.invoke(entityExample, value.get());
            }
        }

        public E getEntityExample() {
            return entityExample;
        }
    }

    public static class ListOperation<E, D> {

        private final Class<E> entityClass;

        private final Class<D> dtoClass;

        private final JpaRepository<E, ?> repository;

        private final ModelMapper modelMapper;

        private Optional<PageRequest> pageRequest;

        private Optional<FilterBuilder<E>> filterBuilder;

        public ListOperation(Class<E> entityClass, Class<D> dtoClass, JpaRepository<E, ?> repository, ModelMapper modelMapper) {
            this.entityClass = entityClass;
            this.dtoClass = dtoClass;
            this.repository = repository;
            this.modelMapper = modelMapper;
        }

        public ListOperation<E, D> withPagination(Optional<Integer> page,
                                                  Optional<Integer> size,
                                                  Optional<String> sort) {
            this.pageRequest = getPageRequest(page, size, sort);
            return this;
        }

        public ListOperation<E, D> withFiltering() {
            this.filterBuilder = Optional.of(new FilterBuilder(entityClass));
            return this;
        }

        public <T> ListOperation<E, D> field(String field, Optional<T> value) {
            filterBuilder.get().field(field, value);
            return this;
        }

        public List<D> getResult() {
            Optional<Example<E>> objectExample = filterBuilder
                    .map(FilterBuilder::getEntityExample)
                    .map(Example::of);
            List<E> entityList = getResultList(repository, pageRequest, objectExample);
            return entityList.stream()
                    .map(e -> modelMapper.map(e, dtoClass))
                    .collect(Collectors.toList());
        }
    }

    public static <E, D> ListOperation<E, D> list(Class<E> entityClass,
                                                  Class<D> dtoClass,
                                                  JpaRepository<E, ?> repository,
                                                  ModelMapper modelMapper) {
        return new ListOperation<>(entityClass, dtoClass, repository, modelMapper);
    }

    public static Optional<PageRequest> getPageRequest(Optional<Integer> page, Optional<Integer> size, Optional<String> sort) {
        Optional<PageRequest> pageRequest;
        if (page.isPresent() && size.isPresent()) {
            pageRequest = sort.map(s -> Optional.of(PageRequest.of(page.get(), size.get(), Sort.by(s)))).orElseGet(() -> Optional.of(PageRequest.of(page.get(), size.get())));
        } else {
            pageRequest = Optional.empty();
        }
        return pageRequest;
    }

    private static <T> List<T> getResultList(JpaRepository<T, ?> repository,
                                             Optional<PageRequest> pageRequest,
                                             Optional<Example<T>> example) {
        List<T> result;
        if (pageRequest.isPresent() && example.isPresent()) {
            result = repository.findAll(example.get(), pageRequest.get()).getContent();
        } else
            result = pageRequest.map(request -> repository.findAll(request).getContent()).orElseGet(() -> example.map(repository::findAll).orElseGet(repository::findAll));
        return result;
    }


}
