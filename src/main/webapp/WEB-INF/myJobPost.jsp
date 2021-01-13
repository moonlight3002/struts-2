<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Job Post</title>
<link rel="icon" type="image/png" href="/ID0420FF19OWidya/assets/icon.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/navSearchResultStyle.css">
    <link rel = "stylesheet" href="/ID0420FF19OWidya/css/navStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/jobNavStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/feedbackStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/viewJobStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/myJobPostStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/newjobItemStyle.css">

</head>
<body>
    <s:include value="navbar.jsp"></s:include>
   <s:include value="jobNavbar.jsp"></s:include>


<div class="content-wrapper row">
    <div class="col-12 row content-inner">
        <div class="content-right col-12 col-md-12">
        <div class="content-right-items row">
        <s:iterator value="myJobLists">
            <div class="content-job-item col-12 row" href="" data-userid="<s:property value="userID"/>" data-id="<s:property value="JobID"/>">
	            <div class="col-12 col-sm-6 row">
	            	<div class="job-position col-12"><s:property value="position"/></div>
 	                <div class="job-company col-12"><s:property value="companyName"/></div>
	                <div class="job-location col-12"><i class="fas fa-map-marker-alt"></i><s:property value="city.cityName"/>, <s:property value="country.countryName"/></div>
					<div class="col-12"><a href="#" data-id="<s:property value="JobID"/>" id="viewApplicantLists">View applicants</a><span> | </span><a href="#" data-userid="<s:property value="userID"/>" data-id="<s:property value="JobID"/>" data-toggle="modal" data-target="#viewJobDetails">Job Details</a></div>
	            </div>
			  <div class="col-12 col-sm-6 row">
	                        <div class="col-12 job-place-hours">
	                            <div class="job-place"><i class="fas fa-street-view"></i><s:property value="place"/></div>
	                            <div class="job-hours"><i class="fas fa-clock"></i><s:property value="hours"/></div>
	                        </div>
						
	                        <div class="job-time col-12 row">
	                            <div class="job-deadline col-7 col-sm-12"><s:if test="deadlineSubmission!='Closed'"><i class="fas fa-hourglass-half"></i></s:if><s:else><i class="fas fa-hourglass-end"></i></s:else><s:property value="deadlineSubmission"/></div>
	                            <div class="job-created col-5 col-sm-12"><s:property value="createdDate"/></div>
	                        </div>
	
	           </div> 
	       </div>
	    </s:iterator>
        </div>   
    </div>
     
        <div class="content-left d-none d-lg-block position-fixed">
            <div class="row">
            <div class="contentleftCloseBtnDiv d-xl-none d-lg-none d-md-none">
               <i class="fas fa-times contentleftCloseBtn"></i>
            </div>
            <p class="nomore">
				No item to load
				</p>
                <div class="content-left-applicant-items col-12 row">
                    <a class="col-12 row applicant-item newItem" data-id="" href="#">
                        
                        <div class="col-2 applicant-image">
                            <img src=""/>
                        </div>
                        <div class="col-5 applicant-name">
                            <p></p>
                        </div>
                        <div class="col-5 applicant-submit-date">
                            <p></p>
                        </div>
                        <div class="newApplicant-status"><div class="newApplicant"></div></div>
                    </a>
                </div>

                <div class="content-left-view-submission col-6 position-fixed">
                    <div class="row">
                        <div class="view-header col-12">
                            <div class="row">
                           
                                <div class="viewImageDiv col-2">
                                    <img src="">
                                </div>
                                <div class="viewUserNameSubmission col-9">
                                    <p class="fullName"></p>
                                    <p class="submissionDate">Submission Date: <span></span></p>
                                </div>
                                <div class="viewCloseviewBtn col-1">
                                    <i class="fas fa-times"></i>
                                </div>
                            </div>
                        </div>
                        
                        <div class="view-row col-12">
                            <div class="row">
                                <div class="col-4 view-label">          <p>Position</p>
                                </div>
                                <div class="col-8 view-text">  <p class="position"></p>
                                </div>
                            </div>
                        </div>
                        
                        
                        <div class="view-row col-12">
                            <div class="row">
                                <div class="col-4 view-label">          <p>Company</p>
                                </div>
                                <div class="col-8 view-text">  <p class="company"></p>
                                </div>
                            </div>
                        </div>
                        
                        <div class="view-row col-12">
                            <div class="row">
                                <div class="col-4 view-label">          <p>Phone no.</p>
                                </div>
                                <div class="col-8 view-text">
                                    <p class="phone">+62 81264394535</p>
                                </div>
                            </div>
                        </div>
                        
                        <div class="view-row col-12">
                            <div class="row">
                                <div class="col-12 view-label">          <p>Introduction:</p>
                                </div>
                                <div class="col-12 view-text">  
                                    <p class="about"></p>
                                </div>
                            </div>
                        </div>
                        
                        
                        <div class="view-row view-QA col-12">
                            <div class="row">
                                <div class="col-12 view-label">          <p>Q and A:</p>
                                </div>
                            </div>
                        </div>
                        
                        <div class="view-row view-QA-item newItem col-12">
                            <div class="row">
                                <div class="col-12 view-label"><p class="question"></p>
                                </div>
                                <div class="col-12 view-text">  
                                    <p class="answer"></p>
                                </div>
                            </div>
                        </div>
                        
                        
                        
                        <div class="view-row col-12">
                            <div class="row">
                                <div class="col-12 view-attachment ">  
                                    <a class="btn btn-primary attachmentBtn" href=""><i class="fas fa-file-download"></i> Attachment</a>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                    
                </div>
            </div>
        </div>
     </div>   


</div>

<s:include value="footer.jsp"></s:include>
<s:include value="jobDetailsModal.jsp"></s:include>

<div class="alert col-11 col-md-5  error alert-warning" role="alert">
  Server error. please refresh
</div>





    <script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script src="/ID0420FF19OWidya/script/navScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/feedbackScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/myJobPostScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/viewJobScript.js" type="text/javascript"></script>
<script>
$('.myJobPost').addClass('active');
</script>
</body>
</html>