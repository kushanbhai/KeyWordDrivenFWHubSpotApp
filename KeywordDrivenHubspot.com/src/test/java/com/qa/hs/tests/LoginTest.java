package com.qa.hs.tests;

import org.testng.annotations.Test;

import com.qa.hs.keyword.engine.KeywordEngine;

public class LoginTest {

	public KeywordEngine keyWordEngine;
	
	@Test
	public void loginTest() {
		keyWordEngine = new KeywordEngine();
		keyWordEngine.startExecution("login");
	}
	
	@Test
	public void signUpTest() {
		keyWordEngine = new KeywordEngine();
		keyWordEngine.startExecution("signUp");
	}
	
}
