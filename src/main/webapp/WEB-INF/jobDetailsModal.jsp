<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="modal fade" id="viewJobDetails" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    
      <div class="modal-body row">
        <div class="jobDetailsDiv col-12 row">
            <div class="col-12 position" class="">
                <p id="position" ></p>
                    <button type="button" class="close closeJobDetailBtn" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                
            </div>
            <div class="col-12 company" >
                <p id="companyName" ></p>
            </div>
            <div class="col-12 location">
                <p id="location" ><i class="fas fa-map-marker-alt"></i></p>
            </div>
            <div class="col-12 row date-place-hours-deadline">
                <div class="col-12 col-md-4">
                    <p id="postedDate"></p>
                </div>
                <div class="col-12 col-md-4 hours-place row">
                    <div><i class="fas fa-street-view"></i><p id="place"></p></div>
                    <div><i class="fas fa-clock"></i><p id="hours"></p></div>
                </div>
                <div class="col-12 col-md-4">
                   <i class="far fa-calendar-alt"></i><p id="deadline"></p>
                </div>
            </div>
            <div class="col-12 row">
                <div class="col-12">
                    <p id="subdetail-header">Company Information</p>
                </div>
                <div class="col-12">
                    <p id="companyInfo"></p>
                </div>
            </div>
            <div class="col-12 row">
                <div class="col-12">
                    <p id="subdetail-header">Job Description</p>
                </div>
                <div class="col-12">
                    <p id="jobDesc"></p>
                </div>
            </div>
            <div class="col-12 row">
                <div class="col-12">
                    <p id="subdetail-header">Job Requirement</p>
                </div>
                <div class="col-12">
                    <p id="jobRequirement"></p>
                </div>
            </div>
            <div class="col-12 row">
                <div class="col-12">
                    <p id="subdetail-header">Required Skill</p>
                </div>
                <div class="col-12">
                    <p id="jobSkill"></p>
                </div>
            </div>
            <div class="col-12 row">
                <div class="col-12">
                    <p id="subdetail-header">Notes</p>
                </div>
                <div class="col-12">
                    <p id="jobNotes"></p>
                </div>
            </div>
        </div>      
      </div>
      <div class="modal-footer">
        <a href="" target="_blank" id="applyJob" class=" applyBtn btn btn-info" data-id="">Apply</a>
        <a class="hasApply"><i class="fas fa-check-circle"></i> You have applied to this Job</a>
      </div>
    </div>
  </div>
</div>