<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="row" id="upperCollapse">
                    <a class="collapseHead col-6" data-toggle="collapse" data-target="#collapse1" aria-expanded="false" aria-controls="collapse1" >Manage</a>
                    <a class="collapseHead col-6" data-toggle="collapse" data-target="#collapse2" aria-expanded="false" aria-controls="collapse2" >Setting</a>
                    <!-- <a class="collapseHead col-4" data-toggle="collapse" data-target="#collapse3" aria-expanded="false" aria-controls="collapse3" >Report</a> -->

                    <div class="col-12 accordion-group upperCollapse-content">
                        <div class="collapse" id="collapse1" data-parent="#upperCollapse">
                          <div class="card card-body">
                              <a href="http://localhost:8080/ID0420FF19OWidya/admin" class="collapseLink">User</a>
              <!--                 <a class="collapseLink">Job Post</a> -->
                              <a href="http://localhost:8080/ID0420FF19OWidya/admin/feedback" class="collapseLink">Feedback</a>
                          </div>
                        </div>

                        <div class="collapse" id="collapse2" data-parent="#upperCollapse">
                          <div class="card card-body">
                              <a class="collapseLink">Technology</a>
                          </div>
                        </div>

                 <!--        <div class="collapse" id="collapse3" data-parent="#upperCollapse">
                          <div class="card card-body">
                            <a class="collapseLink" href="http://localhost:8080/ID0420FF19OWidya/admin/technology">Chart</a>
                          </div>
                        </div> -->

                    </div>
                </div>