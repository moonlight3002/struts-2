<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main</title>
<link rel="icon" type="image/png" href="/ID0420FF19OWidya/assets/icon.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">

    <link rel="stylesheet" href="/ID0420FF19OWidya/css/navSearchResultStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/forrmControlStyle.css">
    <link rel = "stylesheet" href="/ID0420FF19OWidya/css/navStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/mainStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/feedbackStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/jobItemStyle.css">
        <link rel="stylesheet" href="/ID0420FF19OWidya/css/viewJobStyle.css">
	<style>
		.newItem{
			display:none;
		}
	</style>
</head>
<body>
    <s:include value="navbar.jsp"></s:include>
<div class="content-wrapper row">
    <div class="sideBar-left d-sm-none d-none d-md-block col-md-3 col-lg-2 position-fixed row">
        <div class="sideBar-user col-12 row">
            <div class="sideBar-user-header col-12 row">
                <div class="sideBar-user-header-back col-12 row">
                    <div class="sideBar-user-welcome col-12"><h5>WELCOME,</h5>
                    <div class="sideBar-user-name col-12"><h6><s:property value='%{#session.userData.firstName}'/> <s:property value='%{#session.userData.lastName}'/></h6></div>
                    </div>
                    
                </div>
                <div class="sideBar-user-profilePic col-12">
                `   <div class="user-profilePic">
                <s:set var="nameInitial" value="%{#session.userData.firstName.substring(0,1).toUpperCase()}" />
                        <img src="<s:property value='#urlpic'/>" onerror='this.onerror=null;this.src="/ID0420FF19OWidya/assets/defaultpp_<s:property value='%{nameInitial}'/>.jpg"'/>
                    </div>
                </div>
            </div>
            
        </div>
         <div class="sideBar-notification col-12">
            <div class="sideBar-notification-Title">Notification</div>
            <div class="sideBar-notification-items">
            	<div class="row">
	            	<div class="notification-items newItem col-12" data-date>
	                    <div class="notification-date"></div>
	                    <a class="notification-time-message newItem row" data-id href target="_blank">
	                        <div class="notification-time col-2"></div>
	                        <div class="notification-message col-10"></div>
	                    </a>               
	                </div>
            	</div>
                
            </div>
        </div> 
    </div>
    <div class="contentBar col-lg-12 row d-flex justify-content-between">
        <div class="sideBar d-sm-none d-none d-md-block col-md-3 col-lg-2" style="background-image: url(/ID0420FF19OWidya/assets/milkyway.jpg)">
        </div>
        <div class="content col-md-7 col-lg-8 row">
            <div class="content-head-wrapper col-12 row">
                <div class="content-jobHead col-12 row">
                <div class="col-10 content-jobHead-Title">
                     <h3>Jobs</h3>
                </div>
                <div class="col-2 content-jobHead-Button">
                    <a type="button" href="/ID0420FF19OWidya/job/hire" target="_blank" class="btn btn-info addJobBtn">+</a>    
                </div>
                <div class="col-12 content-jobHead-Filter row">
                    <div class="col-12 content-jobHead-Filter-location row d-flex justify-content-end">
                        <select class="custom-select Filter-country col-5 col-sm-3" id="country"/>
                          <option value="0" selected>All Country</option>
                        </select>
                        <select class="custom-select Filter-city col-5 col-sm-3" id="city">
                          <option value="0" selected>All City</option>
                        </select>
                    </div>
                    <div class="col-12 content-jobHead-Filter-others row">
                        <div class="col-12 col-sm-6 Filter-place d-flex justify-content-left">
                            <div class="form-check">
              					<input class="form-check-input filter" type="radio" name="place" id="remote" value="remote"/>
              					<label class="form-check-label" for="exampleRadios1">Remote</label>
              				</div>
                            <div class="form-check form-check-inline">
                              	<input class="form-check-input filter" type="radio" name="place" id="onsite" value="onsite"/>
              					<label class="form-check-label" for="exampleRadios1">Onsite</label>
                            </div>
                            <div class="form-check form-check-inline">
                              	<input class="form-check-input filter" type="radio" name="place" id="all" value="" checked/>
              					<label class="form-check-label" for="exampleRadios1">All</label>
                            </div>
                        </div>
                        <div class="col-12 col-sm-6 Filter-hours d-flex justify-content-left">
                            <div class="form-check form-check-inline">
                              	<input class="form-check-input filter" type="radio" name="hours" id="fulltime" value="fulltime"/>
              					<label class="form-check-label" for="exampleRadios1">Full Time</label>
                            </div>
                            <div class="form-check form-check-inline">
                              	<input class="form-check-input filter" type="radio" name="hours" id="parttime" value="parttime"/>
              					<label class="form-check-label" for="exampleRadios1">Part Time</label>
                            </div>
                            <div class="form-check form-check-inline">
                              	<input class="form-check-inpu filter" type="radio" name="hours" id="all" value="" checked/>
              					<label class="form-check-label" for="exampleRadios1">All</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            
            
            <div class="content-job col-12 row">
                <a class="content-job-item col-12 row newItem" href="" data-id="" data-userid="" data-toggle="modal" data-target="#viewJobDetails">
                    <div class="col-12 col-sm-7 row">
                        <div class="job-position col-12"></div>
                        <div class="job-company col-12"></div>
                        <div class="job-location col-12"><i class="fas fa-map-marker-alt"></i></div>
                    </div>
                    <div class="col-12 col-sm-5 row">
                        <div class="col-12 job-place-hours">
                            <div class="job-place"><i class="fas fa-street-view"></i></div>
                            <div class="job-hours"><i class="fas fa-clock"></i></div>
                        </div>

                        <div class="job-time col-12 row">
                            <div class="job-deadline col-7 col-sm-12"><i class="fas fa-hourglass-half"></i></div>
                            <div class="job-created col-5 col-sm-12"></div>
                        </div>

                    </div>
                </a>
               
                <div class="content-job-item seemore active">
                <div class="spinner-grow text-info" role="status">
				  <span class="sr-only">Loading...</span>
				</div>
				<div class="spinner-grow text-info" role="status">
				  <span class="sr-only">Loading...</span>
				</div>
				<div class="spinner-grow text-info" role="status">
				  <span class="sr-only">Loading...</span>
				</div>
				</div>
				<p class="nomore">
				No more item to load
				</p>
            
        </div>
        </div>
        	<div class="sideBar-right d-lg-none d-none d-xl-block row">
	            <div class="links col-12">
	                <p><a href="http://localhost:8080/ID0420FF19OWidya/about" target="_blank" > About us </a>&nbsp;|&nbsp; 
	                <a href="http://localhost:8080/ID0420FF19OWidya/privacyPolicy" target="_blank"> Privacy Policy </a>&nbsp;|&nbsp;
	                <a href="" data-toggle="modal" data-target="#feedbackForm"> Send Feedback </a>
	                </p>
	            </div>
        	</div>
    </div>
    <!-- Modal -->
<div class="modal fade" id="feedbackForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
      <form>
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Your opinion matters </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body row">
          <div class="feedback-category col-12 row">
              <div class="form-check">
              <input class="form-check-input" type="radio" name="feedbackCategory" id="exampleRadios1" value="suggestion" checked>
              <label class="form-check-label" for="exampleRadios1">
                Suggestion
              </label>
            </div>
            <div class="form-check">
              <input class="form-check-input" type="radio" name="feedbackCategory" id="exampleRadios2" value="report">
              <label class="form-check-label" for="exampleRadios2">
                Report
              </label>
            </div>
              <div class="form-check">
              <input class="form-check-input" type="radio" name="feedbackCategory" id="exampleRadios2" value="others">
              <label class="form-check-label" for="exampleRadios2">
                others
              </label>
            </div>
          </div> 
        <div class="form-group feedback-message">
            <textarea class="form-control" id="feedbackMessage" rows="3" placeholder="Aa" name="feedbackMessage"></textarea>
        </div>       
      </div>
      <div class="modal-footer">
        <button type="button" id="sendFeedback" class="btn btn-primary">Send</button>
      </div>
    </div>
    </form>
  </div>
</div>

<s:include value="jobDetailsModal.jsp"></s:include>

<div class="alert col-11 col-md-5 thankyou alert-success" role="alert">
  Thank you to our dear customers. We will keep improving to serve you better.
</div>

<div class="alert col-11 col-md-5  error alert-warning" role="alert">
  Server error. please refresh
</div>
</div>





    <script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script src="/ID0420FF19OWidya/script/navScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/mainScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/getJobScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/feedbackScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/viewJobScript.js" type="text/javascript"></script>
<s:if test="%{#session.role.equals('admin')}"><script src="/ID0420FF19OWidya/script/adminMainPageScript.js" type="text/javascript"></script></s:if><s:else></s:else>
</body>
</html>