/**
 * 
 */
var patterns = {
	userName : /^[\w]{5,100}$/i,
	email : /^[a-zA-z\d\.-]*@[\w-]*\.[a-zA-Z]{2,3}(\.[a-zA-Z]{2,3})?$/,
	password : /^[\w@%&=!]{5,20}$/,
	companyName: /^[a-zA-z .&:,\-()\/.\'@]{3,100}$/,
	companyInfo: /^[\s\S]{10,1000}$/,
	position: /^[a-zA-z .&:,\-()\/.\'@]{3,100}$/,
	jobDescription: /^[\s\S]{10,2000}$/,
	jobRequirements: /^[\s\S]{10,2000}$/,
	notes: /^[\s\S]{0,2000}$/,
	question: /^[\s\S]{10,2000}$/,
	countryID: /^[\d]*$/,
	cityID: /^[\d]*$/,
	deadlineSubmission: /^([0-2][0-9]|3[0-1])\/(0[0-9]|1[0-2])\/(202[0-1])$/,
	phoneCode: /^[\d]{1,4}$/,
	phoneNo: /^[\d]{8,16}$/,
	aboutApplicant: /^[\s\S]{10,2000}$/,
	answer: /^[\s\S]{10,2000}$/
};

var errorMessage = {
	userName : "min 5 char alphanumeric",
	password : "5 to 20 (letters, numbers, symbols(@%&_=!)",
	email : "format: someone@example.com",
	companyName: "Invalid company name",
	companyInfo: "min 10 to max 1000 chars",
	position: "Invalid",
	jobDescription: "min 10 to max 2000 chars",
	jobRequirements: "min 10 to max 2000 chars",
	deadlineSubmission: "dd/mm/yyyy",
	notes: "max 2000 chars",
	question: "min 10 - max 2000 chars",
	aboutApplicant: "min 10 - max 2000 chars",
	answer: "min 10 - max 2000 chars",
	global : "required"
};

