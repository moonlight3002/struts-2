/**
 * 
 */

var searchOffset = 0;
$(function(){
	getFeedbackList(getCurrentDataFilter("0"));
	
	$('.filterToggleDiv').on('click', function(){
		$('.sideBarFilter').removeClass('d-none').addClass('d-block filterShow').hide().effect('slide', { direction: "left" }, 200);
	})
	
	$('.filter-Close').on('click', function(){
		$('.sideBarFilter').addClass('d-none').removeClass('d-block filterShow');
	})

	$(document).on('click', '.viewFeedback', function(){
		viewFeedback(getFeedbackData($(this).data("id")));
	})

	
	
	
	$(window).scroll(function() {
	   if($(window).scrollTop() + $(window).height() > ($('.seemore').offset().top-50)) {
	       console.log("bottom!");
	       if($('.seemore').hasClass('active')){
	    	   getFeedbackList(getCurrentDataFilter(searchOffset));	
	       }

	   }
	});
	
	$('.filterBtn').on('click', function(){
		searchOffset = 0;
		$('.nomore').hide();
		$('.seemore').show();
		$('.tableContent').find(`tbody>tr:not(.newItem)`).remove();
		getFeedbackList(getCurrentDataFilter("0"));
	})

	
	$('.setSolvedBtnExecute').on('click', function(){
		var id = $(this).attr("data-id");
		solveFeedback(getFeedbackData($(this).attr("data-id")), function(result){
			if(result>0){
				$('#viewFeedback').modal('hide');
				$('.alert.updateSuccess').fadeIn().delay(2000).fadeOut();
				$('.tableContent').find(`.dataItem[data-id=${id}]>.status`).text('solved');
			}else{
				$('#viewFeedback').modal.hide();
				$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
					$('#viewFeedback').modal.show();
				});
				
			}
		});
	})
	
	$('.deleteFeedbackBtn').on('click', function(){
		var data = getCheckedFeedback();
		if (Object.keys(data.feeds).length !== 0){
			
			deleteFeedback(data, function(result){
				if (result>0){
					$.each(data.feeds, function(i, item){
						$('.tableContent').find(`.dataItem[data-id=${data.feeds[i].feedbackID}]`).remove();
					})		
				}else{
					$('.alert.error').fadeIn().delay(2000).fadeOut();
				}
			})
			
		}else{
			$('.alert.noneSelected').fadeIn().delay(2000).fadeOut();
		}
		
	})
	

})


function getFeedbackData(id){
	var obj={}
	var feedbackData={}
	feedbackData['feedbackID'] = id;	
	obj['feedback'] = feedbackData
	console.log(obj)
	return obj
}

function getCheckedFeedback(){
	var obj={}
	var feedbackDatas=[]
	idArray=[]
	$('input[name="feedbackSelect"]:checked').each(function(){
		feed = {}
		feed['feedbackID'] = $(this).data("id");
		idArray.push($(this).data("id"));
		feedbackDatas.push(feed);
	})
	
	obj['feeds'] = feedbackDatas
	console.log(obj)
	return obj
}


function getCurrentDataFilter(offset){
	obj={}
	obj['fromID'] = ($('.filter-Content').find('input[name="fromID"]').val() ? $('.filter-Content').find('input[name="fromID"]').val() : "0");
	obj['toID'] = ($('.filter-Content').find('input[name="toID"]').val() ? $('.filter-Content').find('input[name="toID"]').val() : "0");
	obj['fromUserID'] = ($('.filter-Content').find('input[name="fromUserID"]').val() ? $('.filter-Content').find('input[name="fromUserID"]').val() : "0");
	obj['toUserID'] = ($('.filter-Content').find('input[name="toUserID"]').val() ? $('.filter-Content').find('input[name="toUserID"]').val() : "0");
	obj['fromDate'] = $('.filter-Content').find('input[name="fromDate"]').val();
	obj['toDate'] = $('.filter-Content').find('input[name="toDate"]').val();
	obj['category'] = $('.filter-Content').find('select[name="category"]').val();
	obj['read'] = $('.filter-Content').find('select[name="read"]').val();
	obj['solved'] = $('.filter-Content').find('select[name="solved"]').val();
	obj['offset'] = offset
	console.log(obj)
	
	return obj;
	
}

function changeData(data){
	
}

function appendDatatoTable(data){
	$.each(data, function(i, item){
		searchOffset +=1;
		$cloneElem = $('.tableContent').find('tbody > tr.newItem').clone();
		$cloneElem.attr("data-id", `${data[i].feedbackID}`);
		$cloneElem.find('#feedbackSelect').attr("data-id", `${data[i].feedbackID}`);
		$cloneElem.find('td.feedbackID').text(`${data[i].feedbackID}`);
		$cloneElem.find('td.category').text(`${data[i].feedbackCategory}`);
		$cloneElem.find('td.sentDate').text(`${data[i].createdDate}`);
		var status="";
		if(data[i].solved === false){
			status= "unsolved"
		}else{
			status = "solved"
		}
		if(data[i].read === false){
			$cloneElem.addClass('newdataItem');
		}
		$cloneElem.find('.status').text(`${status}`);
		$cloneElem.find('.viewFeedback').attr("data-id", `${data[i].feedbackID}`);
		$cloneElem.find('.userID').attr("href", `http://localhost:8080/ID0420FF19OWidya/${data[i].userName}`).text(`${data[i].userID}`);		
		$cloneElem.removeClass('newItem');
		$cloneElem.appendTo('.tableContent > tbody');
		
	})
}

function appendFeedbackModal(data){
	$('#viewFeedback').find('.sendDate>b').text(`${data.createdDate}`);
	$('#viewFeedback').find('.category>b').text(`${data.feedbackCategory}`);
	$('#viewFeedback').find('.message').text(`${data.feedbackMessage}`);
	if(data.solved===false){
		$('#viewFeedback').find('.setSolvedBtnExecute').show().attr("data-id",`${data.feedbackID}`);
	}else{
		$('#viewFeedback').find('.setSolvedBtnExecute').hide();
	}
	if(data.read===false){
		$('.tableContent').find(`.dataItem[data-id=${data.feedbackID}]`).removeClass('newdataItem');
	}
	
	
}
function getFeedbackList(data){
	$('.seemore').removeClass('active');
	console.log(data);
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/ID0420FF19OWidya/admin/feedback/getFeedbackList",
		data: JSON.stringify(data),
		contentType:'application/json',
		success: function(data){
			console.log(data);
			if(data.length >0){
				if(data.length>=15){
					$('.seemore').addClass('active');
				}else{
					$('.seemore').fadeOut();
					$('.nomore').show();
				}
				appendDatatoTable(data);
				
			}else{
				$('.seemore').fadeOut();
				$('.nomore').show();
			}
		},
		error: function(data){
			console.log("error")
			console.log(data)
		}
	})
}

function viewFeedback(data){
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/ID0420FF19OWidya/admin/feedback/viewFeedback.action",
		data: JSON.stringify(data),
		contentType:'application/json',
		success: function(result){
			console.log(result)
			if(result.userID!="0"){
				appendFeedbackModal(result);
			}else{
				$('#viewFeedback').modal('hide');
				$('.alert.error').fadeIn().delay(2000).fadeOut();
			}
			
		},
		error: function(result){
			console.log("error")
			console.log(result)
			$('#viewFeedback').modal('hide');
			$('.alert.error').fadeIn().delay(2000).fadeOut();
		}
	})
}

function solveFeedback(data, callback){
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/ID0420FF19OWidya/admin/feedback/solveFeedback.action",
		data: JSON.stringify(data),
		contentType:'application/json',
		success: function(result){
			console.log(result);
			callback(result);
		},
		error: function(result){
			console.log("error")
			console.log(result)
		}
	})
}

function deleteFeedback(data, callback){
	$.ajax({
		type: "POST",
		url: "feedback/deleteFeedback.action",
		data: JSON.stringify(data),
		contentType:'application/json',
		success: function(result){
			console.log(result);
			callback(result);
		},
		error: function(result){
			console.log("error")
			console.log(result)
		}
	})
}