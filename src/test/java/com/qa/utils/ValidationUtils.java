package com.qa.utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class ValidationUtils {
	
	public static void validateStatusCode(Response response, int expectedCode) {
        Assert.assertEquals(response.getStatusCode(), expectedCode);
    }

    public static void validatePaymentSuccess(Response response) {
        String status = response.jsonPath().getString("status");
        int amount = response.jsonPath().getInt("amount");
        String currency = response.jsonPath().getString("currency");

        Assert.assertEquals(status, "requires_payment_method");
        Assert.assertEquals(amount, 2000);
        Assert.assertEquals(currency, "usd");
    }

    public static void validateErrorResponse(Response response) {
        int statusCode = response.getStatusCode();
        String errorMessage = response.jsonPath().getString("error.message");

        Assert.assertEquals(statusCode, 400);
        Assert.assertTrue(errorMessage.contains("must be greater than or equal to 1"));
    }

}
