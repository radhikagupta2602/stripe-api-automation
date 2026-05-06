package com.qa.base;

import com.qa.config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	

	    @BeforeClass
	    public void setup() {

            Assert.assertNotNull(
                    Config.SECRET_KEY,
                    "STRIPE_SECRET_KEY is not set. Please add it in Run Configuration environment variables."
            );

            Assert.assertEquals(
                    Config.BASE_URI,
                    "https://api.stripe.com",
                    "Unexpected BASE_URI configured."
            );
        }

}