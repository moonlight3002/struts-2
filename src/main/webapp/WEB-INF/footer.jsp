<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
    <div class="footerBar col-12 row">
            <div class="links col-12">
                <p><a href="http://localhost:8080/ID0420FF19OWidya/about" target="_blank" > About us </a> | <a href="http://localhost:8080/ID0420FF19OWidya/privacyPolicy" target="_blank"> Privacy Policy </a> | <a href="" data-toggle="modal" data-target="#feedbackForm"> Send Feedback </a>
                </p>
            </div>
    </div>
    
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