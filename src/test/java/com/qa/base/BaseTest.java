package com.qa.base;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseTest {
	
	 protected String baseURI = "https://api.stripe.com";
	    protected String secretKey = "sk_test_51TSkh0QeGonNQLShS8CRFd99vexkQ5J91vVkCovc5soFx0Rs9dJoxOtci8I7w4wpkOw98adHX1BGfkf3zMfdWO3C00nWV6mUG8";

	    @BeforeClass
	    public void setup() {
	        RestAssured.baseURI = baseURI;
	    }

}
