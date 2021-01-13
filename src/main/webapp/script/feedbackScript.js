/**
 * 
 */

$(function(){
	$('#sendFeedback').click(function(){
		sendFeedback();
	})
})

function getFeedbackData(){
	var obj={}
	var feedbackData={}
	feedbackData['feedbackCategory'] = $('input[name="feedbackCategory"]:checked').val();
	feedbackData['feedbackMessage'] =$('#feedbackMessage').val();
	obj['feedback'] = feedbackData;
	console.log(obj);
	return obj;
}


function sendFeedback(){
	$.ajax({
		type : "POST",
		data : JSON.stringify(getFeedbackData()),
		contentType : 'application/json',
		url : "http://localhost:8080/ID0420FF19OWidya/sendFeedback.action",
		success : function(data) {
			console.log(data);
			if(data >0){
				$('#feedbackForm').modal('hide');
				$('.alert.thankyou').fadeIn().delay(2000).fadeOut();
				$('#feedbackMessage').val('');
			}else{
				$('#feedbackForm').modal('hide');
				$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
					$('#feedbackForm').modal('show');
				});
			}

		},
		error: function(data){
			$('#feedbackForm').modal('hide');
			$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
				$('#feedbackForm').modal('show');
			});
		}
	})
}