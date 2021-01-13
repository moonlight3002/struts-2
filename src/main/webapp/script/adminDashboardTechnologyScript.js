/**
 * 
 */

$(function(){
	adminGetTechnologyList();

	$('.addTechnologyBtnExecute').on('click', function(){
		var data = getModalData();
		console.log(data);
		addTechnology(data, function(result){
			if(result>0){
				$('#addTechnology').modal('hide');
				$cloneElem = $('.tableContent').find('tbody > tr.newItem').clone();
				$cloneElem.attr("data-id", `${result}`);
				$cloneElem.find('#technologySelect').attr("data-id", `${result}`);
				$cloneElem.find('td.technologyID').text(`${result}`);
				$cloneElem.find('input[name="technologyName"]').val(`${data.tech.technologyName}`).attr("data-id", `${result}`).attr("data-default", `${data.tech.technologyName}`);;	
				$cloneElem.removeClass('newItem');
				$cloneElem.appendTo('.tableContent > tbody');
			}else{
				$('#addTechnology').modal('hide');
				$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
					$('#addTechnology').modal('show');
				});
			}
		});
	})
	
	$(document).on('blur', 'td.technologyName>input[name="technologyName"]', function(){
		$elem = $(this);
		var val = $(this).val();
		var id = $(this).attr("data-id");
		if($(this).val()!== $(this).attr("data-default")){
			updateTechnology(getUpdateData($elem), function(result){
				if(result>0){
					$('.tableContent').find(`td.technologyName>input[name="technologyName"][data-id=${id}]`).attr("data-default", `${val}`)
					$('.alert.updateSuccess').fadeIn().delay(2000).fadeOut();
				}else{
					$('.alert.error').fadeIn().delay(2000).fadeOut();
				}
			});
		}

	})

	
	$('.deleteTechnologyBtn').on('click', function(){
		var data = getCheckedTechnology();
		if (Object.keys(data.technologies).length != 0){
			
			deleteTechnology(data, function(result){
				if (result>0){
					$.each(data.technologies, function(i, item){
						$('.tableContent').find(`.dataItem[data-id=${data.technologies[i].technologyID}]`).remove();
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

function getUpdateData($elem){
	var obj={}
	var techData={}
	techData['technologyName'] = $elem.val();	
	techData['technologyID'] = $elem.attr("data-id");
	obj['tech'] = techData
	console.log(obj)
	return obj
}

function getModalData(){
	var obj={}
	var techData={}
	techData['technologyName'] = $('.adminModal').find('input[name="technologyName"]').val();	
	obj['tech'] = techData
	console.log(obj)
	return obj
}


function getTechnologyData(id){
	var obj={}
	var techData={}
	techData['technologyID'] = id;	
	obj['tech'] = techData
	console.log(obj)
	return obj
}

function getCheckedTechnology(){
	var obj={}
	var techDatas=[]
	idArray=[]
	$(document).find('input[name="technologySelect"]:checked').each(function(){
		feed = {}
		feed['technologyID'] = $(this).data("id");
		idArray.push($(this).data("id"));
		techDatas.push(feed);
	})
	
	obj['technologies'] = techDatas
	console.log(obj)
	return obj
}


function changeData(data){
	
}

function appendDatatoTable(data){
	console.log(data)
	$.each(data, function(i, item){
		$cloneElem = $('.tableContent').find('tbody > tr.newItem').clone();
		$cloneElem.attr("data-id", `${data[i].technologyID}`);
		$cloneElem.find('#technologySelect').attr("data-id", `${data[i].technologyID}`);
		$cloneElem.find('td.technologyID').text(`${data[i].technologyID}`);
		$cloneElem.find('input[name="technologyName"]').val(`${data[i].technologyName}`).attr("data-id", `${data[i].technologyID}`).attr("data-default", `${data[i].technologyName}`);	
		$cloneElem.removeClass('newItem');
		$cloneElem.appendTo('.tableContent > tbody');
		
	})
}


function adminGetTechnologyList(){
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/ID0420FF19OWidya/admin/technology/adminGetTechnologyList",
		success: function(data){
			console.log(data);
			if(data.length >0){
				appendDatatoTable(data);
				
			}
		},
		error: function(data){
			console.log("error")
			console.log(data)
		}
	})
}


function updateTechnology(data, callback){
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/ID0420FF19OWidya/admin/technology/adminUpdateTechnology",
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

function addTechnology(data, callback){
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/ID0420FF19OWidya/admin/technology/adminAddTechnology",
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

function deleteTechnology(data, callback){
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/ID0420FF19OWidya/admin/technology/adminDeleteTechnology",
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

