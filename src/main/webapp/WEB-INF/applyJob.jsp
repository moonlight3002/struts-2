<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Apply</title>
<link rel="icon" type="image/png" href="/ID0420FF19OWidya/assets/icon.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">

    <link rel="stylesheet" href="/ID0420FF19OWidya/css/navSearchResultStyle.css">
    <link rel = "stylesheet" href="/ID0420FF19OWidya/css/navStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/jobNavStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/feedbackStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/viewJobStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/applyJobStyle.css">

</head>
<body>
    <s:include value="navbar.jsp"></s:include>
   <s:include value="jobNavbar.jsp"></s:include>

<s:push value="jobDetails">
<div class="content-wrapper row">
    <div class="content-left col-12 row">
        <div class="content-left-blank-space d-none d-sm-none d-lg-block col-lg-6 col-xl-6">
     
        </div>
        <div class="content-left-form col-12 col-sm-12 col-lg-6 row submissionFormDiv">
        <s:form id="submitApplicationForm" name="submitApplicationForm"
				data-action="job/submit" enctype="multipart/form-data"
				action="http://localhost:8080/ID0420FF19OWidya/job/apply/submit.action" theme="simple">
            <div class="row">
                <div class="submissionForm-Title col-12">
                    <h5>Application Form</h5>
                </div>
                
				
				 <input type="hidden" theme="simple" name="jobDetails.userID" value="<s:property value="userID"/>">
                <input type="hidden" theme="simple" name="jobApplicationData.jobPostID" value="<s:property value="jobID"/>">
                <div class="col-12">
                    <div class="form-group row company-div">
                        <label for="position" class="col-sm-2 col-form-label">Position</label>
                        <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext" id="position" value="<s:property value="position"/>">
                        </div>
                    </div>
                </div>
                <div class="col-12">
                    <div class="form-group row company-div">
                        <label for="position" class="col-sm-2 col-form-label">Company</label>
                        <div class="col-sm-10">
                          <input type="text" readonly class="form-control-plaintext" id="company" value="<s:property value="companyName"/>">
                        </div>
                    </div>
                </div>

                <div class="col-12">
                    <div class="form-group row">
                        <label for="position" class="col-sm-2 col-form-label">Phone No.*</label>
                        <div class="col-sm-10 phone-input">
                        	<p>+</p>
                        	<s:select theme="simple" data-id="0" id="phoneCountryCode"
									class="form-control form-control-sm form-input-select" list="%{countries}"
									listValue="%{phoneCode}"  listKey="phoneCode"
									name="jobApplicationData.phoneCode" value="62" data-category="skill"
									data-default="0" data-name="phoneCode"/>
                          <input type="text" class="form-control form-input-text" id="phoneNo" name="jobApplicationData.phoneNo" data-name="phoneNo">
                        </div>
                    </div>
                </div>
                <div class="col-12">
                    <div class="form-group row">
                        <label for="about" class="col-sm-12 col-form-label">Tell something about yourself*</label>
                        <textarea class="form-control form-input-textarea" id="about" rows="3" name="jobApplicationData.aboutApplicant" placeholder="max 2000 chars" data-name="aboutApplicant"></textarea>
                    	<div class="invalid-feedback"></div>
                    </div>
                </div>
				<s:if test="jobQuestions.isEmpty()==false">
                <div class="col-12 questions">
                    <div class="questions-title"><p>Questions*</p></div>
                    <s:set var="count" value="0"/>
                    <s:iterator value="jobQuestions">
                    
                    
                    <div class="form-group row newItem">
                        <label for="<s:property value='jobQuestionID'/>"  class="col-sm-12 col-form-label question"><s:property value="question"/></label>
                        
                        <input type="hidden" theme="simple" name="jobApplicationData.jobAnswers[<s:property value='#count'/>].jobQuestionID" value="<s:property value='jobQuestionID'/>">
                        <textarea class="form-control form-input-textarea" id="questionID" rows="3" data-id="<s:property value='jobQuestionID'/>" name="jobApplicationData.jobAnswers[<s:property value='#count'/>].answer" data-name="answer"></textarea>
                    	<div class="invalid-feedback"></div>
                    	<s:set var="counter" value="%{#count+1}"/>
						<s:set var="count" value="%{#counter}"/>
                    </div>
                    
                    </s:iterator>

                </div>
				</s:if>
                <div class="col-12">
                    <div class="custom-file">
                          <input type="file" class="custom-file-input" id="customFile" lang="es" name="applicationAttachment" accept="application/pdf">
                          <label class="custom-file-label" data-browse="Attach File" for="applicationAttachment" >pdf (max size 2MB)</label>
                    </div>
                </div>
                </div>
                <s:submit action="http://localhost:8080/ID0420FF19OWidya/job/apply/submit.action" class="btn btn-info col-3 submitBtn"></s:submit>
                </s:form>
            
            
            
        <!--     <div class="col-12 submitDiv">
                <a class="btn btn-success col-12 submitBtn">Submit</a>
            </div> -->
        
        </div>
        
     </div>	 
  

   <div class="content-right d-none d-sm-none d-md-none d-lg-block col-lg-6 position-fixed row">
       <div class="jobDetailsDiv col-12 row">
            <div class="col-12 position" class="">
                <p id="position" ><s:property value="position"/></p>                
            </div>
            <div class="col-12 company" >
                <p id="companyName" ><s:property value="companyName"/></p>
            </div>
            <div class="col-12 location">
                <p id="location" ><i class="fas fa-map-marker-alt"></i><s:property value="country.countryName"/>, <s:property value="city.cityName"/></p>
            </div>
            <div class="col-12 row date-place-hours-deadline">
                <div class="col-12 col-md-4">
                    <p id="postedDate"><s:property value="createdDate"/></p>
                </div>
                <div class="col-12 col-md-4 hours-place row">
                    <div><i class="fas fa-street-view"></i><p id="place"><s:property value="place"/></p></div>
                    <div><i class="fas fa-clock"></i><p id="hours"><s:property value="hours"/></p></div>
                </div>
                <div class="col-12 col-md-4">
                   <i class="far fa-calendar-alt"></i><p id="deadline">Due <s:property value="deadlineSubmission"/></p>
                </div>
            </div>
            <div class="col-12 row">
                <div class="col-12">
                    <p id="subdetail-header">Company Information</p>
                </div>
                <div class="col-12">
                    <p id="companyInfo"><s:property value="companyInfo" /></p>
                </div>
            </div>
            <div class="col-12 row">
                <div class="col-12">
                    <p id="subdetail-header">Job Description</p>
                </div>
                <div class="col-12">
                    <p id="jobDesc"><s:property value="jobDescription"/></p>
                </div>
            </div>
            <div class="col-12 row">
                <div class="col-12">
                    <p id="subdetail-header">Job Requirement</p>
                </div>
                <div class="col-12">
                    <p id="jobRequirement"><s:property value="jobRequirements"/></p>
                </div>
            </div>
            <div class="col-12 row">
                <div class="col-12">
                    <p id="subdetail-header">Required Skill</p>
                </div>
                <div class="col-12">
                    <p id="jobSkill"><s:property value="requiredSkill"/></p>
                </div>
            </div>
            <div class="col-12 row">
                <div class="col-12">
                    <p id="subdetail-header">Notes</p>
                </div>
                <div class="col-12">
                    <p id="jobNotes"><s:property value="notes"/></p>
                </div>
            </div>
        </div>      
     </div>


</div>
</s:push>
<s:include value="footer.jsp"></s:include>


<div class="alert col-11 col-md-5  error alert-warning" role="alert">
  Server error. please refresh
</div>





    <script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="/ID0420FF19OWidya/script/navScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/feedbackScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/regexValidate.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/applyJobScript.js" type="text/javascript"></script>
</body>
</html>