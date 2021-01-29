$(document).ready(function () {
    $(".owl-carousel").owlCarousel({
        loop: true,
        margin: 10,
        //nav: true,
        dots: false,
        // autoplay: true, // автопрокрутка
        // autoplayTimeout: 3000, // задержка в мс
        // autoplayHoverPause: false,
        // onTranslate: function (e) {
        //     $(".owl-item").removeClass("highlighted");
        // },
        // onTranslated: function (e) {
        //     $(".owl-item").eq(e.item.index).addClass("highlighted");
        // },
        responsive: {
            0: {
                items: 1
            },
            600: {
                items: 3
            },
            1000: {
                items: 5
            }
        }
    });

    $('.js-category-carousel').on('click', (e) => {
        if (e.target.className != 'owl-stage') {
            $('.spinner').toggleClass('hidden');
            $('.category').each((i, item) => $(item).removeClass('category-active'));
            $(e.target).addClass('category-active');
            fetch('api/post?statusId=3&category=' + e.target.id + '&city=' + e.target.getAttribute('city-name'))
                .then(response => response.json())
                .then(data => {
                    const list = $('.js-posts').html('');
                    data.forEach(item => {
                        list.append(`
                    <a href="post/${item.id}">
					<li class="post-card">
						<img src="photo/${item.id}" class="post-image w-100" alt="post image">
						<div class="post-info w-100 d-flex flex-column justify-content-between p-1">
							<div class="d-flex justify-content-between align-items-satrt">
								<span>${item.header}</span>
								<span>${item.dateFrom}</span>
							</div>
							<div class="text-center">
                                 ${item.description}
                            </div>
							<div class="align-self-end">
								<div class="d-flex align-items-end">
									${item.likes.likes}
									<img src="/images/like_icon.svg" alt="like-icon" class="ms-1 mb-1">
								</div>
							</div>
						</div>
					</li>
					</a>
				`)
                    });
                })
                .catch((err) => console.error(err))
                .finally(() => $('.spinner').toggleClass('hidden'));
        }
    })
});