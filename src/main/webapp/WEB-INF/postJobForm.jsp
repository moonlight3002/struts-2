<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hire now</title>
<link rel="icon" type="image/png" href="/ID0420FF19OWidya/assets/icon.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<link href='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/datepickerStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/navSearchResultStyle.css">
    <link rel = "stylesheet" href="/ID0420FF19OWidya/css/navStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/jobNavStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/feedbackStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/hireFormStyle.css">

<style>
.newItem{
	display:none;
}
</style>
</head>
<body>

    <s:include value="navbar.jsp"></s:include>
   <s:include value="jobNavbar.jsp"></s:include>

<div class="content-wrapper row">
	<s:form class="hireForm col-12" id="submitForm" action="posting.action" theme="simple">
        <div class="hireFormDiv row">
            <div class="form-Upper col-12 row">
                <div class="form-left col-12 col-md-6 ">
                    <div class="row">
                        <div class="section-1 col-12 row">
                            <div class="col-12 row form-item">
                              <label for="companyName" class="col-4 form-label">Company*</label>
                              <s:textfield theme="simple" class="col-8 form-control form-input-text" id="companyName" name="companyName" data-name="companyName"></s:textfield>
                            	<div class="invalid-feedback"></div>
                            </div>

                            <div class="col-12 row form-item">
                                <label for="location" class="col-12 col-md-4 form-label">Company Location:*</label>
                                <select id="country" class="form-control form-input-select col-5 col-md-3" name="country.countryID" data-name="countryID">
                                    <option value="-1" selected disabled>Country</option>
                                </select>

                                <select id="city" class="form-control form-input-select col-5 col-md-3" name="city.cityID" data-name="cityID">
                                    <option value="-1" selected disabled>City</option>
                                </select>
                            </div>

                            <div class="col-12 row form-item">
                                <label for="companyInfo" class="col-12 form-label">Company Information*</label>
								<s:textarea theme="simple" class="form-control form-input-textarea" id="companyInfo" rows="3" name="companyInfo" data-name="companyInfo"></s:textarea>
								<div class="invalid-feedback"></div>
                            </div>
                        </div>
                    
                    </div>
                    
                    
                    <div class="section-2 col-12 row">
                        <div class="col-12 row form-item">
                          <label for="position" class="col-4 form-label">Position:*</label>
                          <s:textfield theme="simple" class="col-8 form-control form-input-text" id="position" name="position" data-name="position"></s:textfield>
                          <div class="invalid-feedback">aa</div>
                        </div>

                        <div class="col-12 row form-item">
                            <label for="location" class="col-4 form-label">Working Place:*</label>
                            <div class="form-check col-4">
                                <input class="form-check-input form-input-radio" type="radio" name="place" id="remote" value="remote" checked>
                                <label class="form-check-label form-label" for="remote">
                                    Remote
                                </label>
                            </div>
                            <div class="form-check col-4">
                                  <input class="form-check-input form-input-radio" type="radio" name="place" id="onsite" value="onsite">
                                  <label class="form-check-label form-label" for="onsite">
                                    Onsite
                                  </label>
                            </div>
                        </div>
                        
                        <div class="col-12 row form-item">
                            <label for="hours" class="col-4 form-label">Working Hours:*</label>
                            <div class="form-check col-4">
                                  <input class="form-check-input form-input-radio" type="radio" name="hours" id="fulltime" value="fulltime" checked>
                                  <label class="form-check-label form-label" for="fulltime">
                                    Full Time
                                  </label>
                            </div>
                            <div class="form-check col-4">
                                  <input class="form-check-input form-input-radio" type="radio" name="hours" id="parttime" value="parttime">
                                  <label class="form-check-label form-label" for="parttime">
                                    Part Time
                                  </label>
                            </div>

                        </div>

                        <div class="col-12 row form-item">
                            <label for="jobDesc" class="col-12 form-label">Job Description:*</label>
                             <s:textarea theme="simple" class="form-control form-input-textarea" id="jobDesc" rows="3" name="jobDescription" data-name="jobDescription"></s:textarea>
                             <div class="invalid-feedback"></div>
                        </div>
                        
                        <div class="col-12 row form-item">
                            <label for="skill" class="col-12 form-label">Minimum Skill required </label>
                            
                            <s:select theme="simple" data-id="0"
									headerKey="-1" headerValue="choose one or more"
									class="form-control form-input-multiple" data-style="btn-link" id="skill" list="technologies"
									listValue="technologyName" listKey="technologyID"
									name="jobSkills.technologyID" data-category="skill"
									data-default="0" multiple="true"/> 
                        </div>
                        
                        <div class="col-12 row form-item">
                                <label for="jobRequirement" class="col-12 form-label">Requirement:*</label>
                                <s:textarea theme="simple" class="form-control form-input-textarea" id="jobRequirement" rows="3" name="jobRequirements" data-name="jobRequirements"></s:textarea> 
                                <div class="invalid-feedback"></div>
                        </div>
                    </div>
                </div>
                <div class="form-right col-12 col-md-6">
                    <div class="row">
                        <div class="section-3 col-12 row">
                            

                            <div class="col-12 row form-item">
                              <label for="deadline" class="col-4 form-label">Submission Deadline*</label>
                              <s:textfield theme="simple" class="col-8 form-control dateTimeInput datepickerInput form-input-text" id="deadline" autocomplete="off" name="deadlineSubmission" data-name="deadlineSubmission"></s:textfield>
                            	<div class="invalid-feedback"></div>
                            </div>

                            <div class="col-12 row form-item">
                                <label for="notes" class="col-12 form-label">Additional Notes:</label>                              
                                <s:textarea theme="simple" class="form-control form-input-textarea" id="notes" rows="3" name="notes" data-name="notes"></s:textarea> 
                                <div class="invalid-feedback"></div>
                            </div>
                        </div>
                    </div>
                    
                    
                    <div class="section-4 col-12 row">
                        <div class="col-12 row form-item questionDiv-items">
                            <label for="question" class="col-12 form-label form-label-question">Questions for Candidates:<button type="button" class="btn btn-light addQuestionBtn"><i class="fas fa-plus"></i></button></label>
                            
                            <s:iterator value="jobQuestions">
                            
                            
                            <div class="questionDiv-item col-12 row" data-id=0>
                                <button type="button"  class="btn deleteQuestionBtn col-1" data-id=0><i class="fas fa-times" data-id=0></i></button>
                                <div class="col-10">
	                                <s:textarea theme="simple" class="form-control form-input-question form-input-textarea" id="question" rows="3" name="question" data-name="question"></s:textarea>                            
	                            	<div class="invalid-feedback"></div>
                            	</div>
                            </div>
                            </s:iterator>
                            
                        </div>
                        
                        
                        
                    </div>
                </div>
            </div>
            <div class="form-Bottom col-12 ">
                <input type="submit" class="submitjobpostBtn" value="Publish">
            </div>
            
        </div>
        
    </s:form>

</div>

<s:include value="footer.jsp"></s:include>


<div class="alert col-11 col-md-5  error alert-warning" role="alert">
  Server error. please refresh
</div>


<div class="questionDiv-item col-12 newItem row" data-id>
    <button type="button" class="btn deleteQuestionBtn col-1"><i class="fas fa-times" data-id></i></button>
    <div class="col-10">
	    <s:textarea theme="simple" class="form-control form-input-question form-input-textarea" id="question" rows="3" name="" data-name="question"></s:textarea> 
		<div class="invalid-feedback"></div>	
	</div>		
</div>


    <script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js' type='text/javascript'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>

<script>

	$('.hire').addClass('active');
    $(".datepickerInput").datepicker({
        "startDate" : new Date(),
        "endDate" : '+1m',
        "format" : 'dd/mm/yyyy',
    });
    
    $('.form-input-multiple').selectpicker();

</script>
<script src="/ID0420FF19OWidya/script/navScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/feedbackScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/getJobScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/regexValidate.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/hireFormScript.js" type="text/javascript"></script>
</body>
</html>