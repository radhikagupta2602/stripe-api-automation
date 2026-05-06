package com.qa.stripe_tests;

import com.qa.base.BaseTest;
import com.qa.services.PaymentService;
import com.qa.testdata.PaymentTestData;
import com.qa.utils.ValidationUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StripePaymentTest extends BaseTest {
	
	PaymentService paymentService = new PaymentService();
	
    @Test
    public void createPaymentIntent() {
    	
    	Response response = paymentService.createPaymentIntent(
    			PaymentTestData.VALID_AMOUNT,
    			PaymentTestData.VALID_CURRENCY
    			);
    	
    	ValidationUtils.validateStatusCode(response, 200);
        ValidationUtils.validatePaymentSuccess(response);
    }
    
    @Test
    public void createPaymentIntent_InvalidAmount() {

        Response response = paymentService.createPaymentIntent(
       
        		PaymentTestData.INVALID_AMOUNT,
        		PaymentTestData.VALID_CURRENCY
        		);

        ValidationUtils.validateErrorResponse(response);
    }
    
    @Test
    public void createPaymentIntent_InvalidApiKey() {

        String invalidKey = "sk_test_invalid123";

        Response response = paymentService.createPaymentIntentWithCustomKey(
                PaymentTestData.VALID_AMOUNT,
                PaymentTestData.VALID_CURRENCY,
                invalidKey
        );

        ValidationUtils.validateStatusCode(response, 401);

        String errorMessage = response.jsonPath().getString("error.message");
        Assert.assertTrue(errorMessage.toLowerCase().contains("invalid api key"));
    }
    
    
    @Test
    public void createPaymentIntent_MissingAmount() {

        Response response = paymentService.createPaymentIntentWithoutAmount(
                PaymentTestData.VALID_CURRENCY
        );

        ValidationUtils.validateStatusCode(response, 400);

        String errorMessage = response.jsonPath().getString("error.message");

        Assert.assertTrue(errorMessage.toLowerCase().contains("amount"));
    }
    

}
