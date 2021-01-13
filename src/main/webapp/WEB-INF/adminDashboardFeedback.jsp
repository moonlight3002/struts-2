<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
<link rel="icon" type="image/png" href="/ID0420FF19OWidya/assets/icon.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">

    <link rel="stylesheet" href="/ID0420FF19OWidya/css/navSearchResultStyle.css">
    <link rel = "stylesheet" href="/ID0420FF19OWidya/css/navStyle.css">
    <link rel="stylesheet" href="/ID0420FF19OWidya/css/adminDashboardStyle.css">
</head>
<body>
	<s:include value="navbar.jsp"></s:include>
	<div class="content-wrapper row">
    <s:include value="adminSideBar.jsp"></s:include>
    <div class="contentBar col-12">
        <div class="row">
            <div class="sideBar d-none d-md-block d-xl-block col-md-3 col-lg-3">
            </div>
            
            <div class="sideBarFilter row col-2 d-none d-lg-block d-xl-block">
                    <div class="col-12 sideBarFilterContent"> 
                        <div class="filter-Title"><h5>Filter</h5>
                            <div class="filter-Close d-block d-lg-none d-xl-none"><i class="fas fa-times-circle"></i></div></div>
                        <div class="filter-Content">
                        	<div class="form-group row">
                                <label for="fromID" class="col-12 col-form-label filterLabel">Category</label>
                                <div class="col-12 filterUserID row">
                                    <select class="custom-select filterInputSelect" id="category" name="category">
                                    <option value="suggestion" selected>Suggestion</option>
                                    <option value="report">Report</option>
                                    <option value="others">Others</option>
                                    <option value="all">All</option>
                                  </select>
                                </div>
                            </div>
                            
                            <div class="form-group row">
                                <label for="fromID" class="col-12 col-form-label filterLabel">Feedback ID</label>
                                <div class="col-12 filterInput row">
                                    <input type="text" class="form-control col-4 filterInputText" name="fromID" id="fromID" placeholder="">
                                    <div class="col-1">-</div>
                                    <input type="text" class="form-control filterInputText col-4" name="toID" id="toID" placeholder="">
                                </div>
                            </div>
                            
                            <div class="form-group row">
                                <label for="fromUserID" class="col-12 col-form-label filterLabel">User ID</label>
                                <div class="col-12 filterInput row">
                                    <input type="text" class="form-control col-4 filterInputText" name="fromUserID" id="fromUserID" placeholder="">
                                    <div class="col-1">-</div>
                                    <input type="text" class="form-control filterInputText col-4" name="toUserID" id="toUserID" placeholder="">
                                </div>
                            </div>
                            
                            <div class="form-group row">
                                <label for="fromID" class="col-12 col-form-label filterLabel">Created Date</label>
                                <div class="col-12 filterInput row">
                                    <input type="text" class="form-control col-12 filterInputText" name="fromDate" id="fromDate" placeholder="from Date">
                                    
                                    <input type="text" class="form-control filterInputText col-12" name="toDate" id="toDate" placeholder="to Date">
                                </div>
                            </div>
                            
                            <div class="form-group row">
                                <label for="fromID" class="col-12 col-form-label filterLabel">Status</label>
                                <div class="col-12 filterUserID row">
                                    <select class="custom-select filterInputSelect" id="solved" name="solved">
                                    <option value="0" selected>Unsolved</option>
                                    <option value="1">Solved</option>
                                    <option value="2">All</option>
                                  </select>
                                </div>
                            </div>
                            
                            <div class="form-group row">
                                <label for="fromID" class="col-12 col-form-label filterLabel">Seen</label>
                                <div class="col-12 filterUserID row">
                                    <select class="custom-select filterInputSelect" id="read" name="read">
                                    <option value="0">Unseen</option>
                                    <option value="1">Seen</option>
                                    <option value="2" selected>All</option>
                                  </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <button class="btn btn-light filterBtn col-12"><i class="fas fa-angle-double-right"></i></button>
                            </div>
                            
                        </div>
                    </div>
            </div>
            <div class="upperBar d-block col-12 d-md-none d-xl-none">
                <s:include value="adminUpperBar.jsp"></s:include>
            </div>
            <div class="content col row">
                
                <div class="content-head-wrapper col-12 row">
                    <div class="content-adminHead col-12 row">
                        <div class="col-12 row content-adminHead-Title">
                            <div class="filterToggleDiv"><i class="fas fa-filter col-1 d-lg-none"></i></div>
                            <h3 class="col-9 col-sm-2">Feedback</h3>
                            <div class="col-12 col-sm-12 content-userHead-Button row">
                                <button class="btn btn-info adminBtn deleteFeedbackBtn"><i class="fas fa-trash-alt"></i> Delete Feedback</button>
                            </div>
                        </div>
                    </div>
                    <table class="tableHead">
                      <thead class="thead">
                        <tr>
                            <th style="width:5%"></th>
                            <th style="width:5%">Feedback ID</th>
                            <th style="width:5%">User ID</th>
                            <th style="width:10%">Category</th>
                            <th style="width:10%">Sent Date</th>
                            <th style="width:5%">Status</th>
                            <th style="width:10%"></th>
                        </tr>
                      </thead>
                    </table>
                </div>


                <div class="content-user col-12 row">
                    <table class="tableContent">
                      <thead>
                        <tr>
                            <th style="width:5%"></th>
                            <th style="width:5%"></th>
                            <th style="width:5%"></th>
                            <th style="width:10%"></th>
                            <th style="width:10%"></th>
                            <th style="width:5%"></th>
                            <th style="width:10%"></th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr data-id="" class="newItem dataItem">
                            <td class="checkboxInputTable">
                                <input type="checkbox"  id="feedbackSelect" data-id="" name="feedbackSelect">
                            </td>
                            <td class="feedbackID">12</td>
                            <td><a class="userID" href="/username" target="_blank">12</a></td>
                            <td class="category">Feedback</td>
                            <td class="sentDate">12/09/2020 12:13:15</td>
                            <td class="status">Solved</td>
                            <td><a href="" class="viewFeedback" data-id="" data-toggle="modal" data-target="#viewFeedback"><i class="fas fa-envelope-open-text">View Feedback</i></a>
                            </td>
                        </tr>
                        
                      </tbody>
                    </table>
                    
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

            
        </div>
    </div>
    <div class="alert col-11 col-md-5  error alert-warning" role="alert">
			Server error. please refresh</div>

		<div class="alert col-11 col-md-5  updateSuccess alert-success"
			role="alert">Update success</div>
			
		<div class="alert col-11 col-md-5  deleteSuccess alert-info"
			role="alert">Delete Success</div>
		<div class="alert col-11 col-md-5  noneSelected alert-warning"
			role="alert">Please select one or more item</div>

</div>
    <!-- Modal -->
    
   


<div class="modal fade" id="viewFeedback" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-md">
      <form>
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body adminModal row">
          
        <div class="col-12">
          <div><p class="sendDate">Date : <b></b></p></div>
          <div><p class="category">Status : <b></b></p></div>
          <div><p>Message :</p></div>
          <div><p class="message"></p></div>
        </div>
          
          
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info adminModalBtn setSolvedBtnExecute" data-id="">Solved?</button>
      </div>
    </div>
    </form>
  </div>
</div>  


<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script src="/ID0420FF19OWidya/script/navScript.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/adminDashboardFeedbackScript.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
</body>
</html>