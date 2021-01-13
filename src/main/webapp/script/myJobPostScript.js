/**
 * 
 */

$(function(){
	$(document).on('click', '#viewApplicantLists', function(){
		if($('.content-left-view-submission').css('display')=='block'){
			$('.viewCloseviewBtn').trigger('click');
		}
		$('.content-right').removeClass('col-md-12').addClass('col-md-6');
		$('.content-left').addClass('col-md-6');
		
		$('.content-left-applicant-items').find('.applicant-item:not(.newItem)').remove();
		$('.content-left').removeClass('d-none').hide().show("slide", { direction: "right" }, 200);
		var obj={}
		var jobdata={}
		jobdata['jobID'] = $(this).attr("data-id");
		obj['job'] = jobdata
		console.log(obj);
		viewApplicantLists(obj);
	})
	
	$(document).on('click', '.applicant-item', function(){
		var obj={}
		var jobApplicationdata={}
		jobApplicationdata['jobApplicationID'] = $(this).attr("data-id");
		obj['jobApplication'] = jobApplicationdata;
		viewApplication(obj);
		$('.content-left-view-submission').show("slide", { direction: "down" }, 200);
		
	})
	
	$('.viewCloseviewBtn').on('click', function(){
		$('.content-left-view-submission').hide("slide", { direction: "down" }, 200);
	})
	
	$('.contentleftCloseBtn').on('click', function(){
		$('.content-left').hide("slide", { direction: "right" }, 200);
	})
})


function appendApplicantList(datas){
	$.each(datas, function(i, item){
		$cloneElem = $('.content-left-applicant-items').find('.applicant-item.newItem').clone();
		if(datas[i].read==true){
			console.log("false");
			$cloneElem.find('.newApplicant-status').hide();
		}
		$cloneElem.attr("data-id", `${datas[i].jobApplicationID}`);
		$cloneElem.find('img').attr('src', `/ID0420FF19OWidya/getFile?userID=${datas[i].jobApplicationUser.userID}&profilePic=${datas[i].jobApplicationUser.profilePic}`);
		var defaultprofilePic = "defaultpp_"+datas[i].jobApplicationUser.firstName.substring(0,1).toUpperCase();
		$cloneElem.find('img').attr("onerror",`this.onerror=null;this.src="\\/ID0420FF19OWidya\\/assets\\/${defaultprofilePic}.jpg"`);
		$cloneElem.find('.applicant-name>p').text(`${datas[i].jobApplicationUser.firstName} ${datas[i].jobApplicationUser.lastName}`);
		$cloneElem.find('.applicant-submit-date>p').append(`${datas[i].jobApplyDate}`)
		$cloneElem.removeClass('newItem');
		$cloneElem.appendTo('.content-left-applicant-items')
		
	})
}

function appendApplicationData(data){
	$('.content-left-view-submission').find('.view-QA-item:not(.newItem)').remove();
	var defaultprofilePic = "defaultpp_"+data.jobApplicationUser.firstName.substring(0,1).toUpperCase();
	$('.content-left-view-submission').find('img').attr("onerror",`this.onerror=null;this.src="\\/ID0420FF19OWidya\\/assets\\/${defaultprofilePic}.jpg"`);
	$('.content-left-view-submission').find('.viewImageDiv>img').attr('src', `/ID0420FF19OWidya/getFile?userID=${data.jobApplicationUser.userID}&profilePic=${data.jobApplicationUser.profilePic}`)
	$('.content-left-view-submission').find('.fullName').text(`${data.jobApplicationUser.firstName} ${data.jobApplicationUser.lastName}`)
	$('.content-left-view-submission').find('.submissionDate>span').text(`${data.jobApplyDate}`)
	$('.content-left-view-submission').find('.position').text(`${data.jobPost.position}`)
	$('.content-left-view-submission').find('.company').text(`${data.jobPost.companyName}`)
	$('.content-left-view-submission').find('.phone').text(`+${data.phoneCode} ${data.phoneNo}`)
	$('.content-left-view-submission').find('.about').text(`${data.aboutApplicant}`)
	if (data.attachment !== null){
		$('.content-left-view-submission').find('.attachmentBtn').attr('href', `/ID0420FF19OWidya/job/myJobPost/downloadAttachment?jobApplication.attachment=${data.attachment}&jobApplication.jobApplicationUser.userID=${data.jobApplicationUser.userID}`)
	}else{
		$('.content-left-view-submission').find('.attachmentBtn').remove();
	}

	var jobAns = data.jobAnswers;
	if (jobAns !==null){
		$.each(jobAns, function(i,item){
			$cloneItem = $('.view-QA-item.newItem').clone();
			$cloneItem.find('.question').text(`${jobAns[i].question}`);
			$cloneItem.find('.answer').text(`${jobAns[i].answer}`);
			$cloneItem.removeClass('newItem');
			$cloneItem.appendTo('.view-QA');
		})
	}
	
	
	
}

function viewApplicantLists(obj){
	$('.nomore').hide();
	$.ajax({
		type : "POST",
		data : JSON.stringify(obj),
		contentType: 'application/json',
		url : "/ID0420FF19OWidya/job/myJobPost/viewApplicantLists.action",
		success : function(datas) {
			console.log(datas)
			if(datas.length >0){
				appendApplicantList(datas);
			}else{
				$('.nomore').show();
			}
		}
	})
}

function viewApplication(obj){
	$.ajax({
		type : "POST",
		data : JSON.stringify(obj),
		contentType: 'application/json',
		url : "/ID0420FF19OWidya/job/myJobPost/viewApplication.action",
		success : function(data) {
			console.log(data)
			if(data.jobApplicationID !== ""){
				$(`.applicant-item[data-id='${data.jobApplicationID}']`).find('.newApplicant-status').fadeOut();				
				appendApplicationData(data);
			}
		}
	})
}