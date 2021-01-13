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
                                <label for="fromID" class="col-12 col-form-label filterLabel">User ID</label>
                                <div class="col-12 filterInput row">
                                    <input type="text" class="form-control col-4 filterInputText" name="fromID" id="fromID" placeholder="">
                                    <div class="col-1">-</div>
                                    <input type="text" class="form-control filterInputText col-4" name="toID" id="toID" placeholder="">
                                </div>
                            </div>
                            
                            <div class="form-group row">
                                <label for="firstName" class="col-12 col-form-label filterLabel">First Name</label>
                                <div class="col-12 filterInput row">
                                    <input type="text" class="form-control col-12 filterInputText" name="firstName" id="firstName" placeholder="">
                                </div>
                            </div>
                            
                            <div class="form-group row">
                                <label for="lastName" class="col-12 col-form-label filterLabel">Last Name</label>
                                <div class="col-12 filterInput row">
                                    <input type="text" class="form-control col-12 filterInputText" name="lastName" id="lastName" placeholder="">
                                </div>
                            </div>
                            
                            
                            <div class="form-group row">
                                <label for="email" class="col-12 col-form-label filterLabel">Email</label>
                                <div class="col-12 filterInput row">
                                    <input type="text" class="form-control col-12 filterInputText" name="email" id="email" placeholder="">
                                </div>
                            </div>
                            
                            <div class="form-group row">
                                <label for="fromID" class="col-12 col-form-label filterLabel">Member since</label>
                                <div class="col-12 filterInput row">
                                    <input type="text" class="form-control col-12 filterInputText" name="fromDate" id="fromDate" placeholder="from Date">
                                    
                                    <input type="text" class="form-control filterInputText col-12" name="toDate" id="toDate" placeholder="to Date">
                                </div>
                            </div>
                            
                            <div class="form-group row">
                                <label for="fromID" class="col-12 col-form-label filterLabel">Status</label>
                                <div class="col-12 filterUserID row">
                                    <select class="custom-select filterInputSelect" id="deleted" name="deleted">
                                    <option value="0" selected>Active</option>
                                    <option value="1">Deactivated</option>
                                    <option value="2">All</option>
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
                            <h3 class="col-9 col-sm-2">User</h3>
                            <div class="col-12 col-sm-12 content-userHead-Button row">
                                <button class="btn btn-info adminBtn sendEmailBtn" data-toggle="modal" data-target="#sendEmail"><i class="fas fa-envelope"></i> Send Email</button>
                                <button class="btn btn-info adminBtn deactivateUserBtn" data-toggle="modal" data-target="#deleteUser"><i class="fas fa-user-minus"></i> Deactivate</button>
                            </div>
                        </div>
                    </div>
                    <table class="tableHead">
                      <thead class="thead">
                        <tr>
                            <th style="width:5%"></th>
                            <th style="width:5%">User ID</th>
                            <th style="width:10%">User Name</th>
                            <th style="width:10%">Email</th>
                            <th style="width:10%">Member Since</th>
                            <th style="width:10%">Last Login</th>
                            <th style="width:5%">Status</th>
                            <th style="width:5%"></th>
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
                            <th style="width:10%"></th>
                            <th style="width:10%"></th>
                            <th style="width:10%"></th>
                            <th style="width:10%"></th>
                            <th style="width:5%"></th>
                            <th style="width:5%"></th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr data-id="" class="newItem dataItem">
                            <td class="checkboxInputTable">
                                <input type="checkbox"  id="userSelect" data-id="" name="userSelect">
                            </td>
                            <td><a class="userID" href="/username" target="_blank">12</a></td>
                            <td><input type="text" class="" id="userName" name="userName" value="Markz" data-id="" data-column="user_name" data-default="">
                            </td>
                            <td><input type="email" class="" id="email" name="email" value="mark@gmail.com" data-id="" data-column="email" data-default="">
                            </td>
                            
                            <td><input type="text" class="" readonly id="createdDate" name="createdDate" value="12/09/2020"  data-default=""></td>
                            <td><input type="text" class="" readonly id="lastLogin" name="lastLogin" value="12/09/2020 12:13:15"  data-default=""></td>
                            
                            <td class="status">Active</td>
                            <td><a href="" class="changePasswordBtn" data-id="" data-toggle="modal" data-target="#changePassword"><i class="fas fa-key"></i></a>
                            
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
			role="alert">Update Success</div>
			
		<div class="alert col-11 col-md-5  deleteSuccess alert-info"
			role="alert">Deactivated Success</div>
		<div class="alert col-11 col-md-5  emailSuccess alert-success"
			role="alert">Email Sent!</div>
			<div class="alert col-11 col-md-5  noneSelected alert-warning"
			role="alert">Please select one or more item</div>

</div>
    <!-- Modal -->
    
<div class="modal fade" id="deleteUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-md">
      <form>
    <div class="modal-content">
      <div class="modal-body adminModal row">
          <h6 class="col-12">Are You Sure you want to Deactivate User?</h6>
          <p class="col-12"><b>User ID: </b></p>
          <p class="col-12 ids"><b>12,11,13</b></p>
          
          
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info adminModalBtn deleteUserBtnExecute">Delete</button>
      </div>
    </div>
    </form>
  </div>
</div> 
    
    
<div class="modal fade" id="sendEmail" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
      <form>
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Send Email </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body adminModal row">
          <div class="email-recipient col-12 row">
              <label for="email-recipient-label" class="col-2">To: </label>
            <div class="form-check col-2">
              <input class="form-check-input" type="radio" name="toUser" id="toUser" value="all" checked>
              <label class="form-check-label" for="exampleRadios1">
                All User
              </label>
            </div>
              <div class="form-check col2">
              <input class="form-check-input" type="radio" name="toUser" id="toUser" value="selected">
              <label class="form-check-label" for="exampleRadios2">
                Selected User
              </label>
            </div>
          </div>
        <div class="col-12 email-subject">
          <label for="subject">Subject</label>
          <input type="text" class="form-control" id="subject" placeholder="Subject" value="" name="subject" required>
			
        </div>
        <div class="col-12 form-group email-message">
            <label for="message">Messages</label>
            <textarea class="form-control" id="message" rows="3" name="message" placeholder="Aa"></textarea>
        </div>
          
          
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info adminModalBtn sendEmailBtnExecute">Send</button>
      </div>
    </div>
    </form>
  </div>
</div>  


<div class="modal fade" id="changePassword" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
      <form>
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Change Password </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body adminModal row">
          
        <div class="col-12 email-subject">
          <label for="subject">New Password</label>
          <input type="password" class="form-control" id="subject" placeholder="New Password" name="password" value="" data-id="" data-column="password" required>
			<div class="valid-feedback">
        Perfect!
      		</div>
      		<div class="invalid-feedback">
           5 to 20 (letters, numbers, symbols(@%&_=!)
        	</div>
        </div>
          
          
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info adminModalBtn changePasswordBtnExecute">Save</button>
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
<script src="/ID0420FF19OWidya/script/regexValidate.js" type="text/javascript"></script>
<script src="/ID0420FF19OWidya/script/adminDashboardScript.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
</body>
</html>