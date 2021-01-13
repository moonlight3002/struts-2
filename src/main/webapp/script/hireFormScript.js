/**
 * 
 */

$(function(){
	var questionIndex = 1;
	
	$(document).ready(function(){
		var datas = getCountry(function(datas){
//			console.log("datas", datas);
			$.each(datas, function(i, item){
//				console.log(datas[i].countryName)
				$('#country').append(`<option value="${datas[i].countryID}">${datas[i].countryName}</option>`)
			})
		});
	});
	
	
	$('#country').on('change', function(){
		$('#city').find('option:not(:first)').remove();
		var datas = getCity($(this).val(),function(datas){
			$.each(datas, function(i, item){
//				console.log(datas[i].cityName)
				$('#city').append(`<option value="${datas[i].cityID}">${datas[i].cityName}</option>`)
			})
		});
	});
	
	$('.addQuestionBtn').click(function(){
		$cloneItem = $('.questionDiv-item.newItem').clone();
		$cloneItem.attr("data-id", `${questionIndex}`);
		$cloneItem.find('.deleteQuestionBtn').attr("data-id", `${questionIndex}`);
		$cloneItem.removeClass('newItem');
		
		$cloneItem.appendTo('.questionDiv-items');
		questionIndex+=1;
		
	})
	
	$(document).on('click','.deleteQuestionBtn',function(){
		var tempID = $(this).attr("data-id");
		console.log(tempID);
		$('.questionDiv-items').find(`.questionDiv-item[data-id=${tempID}]`).remove();
		
	})
	
	$('#submitForm').on('submit',(function(e){
		e.preventDefault();
		setQuestionIndex(function(){
			console.log("submit")
			if(validateHiringForm($('form'))==true){
				$('form')[0].submit();
				console.log("submit1")
			};

		})
	}))
	
	$('#skill').find('option[value="-1"]').attr('disabled', 'disabled');
	
	
	$(document).on('keyup blur change', '.form-input-text, .form-input-textarea.form-control, .form-input-select', function(){
		validate($(this), patterns[$(this).attr("data-name")]);
	})
})

function setQuestionIndex(callback){
	var countquestion=0;
		$('.questionDiv-item').each(function(){
			$(this).find('textarea').attr("name", `jobQuestions[${countquestion}].question`);
			countquestion+=1;
		})
		callback()
}

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
			isValid = false;
		}
	})
	$formElem.find('.form-input-select').each(function(){
		isValidInput = validate($(this), patterns[$(this).attr("data-name")]);
		if(isValidInput == false){
			isValid = false;
		}
	})
	$formElem.find('.form-input-textarea.form-control').each(function(){
		isValidInput = validate($(this), patterns[$(this).attr("data-name")]);
		if(isValidInput == false){
			isValid = false;
		}
	})
	return isValid;
}



	