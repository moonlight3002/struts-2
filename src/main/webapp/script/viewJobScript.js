/**
 * 
 */

$(function(){
	$(document).on('click', '.content-job-item:not(.newItem)', function(){
		viewJobDetails($(this).data("id"), $(this).data("userid"))
	})
	
	
	
})

function appendJobDetails(data){
	$('.jobDetailsDiv').find('p#position').text(`${data.position}`);
	$('.jobDetailsDiv').find('p#companyName').text(`${data.companyName}`);
	$('.jobDetailsDiv').find('p#location').text(`${data.city.cityName}, ${data.country.countryName}`);
	$('.jobDetailsDiv').find('p#postedDate').text(`${data.createdDate}`);
	$('.jobDetailsDiv').find('p#place').text(`${data.place}`);
	$('.jobDetailsDiv').find('p#hours').text(`${data.hours}`);
	$('.jobDetailsDiv').find('p#deadline').text(`${data.deadlineSubmission}`);
	$('.jobDetailsDiv').find('p#companyInfo').text(`${data.companyInfo}`);
	$('.jobDetailsDiv').find('p#jobDesc').text(`${data.jobDescription}`);
	$('.jobDetailsDiv').find('p#jobRequirement').text(`${data.jobRequirements}`);
	if(data.requiredSkill!== null){
		$('.jobDetailsDiv').find('p#jobSkill').text(`${data.requiredSkill}`);
	}else{
		$('.jobDetailsDiv').find('p#jobSkill').text(``);
	}
	if(data.notes!==null){
		$('.jobDetailsDiv').find('p#jobNotes').text(`${data.notes}`);
	}else{
		$('.jobDetailsDiv').find('p#jobNotes').text(``);
	}

	

}



function viewJobDetails(jobID, userID){
	$.ajax({
		type : "POST",
		url : "job/view",
		data: JSON.stringify({"job": {"userID":userID, "jobID": jobID}}),
		contentType: 'application/json',
		success : function(data) {
			console.log(data)
			if(data){
				console.log("jobdetails:",data);
				if(data.deadlineSubmission!=='Closed'){
					if(data.hasApply == false){
						$('#viewJobDetails').find('.hasApply').css("display", "none");
						$('#viewJobDetails').find('.applyBtn').css("display", "block");
						$('#viewJobDetails').find('.applyBtn').attr('data-id', `${data.jobID}`);
						$('#viewJobDetails').find('.applyBtn').attr('href', `http://localhost:8080/ID0420FF19OWidya/job/apply?jobID=${data.jobID}`);
					}else{
						$('#viewJobDetails').find('.hasApply').css("display", "block");
						$('#viewJobDetails').find('.applyBtn').css("display", "none");
					}

				}else{
					$('#viewJobDetails').find('.hasApply').css("display", "none");
					$('#viewJobDetails').find('.applyBtn').css("display", "none");
				}

				appendJobDetails(data);
			}else{
				$(`#viewJobDetails`).modal('hide');
				$('.alert.error').fadeIn().delay(2000).fadeOut();
			}
			
			
		}
	})
}