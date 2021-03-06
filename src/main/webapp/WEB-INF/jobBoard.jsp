<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Job Board</title>
<link rel="icon" type="image/png" href="/ID0420FF19OWidya/assets/icon.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">

    <link rel="stylesheet" href="/ID0420FF19OWidya/css/navSearchResultStyle.css">
    <link rel = "stylesheet" href="/ID0420FF19OWidya/css/navStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/jobBoardStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/jobNavStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/jobItemStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/feedbackStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/viewJobStyle.css">
</head>
<body>
    <s:include value="navbar.jsp"></s:include>
   <s:include value="jobNavbar.jsp"></s:include>

<div class="content-wrapper row">
    <div class="sideBar-left d-sm-none d-none d-md-block col-md-3 col-lg-2 position-fixed row">
        <div class="col-12 filter-Title"><h6>Filter</h6><div class="closeFilterBtn d-md-none"><i class="fas fa-times-circle"></i></div></div>
        <div class="col-12 filter-content row">
            <div class="col-12 filter-category-content">
                <div class="filter-category-content-item">
                    <div class="form-check filter-input-div">
                      <input type="text" class="form-control " id="position-company-filter" placeholder="Position, Company">
                        
                    </div>
 
                </div>
            </div>
<!--            ============================-->
            <div class="col-12 filter-category-content">
                <p class="filter-category-title">Location</p>
                <div class="filter-category-content-item">
                    <div class="form-check filter-input-div">
                      <select class="custom-select form-item Filter-select" id="country">
                          <option value="0" selected>Country</option>
                        </select>
                        
                    </div>
 
                    <div class="form-check filter-input-div">
                      <select class="custom-select Filter-select" id="city">
                          <option value="0" selected>City</option>
                        </select>
                    </div>
 
                </div>
            </div>
<!--            ============================-->
            <div class="col-12 filter-category-content">
                <p class="filter-category-title">Work Place</p>
                <div class="filter-category-content-item">
                    <div class="form-check filter-item-div">
                      <input class="form-check-input" type="radio" name="place" id="allplace" value="" checked>
                      <label class="form-check-label" for="exampleRadios1">
                        All
                      </label>
                    </div>
 
                    <div class="form-check filter-item-div">
                      <input class="form-check-input" type="radio" name="place" id="onsite" value="onsite">
                      <label class="form-check-label" for="exampleRadios1">
                        Onsite
                      </label>
                    </div>
     
 
                    <div class="form-check filter-item-div">
                      <input class="form-check-input" type="radio" name="place" id="remote" value="remote" >
                      <label class="form-check-label" for="exampleRadios1">
                        Remote
                      </label>
                    </div>
 
                </div>
            </div>
<!--            ============================-->
            <div class="col-12 filter-category-content">
                <p class="filter-category-title">Hours</p>
                <div class="filter-category-content-item">
                    <div class="form-check filter-item-div">
                      <input class="form-check-input" type="radio" name="hours" id="allhours" value="" checked>
                      <label class="form-check-label" for="exampleRadios1">
                        All
                      </label>
                    </div>
 
                    <div class="form-check filter-item-div">
                      <input class="form-check-input" type="radio" name="hours" id="fulltime" value="fulltime" >
                      <label class="form-check-label" for="exampleRadios1">
                        Full Time
                      </label>
                    </div>
     
 
                    <div class="form-check filter-item-div">
                      <input class="form-check-input" type="radio" name="hours" id="parttime" value="parttime" >
                      <label class="form-check-label" for="exampleRadios1">
                        Part Time
                      </label>
                    </div>
 
                </div>
            </div>
<!--            ============================-->
            <div class="col-12 filter-category-content">
                <p class="filter-category-title">Skill</p>
                        
                <div class="filter-category-content-item">
                
                <s:iterator value="technologyLists">
                    <div class="custom-control custom-checkbox filter-item-div">
                      <input type="checkbox" class="custom-control-input skill-filter-item-input" id="<s:property value="technologyName"/>" data-id="<s:property value="technologyID"/>">
                      <label class="custom-control-label skill-filter-item-label" for="<s:property value="technologyName"/>"><s:property value="technologyName"/></label>
                    </div>
                  </s:iterator>
                </div>
            </div>
        
<!--        =======================-->
      
        </div>
    </div>
            
           <!--        =======================--><!--        =======================--><!--        =======================--><!--        =======================--><!--        =======================--> 
          <div class="contentBar col-lg-12 row d-flex justify-content-between">
	        <div class="sideBar d-sm-none d-none d-md-block col-md-3 col-lg-2">
	        </div>
	        <div class="content col-md-9 col-lg-6 row">
	            <div class="content-head-wrapper col-12 row">
	                <div class="search-Title">
	                    <div class="filterBtn d-md-none d.none"><i class="fas fa-filter"></i></div>
	                    <h5>Job</h5>
	                
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
               
                <div class="seemore active">
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
	        <div class="sideBar-right d-sm-none d-none d-lg-block row">
		            <div class="links col-12">
		                <p><a href="http://localhost:8080/ID0420FF19OWidya/about" target="_blank" > About us </a>&nbsp;|&nbsp; 
		                <a href="http://localhost:8080/ID0420FF19OWidya/privacyPolicy" target="_blank"> Privacy Policy </a><s:if test='%{#session.userData}'>&nbsp;|&nbsp;
		                <a href="" data-toggle="modal" data-target="#feedbackForm"> Send Feedback </a></s:if>
		                <s:else></s:else>
		                </p>
		            </div>
	        </div>
    	</div>
    	
    	 <div class="sideBar-job d-sm-none d-none d-md-block col-md-5 col-lg-5 position-fixed row">
    	 	<div></div>
    	 	
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




    <script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="/ID0420FF19OWidya/script/navScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/getJobScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/feedbackScript.js" type="text/javascript"></script>

<script src="/ID0420FF19OWidya/script/jobPageScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/viewJobScript.js" type="text/javascript"></script>

<script>
	$('.jobBoard').addClass('active');
</script>


</body>
</html>