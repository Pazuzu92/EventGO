$(document).ready(function(){
  $(".owl-carousel").owlCarousel({
  	loop:true,
    margin:10,
    nav:true,
    dots: false,
    responsive:{
        0:{
            items:1
        },
        600:{
            items:3
        },
        1000:{
            items:5
        }
    }
  });

  /*$('.js-category-btn').on('click', function() {
  	fetch('URL?category=123123')
	  	.then(response => response.json())
	  	.then(data => {
  			const list = $('.post-list').html('');
  			data.forEach(item => {
				list.append(`
					<li class="post-card">
						<img src=`${item.image}` class="post-image w-100" alt="post image">
						<div class="post-info d-flex flex-column justify-content-between p-1">
							<div class="d-flex justify-content-between align-items-satrt">
								<span>`item.name`</span>
								<span>`${item.time}`</span>
							</div>
							<div class="d-flex align-items-center text-center">«Динамо-Ак Барс» (Казань) - «Енисей» (Красноярск)</div>
							<div class="align-self-end">
								<div class="d-flex align-items-end">
									200
									<img src="like_icon.svg" alt="like-icon" class="ms-1 mb-1">
								</div>
							</div>
						</div>
					</li>
				`)
  			});
	  	})
  })*/
});