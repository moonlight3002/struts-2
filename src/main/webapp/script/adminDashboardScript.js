/**
 * 
 */

var searchOffset = 0;
var idArray=[];
$(function(){
	getUsersList(getCurrentDataFilter("0"));
	
	$('.filterToggleDiv').on('click', function(){
		$('.sideBarFilter').removeClass('d-none').addClass('d-block filterShow').hide().effect('slide', { direction: "left" }, 200);
	})
	
	$('.filter-Close').on('click', function(){
		$('.sideBarFilter').addClass('d-none').removeClass('d-block filterShow');
	})
	
	$('.sendEmailBtnExecute').on('click', function(){
		var data = getEmailData();
		if (Object.keys(data).length !== 0){
			sendBulkEmail(getEmailData(), function(result){
				if(result>0){
					$('.modal#sendEmail').modal('hide');
					$('.alert.emailSuccess').fadeIn().delay(2000).fadeOut();

				}else{
					$('.modal#sendEmail').modal('hide');
					$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
						$('.modal#sendEmail').modal('show');
					});
				}
			});
		}else{
			$('.modal#sendEmail').modal('hide');
			$('.alert.noneSelected').fadeIn().delay(2000).fadeOut();
		}
		
		
	})
	
	$('.deactivateUserBtn').on('click', function(){
		var userArray = getCheckedUser();
		if (userArray.users.length <1){
			$('#deleteUser').modal("hide");
			$('.alert.noneSelected').fadeIn().delay(2000).fadeOut();
			
		}
		
		$('.ids>b').text(idArray);
		$('.deleteUserBtnExecute').on('click', function(){	
				deactivateUser(userArray, function(result){
					if(result>0){
						$('.modal#deleteUser').modal('hide');
						$('.alert.deleteSuccess').fadeIn().delay(2000).fadeOut();
						$.each(userArray.users, function(i,item){
							$('.tableContent').find(`tr[data-id="${userArray.users[i].userID}"]`).remove();
						})

					}else{
						$('.modal#deleteUser').modal('hide');
						$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
							$('.modal#deleteUser').modal('show');});
					}
				});
			
		})
	})
	
	
	
	$(window).scroll(function() {
	   if($(window).scrollTop() + $(window).height() > ($('.seemore').offset().top-50)) {
	       console.log("bottom!");
	       if($('.seemore').hasClass('active')){
		       getUsersList(getCurrentDataFilter(searchOffset));	
	       }

	   }
	});
	
	$('.filterBtn').on('click', function(){
		searchOffset = 0;
		$('.tableContent').find(`tbody>tr:not(.newItem)`).remove();
		getUsersList(getCurrentDataFilter("0"));
	})
	
	$(document).on('keyup', '.dataItem>td>input[name="userName"], .dataItem>td>input[name="email"], input[name="password"]', function(){
		$elem = $(this);
		validate($elem, patterns[`${$elem.attr("name")}`]);
	})
	
	$(document).on('blur', '.dataItem>td>input[name="userName"], .dataItem>td>input[name="email"]', function(){
		$elem = $(this);
		if($(this).val().toLowerCase() != $(this).attr("data-default").toLowerCase()){
			var isValid = validate($elem, patterns[`${$elem.attr("name")}`]);
			if(isValid){
				updateUserColumn(getDataColumn($(this)), function(result){
					if(result>0){
						$('.alert.updateSuccess').fadeIn().delay(2000).fadeOut();
						$elem.attr("data-default", $elem.val());
						
					}else{
						$('.alert.error').fadeIn().delay(2000).fadeOut();
						$elem.val(`${$elem.attr('data-default')}`);
					}
				});
			}
			
		}
		
	})
	
	$(document).on('click', '.changePasswordBtn', function(){
		$elem = $(this)
		$('.modal#changePassword').find('input[name="password"]').attr("data-id", `${$elem.attr("data-id")}`);
	})
	
	$(document).on('click', '.changePasswordBtnExecute', function(){
		$elem = $('.modal#changePassword').find('input[name="password"]');
		var isValid = validate($elem, patterns[`${$elem.attr("name")}`]);
		if(isValid){
			updateUserColumn(getDataColumn($elem), function(result){
				if(result>0){
					$('.alert.updateSuccess').fadeIn().delay(2000).fadeOut();
					$('.modal#changePassword').modal('hide');
				}else{
					$('.modal#changePassword').modal('hide');
					$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
						$('.modal#changePassword').modal('show');
					});
				}
			});
			
		}
		
	})

})

function getEmailData(){
	var obj={}
	var countemail=0;
	obj['toUser'] = $('input[name="toUser"]:checked').val();
	obj['subject'] = $('input[name="subject"]').val();
	obj['message'] = $('textarea[name="message"]').val();
	if($('input[name="toUser"]:checked').val()=="selected"){
		var emails = "";
		$('input[name="userSelect"]:checked').each(function(){
			if (countemail!==0){
				emails += ",";
			}
			var id = $(this).data("id");
			emails += $(`input[name="email"][data-id=${$(this).data("id")}]`).val();
			countemail +=1
		})
		if (emails !== ""){
			obj['recipientEmail'] = emails
		}else{
			obj={}
		}

	}
	
	console.log(obj);
	return obj
}

function getCheckedUser(){
	var obj={}
	var userDatas=[]
	idArray=[]
	$('input[name="userSelect"]:checked').each(function(){
		userData = {}
		userData['userID'] = $(this).data("id");
		userData['email'] = $(`input[name="email"][data-id=${$(this).data("id")}]`).val();
		idArray.push($(this).data("id"));
		userDatas.push(userData);
	})
	
	obj['users'] = userDatas
	console.log(obj)
	return obj
}


function getDataColumn($elem){
	var obj={}
	var userdata = {}
	userdata['userID'] = $elem.attr('data-id');
	obj['column'] = $elem.attr('data-column');
	obj['datatoUpdate'] = $elem.val();
	obj['user'] = userdata
	console.log(obj)
	return obj
}


function getCurrentDataFilter(offset){
	obj={}
	obj['fromID'] = ($('.filter-Content').find('input[name="fromID"]').val() ? $('.filter-Content').find('input[name="fromID"]').val() : "0");
	obj['toID'] = ($('.filter-Content').find('input[name="toID"]').val() ? $('.filter-Content').find('input[name="toID"]').val() : "0");
	obj['fromDateCreated'] = $('.filter-Content').find('input[name="fromDate"]').val();
	obj['toDateCreated'] = $('.filter-Content').find('input[name="toDate"]').val();
	obj['firstName'] = $('.filter-Content').find('input[name="firstName"]').val();
	obj['lastName'] = $('.filter-Content').find('input[name="lastName"]').val();
	obj['email'] = $('.filter-Content').find('input[name="email"]').val();
	obj['deleted'] = $('.filter-Content').find('select[name="deleted"]').val();
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
		$cloneElem.attr("data-id", `${data[i].userID}`);
		$cloneElem.find('input[name="userSelect"]').attr("data-id", `${data[i].userID}`);
		$cloneElem.find('.userID').attr("href", `http://localhost:8080/ID0420FF19OWidya/${data[i].userName}`).text(`${data[i].userID}`);
		$cloneElem.find('input[name="userName"]').val(`${data[i].userName}`);
		$cloneElem.find('input[name="userName"]').attr("data-id", `${data[i].userID}`).attr("data-default", `${data[i].userName}`);
		$cloneElem.find('input[name="email"]').val(`${data[i].email}`);
		$cloneElem.find('input[name="email"]').attr("data-id", `${data[i].userID}`).attr("data-default", `${data[i].email}`);
		$cloneElem.find('input[name="createdDate"]').val(`${data[i].datecreated}`);
		$cloneElem.find('input[name="lastLogin"]').val(`${data[i].lastLogin}`);
		$cloneElem.find('.changePasswordBtn').attr("data-id", `${data[i].userID}`);
		var status="";
		if(data[i].deleted === false){
			status= "Active"
		}else{
			status = "Deactivated"
		}
		$cloneElem.find('.status').text(`${status}`);
		$cloneElem.removeClass('newItem');
		$cloneElem.appendTo('.tableContent > tbody');
		
	})
}
function getUsersList(data){
	$('.seemore').removeClass('active');
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/ID0420FF19OWidya/admin/getUsersList",
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

function updateUserColumn(data, callback){
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/ID0420FF19OWidya/admin/updateUserColumn.action",
		data: JSON.stringify(data),
		contentType:'application/json',
		success: function(result){
			console.log(result);
			callback(result);
		},
		error: function(result){
			console.log("error")
			console.log(result)
			callback(result);
		}
	})
}

function deactivateUser(data, callback){
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/ID0420FF19OWidya/admin/deactivateUser.action",
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

function sendBulkEmail(data, callback){
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/ID0420FF19OWidya/admin/sendBulkEmail.action",
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


function validate($elem, regex) {
	var isValid = false;
	console.log($elem.val())
	if (regex.test($elem.val())) {
		console.log("validated");
		if ($elem.hasClass('is-invalid')) {
			$elem.removeClass('is-invalid');
		}
		$elem.addClass('is-valid');
		isValid = true;
	}else {
        if ($elem.hasClass('is-valid')) {
        	$elem.removeClass('is-valid');
		}
		console.log("not validate");
		$elem.addClass('is-invalid');
		isValid = false;
	}
	return isValid;
}