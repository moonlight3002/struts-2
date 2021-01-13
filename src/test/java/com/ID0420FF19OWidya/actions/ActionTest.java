package com.ID0420FF19OWidya.actions;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import com.ID0420FF19OWidya.models.City;
import com.ID0420FF19OWidya.models.Country;
import com.ID0420FF19OWidya.models.Job;
import com.ID0420FF19OWidya.models.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

public class ActionTest extends StrutsTestCase {
//	@Test
//	public void testLoginNoData() throws Exception{
//		ActionProxy actionProxy = getActionProxy("/login");
//		String result = actionProxy.execute();
//		System.out.println("result" + result);
//		assertEquals("the execute method did not return"+ "input" +"but should have", "input", result) ;
///*		
//		 * ActionProxy actionProxy = getActionProxy("/job.action"); ViewJobAction action
//		 * = (ViewJobAction) actionProxy.getAction();
//		 * assertNotNull("the action should not be null", action); String result =
//		 * actionProxy.execute(); assertEquals("the execute method did not return" +
//		 * ActionSupport.SUCCESS +"but should have", ActionSupport.SUCCESS, result);
//		 */
//		
////		Map<String, Object> sessionMap = new HashMap<String, Object>();
////		actionProxy.getInvocation().getInvocationContext().setSession(sessionMap);
// 	}
//	
//	@Test
//	public void testLoginTc01() throws Exception{
//		request.setParameter("email", "widyangkasa@gmail.com");
//		request.setParameter("password", "secret");
//		ActionProxy actionProxy = getActionProxy("/login");
//		String result = actionProxy.execute();
//		System.out.println("result" + result);
//		assertEquals("the execute method did not return"+ "input" +"but should have", "input", result) ;
// 	}
//	
//	@Test
//	public void testLoginTc02() throws Exception{
//		request.setParameter("email", "email@gmail.com");
//		request.setParameter("password", "secret");
//		ActionProxy actionProxy = getActionProxy("/login");
//		String result = actionProxy.execute();
//		System.out.println("result" + result);
//		assertEquals("the execute method did not return"+ "input" +"but should have", "input", result) ;
// 	}
//	
//	@Test
//	public void testLoginTc03() throws Exception{
//		request.setParameter("email", "widyangkasa@gmail.com");
//		request.setParameter("password", "hahaha");
//		ActionProxy actionProxy = getActionProxy("/login");
//		String result = actionProxy.execute();
//		System.out.println("result" + result);
//		assertEquals("the execute method did not return"+ "success" +"but should have", "success", result) ;
// 	}
	
//	@Test
//	public void testRegisterTc01() throws Exception{
//		request.setParameter("firstName", "Widya");
//		request.setParameter("lastName", "Limarto");
//		request.setParameter("email", "widyangkasa@gmail.com");
//		request.setParameter("dob", "22/09/1994");
//		request.setParameter("password", "secret");
//		
//		ActionProxy actionProxy = getActionProxy("/register");
//		String result = actionProxy.execute();
//		assertEquals("the execute method did not return"+ "input" +"but should have", "input", result) ;
// 	}
//	
//	@Test
//	public void testRegisterTc02() throws Exception{
//		request.setParameter("firstName", "Widya");
//		request.setParameter("lastName", "Limarto");
//		request.setParameter("email", "widyangkasa@@gmail.com");
//		request.setParameter("dob", "22/09/2200");
//		request.setParameter("password", "secret");
//		
//		ActionProxy actionProxy = getActionProxy("/register");
//		String result = actionProxy.execute();
//		assertEquals("the execute method did not return"+ "input" +"but should have", "input", result) ;
// 	}
//	
//	@Test
//	public void testRegisterTc03() throws Exception{
//		request.setParameter("email", "nascetur.ridiculus@eget.co.uk");
//		request.setParameter("vid", "odiJyusXXoCHJUdLleZzJFoRJzfhhbRHeUEBusyvkuRgohIrMF0");
//		
//		ActionProxy actionProxy = getActionProxy("/register/verification");
//		String result = actionProxy.execute();
//		assertEquals("the execute method did not return"+ "success" +"but should have", "success", result) ;
// 	}
	
//	@Test
//	public void testPasswordRetrievalTc01() throws Exception{
//		request.setParameter("email", "email@gmail.com");	
//		ActionProxy actionProxy = getActionProxy("/forgetPassword");
//		RetrievePasswordAction rp = (RetrievePasswordAction) actionProxy.getAction();
//		actionProxy.execute();		
//		assertEquals("the execute method did not assign result String value"+ "email not Exist" +"but should have", "email not Exist", rp.getResult()) ;
// 	}
//	
//	@Test
//	public void testPasswordRetrievalTc02() throws Exception{
//		request.setParameter("email", "email@gmail.co.id.id");	
//		ActionProxy actionProxy = getActionProxy("/forgetPassword");
//		RetrievePasswordAction rp = (RetrievePasswordAction) actionProxy.getAction();
//		actionProxy.execute();		
//		assertEquals("the execute method did not assign result String value"+ "invalid Email" +"but should have", "invalid Email", rp.getResult()) ;
// 	}
//	
//	@Test
//	public void testPasswordRetrievalTc03() throws Exception{
//		request.setParameter("email", "widyangkasa@gmail.com");	
//		ActionProxy actionProxy = getActionProxy("/forgetPassword");
//		RetrievePasswordAction rp = (RetrievePasswordAction) actionProxy.getAction();
//		actionProxy.execute();		
//		assertEquals("the execute method did not assign result String value"+ "success" +"but should have", "success", rp.getResult()) ;
// 	}
	
//	@Test
//	public void testProfileTc01() throws Exception{
//		ActionProxy actionProxy = getActionProxy("/widya");
//		String result = actionProxy.execute();		
//		assertEquals("the execute method did not assign result String value"+ "sessioninvalid" +"but should have", "sessionInvalid", result) ;
// 	}
//	
//	@Test
//	public void testProfileTc02() throws Exception{
//		User user = new User();
//		user.setUserName("BME09");
//		user.setUserID(18);
//		user.setRole("user");
//		Map<String, Object> sessionMap = new HashMap<String, Object>();
//		sessionMap.put("userData", user);
//		ActionProxy actionProxy = getActionProxy("/BME09");
//		actionProxy.getInvocation().getInvocationContext().setSession(sessionMap);
//		String result = actionProxy.execute();		
//		assertEquals("the execute method did not assign result String value"+ "successPrivate" +"but should have", "successPrivate", result) ;
// 	}
//	
//	@Test
//	public void testProfileTc03() throws Exception{
//		User user = new User();
//		user.setUserName("BME09");
//		user.setUserID(18);
//		user.setRole("user");
//		Map<String, Object> sessionMap = new HashMap<String, Object>();
//		sessionMap.put("userData", user);
//		ActionProxy actionProxy = getActionProxy("/widya");
//		actionProxy.getInvocation().getInvocationContext().setSession(sessionMap);
//		String result = actionProxy.execute();		
//		assertEquals("the execute method did not assign result String value"+ "successPublic" +"but should have", "successPublic", result) ;
// 	}
	
	@Test
	public void testJobTc01() throws Exception{
		User user = new User();
		user.setUserName("BME09");
		user.setUserID(18);
		user.setRole("user");

		request.setParameter("companyName", "PT. ABC");
		request.setParameter("city.cityID", "21553");
		request.setParameter("country.countryID", "102");
		request.setParameter("companyInfo", "lorem Ipsum");
		request.setParameter("position", "Software Engineer");
		request.setParameter("place", "remote");
		request.setParameter("hours", "fulltime");
		request.setParameter("jobDescription", "lorem Ipsum");
		request.setParameter("jobRequirements", "lorem Ipsum");
		request.setParameter("deadlineSubmission", "02/12/2021");

		Map<String, Object> sessionMap = new HashMap<String, Object>();
		sessionMap.put("userData", user);
		ActionProxy actionProxy = getActionProxy("/job/posting");
		actionProxy.getInvocation().getInvocationContext().setSession(sessionMap);
		String result = actionProxy.execute();		
		assertEquals("the execute method did not assign result String value"+ "input" +"but should have", "input", result) ;
 	}
	
	
	@Test
	public void testJobTc02() throws Exception{
		User user = new User();
		user.setUserName("Inggrid12355");
		user.setUserID(1);
		user.setRole("user");

		request.setParameter("jobID", "69");

		Map<String, Object> sessionMap = new HashMap<String, Object>();
		sessionMap.put("userData", user);
		ActionProxy actionProxy = getActionProxy("/job/apply");
		actionProxy.getInvocation().getInvocationContext().setSession(sessionMap);
		String result = actionProxy.execute();		
		assertEquals("the execute method did not assign result String value"+ "applicationexist" +"but should have", "applicationexist", result) ;
 	}
	
	@Test
	public void testJobTc03() throws Exception{
		User user = new User();
		user.setUserName("Inggrid12355");
		user.setUserID(1);
		user.setRole("user");

		request.setParameter("jobID", "221");

		Map<String, Object> sessionMap = new HashMap<String, Object>();
		sessionMap.put("userData", user);
		ActionProxy actionProxy = getActionProxy("/job/apply");
		actionProxy.getInvocation().getInvocationContext().setSession(sessionMap);
		String result = actionProxy.execute();		
		assertEquals("the execute method did not assign result String value"+ "invalidJobPost" +"but should have", "invalidJobPost", result) ;
 	}
}
