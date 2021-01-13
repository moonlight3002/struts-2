var jobListOffset = 0;

$(function(){
	
	getNotificationList();
	console.log($('.content').height());

	$(window).scroll(function() {
	/*    console.log("window", $(window).scrollTop());
	    console.log("element", $('.seemore').offset().top);
	    console.log("window height",$(window).height());*/
//	    console.log($(window).scrollTop()+$(window).height());
	   if($(window).scrollTop() + $(window).height() > ($('.seemore.active').offset().top-50)) {
	       console.log("bottom!");
	       loadgetJobLists("append");	
	   }
	});
	
	$(document).ready(function(){
		var datas = getCountry(function(datas){
//			console.log("datas", datas);
			$.each(datas, function(i, item){
//				console.log(datas[i].countryName)
				$('#country').append(`<option value="${datas[i].countryID}">${datas[i].countryName}</option>`)
			})
		});
		$('.seemore').addClass("active");
		$('.nomore').hide();
		$('.seemore').show();
		loadgetJobLists("append");
	});
	
	$('#country').on('change', function(){
		console.log("change")
		$('#city').find('option:not(:first)').remove();
		console.log("country: ", $(this).val(), "city", $('#city').val());
		var datas = getCity($(this).val(),function(datas){
			console.log("datas", datas);
			$.each(datas, function(i, item){
//				console.log(datas[i].cityName)
				$('#city').append(`<option value="${datas[i].cityID}">${datas[i].cityName}</option>`)
			})
		});
		$('.seemore').addClass("active");
		$('.nomore').hide();
		$('.seemore').show();
		loadgetJobLists("refresh");


	});
	
	$('#city').on('change', function(){
		console.log("change")
		$('.seemore').addClass("active");
		$('.nomore').hide();
		$('.seemore').show();
		loadgetJobLists("refresh");
	});
	
	$('input[type=radio].filter').on('change', function(){
		console.log("change")
//		var radioName= $(this).attr("name");
//		console.log($(`input[name=${radioName}]:checked`).val());
		$('.seemore').addClass("active");
		$('.nomore').hide();
		$('.seemore').show();
		loadgetJobLists("refresh");
	});
	
	
	
});

/*function getFilterData(){
	var obj={}
	obj['country'] = {'countryID': $('#country').val()};
	obj['city'] = {'cityID': $('#city').val()};
	obj['place'] = $('input[name="place"]:checked').val();
	obj['hours'] = $('input[name="hours"]:checked').val();
	obj['offset'] = jobListOffset;
	console.log(obj);
	return obj;
}*/

function loadgetJobLists(method){
	console.log("in load method")
	$('.seemore').show();
	if (method == "refresh"){
		$('html, body').scrollTop( 0, 200, "swing");
		$('.content-job').find('.content-job-item:not(.newItem):not(.seemore)').fadeOut("normal", function(){
			$(this).remove();
		});
		jobListOffset=0;
		
	}
	if($('.seemore').hasClass("active")){
    	$('.seemore').removeClass("active");
		getJobList(function(jobdatas){
			if (jobdatas.length != 0){
				console.log("jobdatas", jobdatas);
				appendJobList(jobdatas);
				$('.seemore').addClass("active");
				if(jobdatas.length < 10){
					$('.seemore').removeClass("active");
					$('.seemore').fadeOut(2000, function(){
						   $('.nomore').fadeIn(100);
					   });
				}
		   } else if (jobdatas.length == 0){
			   $('.seemore').removeClass("active");
			   $('.seemore').fadeOut(2000, function(){
				   $('.nomore').fadeIn(100);
			   });
			  
		   }
		});
    }
}


function getFilterData(){
	var obj={}
	var jobdata = {}
	jobdata['country'] = {'countryID': $('#country').val()};
	jobdata['city'] = {'cityID': $('#city').val()};
	jobdata['place'] = $('input[name="place"]:checked').val();
	jobdata['hours'] = $('input[name="hours"]:checked').val();
	obj['offset'] = jobListOffset;
	obj['jobData'] = jobdata;
	console.log(obj);
	return obj;
}

function appendJobList(jobdatas){

	$.each(jobdatas, function(i, item){
		jobListOffset += 1;
		$cloneJobItem = $('.content-job-item.newItem').clone();
		$cloneJobItem.attr("data-id", `${jobdatas[i].jobID}`)
		$cloneJobItem.attr("data-userid", `${jobdatas[i].userID}`)
		$cloneJobItem.find('.job-position').append(`${jobdatas[i].position}`);
		$cloneJobItem.find('.job-company').append(`${jobdatas[i].companyName}`);
		$cloneJobItem.find('.job-location').append(`${jobdatas[i].city.cityName}, ${jobdatas[i].country.countryName}`);
		$cloneJobItem.find('.job-place').append(`${jobdatas[i].place}`);
		$cloneJobItem.find('.job-hours').append(`${jobdatas[i].hours}`);
		$cloneJobItem.find('.job-deadline').append(`Due ${jobdatas[i].deadlineSubmission}`);
		$cloneJobItem.find('.job-created').append(`${jobdatas[i].createdDate}`);
		$cloneJobItem.removeClass("newItem");
		$cloneJobItem.insertBefore('.seemore').hide().fadeIn();
		
	})
}

function getNotificationList(){
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/ID0420FF19OWidya/getNotifications.action",
		success : function(datas) {
			console.log(datas)
			appendNotificationData(datas);
		}
	})
}

function appendNotificationData(datas){
	dateArray =[]
	
	$.each(datas, function(i, item){
		if(dateArray.includes(datas[i].createdDate)==false){
			dateArray.push(datas[i].createdDate)
			$cloneNotifItems = $('.notification-items.newItem').clone();		
			$cloneNotifItems.attr("data-date", `${datas[i].createdDate}`);
			$cloneNotifItems.find('.notification-date').text(`${datas[i].createdDate}`);
			$cloneNotifItems.find('.notification-time-message').attr("data-id", `${datas[i].notificationID}`).removeClass("newItem");
			if(datas[i].notificationCategory =="viewprofile"){
				$cloneNotifItems.find('.notification-time-message').attr("href", `http://localhost:8080/ID0420FF19OWidya/${datas[i].link}`);
			}else if(datas[i].notificationCategory =="welcome"){
				$cloneNotifItems.find('.notification-time-message').attr("href", `http://localhost:8080/ID0420FF19OWidya/aboutus`);
			}else if(datas[i].notificationCategory =="follow"){
				$cloneNotifItems.find('.notification-time-message').attr("href", `http://localhost:8080/ID0420FF19OWidya/${datas[i].link}`);
			}else if(datas[i].notificationCategory =="unfollow"){
				$cloneNotifItems.find('.notification-time-message').attr("href", `http://localhost:8080/ID0420FF19OWidya/${datas[i].link}`);
			}else if(datas[i].notificationCategory =="hiring"){
				$cloneNotifItems.find('.notification-time-message').attr("href", `http://localhost:8080/ID0420FF19OWidya/job/apply?jobID=${datas[i].link}`);
			}else if(datas[i].notificationCategory =="newapplicant"){
				$cloneNotifItems.find('.notification-time-message').attr("href", `http://localhost:8080/ID0420FF19OWidya/job/myJobPost`);
			}else if(datas[i].notificationCategory =="viewapplication"){
				$cloneNotifItems.find('.notification-time-message').attr("href", ``);
			}
			$cloneNotifItems.find('.notification-time').text(`${datas[i].createdTime}`);
			$cloneNotifItems.find('.notification-message').html(`${datas[i].notificationMessage}`);
			$cloneNotifItems.removeClass("newItem");
			$cloneNotifItems.appendTo('.sideBar-notification-items>.row').hide().fadeIn();
		}else{
			$cloneNotifItem = $('.notification-time-message.newItem').clone();
			$cloneNotifItem.attr("data-id", `${datas[i].notificationID}`);
			if(datas[i].notificationCategory =="viewprofile"){
				$cloneNotifItem.attr("href", `http://localhost:8080/ID0420FF19OWidya/${datas[i].link}`);
			}else if(datas[i].notificationCategory =="welcome"){
				$cloneNotifItem.attr("href", `http://localhost:8080/ID0420FF19OWidya/aboutus`);
			}else if(datas[i].notificationCategory =="follow"){
				$cloneNotifItem.attr("href", `http://localhost:8080/ID0420FF19OWidya/${datas[i].link}`);
			}else if(datas[i].notificationCategory =="unfollow"){
				$cloneNotifItem.attr("href", `http://localhost:8080/ID0420FF19OWidya/${datas[i].link}`);
			}else if(datas[i].notificationCategory =="hiring"){
				$cloneNotifItem.attr("href", `http://localhost:8080/ID0420FF19OWidya/job/apply?jobID=${datas[i].link}`);
			}else if(datas[i].notificationCategory =="newapplicant"){
				$cloneNotifItem.attr("href", `http://localhost:8080/ID0420FF19OWidya/job/myJobPost`);
			}else if(datas[i].notificationCategory =="viewapplication"){
				$cloneNotifItem.attr("href", ``);
			}
			$cloneNotifItem.find('.notification-time').text(`${datas[i].createdTime}`);
			$cloneNotifItem.find('.notification-message').html(`${datas[i].notificationMessage}`);
			$cloneNotifItem.removeClass("newItem");
			$cloneNotifItem.appendTo(`.notification-items[data-date="${datas[i].createdDate}"]`).hide().fadeIn();
		}
		
		
	})
	console.log(dateArray)
}