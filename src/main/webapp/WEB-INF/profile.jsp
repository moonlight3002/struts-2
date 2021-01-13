<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><s:property
		value="%{#session.userData.firstName} %{#session.userData.lastName}" /></title>
<link rel="icon" type="image/png" href="/ID0420FF19OWidya/assets/icon.png">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
	
	<link href='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css' rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="/ID0420FF19OWidya/css/navSearchResultStyle.css">
	<link rel="stylesheet" href="/ID0420FF19OWidya/css/datepickerStyle.css">
<link rel="stylesheet" href="/ID0420FF19OWidya/css/navStyle.css">

<link rel="stylesheet" href="/ID0420FF19OWidya/css/feedbackStyle.css">

<link rel="stylesheet"
	href="/ID0420FF19OWidya/css/profileHeaderStyle.css">
<link rel="stylesheet" href="/ID0420FF19OWidya/css/followStyle.css">
<link rel="stylesheet" href="/ID0420FF19OWidya/css/profileStyle.css">
<link rel="stylesheet"
	href="/ID0420FF19OWidya/css/privateProfileStyle.css">
<s:if test="%{#session.role.equals('admin')}"><link rel="stylesheet"
	href="/ID0420FF19OWidya/css/adminProfileStyle.css"></s:if>
</head>
<body>
	<s:include value="navbar.jsp"></s:include>
	<div class="content-wrapper row d-flex justify-content-center">
		<s:push value="user">
			<div
				class="contentBar col-12 col-md-11 col-lg-10 row d-flex justify-content-center">
				<div class="profile-Header col-12">
					<div class="profile-Header-Top">
						<s:url action='getFile?userID=%{userID}&headerPic=%{headerPic}'
							var="HeaderPic" />
						<div class="profile-Header-User-headerPicture"
							style="background-image: url(<s:property value='#HeaderPic'/>), url(/ID0420FF19OWidya/assets/defaulthp_.jpg);">
							<div class="editProfilePicture">
								<button class="editBtn btn btn-light" type="button"
									data-toggle="modal" data-target="#uploadHeaderPic">
									<i class="fas fa-image"></i>
								</button>
							</div>

						</div>
						<div class="profile-Header-User-profilePicture">
							<div class="profile-profilePicture">
								<s:url
									action='getFile?userID=%{userID}&profilePic=%{profilePic}'
									var="ProfilePic" />
								<s:set var="nameInitial"
									value="%{firstName.substring(0,1).toUpperCase()}" />
								<img src="<s:property value='#ProfilePic'/>"
									onerror='this.onerror=null;this.src="/ID0420FF19OWidya/assets/defaultpp_<s:property value='%{nameInitial}'/>.jpg"' />

							</div>
							<div class="editProfilePicture">
								<button class="editBtn btn btn-light" type="button"
									data-toggle="modal" data-target="#uploadProfilePic">
									<i class="fas fa-camera"></i>
								</button>
							</div>
						</div>
					</div>
					<div class="profile-Header-Bottom row">
						<div class="profile-Header-User col-12">

							<div class="profile-Header-User-name-follow row">
								<div class="profile-Header-User-name col-12"
									id="<s:property value="userID"/>">
									<p><s:property value="firstName" /> <s:property value="lastName" /></p>
								</div>
								<s:if test="%{#session.role.equals('admin') && user.userID!=#session.userData.userID}">
								<div class="profile-Header-User-follow col-12 follow-status">
	                        	<s:set value="isFollowing" var="isFollow"/>
		                        	<a data-status="following" class="follow-status" style="display:<s:if test='alreadyFollowing'>flex</s:if><s:else>none</s:else>"><i class="fas fa-check-circle"></i><p>Following</p>
		                        	<div class="unfollow followBtn" data-action="unfollow"><p><i class="fas fa-times-circle">unfollow</i></p></div>
		                                </a>
	                                <button data-status="not-following" type="button" class="follow-status followBtn btn btn-primary"  data-action="follow" style="display:<s:if test='alreadyFollowing'>none</s:if><s:else>block</s:else>">+Follow</button>
	                                
	                            </div>
	                            </s:if>
							</div>

						</div>
						<div class="profile-Header-Menu col-12 row">
							<div class="about menuDiv col-4">
								<a id="aboutme" class="active">About</a>
							</div>
							<div class="follower menuDiv col-4">
								<a id="follower">Followers (<s:property
										value="followerCount" />)
								</a>
							</div>
							<div class="following menuDiv col-4">
								<a id="following">Following (<s:property
										value="followingCount" />)
								</a>
							</div>
						</div>

					</div>
				</div>

				<div class="profile-content Aboutme col-12 row active">
					<div class="profile-content-right col-12 col-md-5 d-flex align-top">
						<div class="profile-category about-me row">
							<div class="profile-category-header col-12">
								<p>About me</p>
								<div class="profile-edit-options">
									<a class="profile-edit-elipsis"><i
										class="fas fa-ellipsis-h"></i></a>
									<div class="profile-edit-elipsis-item">
										<a class="profile-edit" href="" data-action="viewAboutme"
											data-toggle="modal" data-target="#updateAboutme"><i
											class="fas fa-pen"></i></a>
									</div>
								</div>
							</div>
							<div class="profile-category-item  col-12 row">
								<div class="profile-category-content profile-dob col-12">
									<div class="profile-category-icon">
										<i class="fas fa-birthday-cake"></i>
									</div>
									<div class="profile-category-data" id="dob">
										<p class="dob"><s:property value="dob" /></p>
									</div>

								</div>
							</div>
							<div class="profile-category-item  col-12 row">
								<div class="profile-category-content profile-bio col-12">
									<div class="profile-category-icon">
										<i class="fas fa-book"></i>
									</div>
									<div class="profile-category-data" id="bio">
										<p><s:property value="bio" /></p>
									</div>
								</div>
							</div>
						</div>
						<div class="profile-category skills row">
							<div class="profile-category-header col-12">
								<p>Skill</p>
								<div class="profile-edit-options">
									<a class="profile-edit-elipsis"><i
										class="fas fa-ellipsis-h"></i></a>
									<div class="profile-edit-elipsis-item">
										<a class="profile-add" href="" data-toggle="modal"
											data-target="#addSkill"><i class="fas fa-plus"></i></a> <a
											class="profile-edit" data-action="viewAllSkills" href=""
											data-toggle="modal" data-target="#updateSkill"><i
											class="fas fa-pen"></i></a>
									</div>
								</div>
							</div>

							<div class="profile-category-item newItem col-12 row" data-id="0"
								data-category="skill">
								<div class="profile-category-content profile-skill col-12">
									<div class="profile-category-icon">
										<i class="fas fa-puzzle-piece"></i>
									</div>
									<div class="profile-category-data">
										<a class="technologyName"></a><a class="skillLevel"> - </a>
									</div>

								</div>
							</div>

							<s:iterator value="skills">
							<s:if test='%{!deleted}'>
								<s:if test="show">
								<div class="profile-category-item col-12 row"
									data-id="<s:property value="skillID" />" data-category="skill">
									<div class="profile-category-content profile-skill col-12">
										<div class="profile-category-icon">
											<i class="fas fa-puzzle-piece"></i>
										</div>
										<div class="profile-category-data">
											<a class="technologyName"><s:property
													value="technologyName" /></a><a class="skillLevel"><s:if
													test="skillLevel!='not specified'"> - <s:property
														value="skillLevel" />
												</s:if></a>
										</div>

									</div>
								</div>
								</s:if>
								</s:if>
							</s:iterator>

						</div>
						<div class="profile-category connnection ">
							<div class="profile-category-header col-12">
								<p>Connect me</p>
								<div class="profile-edit-options">
									<a class="profile-edit-elipsis"><i
										class="fas fa-ellipsis-h"></i></a>
									<div class="profile-edit-elipsis-item">
										<a class="profile-edit" href="" data-action="viewConnection"
											data-toggle="modal" data-target="#updateConnection"><i
											class="fas fa-pen"></i></a>
									</div>
								</div>
							</div>
							<div class="profile-category-item col-12 row">
								<div
									class="profile-category-content profile-connection col-12 row d-flex justify-content-center">
									<div class="profile-connection-link col-2">
										<a href="https://www.facebook.com/<s:property value="facebook"/>"
											target="_blank"
											class="facebook <s:if test="facebook==null||facebook==''">disable</s:if>"><i
											class="fab fa-facebook-square"></i></a>

									</div>
									<div class="profile-connection-link  col-2">
										<a
											href="https://www.facebook.com/<s:property value="twitter"/>"
											class="twitter <s:if test="twitter==null||twitter==''">disable</s:if>" target="_blank" class="twitter"><i class="fab fa-twitter"></i></a>
									</div>
									<div class="profile-connection-link col-2">
										<a
											href="https://www.instagram.com/<s:property value="instagram"/>"
											class="instagram <s:if test="instagram==null||instagram==''">disable</s:if>" target="_blank" class="instagram"><i class="fab fa-instagram"></i></a>
									</div>
									<div class="profile-connection-link youtube col-2">
										<a
											href="https://www.youtube.com/<s:property value="youtube"/>"
											class="youtube <s:if test="youtube==null||youtube==''">disable</s:if>" target="_blank" class="youtube"><i class="fab fa-youtube"></i></a>
									</div>
									<div class="profile-connection-link  col-2">
										<a href="https://www.github.com/<s:property value="github"/>"
											class="github <s:if test="github==null||github==''">disable</s:if>" target="_blank" class="github"><i class="fab fa-github"></i></a>
									</div>
								</div>

							</div>

						</div>
					</div>

					<div class="profile-content-left col-12 col-md-6">
						<div class="profile-category experiences row">
							<div class="profile-category-header col-12">
								<p>Work Experience</p>
								<div class="profile-edit-options">
									<a class="profile-edit-elipsis"><i
										class="fas fa-ellipsis-h"></i></a>
									<div class="profile-edit-elipsis-item">
										<a class="profile-add" href="" data-toggle="modal"
											data-target="#addExperience"><i class="fas fa-plus"></i></a>
										<a class="profile-edit" href=""
											data-action="viewAllExperiences" href="" data-toggle="modal"
											data-target="#updateExperience"><i class="fas fa-pen"></i></a>
									</div>
								</div>
							</div>

							<div class="profile-category-item newItem col-12 row" data-id="0"
								data-category="experience">
								<div class="profile-category-content col-12">
									<div class="profile-category-icon">
										<i class="fas fa-briefcase"></i>
									</div>
									<div class="profile-category-data">
										<div class="profile-experience-position ">
											<p></p>
										</div>
										<div class="profile-experience-company ">
											<p></p>
										</div>
										<div class="profile-experience-workPeriod ">
											<a class="month startMonth"></a> <a class="startYear"></a> -
											<a class="month endMonth" /></a> <a class="endYear"></a>
										</div>
										<div class="profile-experience-description ">
											<p></p>
										</div>
									</div>
								</div>
							</div>
							<s:iterator value="experiences">
							<s:if test='%{!deleted}'>
							<s:if test="show">
								<div class="profile-category-item col-12 row"
									data-id="<s:property value="experienceID" />"
									data-category="experience">
									<div class="profile-category-content col-12">
										<div class="profile-category-icon">
											<i class="fas fa-briefcase"></i>
										</div>
										<div class="profile-category-data">
											<div class="profile-experience-position ">
												<p><s:property value="position" /></p>
											</div>
											<div class="profile-experience-company ">
												<p><s:property value="company" /></p>
											</div>
											<div class="profile-experience-workPeriod ">
												<a class="month startMonth"><s:property
														value="startMonth" /></a> <a class="startYear"><s:property
														value="startYear" /></a> - <a class="month endMonth" /><s:property value="endMonth" /></a> <a class="endYear"><s:property value="endYear" /></a>
											</div>
											<div class="profile-experience-description ">
												<p><s:property value="experienceDesc" /></p>
											</div>
										</div>
									</div>
								</div>
								</s:if>
								</s:if>
							</s:iterator>
						</div>
						<div class="profile-category educations row">
							<div class="profile-category-header col-12">
								<p>Education</p>
								<div class="profile-edit-options">
									<a class="profile-edit-elipsis"><i
										class="fas fa-ellipsis-h"></i></a>
									<div class="profile-edit-elipsis-item">
										<a class="profile-add" href="" data-toggle="modal"
											data-target="#addEducation"><i class="fas fa-plus"></i></a> <a
											class="profile-edit" data-action="viewAllEducations" href=""
											data-toggle="modal" data-target="#updateEducation"><i
											class="fas fa-pen"></i></a>
									</div>
								</div>
							</div>

							<div class="profile-category-item  newItem col-12 row"
								data-id="0" data-category="education">
								<div class="profile-category-content col-12">
									<div class="profile-category-icon">
										<i class="fas fa-graduation-cap"></i>
									</div>
									<div class="profile-category-data">
										<div class="profile-education-school">
											<p></p>
										</div>
										<div class="profile-education-degree">
											<p></p>
										</div>
										<div class="profile-education-studyPeriod">
											<a class="startYear"></a> - <a class="endYear"></a>
										</div>
										<div class="profile-education-description ">
											<p></p>
										</div>
									</div>
								</div>
							</div>

							<s:iterator value="educations">
							<s:if test='%{!deleted}'>
							<s:if test="show">
								<div class="profile-category-item col-12 row"
									data-id="<s:property value='educationID'/>"
									data-category="education">
									<div class="profile-category-content col-12">
										<div class="profile-category-icon">
											<i class="fas fa-graduation-cap"></i>
										</div>
										<div class="profile-category-data">
											<div class="profile-education-school">
												<p><s:property value="school" /></p>
											</div>
											<div class="profile-education-degree">
												<p><s:property value="degree" /></p>
											</div>
											<div class="profile-education-studyPeriod ">
												<a class="startYear"><s:property value="startYear" /></a> -
												<a class="endYear"><s:property value="endYear" /></a>
											</div>
											<div class="profile-education-description ">
												<p><s:property value="educationDesc" /></p>
											</div>
										</div>
									</div>
								</div>
								</s:if>
								</s:if>
							</s:iterator>
						</div>

					</div>




				</div>


				<div class="profile-content Follow Follower col-12 row ">
					<div class="profile-follow-upper col-12">
						<form class="form-inline">
							<input class="Follower follow-search form-control mr-sm-2"
								type="search" placeholder="Search Follower"
								data-action="Follower" aria-label="Search">
						</form>
					</div>
					<div class="profile-follow-bottom col-12 row">
						<div class="Follower follow-items col-12 row">
							<div class="follow-item col-6 col-md-3 col-lg-2 newItem">
								<a href="">
									<div class="follow-item-top">
										<img src="" />
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

					<p class="nomore" data-action="Follower">No more item to load</p>
				</div>
				<div class="profile-content Follow Following col-12 row">
					<div class="profile-follow-upper col-12">
						<form class="form-inline">
							<input class="Following follow-search form-control mr-sm-2"
								type="search" placeholder="Search Following"
								data-action="Following" aria-label="Search">
						</form>
					</div>
					<div class="profile-follow-bottom col-12 row">
						<div class="Following follow-items col-12 row">
							<div class="follow-item col-6 col-md-3 col-lg-2 newItem">
								<a href="">
									<div class="follow-item-top">
										<img src="img/icon.png" />
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
					<p class="nomore" data-action="Following">No more item to load
					</p>
				</div>
			</div>
		</s:push>


		<s:include value="footer.jsp"></s:include>


		<div class="alert col-11 col-md-5  error alert-warning" role="alert">
			Server error. please refresh</div>

		<div class="alert col-11 col-md-5  updateThankyou alert-success"
			role="alert">Edit Success</div>

		<div class="alert col-11 col-md-5  addThankyou alert-success"
			role="alert">Add Success</div>

	</div>
	<!-- Modal -->
	<div class="modal fade" id="uploadProfilePic" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<s:form id="uploadForm" name="uploadForm"
				data-action="uploadProfilePicture" enctype="multipart/form-data"
				action="uploadProfilePicture.action" theme="simple">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Upload Profile
							Picture</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body row">
						<div class="custom-file">
							<input type="file" class="custom-file-input" id="customFile"
								name="picture" theme="simple" /> <label
								class="custom-file-label" for="customFile">Choose file</label>
						</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="uploadBtn btn btn-primary"
							value="Upload" data-action="uploadProfilePicture">
					</div>
				</div>
			</s:form>
		</div>
	</div>


	<div class="modal fade" id="uploadHeaderPic" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<s:form id="uploadForm" name="uploadForm"
				data-action="uploadHeaderPicture" enctype="multipart/form-data"
				action="uploadHeaderPicture.action" theme="simple">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Upload Header
							Picture</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body row">
						<div class="custom-file">
							<input type="file" class="custom-file-input" id="customFile"
								name="picture" theme="simple" /> <label
								class="custom-file-label" for="customFile">Choose file</label>
						</div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="uploadBtn btn btn-primary"
							value="Upload" data-action="uploadHeaderPicture">
					</div>
				</div>
			</s:form>
		</div>
	</div>




	<div class="modal fade" id="addEducation" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Add Education</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body row">
					<form class="addForm formAddEducation row"
						data-action="addEducation">
						<div class="add-data-item row" data-category="education"
							data-id="0">
							<div class="form-row row col-12">
								<div class="form-group form-school col-8">
									<label for="staticEmail" class="textFieldLabel">School</label>
									<div class="textFieldInput">
										<input type="text" class="form-control" id="school"
											name="school">
									</div>
								</div>

								<div
									class="form-show custom-control custom-switch align-end  col-4">
									<input type="checkbox" class="switchInput custom-control-input"
										id="show" name="show" checked> <label
										class="custom-control-label switchLabel" for="customSwitch1">Show</label>
								</div>
							</div>

							<div class="form-row row col-12">
								<div class="form-group form-degree col-8">
									<label for="staticEmail" class="textFieldLabel">Degree</label>
									<div class="textFieldInput">
										<input type="text" class="form-control" id="degree"
											name="degree">
									</div>
								</div>
							</div>

							<div class="form-row row col-12">
								<div class="form-group col-5 col-md-3 form-startYear">
									<label for="startYear">Start Year</label>

									<s:select theme="simple" data-id="0" headerKey="0"
										headerValue="Year" class="form-select form-control"
										list="year" name="startYear" id="startYear" value="startYear"
										data-category="education" data-default="0" />
								</div>
								<div class="form-group col-5 col-md-3 form-endYear">
									<label for="endYear">End Year</label>

									<s:select theme="simple" data-id="0" headerKey="0"
										headerValue="Year" class="form-select form-control"
										list="year" name="endYear" id="endYear" value="endYear"
										data-category="education" data-default="0" />
								</div>
							</div>

							<div class="form-row row col-12">
								<div class="form-group col-12 form-description">
									<div class="form-group">
										<label for="educationDesc">Description</label>
										<textarea class="form-control" id="educationDesc" rows="3"
											name="educationDesc"></textarea>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="addBtn btn btn-primary" value="Add"
						data-action="addEducation">Add</button>
				</div>
			</div>

		</div>
	</div>

	<!-- ========================================================================================================================================= -->
	<div class="modal fade" id="updateEducation" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Edit Education</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body row updateModal">
					<form class="updateForm formEditEducation row"
						data-action="updateEducation" data-category="education">


						<div class="update-data-item newItem row"
							data-category="education" data-id="0">
							<div class="form-row row col-12">
								<div class="form-group form-school col-8">
									<label for="staticEmail" class="textFieldLabel">School</label>
									<div class="textFieldInput">
										<input type="text" class="form-control form-control-plaintext"
											id="school" name="school">
									</div>
								</div>
								<div
									class="form-show custom-control custom-switch align-end  col-4">
									<input type="checkbox" class="switchInput custom-control-input"
										id="show" name="show"> <label
										class="custom-control-label switchLabel" for="customSwitch1">Show</label>
								</div>

							</div>

							<div class="form-row row col-12">
								<div class="form-group form-degree col-8">
									<label for="staticEmail" class="textFieldLabel">Degree</label>
									<div class="textFieldInput">
										<input type="text" class="form-control form-control-plaintext"
											id="degree" name="degree">
									</div>
								</div>
								<s:if test="%{#session.role.equals('admin')}">
								<div
									class="form-delete custom-control custom-switch align-end  col-4">
									<input type="checkbox" class="switchInput custom-control-input"
										id="deleted" name="deleted"> <label
										class="custom-control-label switchLabel" for="customSwitch1">Deleted</label>
								</div>
								</s:if>
								<s:else>
 								<div class="form-delete col-4">
									<a class="deleteBtn" data-category="education"
										data-action-category="updateEducation"
										data-action="deleteEducation" data-id="0"><i
										class="fas fa-trash"></i></a>
								</div> 
								</s:else>
							</div>

							<div class="form-row row col-12">
								<div class="form-group col-5 col-md-3 form-startYear">
									<label for="startYear">Start Year</label>

									<s:select theme="simple" data-id="0" headerKey="0"
										headerValue="Year"
										class="form-select form-control-plaintext form-control"
										list="year" name="startYear" id="startYear" value="startYear"
										data-category="education" data-default="0" />
								</div>
								<div class="form-group col-5 col-md-3 form-endYear">
									<label for="endYear">End Year</label>

									<s:select theme="simple" data-id="0" headerKey="0"
										headerValue="Year"
										class="form-select form-control-plaintext form-control"
										list="year" name="endYear" id="endYear" value="endYear"
										data-category="education" data-default="0" />
								</div>
							</div>

							<div class="form-row row col-12">
								<div class="form-group col-12 form-description">
									<div class="form-group">
										<label for="educationDesc">Description</label>
										<textarea class="form-control form-control-plaintext"
											id="educationDesc" rows="3" name="educationDesc"></textarea>
									</div>
								</div>
							</div>
							
							<s:if test="%{#session.role.equals('admin')}">
							<div class="form-row col-12">
									<a class="deletePermanentBtn btn btn-danger" data-category="education"
										data-action-category="updateEducation"
										data-action="deleteEducationPermanently" data-id="0"><i
										class="fas fa-trash"></i></a>
							</div>
							</s:if>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="updateBtn btn btn-primary"
						value="Update" data-action="updateEducation">Save</button>
				</div>
			</div>

		</div>
	</div>
	<!-- ================================================================================================================================== -->

	<div class="modal fade" id="updateExperience" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Edit Education</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body row updateModal">
					<form class="updateForm formEditExperience row"
						data-action="updateExperience" data-category="experience">


						<div class="update-data-item newItem row"
							data-category="experience" data-id="0">
							<div class="form-row row col-12">
								<div class="form-group form-school col-8">
									<label for="staticEmail" class="textFieldLabel">Position</label>
									<div class="textFieldInput">
										<input type="text" class="form-control form-control-plaintext"
											id="positon" name="position">
									</div>
								</div>

								<div
									class="form-show custom-control custom-switch align-end  col-4">
									<input type="checkbox"
										class="switchInput custom-control-input " id="show"
										name="show" checked> <label
										class="custom-control-label switchLabel" for="show">Show</label>
								</div>
							</div>

							<div class="form-row row col-12">
								<div class="form-group form-degree col-8">
									<label for="staticEmail" class="textFieldLabel">Company</label>
									<div class="textFieldInput">
										<input type="text" class="form-control form-control-plaintext"
											id="company" name="company">
									</div>
								</div>
								<s:if test="%{#session.role.equals('admin')}">
								<div
									class="form-delete custom-control custom-switch align-end  col-4">
									<input type="checkbox" class="switchInput custom-control-input"
										id="deleted" name="deleted"> <label
										class="custom-control-label switchLabel" for="customSwitch1">Deleted</label>
								</div>
								</s:if>
								<s:else>
 								<div class="form-delete col-4">
									<a class="deleteBtn" data-category="experience"
										data-action-category="updateExperience"
										data-action="deleteExperience" data-id="0"><i
										class="fas fa-trash"></i></a>
								</div> 
								</s:else>
							</div>

							<div class="form-row row col-12">
								<label for="" class="textFieldLabel">Work Period</label>
								<div class="form-group col-6 col-md-5 row form-startPeriod">
									<label for="" class="col-12 workPeriodlabel">From</label>
									<div class="row col-12 work-selects">

										<s:select theme="simple" data-id="0" headerKey="0"
											headerValue="Month"
											class="form-select work-select form-control form-control-plaintext col-5"
											list="#{1:'Jan', 2:'Feb', 3:'Mar', 4:'Apr', 5:'May', 6:'Jun', 7:'Jul', 8:'Aug', 9:'Sep', 10:'Oct', 11:'Nov', 12:'Dec'}"
											name="startMonth" id="startMonth" data-category="experience"
											data-default="0" />

										<s:select theme="simple" data-id="0" headerKey="0"
											headerValue="Year"
											class="form-select form-control form-control-plaintext col-5"
											list="year" name="startYear" id="startYear" value="endYear"
											data-category="education" data-default="0" />

									</div>
								</div>

								<div class="form-group col-6 col-md-5 row form-endPeriod">
									<label for="" class="workPeriodlabel col-12">To</label>
									<div class="row col-12 work-selects">

										<s:select theme="simple" data-id="0" headerKey="0"
											headerValue="Month"
											class="form-select work-select form-control form-control-plaintext col-5"
											list="#{1:'Jan', 2:'Feb', 3:'Mar', 4:'Apr', 5:'May', 6:'Jun', 7:'Jul', 8:'Aug', 9:'Sep', 10:'Oct', 11:'Nov', 12:'Dec'}"
											name="endMonth" id="endMonth" data-category="experience"
											data-default="0" />

										<s:select theme="simple" data-id="0" headerKey="0"
											headerValue="Year"
											class="form-select form-control form-control-plaintext col-5"
											list="year" name="endYear" id="endYear" value="endYear"
											data-category="education" data-default="0" />
										<div class="form-check form-check-inline col-12">
											<s:checkbox theme="simple" data-id="%{expID}"
												class="form-check-input" id="endWorkPeriodCheckbox"
												name="present" data-category="experience"
												data-default="false" value="false" />
											<label class="form-check-label" for="endWorkPeriodCheckbox">Present</label>
										</div>
									</div>
								</div>

							</div>

							<div class="form-row row col-12">
								<div class="form-group col-12 form-description">
									<div class="form-group">
										<label for="experienceDesc">Description</label>
										<textarea class="form-control form-control-plaintext"
											id="experienceDesc" rows="3" name="experienceDesc"></textarea>
									</div>
								</div>
							</div>
							<s:if test="%{#session.role.equals('admin')}">
							<div class="form-row col-12">
									<a class="deletePermanentBtn btn btn-danger" data-category="experience"
										data-action-category="updateExperience"
										data-action="deleteExperiencePermanently" data-id="0"><i
										class="fas fa-trash"></i></a>
							</div>
							</s:if>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="updateBtn btn btn-primary"
						value="Update" data-action="updateExperience">Save</button>
				</div>
			</div>

		</div>
	</div>

	<!-- ================================================================================================================================== -->

	<div class="modal fade" id="addExperience" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Add Experience</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body row">
					<form class="addForm formAddExperience row"
						data-action="addExperience">
						<div class="add-data-item row" data-category="education"
							data-id="0">
							<div class="form-row row col-12">
								<div class="form-group form-school col-8">
									<label for="staticEmail" class="textFieldLabel">Position</label>
									<div class="textFieldInput">
										<input type="text" class="form-control" id="positon"
											name="position">
									</div>
								</div>

								<div
									class="form-show custom-control custom-switch align-end  col-4">
									<input type="checkbox" class="switchInput custom-control-input"
										id="show" name="show" checked> <label
										class="custom-control-label switchLabel" for="show">Show</label>
								</div>
							</div>

							<div class="form-row row col-12">
								<div class="form-group form-degree col-8">
									<label for="staticEmail" class="textFieldLabel">Company</label>
									<div class="textFieldInput">
										<input type="text" class="form-control" id="company"
											name="company">
									</div>
								</div>
							</div>

							<div class="form-row row col-12">
								<label for="" class="textFieldLabel">Work Period</label>
								<div class="form-group col-6 col-md-5 row form-startPeriod">
									<label for="" class="col-12 workPeriodlabel">From</label>
									<div class="row col-12 work-selects">

										<s:select theme="simple" data-id="0" headerKey="0"
											headerValue="Month"
											class="form-select work-select form-control col-5"
											list="#{1:'Jan', 2:'Feb', 3:'Mar', 4:'Apr', 5:'May', 6:'Jun', 7:'Jul', 8:'Aug', 9:'Sep', 10:'Oct', 11:'Nov', 12:'Dec'}"
											name="startMonth" id="startMonth" data-category="experience"
											data-default="0" />

										<s:select theme="simple" data-id="0" headerKey="0"
											headerValue="Year" class="form-select form-control col-5"
											list="year" name="startYear" id="startYear" value="endYear"
											data-category="education" data-default="0" />

									</div>
								</div>
								<div class="form-group col-6 col-md-5 row form-endPeriod">
									<label for="" class="workPeriodlabel col-12">To</label>
									<div class="row col-12 work-selects">

										<s:select theme="simple" data-id="0" headerKey="0"
											headerValue="Month"
											class="form-select work-select form-control col-5"
											list="#{1:'Jan', 2:'Feb', 3:'Mar', 4:'Apr', 5:'May', 6:'Jun', 7:'Jul', 8:'Aug', 9:'Sep', 10:'Oct', 11:'Nov', 12:'Dec'}"
											name="endMonth" id="endMonth" data-category="experience"
											data-default="0" />

										<s:select theme="simple" data-id="0" headerKey="0"
											headerValue="Year" class="form-select form-control col-5"
											list="year" name="endYear" id="endYear" value="endYear"
											data-category="education" data-default="0" />
										<div class="form-check form-check-inline col-12">
											<s:checkbox theme="simple" data-id="%{expID}"
												class="form-check-input" id="endWorkPeriodCheckbox"
												name="present" data-category="experience"
												data-default="false" value="false" />
											<label class="form-check-label" for="endWorkPeriodCheckbox">Present</label>
										</div>
									</div>
								</div>

							</div>

							<div class="form-row row col-12">
								<div class="form-group col-12 form-description">
									<div class="form-group">
										<label for="experienceDesc">Description</label>
										<textarea class="form-control" id="experienceDesc" rows="3"
											name="experienceDesc"></textarea>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="addBtn btn btn-primary" value="Add"
						data-action="addExperience">Add</button>
				</div>
			</div>

		</div>
	</div>

	<!-- ==================================================================================================== -->


	<div class="modal fade" id="addSkill" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Skill</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body row">
					<form class="addForm formAddSkill row" data-category="skill"
						data-action="addSkill">
						<div class="edit-data-item row" data-category="skill" data-id="0">
							<div class="form-group col-4">
								<label for="inputTechonologyID">Technology</label>
								<s:select theme="simple" data-id="0" id="inputTechonologyID"
									headerKey="0" headerValue="choose"
									class="form-select form-control" list="technologies"
									listValue="technologyName" listKey="technologyID"
									name="technologyID" value="0" data-category="skill"
									data-default="0" />

							</div>
							<div class="form-group col-4">
								<label for="inputTechonologyLevel">Skill Level</label>
								<s:select theme="simple" data-id="0" id="inputTechonologyLevel"
									headerKey="0" headerValue="choose"
									class="form-select form-control "
									list="#{'beginner':'beginner', 'intermediate':'intermediate', 'advanced':'advanced', 'not specified':'not specified'}"
									name="skillLevel" value="0" data-category="skill"
									data-default="0" />
							</div>

							<div
								class="form-show custom-control custom-switch align-end  col-4">
								<input type="checkbox" class="switchInput custom-control-input"
									id="show" name="show" checked> <label
									class="custom-control-label switchLabel" for="show">Show</label>
							</div>

						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary addBtn" value="Add"
						data-action="addSkill">Save</button>
				</div>
			</div>

		</div>
	</div>


	<!-- ==================================================================================================== -->




	<div class="modal fade" id="updateSkill" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Skill</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body row updateModal">
					<form class="updateForm formEditSkill row" data-category="skill"
						data-action="updateSkill">
						<div class="update-data-item newItem row" data-category="skill"
							data-id="0">
							<div class="row col-8 selectSkill-div">

								<div class="form-group col-12 col-lg-6">
									<label for="inputTechonologyID">Technology</label> <input
										type="text" readonly
										class="form-control-plaintext skill-input"
										id="inputTechonologyName" name="technologyName"
										data-category="skill" data-default="0"> <input
										type="hidden" readonly
										class="form-control-plaintext skill-input"
										id="inputTechonologyID" name="technologyID" value="0"
										data-category="skill" data-default="0">
								</div>

								<div class="form-group col-4 col-12 col-lg-6">
									<label for="inputTechonologyLevel">Skill Level</label>
									<s:select theme="simple" data-id="0" id="inputTechonologyLevel"
										headerKey="0" headerValue="choose"
										class="form-select form-control "
										list="#{'beginner':'beginner', 'intermediate':'intermediate', 'advanced':'advanced', 'not specified':'not specified'}"
										name="skillLevel" value="0" data-category="skill"
										data-default="0" />
								</div>
							</div>
							<div class="row col-4 ButtonSkill-div">
								<s:if test="%{#session.role.equals('admin')}">
								<div
									class="form-show custom-control custom-switch align-end  col-12">
									<input type="checkbox" class="switchInput custom-control-input"
										id="show" name="show" checked> <label
										class="custom-control-label switchLabel" for="show">Show</label>
								</div>
								<div
									class="form-delete custom-control custom-switch align-end  col-12">
									<input type="checkbox" class="switchInput custom-control-input"
										id="deleted" name="deleted"> <label
										class="custom-control-label switchLabel" for="customSwitch1">Deleted</label>
								</div>
								</s:if>
								<s:else>
								<div
									class="form-show custom-control custom-switch align-end  col-8">
									<input type="checkbox" class="switchInput custom-control-input"
										id="show" name="show" checked> <label
										class="custom-control-label switchLabel" for="show">Show</label>
								</div>
								<div class="form-delete col-4">
									<a class="deleteBtn" data-category="skill"
										data-action-category="updateSkill"
										data-action="deleteSkill" data-id="0"><i
										class="fas fa-trash"></i></a>
								</div> 
								</s:else>
							</div>
							<s:if test="%{#session.role.equals('admin')}">
							<div class="form-row col-12">
									<a class="deletePermanentBtn btn btn-danger" data-category="skill"
										data-action-category="updateSkill"
										data-action="deleteSkillPermanently" data-id="0"><i
										class="fas fa-trash"></i></a>
							</div>
							</s:if>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary updateBtn"
						value="Update" data-action="updateSkill">Save</button>
				</div>
			</div>

		</div>
	</div>


	<!-- ==================================================================================================== -->
<div class="modal fade" id="updateAboutme" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-md">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Edit my Profile</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body row">
        <form class="updateForm formAboutme row" data-category="aboutme" data-action="updateAboutme">
            <div class="update-data row" data-category="aboutme"
							data-id="0">
				<div class="form-group row col-12">
					            <label for="firstName" class="col-sm-4 col-form-label">First Name</label>
					            <div class="col-sm-8">
					              <input type="text"  class="form-control-plaintext" id="firstName" name="firstName" >
					            </div>
				</div>
				<div class="form-group row col-12">
					            <label for="lastName" class="col-sm-4 col-form-label">Last Name</label>
					            <div class="col-sm-8">
					              <input type="text" class="form-control-plaintext" id="lastName" name="lastName" placeholder="Last Name" >
					            </div>
				 </div>
					          
				<div class="form-group row col-12">
					            <label for="dob" class="col-sm-4 col-form-label">Date of Birth</label>
					            <div class="col-sm-8">
					              <input type="text" class="form-control-plaintext datepickerInput" id="dob" name="dob" placeholder="dd/mm/yyyy" >
					            </div>
				</div>
			     <div class="form-group row col-12">
						    <label for="bio" class="col-sm-4 col-form-label">Biography</label>
						    <textarea class="form-control" id="bio" name="bio" rows="3"></textarea>
				</div>
			          
        
			</div>
			   
        </form>
      </div>
      <div class="modal-footer">
          <button type="button" class="btn btn-primary updateBtn" value="Update" data-action="updateAboutme">Save</button>
      </div>
    </div>

  </div>
</div> 
	<!-- ==================================================================================================== -->

<div class="modal fade" id="updateConnection" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-md">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Edit Link</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body row">
        <form class="updateForm formConnection row" data-category="connection" data-action="updateConnection">
            <div class="update-data row" data-category="aboutme"
							data-id="0">
				<div class="input-group col-12">
				    <div class="input-group-prepend">
				      <div class="input-group-text">facebook.com/</div>
				    </div>
				    <input type="text" class="form-control" name="facebook" id="facebook" placeholder="Username">
				  </div>
				  
				  <div class="input-group col-12">
				    <div class="input-group-prepend">
				      <div class="input-group-text">twitter.com/</div>
				    </div>
				    <input type="text" class="form-control" name="twitter" id="twitter" placeholder="Username">
				  </div>
				  
				  <div class="input-group col-12">
				    <div class="input-group-prepend">
				      <div class="input-group-text">instagram.com/</div>
				    </div>
				    <input type="text" class="form-control" name="instagram" id="instagram" placeholder="Username">
				  </div>
				  
				  <div class="input-group col-12 row">
				    <div class="input-group-prepend">
				      <div class="input-group-text">youtube.com/</div>
				    </div>
				    <input type="text" class="form-control" name="youtube" id="youtube" placeholder="Username">
				  </div>
				  
				  <div class="input-group col-12">
				    <div class="input-group-prepend">
				      <div class="input-group-text">github.com/</div>
				    </div>
				    <input type="text" class="form-control" name="github" id="github" placeholder="Username">
				  </div>
				
			          
        
			</div>
			   
        </form>
      </div>
      <div class="modal-footer">
          <button type="button" class="btn btn-primary updateBtn" value="Update" data-action="updateConnection">Save</button>
      </div>
    </div>

  </div>
</div> 

	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js'
		type='text/javascript'></script>
		
	<script>



var userProfileID = <s:property value="user.userID"/>
var userUserName = "<s:property value='user.userName'/>";

	$(".datepickerInput").datepicker({
        "startDate" : new Date(1935, 11, 31),
        "endDate" : new Date(2015, 11, 31),
        "format" : 'dd/mm/yyyy',
        "yearRange" : [ 1935, 2015 ],
        "setDate" : new Date(1999, 06, 15)
        
    });


</script>
	<script src="/ID0420FF19OWidya/script/navScript.js"
		type="text/javascript"></script>
	<script src="/ID0420FF19OWidya/script/feedbackScript.js"
		type="text/javascript"></script>

	<script src="/ID0420FF19OWidya/script/profileScript.js"
		type="text/javascript"></script>
	<s:if test="%{#session.role.equals('admin')}"><script src="/ID0420FF19OWidya/script/adminProfileScript.js"
		type="text/javascript"></script></s:if><s:else><script src="/ID0420FF19OWidya/script/privateProfileScript.js"
		type="text/javascript"></script></s:else>
<s:if test="%{#session.role.equals('admin') && user.userID!=#session.userData.userID}"><script src="/ID0420FF19OWidya/script/publicProfileScript.js" type="text/javascript"></script></s:if>
</body>
</html>