/**
 * 
 */
$(function(){
	$('#customFile').on('change',function(e){
        //get the file name
		var fileName = e.target.files[0].name;
		        //replace the "Choose a file" label
		$(this).next('.custom-file-label').html(fileName);
		})
		
		$('#submitApplicationForm').on('submit', function(e){
			e.preventDefault();
			if(validateHiringForm($('form'))==true){
				$('form')[0].submit();
				console.log("submit1")
			};
		})
		
		$(document).on('keyup blur change', '.form-input-text, .form-input-textarea.form-control, .form-input-select', function(){
		validate($(this), patterns[$(this).attr("data-name")]);
	})
})

function validate($elem, regex) {
	var isValid = false;
//	console.log($elem.val())
	if (regex.test($elem.val())) {
//		console.log("validated");
		if ($elem.hasClass('is-invalid')) {
			$elem.removeClass('is-invalid');
		}
		isValid = true;
	}else {
//		console.log("invalid");
		$elem.addClass('is-invalid');
		if($elem.val()==""){
			$elem.siblings('.invalid-feedback').text(`${errorMessage['global']}`);
		}else{
			$elem.siblings('.invalid-feedback').text(`${errorMessage[$elem.attr("data-name")]}`);
		}
		isValid = false;
	}
	return isValid;
}


function validateHiringForm($formElem){
	var isValid = true;
	$formElem.find('.form-input-text').each(function(){
		isValidInput = validate($(this), patterns[$(this).attr("data-name")]);
		if(isValidInput == false){
			$(this).focus();
			isValid = false;
		}
	})
	$formElem.find('.form-input-select').each(function(){
		isValidInput = validate($(this), patterns[$(this).attr("data-name")]);
		if(isValidInput == false){
			$(this).focus();
			isValid = false;
		}
	})
	$formElem.find('.form-input-textarea.form-control').each(function(){
		isValidInput = validate($(this), patterns[$(this).attr("data-name")]);
		if(isValidInput == false){
			$(this).focus();
			isValid = false;
		}
	})
	return isValid;
}



//function uploadFormApplication(){
//			var formdata = new FormData();
//		    var file = $(`#customFile`)[0].files[0];
//		    console.log("file:", file);
//		    formdata.append('applicationAttachment', file);
//		   
//		    var jobanss = []
//		    $('textarea').each(function(){
//		    	var jobans = {
//			    		jobQuestionID: $(this).attr('data-id'),
//			    		answer: $(this).val()
//			    }
//		    	jobanss.push(jobans);
//		    })
//		    console.log(jobanss)
//		    var jobAppData ={
//		    		"jobPostID": $('input[name="jobPostID"]').val(),
//		    		"jobApplicationUserID": 7
//		    }
////		    		jobAnswers: jobanss
//		    
//		    console.log(jobAppData)
//		    formdata.append('jobApplicationData', [jobAppData]);
//		    formdata.append('jobID', 138);
//		    console.log("formdata",formdata);
//		    $.ajax({
//		        type: "POST",
//		        enctype: 'multipart/form-data',
//		        url: `http://localhost:8080/ID0420FF19OWidya/job/apply/submit.action`,
//		        data : formdata,
//		        contentType: false,
//		        processData: false,
//		        success: function(data) {
//		            console.log("SUCCESS");
//		           
//		            
//		           
//		        },
//		        error: function() {
//		        	console.log("error")
//		        }
//		    });
//}