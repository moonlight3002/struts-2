/**
 * 
 */

$(window).scroll(function() {
	   if($(window).scrollTop()> 50) {
	       $('.backTop').fadeIn();	
	   }else{
		   $('.backTop').fadeOut();
	   }
});

$('.backTop').on('click', function(){
	console.log("click")
	$('html, body').animate({scrollTop:0}, 500, 'swing');
})