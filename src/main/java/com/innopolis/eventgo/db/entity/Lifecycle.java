package com.innopolis.eventgo.db.entity;

import com.innopolis.eventgo.db.LifecycleStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "lifecycle")
public class Lifecycle {

    @Column(name = "lifecycle_status")
    private LifecycleStatus lifecycleStatus;

    public Lifecycle() {}
    public Lifecycle(LifecycleStatus lifecycleStatus){
        this.lifecycleStatus = lifecycleStatus;
    }

    @Id
    @Column(name = "id")
    private Long id;

    @Override
    public String toString() {
        return "Lifecycle{" +
                "lifecycleStatus=" + lifecycleStatus.toString() +
                ", id=" + id +
                '}';
    }
}
