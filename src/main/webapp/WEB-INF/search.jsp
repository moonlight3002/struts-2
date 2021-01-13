<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search</title>
<link rel="icon" type="image/png" href="/ID0420FF19OWidya/assets/icon.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">

    <link rel="stylesheet" href="/ID0420FF19OWidya/css/navSearchResultStyle.css">
    <link rel = "stylesheet" href="/ID0420FF19OWidya/css/navStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/searchPageStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/feedbackStyle.css">
</head>
<body>
    <s:include value="navbar.jsp"></s:include>
    
	<div class="content-wrapper row">
    <div class="sideBar-left d-sm-none d-none d-md-block col-md-3 col-lg-2 position-fixed row">
        <div class="col-12 filter-Title"><h6>Filter</h6><div class="closeFilterBtn d-md-none"><i class="fas fa-times-circle"></i></div></div>
        <div class="col-12 user-skill-filter">
            <p class="filter-skill-title">Skill</p>
            <div class="filter-skill-checkboxes">
            <s:iterator value="technologyLists">
            
	            <div class="custom-control custom-checkbox filter-skill-checkbox-div">
	              <input type="checkbox" class="custom-control-input skill-filter-item-input "  data-category="filterSkill" id="<s:property value='technologyName'/>" data-id="<s:property value='technologyID'/>">
	              <label class="custom-control-label skill-filter-item-label" for="<s:property value='technologyName'/>"><s:property value='technologyName'/></label>
	            </div>
            </s:iterator>
            </div>
        </div>
        
    </div>
    <div class="contentBar col-lg-12 row d-flex justify-content-between">
        <div class="sideBar d-sm-none d-none d-md-block col-md-3 col-lg-2">
        </div>
        <div class="content col-md-9 col-lg-6 row">
            <div class="content-head-wrapper col-12 row">
                <div class="search-Title">
                    <div class="filterBtn d-md-none d.none"><i class="fas fa-filter"></i></div>
                    <h5>People</h5>
                
                </div>
            </div>
            
            <div class="content-search-user col-12 row">
              
                <a class="content-search-user-item newItem col-12 col-md-12 row" href="">
                    <div class="search-user-right col-3">
                        <div class="search-user-picture-div">
                            <img class="search-user-picture" id="profilePic" src=""/>
                        </div>
                    </div>
                    <div class="search-user-left row col-9">
                        <div class="search-user-fullname col-12" id="fullName">
                            <p></p>
                        </div>
                        <div class="search-user-bio col-12" id="bio">
                            <p></p>
                        </div>
                    </div>
                </a>
                
                <s:iterator value="userCollections">
                <a class="content-search-user-item  col-12 col-md-12 col-lg-9 row" href="<s:property value="userName"/>">
                    <div class="search-user-right col-3">
                        <div class="search-user-picture-div">
                        <s:url action='getFile?userID=%{userID}&profilePic=%{profilePic}'
									var="ProfilePic" />
                         <s:set var="nameInitial" value="firstName.substring(0,1).toUpperCase()" />
                        <img class="search-user-picture" id="profilePic" src="<s:property value='#ProfilePic'/>" onerror='this.onerror=null;this.src="/ID0420FF19OWidya/assets/defaultpp_<s:property value='%{nameInitial}'/>.jpg"'/>
                        </div>
                    </div>
                    <div class="search-user-left row col-9">
                        <div class="search-user-fullname col-12" id="fullName">
                            <p><s:property value="firstName"/> <s:property value="lastName"/></p>
                        </div>
                        <div class="search-user-bio col-12" id="bio">
                            <p><s:if test='bio!=null'><s:property value="bio"/></s:if></p>
                        </div>
                    </div>
                </a>
                </s:iterator>
                
                <div class="content-search-user seemore active col-12">
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
				<p class="nomore col-12">
				No more item to load
				</p>
            
        </div>
        </div>
        	<div class="sideBar-right d-sm-none d-none d-lg-block row">
	            <div class="links col-12">
	                <p><a href="aboutUs.html" target="_blank" > About us </a>&nbsp;|&nbsp; 
	                <a href="privacyPolicy.html" target="_blank"> Privacy Policy </a>&nbsp;|&nbsp;
	                <a href="" data-toggle="modal" data-target="#feedbackForm"> Send Feedback </a>
	                </p>
	            </div>
        	</div>
        <div class="footerBar col-12 d-md-none d-xs-block row">
            <div class="links-below col-12">
                <p><a href="aboutUs.html" target="_blank" > About us </a> | <a href="privacyPolicy.html" target="_blank"> Privacy Policy </a> | <a href="" data-toggle="modal" data-target="#feedbackForm"> Send Feedback </a>
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
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	var defaultInputValue = "<s:property value='inputValue'/>"
</script>


<script src="/ID0420FF19OWidya/script/navScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/feedbackScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/searchPageScript.js" type="text/javascript"></script>
</body>
</html>