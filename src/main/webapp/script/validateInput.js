var patterns = {
	firstName : /^[a-zA-Z ]{3,50}$/i,
	lastName : /^[a-zA-Z]{3,50}$/i,
	password : /^[\w@%&=!]{5,20}$/,
	dob : /^([0-2][0-9]|3[0-1])\/(0[0-9]|1[0-2])\/(19[3-9]\d|20[01]\d)$/,
	email : /^[a-zA-z\d\.-]*@[\w-]*\.[a-zA-Z]{2,3}(\.[a-zA-Z]{2,3})?$/
};

var errorMessage = {
	firstName : "3 to 50 letters length",
	lastName : "3 to 50 letters length, no-space",
	password : "5 to 20 (letters, numbers, symbols(@%&_=!)",
	dob : "format: dd/mm/yyyy",
	email : "format: someone@example.com",
	global : "required"
};

$(function() {
	$('input:not(:input[type=submit])').each(
			function(e) {
				$(this).attr("required", true); // set all input attribute with
												// require for each input
				var inputId = $(this).attr("name"); // lookup for name attribute
				// data-error and name attribute for the span);
				$(
						"<span class='helper-text right-align' data-error='"
								+ errorMessage[inputId]
								+ "' data-success='Perfect!' id='" + inputId
								+ "'+></span>").insertAfter(this);
				console.log($(this).val());
				if ($(this).val() != "") {
					console.log(this + "not null #28");
					validate(this, patterns[inputId]);
                    ErrorMessage(this, inputId);
				}});

	$('input:not(:input[type=submit])').keyup(function(e) {
        console.log("k")
		var inputId = $(this).attr('name');
		validate(this, patterns[inputId]);
		ErrorMessage(this, inputId);
		});

    $('input:not(:input[type=submit])').blur(function(e) {
		var inputId = $(this).attr('name');
		validate(this, patterns[inputId]);
		ErrorMessage(this, inputId);
		});

});



function ErrorMessage(field, inputId) {
    
	if ($(field).val() == "") {
        console.log(errorMessage['global']);
            $(field).siblings(`.helper-text#${inputId}`).attr("data-error", `${errorMessage['global']}`).text(`${errorMessage['global']}`).css("color", "red");
	} else  {
        if ($(field).hasClass("invalid")){
             $(field).siblings(`.helper-text#${inputId}`).attr("data-error", `${errorMessage[inputId]}`).text(`${errorMessage[inputId]}`).css("color", "red");
        }else if($(field).hasClass("valid")){
            var message = $(field).siblings(`.helper-text#${inputId}`).data("success");
            $(field).siblings(`.helper-text#${inputId}`).text(`${message}`).css("color", "green");
        }
		
	}

}

function validate(field, regex) {
	console.log($(field).val())
	if (regex.test($(field).val())) {
		console.log("validated");
		if ($(field).hasClass('invalid')) {
			$(field).removeClass('invalid');
		}
		$(field).addClass('valid');
	}else {
        if ($(field).hasClass('valid')) {
			$(field).removeClass('valid');
		}
		console.log("not validate");
		$(field).addClass('invalid');
	}
}

