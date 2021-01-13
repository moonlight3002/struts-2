function submitForm(actionClass){
	var isvalidate = true;
	$(`input[data-category=${actionClass}]:not([type="submit"])`).each(function() {
		var inputId = $(this).attr("name");
		if ($(this).hasClass("invalid") || $(this).val() == "") {
			console.log(this);
			$(this).focus();
			isvalidate = false;
			ErrorMessage(this, inputId);
		}
	});
	if (isvalidate) {
		console.log("validate..");
		console.log(`#${actionClass} form`);
		$(`form#${actionClass}`).submit();
	};
	
}

function getEmailData(){
	var userData={}
	userData['email'] = $('input[name="email"][data-category="forgetPassword"]').val();
	console.log(userData);
	return userData;
}

$(function(){
    $(`#register`).hide();
    $(`.loginBtn`).addClass("active");
    
    
    
    $('.submitBtn').on('click', function(e) {
		console.log("submit button clicked");
		e.preventDefault();
		var register = "register";
		var login = "login";
		var targetId = e.target.id;
		if (targetId.indexOf("register")!=-1){
			submitForm(register)
		}
		if (targetId.indexOf("login")!=-1){
			submitForm(login)
		}
		
	});
    
    $('.forgetPasswordBtn').on('click', function(e){
    	e.preventDefault();
    	$(this).hide();
    	$('.spinner-border').show();
    	retrievePassword();
    })
    
    
    $(document).on('click', '.loginBtn', function() {
				console.log("clicked");
                $(`.btn`).removeClass("active");
                $(`.loginBtn`).addClass("active");
				$("form#login").show();
				$("form#register").hide();
			});
			
    $(document).on('click', '.registerBtn', function() {
				console.log("clicked");
                $(`.btn`).removeClass("active");
                $(`.registerBtn`).addClass("active");
				$("form#register").show();
				$("form#login").hide();
			});
    
    
    

    var errorfromServer ={
					"userExist": "User exist. please&nbsp<a href='#' id='login' class='loginBtn'>Login</a>&nbspor use another email",
					"userNotExist": "User not exist. please check email or&nbsp<a href='#' id='register' class='registerBtn'>Register</a>&nbsp",
					"invalidPassword": "Wrong password dude!"
			};
			
				var url = window.location.href;
				
				console.log(url);
				if (url.includes("login")){
					$(`.btn`).removeClass("active");
	                $(`.loginBtn`).addClass("active");
					$("form#login").show();
					$("form#register").hide();
					var action = "login";
					if (userNotExist !== "") {
						console.log("user notexist")
						var err = "userNotExist";
						showError(err, action);
					}
					
					if (invalidPassword !== "") {
						console.log("invalid password")
						var err = "invalidPassword";
						showError(err, action);
					}
				}
				else if (url.includes("register")){
					var action = "register";
					$(`.btn`).removeClass("active");
	                $(`.registerBtn`).addClass("active");
					$("form#register").show();
					$("form#login").hide();
					if (userExist !== "") {
						console.log("user exist")
						var err = "userExist";
						showError(err, action);
					}
						
				}else {
					$(".outerDiv").hide();
				}
				
			
			function showError(err, action){
				console.log(err)
				console.log(action)
				console.log("read"+ errorfromServer[`${err}`]);
				$(
						"<span class ='errorMessage'>"+ errorfromServer[err] +"</span>")
						.insertBefore(`form#${action}>.form-group:last`);
			}
})

function retrievePassword(){
	
	$.ajax({
		type : "POST",
		data : JSON.stringify(getEmailData()),
		contentType : 'application/json',
		url : "http://localhost:8080/ID0420FF19OWidya/forgetPassword.action",
		success : function(data) {
			console.log(data);
			$('.forgetPasswordBtn').show();
	    	$('.spinner-border').hide();
			if(data ==="success"){
				$('#forgetPass').modal('hide');
				$('.alert.info').fadeIn().delay(2000).fadeOut();
			}else if(data ==="email not Exist"){
				$('#forgetPass').modal('hide');
				$('.alert.warning').fadeIn().delay(2000).fadeOut(function(){
					$('#forgetPass').modal('show');
				});
			}else{
				$('#forgetPass').modal('hide');
				$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
					$('#forgetPass').modal('show');
				});
			}

		},
		error: function(data){
			$('.forgetPasswordBtn').show();
	    	$('.spinner-border').hide();
			$('#forgetPass').modal('hide');
			$('.alert.error').fadeIn().delay(2000).fadeOut(function(){
				$('#forgetPass').modal('show');
			});
		}
	})
}




	