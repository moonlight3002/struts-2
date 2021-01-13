

$(function(){
	

	
		$(document).on('change','.custom-file-input',function(e){
			console.log("change");
		                //get the file name
		       var fileName = e.target.files[0].name;
		                //replace the "Choose a file" label
		       $(this).next('.custom-file-label').html(fileName);
		})
		
		$('.profile-category').hover(function(){
			$(this).find('.profile-edit-elipsis').fadeIn();
			
		},function(){
			if($(this).find('.profile-edit-elipsis').hasClass('active')){
				$(this).find('.profile-edit-elipsis-item').hide("slide");
				$(this).find('.profile-edit-elipsis').delay(500).removeClass('active');
				
			}
			$(this).find('.profile-edit-elipsis').fadeOut();
		})
		
		
		$('.profile-edit-elipsis').click(function(){
			if($(this).hasClass('active')){
				$(this).removeClass('active');
				$(this).siblings('.profile-edit-elipsis-item').hide("slide");
			}else{
			   $(this).addClass('active')
			   $(this).siblings('.profile-edit-elipsis-item').show("slide");
			}
		});
		
		$('.uploadBtn').click(function(e){
			e.preventDefault();
			console.log("upload")
			uploadPicture($(this).data("action"));
		})
		
		$('.addBtn').click(function(e){

			e.preventDefault();
			console.log("add")
			var action = $(this).data("action");
			addData($(this).data("action"), $(`form[data-action=${action}]`));
		})
		
		$('.updateBtn').click(function(e){
			
			e.preventDefault();
			console.log("update")
			var action = $(this).data("action");
			updateData(action, function(updateDatas){
				console.log(action);
				console.log(updateDatas);
			
				if(action=="updateEducation"){
					$('.profile-category.educations > .profile-category-item:not(.newItem)').remove();
					var datas = updateDatas.user.educations;
					appendUpdateDataSuccess(action, datas)
					console.log(datas);
				}else if(action=="updateExperience"){
					$('.profile-category.experiences > .profile-category-item:not(.newItem)').remove();
					var datas = updateDatas.user.experiences;
					appendUpdateDataSuccess(action, datas)
					console.log(datas);
				}else if(action=="updateSkill"){
					$('.profile-category.skills > .profile-category-item:not(.newItem)').remove();
					var datas = updateDatas.user.skills;
					appendUpdateDataSuccess(action, datas)
					console.log(datas);
				}else if(action=="updateAboutme"){
					console.log("ok");
					console.log(updateDatas.user.dob.split("/"));
					$(`.profile-category-data#dob`).find('p').text(`${updateDatas.user.dob}`);
					$(`.profile-category-data#bio`).find('p').text(`${updateDatas.user.bio}`);
					$(`.profile-Header-User-name`).find('p').text(`${updateDatas.user.firstName} ${updateDatas.user.lastName}`);
					
					var updatedob = $('#dob>p').text().split("/");
					var editupdatedob = updatedob[0] + "-" + fullmonths[updatedob[1]] + "-" + updatedob[2];
					
					$('#dob>p').text(`${editupdatedob}`);
				}else if(action=="updateConnection"){
					console.log("ok");
					if(updateDatas.user.facebook !== '' && updateDatas.user.facebook !== null){
						$(`.profile-connection-link`).find('.facebook').removeClass("disable");
						$(`.profile-connection-link`).find('.facebook').attr("href", `https://www.facebook.com/${updateDatas.user.facebook}`);
					}else{
						$(`.profile-connection-link`).find('.facebook').addClass("disable");
					}
					if(updateDatas.user.twitter !== '' && updateDatas.user.twitter !== null){
						$(`.profile-connection-link`).find('.twitter').removeClass("disable");
						$(`.profile-connection-link`).find('.twitter').attr("href", `https://www.twitter.com/${updateDatas.user.twitter}`);
					}else{
						$(`.profile-connection-link`).find('.twitter').addClass("disable");
					}
					if(updateDatas.user.youtube !== '' && updateDatas.user.youtube !== null){
						$(`.profile-connection-link`).find('.youtube').removeClass("disable");
						$(`.profile-connection-link`).find('.youtube').attr("href", `https://www.youtube.com/${updateDatas.user.youtube}`);
					}else{
						$(`.profile-connection-link`).find('.youtube').addClass("disable");
					}
					if(updateDatas.user.instagram !== '' && updateDatas.user.instagram !== null){
						$(`.profile-connection-link`).find('.instagram').removeClass("disable");
						$(`.profile-connection-link`).find('.instagram').attr("href", `https://www.instagram.com/${updateDatas.user.instagram}`);
					}else{
						$(`.profile-connection-link`).find('.instagram').addClass("disable");
					}
					if(updateDatas.user.github !== '' && updateDatas.user.github !== null){
						$(`.profile-connection-link`).find('.github').removeClass("disable");
						$(`.profile-connection-link`).find('.github').attr("href", `https://www.github.com/${updateDatas.user.github}`);
					}else{
						$(`.profile-connection-link`).find('.github').addClass("disable");
					}
				}
				
				
			});
		})
         
		
		$('.profile-edit').click(function(){
			$('.profile-edit-elipsis').removeClass('active');
			$('.profile-edit-elipsis-item').hide("slide");
			getUserData($(this).data("action"));
			
		})
		
		$(document).on('change', 'input[name="present"]', function(){
			if($(this).prop("checked") == true){
				$(this).parent('.form-check').siblings('select').val("0");
				$(this).parent('.form-check').siblings('select').attr('disabled', "disabled");
				console.log($(this).parent('.form-check').siblings('select').val());
				
			}else{
				$(this).parent('.form-check').siblings('select').removeAttr('disabled');
			}
		})
		
		$(document).on('click','.deleteBtn', function(){
			$elem = $(this)
			deleteData($elem.data('action'), $elem.data("action-category"),$elem.data("id"), function(){
				$elem.parent().parent().parent().remove();
				$(`.profile-category-item[data-category='${$elem.data("category")}'][data-id='${$elem.data("id")}']`).remove();
			})
		})
})


function getFormData(action, elem){
	var FormData = {}
	if (action=="addEducation" || action=="updateEducation"){
		FormData['school'] = elem.find('input[name="school"]').val();
		FormData['degree'] =  elem.find('input[name="degree"]').val();
		FormData['startYear'] = elem.find('select[name="startYear"]').val();
		FormData['endYear'] = elem.find('select[name="endYear"]').val();
		FormData['educationDesc'] = elem.find('textarea[name="educationDesc"]').val();
		FormData['show'] = elem.find('input[name="show"]').prop("checked");
	}else if (action=="addExperience" || action=="updateExperience"){
		FormData['position'] = elem.find('input[name="position"]').val();
		FormData['company'] =  elem.find('input[name="company"]').val();
		FormData['startYear'] = elem.find('select[name="startYear"]').val();
		FormData['startMonth'] = elem.find('select[name="startMonth"]').val();
		FormData['endMonth'] = (elem.find('select[name="endMonth"]').val()==null) ? 0: elem.find('select[name="endMonth"]').val();
		FormData['endYear'] = (elem.find('select[name="endMonth"]').val()==null) ? 0: elem.find('select[name="endYear"]').val();
		FormData['experienceDesc'] = elem.find('textarea[name="experienceDesc"]').val();
		FormData['show'] = elem.find('input[name="show"]').prop("checked");
	}else if (action =="addSkill" || action =="updateSkill"){
		FormData['technologyID'] = elem.find('select[name="technologyID"]').val();
		FormData['technologyName'] = elem.find('select[name="technologyID"]>option:selected').text();
		FormData['skillLevel'] = elem.find('select[name="skillLevel"]').val();
		FormData['show'] = elem.find('input[name="show"]').prop("checked");
	}
	console.log("form data", FormData);
	return FormData;
}

function getaddData(action, elem){
	
	var obj={}	
	if (action=="addEducation"){
		obj['education'] = getFormData(action,elem);
	}else if(action=="addExperience"){
		obj['experience'] = getFormData(action,elem);
	}else if(action=="addSkill"){
		obj['skill'] = getFormData(action,elem);
	}
	console.log(obj);
	return obj;
}

function getupdateData(action){
	var obj={}
	var datas={}
	var datasArray=[]

	
	if (action=="updateEducation"){
		$(`form[data-action=${action}] > .update-data-item:not(.newItem)`).each(function(){
			var data = getFormData(action, $(this));
			data['educationID'] = $(this).data("id");
			datasArray.push(data);
		})
		datas['educations'] = datasArray
	}else if (action=="updateExperience"){
		$(`form[data-action=${action}] > .update-data-item:not(.newItem)`).each(function(){
			var data = getFormData(action, $(this));
			data['experienceID'] = $(this).data("id");
			datasArray.push(data);
		})
		datas['experiences'] = datasArray
	}else if (action=="updateSkill"){
		$(`form[data-action=${action}] > .update-data-item:not(.newItem)`).each(function(){
			var data={}
			data['skillID'] = $(this).data("id");
			data['technologyName']=$(this).find('input[name="technologyName"]').val();
			data['technologyID']=$(this).find('input[name="technologyID"]').val();
			data['skillLevel']=$(this).find('select[name="skillLevel"]').val();
			data['show']=$(this).find('input[name="show"]').prop("checked");
			datasArray.push(data);
		})
		datas['skills'] = datasArray
	}else if (action=="updateConnection"){
		datas['facebook'] = $(`form[data-action=${action}]`).find('input[name="facebook"]').val();
		datas['twitter'] = $(`form[data-action=${action}]`).find('input[name="twitter"]').val();
		datas['instagram'] = $(`form[data-action=${action}]`).find('input[name="instagram"]').val();
		datas['youtube'] = $(`form[data-action=${action}]`).find('input[name="youtube"]').val();
		datas['github'] = $(`form[data-action=${action}]`).find('input[name="github"]').val();
	}else if (action=="updateAboutme"){
		console.log("updateAboutMe")
		datas['firstName'] = $(`form[data-action=${action}]`).find('input[name="firstName"]').val();
		datas['lastName'] = $(`form[data-action=${action}]`).find('input[name="lastName"]').val();
		datas['dob'] = $(`form[data-action=${action}]`).find('input[name="dob"]').val();
		datas['bio'] = $(`form[data-action=${action}]`).find('textarea[name="bio"]').val();
	}
	obj['user'] = datas
	console.log(obj);
	return obj;
}

function getDeleteData(action, id){
	var obj={}
	var datas={}
	if(action=="deleteEducation"){
		datas['educationID'] = id;
		datas['deleted'] = true;
		obj['education'] = datas
	}else if(action=="deleteExperience"){
		datas['experienceID'] = id;
		datas['deleted'] = true;
		obj['experience'] = datas
	}else if(action=="deleteSkill"){
		datas['skillID'] = id;
		datas['deleted'] = true;
		obj['skill'] = datas
	}
	
	console.log(obj);
	return obj;
}


function appendData(action, data){
	console.log("appendData");
	console.log(action)
	if (action=="addEducation" || action=="updateEducation"){
		$cloneItem = $('.profile-category-item.newItem[data-category="education"]').clone();
		$cloneItem.attr("data-id", `${data.educationID}`)
		$cloneItem.find('.profile-education-school>p').append(`${data.school}`);
		$cloneItem.find('.profile-education-degree>p').append(`${data.degree}`);
		$cloneItem.find('.startYear').append(`${data.startYear}`);
		$cloneItem.find('.endYear').append(`${data.endYear}`);
		$cloneItem.find('.profile-education-description>p').append(`${data.educationDesc}`);
		$cloneItem.removeClass("newItem");
		$cloneItem.appendTo('.profile-category.educations').hide().fadeIn();
	}else if(action =="addExperience"|| action=="updateExperience"){
		$cloneItem = $('.profile-category-item.newItem[data-category="experience"]').clone();
		$cloneItem.attr("data-id", `${data.experienceID}`)
		$cloneItem.find('.profile-experience-position>p').append(`${data.position}`);
		$cloneItem.find('.profile-experience-company>p').append(`${data.company}`);
		$cloneItem.find('.startMonth').text(`${months[data.startMonth]}`);
		$cloneItem.find('.endMonth').text(`${months[data.endMonth]}`);
		$cloneItem.find('.startYear').text(`${data.startYear}`);
		if (data.endYear !='0'){
		$cloneItem.find('.endYear').text(`${data.endYear}`);

		}
		$cloneItem.find('.profile-experience-description>p').append(`${data.experienceDesc}`);
		$cloneItem.removeClass("newItem");
		$cloneItem.appendTo('.profile-category.experiences').hide().fadeIn();
	}else if(action=="addSkill"|| action=="updateSkill"){
		$cloneItem = $('.profile-category-item.newItem[data-category="skill"]').clone();
		$cloneItem.attr("data-id", `${data.skillID}`)
		$cloneItem.find('.technologyName').append(`${data.technologyName}`);
		if (data.skillLevel != "not specified" || data.skillLevel != null ){
			$cloneItem.find('.skillLevel').append(`${data.skillLevel}`);
		}
		$cloneItem.removeClass("newItem");
		$cloneItem.appendTo('.profile-category.skills').hide().fadeIn();
	}
	
}


function appendUpdateDataSuccess(action, datas){
	$.each(datas, function(i, item){
		if(datas[i].show == true){
			console.log(datas[i])
			appendData(action, datas[i])
		}

	})
}


function appendUpdateModalData(action, datas){
	if (action == "viewAllEducations"){
		var data = datas.educations;
		$.each(data, function(i, item){
			$cloneItem = $('.update-data-item.newItem[data-category="education"]').clone();
			$cloneItem.attr("data-id", `${data[i].educationID}`)
			$cloneItem.find('input[name="school"]').val(`${data[i].school}`);
			$cloneItem.find('input[name="degree"]').val(`${data[i].degree}`);
			$cloneItem.find('select[name="startYear"]').val(`${data[i].startYear}`);
			$cloneItem.find('select[name="endYear"]').val(`${data[i].endYear}`);
			$cloneItem.find('input[name="show"]').prop("checked", JSON.parse(`${data[i].show}`));
			$cloneItem.find('.educationDesc').val(`${data[i].educationDesc}`);
			$cloneItem.find('.deleteBtn').attr("data-id", `${data[i].educationID}`);
			$cloneItem.removeClass("newItem");
			$cloneItem.appendTo('.updateForm[data-category="education"]').hide().fadeIn();
			
		})
	}else if (action == "viewAllExperiences"){
		var data = datas.experiences;
		$.each(data, function(i, item){
			$cloneItem = $('.update-data-item.newItem[data-category="experience"]').clone();
			$cloneItem.attr("data-id", `${data[i].experienceID}`)
			$cloneItem.find('input[name="position"]').val(`${data[i].position}`);
			$cloneItem.find('input[name="company"]').val(`${data[i].company}`);
			$cloneItem.find('select[name="startMonth"]').val(`${data[i].startMonth}`);
			$cloneItem.find('select[name="startYear"]').val(`${data[i].startYear}`);
			if(data[i].endMonth!=0){
				$cloneItem.find('select[name="endMonth"]').val(`${data[i].endMonth}`);
				$cloneItem.find('select[name="endYear"]').val(`${data[i].endYear}`);
			}else{
				$cloneItem.find('input[name="present"]').prop("checked", "checked");
				$cloneItem.find('select[name="endMonth"]').val('select').val("0").attr('disabled', 'disabled');
				$cloneItem.find('select[name="endYear"]').val('select').val("0").attr('disabled', 'disabled');
			}
			$cloneItem.find('input[name="show"]').prop("checked", JSON.parse(`${data[i].show}`));
			$cloneItem.find('.experienceDesc').val(`${data[i].experienceDesc}`);
			$cloneItem.find('.deleteBtn').attr("data-id", `${data[i].experienceID}`);
			$cloneItem.removeClass("newItem");
			$cloneItem.appendTo('.updateForm[data-category="experience"]').hide().fadeIn();
			
		})
	}else if (action == "viewAllSkills"){
		var data = datas.skills;
		$.each(data, function(i, item){
			$cloneItem = $('.update-data-item.newItem[data-category="skill"]').clone();
			$cloneItem.attr("data-id", `${data[i].skillID}`)
			$cloneItem.find('input[name="technologyName"]').val(`${data[i].technologyName}`);
			$cloneItem.find('input[name="technologyID"]').val(`${data[i].technologyID}`);
			$cloneItem.find('select[name="skillLevel"]').val(`${data[i].skillLevel}`);
			
			$cloneItem.find('input[name="show"]').prop("checked", JSON.parse(`${data[i].show}`));
			$cloneItem.find('.deleteBtn').attr("data-id", `${data[i].skillID}`);
			$cloneItem.removeClass("newItem");
			$cloneItem.appendTo('.updateForm[data-category="skill"]').hide().fadeIn();
			
		})
	}else if (action == "viewConnection"){
		
		if(datas.facebook!=null){
			$('.updateForm[data-category="connection"]').find('input[name="facebook"]').val(`${datas.facebook}`);
		}
		if(datas.twitter!=null){
			$('.updateForm[data-category="connection"]').find('input[name="twitter"]').val(`${datas.twitter}`);
		}
		if(datas.youtube!=null){
			$('.updateForm[data-category="connection"]').find('input[name="youtube"]').val(`${datas.youtube}`);
		}
		if(datas.github!=null){
			$('.updateForm[data-category="connection"]').find('input[name="github"]').val(`${datas.github}`);
		}
		if(datas.instagram!=null){
			$('.updateForm[data-category="connection"]').find('input[name="instagram"]').val(`${datas.instagram}`);
		}
	}else if (action == "viewAboutme"){
		$('.updateForm[data-category="aboutme"]').find('input[name="firstName"]').val(`${datas.firstName}`);
		$('.updateForm[data-category="aboutme"]').find('input[name="lastName"]').val(`${datas.lastName}`);
		$('.updateForm[data-category="aboutme"]').find('input[name="dob"]').val(`${datas.dob}`);
		if(datas.bio!=null){
			$('.updateForm[data-category="aboutme"]').find('textarea[name="bio"]').val(`${datas.bio}`);
		}
}
	
}



function resetModal(action){
	if (action=="addEducation" || action=="addExperience"){
		$(`.modal#${action}`).find('input[type=text]').val('');
		$(`.modal#${action}`).find('select').val('0');
		$(`.modal#${action}`).find('#show').prop("checked", "checked");
		$(`.modal#${action}`).find('textarea').val('');
	}else if (action=="addSkill"){
		$(`.modal#${action}`).find('select').val('0');
		$(`.modal#${action}`).find('#show').prop("checked", "checked");
	}
}


function getUserData(action){
	$('.update-data-item:not(.newItem)').remove();
	 $.ajax({
	        type: "POST",
	        url: `${action}.action`,
	        success: function(data) {
	        	if(data.userID){
		            console.log("SUCCESS");
		            console.log(data);
		            appendUpdateModalData(action, data);
	        	}else{
					$('.alert.error').fadeIn().delay(2000).fadeOut();
	        	}
	        },
	        error: function() {
				$('.alert.error').fadeIn().delay(2000).fadeOut();
	        }
	    });
	
}


function updateData(action, callback){
	var updateDatas = getupdateData(action);
	 $.ajax({
	        type: "POST",
	        url: `${action}.action`,
	        data : JSON.stringify(updateDatas),
	        dataType:"json",
	        contentType: 'application/json',
	        success: function(data) {
	            console.log("SUCCESS");
	            console.log(data);
	            if(data>0){
	            	$(`#${action}`).modal('hide');
	            	$('.alert.updateThankyou').fadeIn().delay(2000).fadeOut();
	            	callback(updateDatas);

	            }else{
	            	$(`#${action}`).modal('hide');
					$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
						$(`#${action}`).modal('show');
					});
	            }
	        },
	        error: function() {
	        	$(`#${action}`).modal('hide');
				$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
					$(`#${action}`).modal('show');
				});
	        }
	    });
}

function deleteData(action, actionCategory, id, callback){
	$.ajax({
        type: "POST",
        url: `${action}.action`,
        data : JSON.stringify(getDeleteData(action, id)),
        contentType: 'application/json',
        success: function(data) {
            console.log("SUCCESS");
            console.log(data);
            if(data> 0){
            	callback()
            }else{
            	$(`#${actionCategory}`).modal('hide');
    			$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
    				$(`#${actionCategory}`).modal('show');
    			});
            }
        },
        error: function() {
        	$(`#${actionCategory}`).modal('hide');
			$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
				$(`#${actionCategory}`).modal('show');
			});
        }
    });
}

function addData(action, elem){
	 $.ajax({
	        type: "POST",
	        url: `${action}.action`,
	        data : JSON.stringify(getaddData(action, elem)),
	        contentType: 'application/json',
	        success: function(data) {
	            console.log("SUCCESS");
	            console.log(data);
	            if(data!=""){
	            	$(`#${action}`).modal('hide');
	            	$('.alert.addThankyou').fadeIn().delay(2000).fadeOut();
	            	if(data.show == true){
		            	appendData(action, data);
	            	}
	            	resetModal(action);
	            }else{
	            	$(`#${action}`).modal('hide');
					$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
						$(`#${action}`).modal('show');
					});
	            }
	        },
	        error: function() {
	        	$(`#${action}`).modal('hide');
				$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
					$(`#${action}`).modal('show');
				});
	        }
	    });
}

function uploadPicture(action){
		    var form = $(`#uploadForm[data-action=${action}]`)[0];
		    console.log("form:", form);
		    var file = new FormData(form);
		    console.log("file",file);
		    $.ajax({
		        type: "POST",
		        enctype: 'multipart/form-data',
		        url: `${action}.action`,
		        data : file,
		        contentType: false,
		        processData: false,
		        success: function(data) {
		            console.log("SUCCESS");
		            console.log(data);
		            if (action == "uploadProfilePicture"){
		            	if(data.profilePic!=null){
			            	$('.profile-profilePicture>img').attr("src", "getFile?userID="+userProfileID+"&profilePic="+data.profilePic);
			            	$('#uploadProfilePic').modal('hide');
		            	}else{
		            		$('#uploadProfilePic').modal('hide');
							$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
								$('#uploadProfilePic').modal('show');
							});
		            	}
		            }else if (action = "uploadHeaderPicture"){
		            	if(data.headerPic!=null){
		            		$('.profile-Header-User-headerPicture').css("background-image", "url(getFile?userID="+userProfileID+"&headerPic="+data.headerPic+")");
		            		$('#uploadHeaderPic').modal('hide');
		            	}else{
		            		$('#uploadHeaderPic').modal('hide');
							$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
								$('#uploadHeaderPic').modal('show');
							});
		            	}
		            }
		            
		           
		        },
		        error: function() {
		        	$('#uploadHeaderPic').modal('hide');
					$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
						$('#uploadHeaderPic').modal('show');
					});
		        }
		    });
		    
	
}



            