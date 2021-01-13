<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body> -->
<s:if test='%{#session.userData}'>
<nav class="navbar navbar-light row justify-content-between">
        <div class="mynav left col-md-5 col-sm-7 col-8 justify-content-start">
         <a class="navbar-brand" href="/ID0420FF19OWidya/home">
         	<img src="/ID0420FF19OWidya/assets/icon.png" width="30" height="30" alt="" loading="lazy"/>
        </a>
        <div class="mynav-search">
        <s:set value="inputValue" var="inp"/>
            <input class="form-control mynav-input-search" type="text" placeholder="Search" id="search" aria-label="Search" autocomplete="off" value="<s:if test='#inp!=null'><s:property value ='#inp'/></s:if>"/>
            <div class="mynav-search-dropdown">
                <a class="nav-link mynav-search-user" href="#">
                </a>
            </div>
        </div>
        
        </div>
       
        <div class="mynav right col-md-3 col-sm-5 col-3 d-flex justify-content-end">
            <a class="mynav-user" href="<s:property value='%{#session.userData.userName}'/>">
            	<s:url action='http://localhost:8080/ID0420FF19OWidya/getFile?userID=%{#session.userData.userID}&profilePic=%{#session.userData.profilePic}' var="urlpic" />
            	<s:set var="nameInitial" value="%{#session.userData.firstName.substring(0,1).toUpperCase()}" />
                <div class="mynav-user-img"><img src="http://localhost:8080/ID0420FF19OWidya/getFile?userID=<s:property value="%{#session.userData.userID}"/>&profilePic=<s:property value="%{#session.userData.profilePic}"/>" onerror='this.onerror=null;this.src="/ID0420FF19OWidya/assets/defaultpp_<s:property value='%{nameInitial}'/>.jpg"'/></div>
                <div class="mynav-user-name d-none d-lg-block"><s:property value='%{#session.userData.firstName}'/>	</div>
            </a>
            <a class="mynav-job d-none d-md-block" href="http://localhost:8080/ID0420FF19OWidya/job">
                <span class="mynav-link-space">
                    <span class="mynav-link-front"><i class="fas fa-briefcase"></i></i></span>
                    <span class="mynav-link-back"><i class="fas fa-briefcase"></i></i></span>
                </span>
            </a>
            <s:if test="%{#session.role.equals('admin')}">
            <a class="mynav-admin d-none d-md-block " href="admin">
                <span class="mynav-link-space">
                <span class="mynav-link-front"><i class="fas fa-user-tie"></i></span>
                    <span class="mynav-link-back"><i class="fas fa-user-tie"></i></span>
                    </span>

            </a>
			</s:if>
            <a class="mynav-logout d-none d-md-block " href="logout">
                <span class="mynav-link-space">
                <span class="mynav-link-front"><i class="fas fa-sign-out-alt"></i></span>
                    <span class="mynav-link-back"><i class="fas fa-sign-out-alt"></i></span>
                    </span>

            </a>
            
            <div class="btn-group d-block d-md-none">
              <div class="dropdown-toggle" data-toggle="dropdown" data-display="static" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-ellipsis-v"></i>
              </div>
              <div class="dropdown-menu dropdown-menu-right">
                <a class="nav-link mynav-job" href="job">Job Board</a>
                <s:if test="%{#session.role.equals('admin')}"><a class="nav-link mynav-admin" href="admin">Admin DashBoard</a></s:if>
                <a class="nav-link mynav-logout" href="logout">Logout</a>
              </div>
            </div>
        
        </div>
    </nav>
 </s:if>   
<!--     ================================================================================================== -->
    
    <s:else>
    <nav class="navbar navbar-light row justify-content-between">
        <div class="mynav left col-md-5 col-sm-7 col-8 justify-content-start">
         <a class="navbar-brand" href="/ID0420FF19OWidya/home">
         	<img src="/ID0420FF19OWidya/assets/icon.png" width="30" height="30" alt="" loading="lazy"/>
        </a>
        
        </div>
       
        <div class="mynav right col-md-3 col-sm-5 col-3 d-flex justify-content-end">
            
            <a class="mynav-login d-none d-md-block" href="http://localhost:8080/ID0420FF19OWidya/home/login">
                <span class="mynav-link-space">
                    <span class="mynav-link-front">Login</span>
                    <span class="mynav-link-back">Login</span>
                </span>
            </a>
            <a class="mynav-register d-none d-md-block " href="http://localhost:8080/ID0420FF19OWidya/home/register">
                <span class="mynav-link-space">
                <span class="mynav-link-front">Register</span>
                    <span class="mynav-link-back">Register</span>
                    </span>

            </a>
        
        </div>
    </nav>
    </s:else>
<%--    <script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
	<script src="/ID0420FF19OWidya/script/navScript.js" type="text/javascript"></script> --%>

