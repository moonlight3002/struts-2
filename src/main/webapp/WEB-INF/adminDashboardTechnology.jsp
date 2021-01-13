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
            
           
            <div class="upperBar d-block col-12 d-md-none d-xl-none">
                <s:include value="adminUpperBar.jsp"></s:include>
            </div>
            <div class="content col row">
                
                <div class="content-head-wrapper col-12 row">
                    <div class="content-adminHead col-12 row">
                        <div class="col-12 row content-adminHead-Title">
                            <h3 class="col-9 col-sm-6">Technology List</h3>
                            <div class="col-12 col-sm-6 content-userHead-Button row">
                            	<button class="btn btn-info adminBtn addTechnologyBtn" data-toggle="modal" data-target="#addTechnology"><i class="fas fa-plus"></i> Add</button>
                                <button class="btn btn-info adminBtn deleteTechnologyBtn"><i class="fas fa-trash-alt"></i> Delete </button>
                            </div>
                        </div>
                    </div>
                    <table class="tableHead">
                      <thead class="thead">
                        <tr>
                            <th style="width:5%"></th>
                            <th style="width:5%">ID</th>
                            <th style="width:20%">Name</th>
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
                            <th style="width:20%"></th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr data-id="" class="newItem dataItem">
                            <td class="checkboxInputTable">
                                <input type="checkbox"  id="technologySelect" data-id="" name="technologySelect" data-default="">
                            </td>
                            <td class="technologyID">12</td>
                            <td class="technologyName"><input type="text" class="" id="technologyName" name="technologyName" value="Java" data-id="" data-column="email" data-default=""></td>
                            
                        </tr>
                        
                      </tbody>
                    </table>
                    
                     
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
    
   


<div class="modal fade" id="addTechnology" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-md">
      <form>
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body adminModal row">
          
        <div class="col-12 technologyName">
          <label for="technologyName">Technology Name:</label>
          <input type="text" class="form-control" value="" name="technologyName" required>
			
        </div>
          
          
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info adminModalBtn addTechnologyBtnExecute" data-id="">Add</button>
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
<script src="/ID0420FF19OWidya/script/adminDashboardTechnologyScript.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
</body>
</html>