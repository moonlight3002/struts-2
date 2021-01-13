/**
 * 
 */
var jobListOffset=0;
$(function(){
	
	$(document).ready(function(){
		var datas = getCountry(function(datas){
//			console.log("datas", datas);
			$.each(datas, function(i, item){
//				console.log(datas[i].countryName)
				$('#country').append(`<option value="${datas[i].countryID}">${datas[i].countryName}</option>`)
			})
		});
		$('.nomore').hide();
		$('.seemore').show();
		loadJob();
	});
	
	$('#country').on('change', function(){
		jobListOffset = 0;
		$('#city').find('option:not(:first)').remove();
		var datas = getCity($(this).val(),function(datas){
			$.each(datas, function(i, item){
//				console.log(datas[i].cityName)
				$('#city').append(`<option value="${datas[i].cityID}">${datas[i].cityName}</option>`)
			})
		});
		$('.content-job').find('.content-job-item:not(.newItem)').fadeOut().remove();
		loadJob();


	});
	
	$('#city').on('change', function(){
		jobListOffset = 0;
		$('.content-job').find('.content-job-item:not(.newItem)').fadeOut().remove();
		loadJob();
	});
	
	
	
	$('#position-company-filter').on("keyup",function(){
		jobListOffset = 0;
		$('.content-job').find('.content-job-item:not(.newItem)').fadeOut().remove();
		loadJob();
	})
	
	$('.skill-filter-item-input').on('change', function(){
		console.log("change")
		jobListOffset = 0;
		$('.content-job').find('.content-job-item:not(.newItem)').fadeOut().remove();
		loadJob();
	})
	
	$('input[type=radio]').on('change', function(){
		jobListOffset = 0;
		$('.content-job').find('.content-job-item:not(.newItem)').fadeOut().remove();
		loadJob();
	});
	
	$(window).scroll(function() {
		   if($(window).scrollTop() + $(window).height() > ($('.seemore').offset().top)) {
		       console.log("bottom!");
		       if($('.seemore').hasClass("active")){
		    	   loadJob();	
		       }
		   }
	});
	
	$('.filterBtn').on('click', function(){
		
		$('.sideBar-left').removeClass('d-sm-none d-none');
		$('.sideBar-left').addClass('d-sm-block col-sm-5 col-6').hide().effect('slide', { direction: "left" }, 200);
	})
	
	$('.closeFilterBtn').on('click', function(){
		$('.sideBar-left').removeClass('d-sm-block col-sm-5 col-6');
		$('.sideBar-left').addClass('d-sm-none d-none');
	})
})

function loadJob(){
	$('.seemore').removeClass("active");
	getJobList(function(jobdatas){
		console.log(jobdatas)
		$.each(jobdatas, function(i, item){
			jobListOffset += 1;
			$cloneJobItem = $('.content-job-item.newItem').clone();
			console.log($cloneJobItem)
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
		$('.seemore').addClass("active");
	})
}


function getFilterData(){
	
	var obj={}
	var jobdata = {}
	jobdata['country'] = {'countryID': $('#country').val()};
	jobdata['city'] = {'cityID': $('#city').val()};
	jobdata['place'] = $('input[name="place"]:checked').val();
	jobdata['hours'] = $('input[name="hours"]:checked').val();
	jobdata['companyName'] = $('#position-company-filter').val();
	jobdata['position'] = $('#position-company-filter').val();

	
	var jobSkills = []
	var check = 0;
	$('.skill-filter-item-input').each(function(){
		if($(this).prop("checked")==false){
			var jobSkill = {};
			jobSkill['technologyID']= $(this).data("id");
			jobSkills.push(jobSkill);
		}else{
			check+=1;
		}
		
	})
	if (check>0){
		jobdata['jobSkills'] = jobSkills;
	}
	obj['offset'] = jobListOffset;
	obj['jobData'] = jobdata;
	console.log(obj);
	return obj;
}