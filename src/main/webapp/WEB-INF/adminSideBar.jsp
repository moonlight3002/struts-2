<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="sideBar-left d-none d-md-block col-md-3 col-lg-2 position-fixed row">
        <div class="accordion" id="accordionExample">
          <div class="card">
            <div class="card-header" id="headingOne">
              <a class="collapseHead" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">Manage</a>
            </div>

            <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
              <div class="card-body">
                <a href="http://localhost:8080/ID0420FF19OWidya/admin"class="collapseLink">User</a>
     <!--              <a class="collapseLink">Job Post</a> -->
                  <a href="http://localhost:8080/ID0420FF19OWidya/admin/feedback" class="collapseLink">Feedback</a>
              </div>
            </div>
          </div>
          <div class="card">
            <div class="card-header" id="headingTwo">
              <a class="collapseHead" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">Setting</a>
            </div>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
              <div class="card-body">
                <a class="collapseLink" href="http://localhost:8080/ID0420FF19OWidya/admin/technology">Technology</a>
              </div>
            </div>
          </div>
<!--           <div class="card">
            <div class="card-header" id="headingThree">
              <a class="collapseHead" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">Report</a>
            </div>
            <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
              <div class="card-body">
                <a class="collapseLink">Chart</a>
              </div>
            </div>
          </div> -->
        </div>
    </div>