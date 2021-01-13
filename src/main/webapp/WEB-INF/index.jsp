<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="icon" type="image/png" href="/ID0420FF19OWidya/assets/icon.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    
<link href='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css' rel='stylesheet' type='text/css'>

<link rel = "stylesheet" href="/ID0420FF19OWidya/css/homeStyle.css">
<link rel="stylesheet" href="/ID0420FF19OWidya/css/datepickerStyle.css">
<link rel="stylesheet" href="/ID0420FF19OWidya/css/forrmControlStyle.css">
</head>
<body>
    <div class="outercontainer">
        <div class="innercontainer row">
            <div class="right-div col-lg-7 d-none d-lg-block row">
                <div class="logo-div col-12 d-flex justify-content-center">
                    <div class="logoImgDiv">
                    <img src="/ID0420FF19OWidya/assets/icon.png" class="logo" alt="" loading="lazy">
                        <div class="buttonDiv row">
                            <button class="btn registerBtn col-md-12">REGISTER</button>
                            <button class="btn loginBtn col-md-12">LOGIN</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="right-div-small col-lg-7 d-block d-xl-none d-lg-none row">
                <div class="logo-div-small col-12 d-flex justify-content-center">
                    <div class="logoImgDiv-small">
                    <img src="/ID0420FF19OWidya/assets/icon.png" class="logo-small" alt="" loading="lazy">
                        <div class="buttonDiv row">
                            <button class="btn registerBtn col-6">REGISTER</button>
                            <button class="btn loginBtn col-6">LOGIN</button>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="input-div col-12 col-lg-4 row">
                <div class="form-div col-12 d-flex justify-content-center">
                    <s:form theme="simple" method="post" action="register" id="register">
                      <div class="form-group">
                        <s:textfield type="text" theme="simple" name="firstName" data-category="register" class="form-control" placeholder="First Name"/>
                      </div>
                    <div class="form-group">
                        <s:textfield type="text" theme="simple" name="lastName" data-category="register" class="form-control" placeholder="Last Name"/>
                      </div>
                      <div class="form-group">
                        <s:textfield type="email" theme="simple" name="email" data-category="register" class="form-control" placeholder="email"/>
                      </div>
                        <div class="form-group">
                        <s:textfield type="text" theme="simple" name="dob" data-category="register" class="form-control datepickerInput" placeholder="date of birth"/>
                      </div>

                        <div class="form-group">
                        <s:password type="password" theme="simple" name="password" data-category="register" class="form-control" placeholder="password"/>
                      </div>

                        <div class="form-group">
                        <s:submit type="submit" theme="simple" data-category="register" class="form-control btn btn-btn-success submitBtn" id="registerSubmitBtn" value="Register"/>
                      </div>
                    </s:form>



                    <s:form method="post" action="login" id="login">
                      <div class="form-group">
                        <s:textfield type="email" theme="simple" name="email" data-category="login" class="form-control" placeholder="email"/>
                      </div>
                        <div class="form-group">
                        <s:password type="password" theme="simple" name="password" data-category="login" class="form-control" placeholder="password"/>
                      </div>
                        <a href="" class="forgetpasswordlink" data-toggle="modal" data-target="#forgetPass">forget Password?</a>
                        <div class="form-group">
                        <s:submit type="submit" theme="simple" data-category="login" class="form-control btn btn-btn-success submitBtn" id="loginSubmitBtn" value="Login"/>
                      </div>
                    </s:form>
                </div>
            </div>
            <div class="footer col-12">
                <a href="aboutUs.html">About us</a> | 
                <a href="privacyPolicy.html">Privacy Policy</a>
            </div>
        </div>

    <!-- Modal -->
    <div class="modal fade" id="forgetPass" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog">
          <form method="post" id="forgetpassword">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Forget Password</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body d-flex justify-content-center">
                  <h6>Please enter your email address</h6>
                <div class="form-group">
                    <s:textfield theme="simple" type="email" name="email" data-category="forgetPassword" class="form-control" placeholder="email"/>
                </div>
              </div>
              <div class="modal-footer">
              
              <div class="spinner-border text-info" role="status">
				  <span class="sr-only">Loading...</span>
			</div>
                <div class="form-group">
                    <button type="submit" data-category="forgetPassword" class="form-control btn btn-btn-success forgetPasswordBtn" id="forgetPasswordBtn">Submit</button>
                </div>
              </div>
            </div>
        </form>
      </div>
    </div>
</div>

<div class="alert col-11 col-md-5 info alert-info" role="alert">
 	Your password has been sent to your email. Thank you
</div>

<div class="alert col-11 col-md-5 warning alert-warning" role="alert">
  Email not exist.
</div>

<div class="alert col-11 col-md-5 error alert-error" role="alert">
  Server Error. Please try again.
</div>
<s:set var="userExist" value="fieldErrors.userExist" /> 
<s:set var="userNotExist" value="fieldErrors.userNotExist" />
<s:set var="invalidPassword" value="fieldErrors.invalidPassword" />
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

<script src='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js' type='text/javascript'></script>

<script>
	
var userExist = "<s:property value='#userExist'/>";
var userNotExist = "<s:property value='#userNotExist'/>";
var invalidPassword = "<s:property value='#invalidPassword'/>";

    $(".datepickerInput").datepicker({
        "startDate" : new Date(1935, 11, 31),
        "endDate" : new Date(2015, 11, 31),
        "format" : 'dd/mm/yyyy',
        "yearRange" : [ 1935, 2015 ],
        "setDate" : new Date(1999, 06, 15)
        
    });

</script>
    <script src="/ID0420FF19OWidya/script/validateInput.js" type="text/javascript"></script>
    <script src="/ID0420FF19OWidya/script/homeScript.js" type="text/javascript"></script>
</body>
</html>