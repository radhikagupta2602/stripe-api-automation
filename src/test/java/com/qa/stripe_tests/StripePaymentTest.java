package com.qa.stripe_tests;

import com.qa.base.BaseTest;
import com.qa.services.PaymentService;
import com.qa.testdata.PaymentTestData;
import com.qa.utils.ValidationUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StripePaymentTest extends BaseTest {
	
	PaymentService paymentService = new PaymentService();

    @DataProvider(name = "invalidAmountData")
    public Object[][] invalidAmountData() {
        return PaymentTestData.invalidAmountData();
    }

    @Test(description = "Creates payment intent with valid amount and currency")
    public void shouldCreatePaymentIntentWithValidData() {
    	
    	Response response = paymentService.createPaymentIntent(
    			PaymentTestData.VALID_AMOUNT,
    			PaymentTestData.VALID_CURRENCY
    			);
    	
    	ValidationUtils.validateStatusCode(response, 200);
        ValidationUtils.validatePaymentSuccess(response);
    }

    @Test(
            dataProvider = "invalidAmountData",
            description = "Validates bad request for invalid amount values"
    )
    public void shouldReturn400ForInvalidAmount(String amount, String currency) {

        System.out.println("Running invalid amount test with amount=" + amount + ", currency=" + currency);


        Response response = paymentService.createPaymentIntent(amount, currency);

        ValidationUtils.validateErrorResponse(response);
    }

    @Test(description = "Creates payment intent with valid amount and currency")
    public void shouldReturn401ForInvalidApiKey() {

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


    @Test(description = "Creates payment intent with valid amount and currency")
    public void shouldReturn400WhenAmountMissing() {

        Response response = paymentService.createPaymentIntentWithoutAmount(
                PaymentTestData.VALID_CURRENCY
        );

        ValidationUtils.validateStatusCode(response, 400);

        String errorMessage = response.jsonPath().getString("error.message");

        Assert.assertTrue(errorMessage.toLowerCase().contains("amount"));
    }
    

}
