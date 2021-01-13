<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><s:property value="%{#session.userData.firstName} %{#session.userData.lastName}"/></title>
<link rel="icon" type="image/png" href="/ID0420FF19OWidya/assets/icon.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">

    <link rel="stylesheet" href="/ID0420FF19OWidya/css/navSearchResultStyle.css">
    <link rel = "stylesheet" href="/ID0420FF19OWidya/css/navStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/feedbackStyle.css">
    
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/profileHeaderStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/followStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/profileStyle.css">

</head>
<body>
 	<s:include value="navbar.jsp"></s:include> 
	<div class="content-wrapper row d-flex justify-content-center">
	<s:push value = "user">
    <div class="contentBar col-12 col-md-11 col-lg-10 row d-flex justify-content-center">
        <div class="profile-Header col-12">
            <div class="profile-Header-Top">
            <s:url action='getFile?userID=%{userID}&headerPic=%{headerPic}' var="HeaderPic" />
                <div class="profile-Header-User-headerPicture" style="background-image: url(<s:property value='#HeaderPic'/>), url(/ID0420FF19OWidya/assets/defaulthp_.jpg);">
                    
                </div>
                <div class="profile-Header-User-profilePicture">
                            <div class="profile-profilePicture">
                            <s:url action='getFile?userID=%{userID}&profilePic=%{profilePic}' var="ProfilePic" />
                            <s:set var="nameInitial" value="%{firstName.substring(0,1).toUpperCase()}" />
                                <img src="<s:property value='#ProfilePic'/>" onerror='this.onerror=null;this.src="/ID0420FF19OWidya/assets/defaultpp_<s:property value='%{nameInitial}'/>.jpg"'/>
                                
                            </div>
                </div>
            </div>
            <div class="profile-Header-Bottom row">
                    <div class="profile-Header-User col-12">
                        
                        <div class="profile-Header-User-name-follow row">
                            <div class="profile-Header-User-name col-12" id="<s:property value="userID"/>"><p><s:property value="firstName"/> <s:property value="lastName"/></p></div>
                        	<div class="profile-Header-User-follow col-12 follow-status">
                        	<s:set value="isFollowing" var="isFollow"/>
	                        	<a data-status="following" class="follow-status" style="display:<s:if test='alreadyFollowing'>flex</s:if><s:else>none</s:else>"><i class="fas fa-check-circle"></i><p>Following</p>
	                        	<div class="unfollow followBtn" data-action="unfollow"><p><i class="fas fa-times-circle">unfollow</i></p></div>
	                                </a>
                                <button data-status="not-following" type="button" class="follow-status followBtn btn btn-primary"  data-action="follow" style="display:<s:if test='alreadyFollowing'>none</s:if><s:else>block</s:else>">+Follow</button>
                                
                            </div>
                        </div>

                    </div>
                    <div class="profile-Header-Menu col-12 row">
                        <div class="about menuDiv col-4">
                            <a id="aboutme" class="active">About</a>
                        </div>
                        <div class="follower menuDiv col-4">
                            <a id="follower">Followers (<s:property value="followerCount"/>)</a>
                        </div>
                        <div class="following menuDiv col-4">
                            <a id="following">Following (<s:property value="followingCount"/>)</a>
                        </div>
                    </div>
                
            </div>
        </div>

        <div class="profile-content Aboutme col-12 row active">
            <div class="profile-content-right col-12 col-md-5 d-flex align-top">
                <div class="profile-category about-me row">
                    <div class="profile-category-header col-12">About me
                    </div>
                    <div class="profile-category-item  col-12 row">
                        <div class="profile-category-content profile-dob col-12">
                            <div class="profile-category-icon"><i class="fas fa-birthday-cake"></i>
                            </div>
                            <div class="profile-category-data" id="dob">
                                <p><s:property value="dob"/></p>
                            </div>

                        </div>
                    </div>
                    <div class="profile-category-item  col-12 row">
                        <div class="profile-category-content profile-bio col-12">
                            <div class="profile-category-icon"><i class="fas fa-book"></i>
                            </div>
                            <div class="profile-category-data" id="bio">
                            	<p><s:property value="bio"/></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="profile-category skill row">
                    <div class="profile-category-header col-12">Skill</div>
                    
                    <s:iterator value="skills">
                    
                    <div class="profile-category-item col-12 row">
                        <div class="profile-category-content profile-skill col-12">
                            <div class="profile-category-icon"><i class="fas fa-puzzle-piece"></i>
                            </div>
                            <div class="profile-category-data">
                            <p><s:property value="technologyName"/><s:if test="skillLevel!='not specified'"> - <s:property value="skillLevel"/></s:if></p>
                            </div>
                            
                        </div>
                    </div>
                    </s:iterator>

                </div>
                <div class="profile-category connnection ">
                    <div class="profile-category-header col-12">Connect me</a></div>
                    <div class="profile-category-item col-12 row">
                        <div class="profile-category-content profile-connection col-12 row d-flex justify-content-center">
                            <div class="profile-connection-link col-2">
                            <a href="www.facebook.com/<s:property value="facebook"/>" target="_blank" class="facebook <s:if test="facebook==null">disable</s:if>"><i class="fab fa-facebook-square"></i></a>
                            
                            </div>
                            <div class="profile-connection-link  col-2"><a href="https://www.facebook.com/<s:property value="twitter"/>" class="twitter <s:if test="twitter==null">disable</s:if>" target="_blank" class="twitter"><i class="fab fa-twitter"></i></a></div>
                            <div class="profile-connection-link col-2"><a href="https://www.instagram.com/<s:property value="instagram"/>" class="instagram <s:if test="instagram==null">disable</s:if> target="_blank" class="instagram"><i class="fab fa-instagram"></i></a></div>
                            <div class="profile-connection-link youtube col-2"><a href="https://www.youtube.com/<s:property value="youtube"/>" class="youtube <s:if test="youtube==null">disable</s:if> target="_blank" class="youtube"><i class="fab fa-youtube"></i></a></div>
                            <div class="profile-connection-link  col-2"><a href="https://www.github.com/<s:property value="github"/>" class="github <s:if test="github==null">disable</s:if> target="_blank" class="github"><i class="fab fa-github"></i></a></div>
                        </div>
                        
                    </div>
                    
                </div>
            </div> 
            
            <div class="profile-content-left col-12 col-md-6">
                <div class="profile-category experience row">
                    <div class="profile-category-header col-12">Work Experience</i></a></div>
                    <s:iterator value="experiences">
                    <div class="profile-category-item col-12 row">
                        <div class="profile-category-content col-12">
                            <div class="profile-category-icon"><i class="fas fa-briefcase"></i>
                            </div>
                            <div class="profile-category-data">
                                <div class="profile-experience-position "><p><s:property value="position"/></p>
                                </div>
                                <div class="profile-experience-company "><p><s:property value="company"/></p></div>
                                <div class="profile-experience-workPeriod "><a class="month startMonth"><s:property
																		value="startMonth" /></a> <a class="startYear"><s:property
																		value="startYear" /></a> - <a class="month endMonth"/><s:property
																		value="endMonth" /></a> <a class="endYear"><s:property
																		value="endYear" /></a></div>
                                <div class="profile-experience-description "><p><s:property value="experienceDesc"/></p></div>
                            </div>
                        </div>
                    </div>
                    </s:iterator>
                </div>
                <div class="profile-category educations row">
                    <div class="profile-category-header col-12">Education</div>
                   
                   <s:iterator value="educations">
                    <div class="profile-category-item col-12 row">
                        <div class="profile-category-content col-12">
                            <div class="profile-category-icon"><i class="fas fa-graduation-cap"></i>
                            </div>
                            <div class="profile-category-data">
                                <div class="profile-education-school"><p><s:property value="school"/></p>
                                </div>
                                <div class="profile-education-degree"><p><s:property value="degree"/></p></div>
                                <div class="profile-education-studyPeriod "><a class="startYear"><s:property
																		value="startYear" /></a> - <a class="endYear"><s:property
																		value="endYear" /></a></div>
                                <div class="profile-education-description "><p><s:property value="educationDesc"/></p></div>
                            </div>
                        </div>
                    </div>
                    </s:iterator>
                </div>
                
            </div> 
            
            
            
            
        </div>

        
        <div class="profile-content Follow Follower col-12 row ">
            <div class="profile-follow-upper col-12">
                <form class="form-inline">
                    <input class="Follower follow-search form-control mr-sm-2" type="search" placeholder="Search Follower" data-action="Follower" aria-label="Search">
                </form>
            </div>
            <div class="profile-follow-bottom col-12 row">
                <div class="Follower follow-items col-12 row">
                    <div class="follow-item col-6 col-md-3 col-lg-2 newItem">
                        <a href="">
                            <div class="follow-item-top">
                                <img src="img/icon.png"/>
                            </div>
                            <div class="follow-item-bottom">
                                <div class="follow-name"></div>
                                <div class="follow-follower"></div>
                            </div>
                        </a>
                    </div>
                </div>
                
            </div>
            <div class="follower seemore" data-action="Follower">
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
             
             <p class="nomore" data-action="Follower">
                    No more item to load
             </p>
        </div>
        <div class="profile-content Follow Following col-12 row">
            <div class="profile-follow-upper col-12">
                <form class="form-inline">
                    <input class="Following follow-search form-control mr-sm-2" type="search" placeholder="Search Following" data-action="Following" aria-label="Search">
                </form>
            </div>
            <div class="profile-follow-bottom col-12 row">
                <div class="Following follow-items col-12 row">
                    <div class="follow-item col-6 col-md-3 col-lg-2 newItem">
                        <a href="">
                            <div class="follow-item-top">
                                <img src="img/icon.png"/>
                            </div>
                            <div class="follow-item-bottom">
                                <div class="follow-name"></div>
                                <div class="follow-follower"></div>
                            </div>
                        </a>
                    </div>
                </div>
                
            </div>
            <div class="following seemore" data-action="Following">
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
             <p class="nomore" data-action="Following">
                    No more item to load
             </p>
        </div>
    </div>
        </s:push>
	

  <s:include value="footer.jsp"></s:include>


<div class="alert col-11 col-md-5  error alert-warning" role="alert">
  Server error. please refresh
</div>
</div>

      <!-- Modal -->
<div class="modal fade" id="uploadProfilePic" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
      <s:form id="uploadForm" name="uploadForm" data-action="uploadProfilePicture" enctype="multipart/form-data" action="uploadProfilePicture.action" theme="simple">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Upload Profile Picture</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body row">
          <div class="custom-file">
              <input type="file" class="custom-file-input" id="customFile"  name="picture" theme="simple"/>
              <label class="custom-file-label" for="customFile">Choose file</label>
            </div>
      </div>
      <div class="modal-footer">
        <input type="submit" class="uploadBtn btn btn-primary" value="Upload" data-action="uploadProfilePicture">
      </div>
    </div>
    </s:form>
  </div>
</div>
    
    
<div class="modal fade" id="uploadHeaderPic" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
      <s:form id="uploadForm"  name="uploadForm" data-action="uploadHeaderPicture" enctype="multipart/form-data" action="uploadHeaderPicture.action" theme="simple">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Upload Header Picture</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body row">
          <div class="custom-file">
              <input type="file" class="custom-file-input" id="customFile"  name="picture" multiple type="file">
              <label class="custom-file-label" for="customFile">Choose file</label>
            </div>
      </div>
      <div class="modal-footer">
        <input type="submit" class="uploadBtn btn btn-primary" value="Upload" data-action="uploadHeaderPicture">
      </div>
    </div>
    </s:form>
  </div>
</div> 
</div>
    
    



    <script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script>

var userProfileID = <s:property value="user.userID"/>

</script>
<script src="/ID0420FF19OWidya/script/navScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/feedbackScript.js" type="text/javascript"></script>

<script src="/ID0420FF19OWidya/script/profileScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/publicProfileScript.js" type="text/javascript"></script>
</body>
</html>