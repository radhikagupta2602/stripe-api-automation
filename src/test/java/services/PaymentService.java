package services;

import config.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PaymentService {
	
		
	public Response createPaymentIntent(String amount, String currency) {
		
		return RestAssured
		.given()
		.baseUri(Config.BASE_URI)
		.header("Authorization", "Bearer " + Config.SECRET_KEY)
        .contentType("application/x-www-form-urlencoded")
        .formParam("amount", amount)
        .formParam("currency", currency)
        .formParam("payment_method_types[]", "card")
        .post("/v1/payment_intents");		
		
	}
	
	public Response createPaymentIntentWithCustomKey(String amount, String currency, String apiKey) {

	    return RestAssured
	            .given()
	            .baseUri(Config.BASE_URI)
	            .header("Authorization", "Bearer " + apiKey)
	            .contentType("application/x-www-form-urlencoded")
	            .formParam("amount", amount)
	            .formParam("currency", currency)
	            .formParam("payment_method_types[]", "card")
	            .post("/v1/payment_intents");
	}
	
	public Response createPaymentIntentWithoutAmount(String currency) {

	    return RestAssured
	            .given()
	            .baseUri(Config.BASE_URI)
	            .header("Authorization", "Bearer " + Config.SECRET_KEY)
	            .contentType("application/x-www-form-urlencoded")
	            // ❌ amount missing
	            .formParam("currency", currency)
	            .formParam("payment_method_types[]", "card")
	            .post("/v1/payment_intents");
	}

}
