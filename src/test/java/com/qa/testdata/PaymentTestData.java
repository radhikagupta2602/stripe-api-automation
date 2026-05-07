package com.qa.testdata;

public class PaymentTestData {
	
	public static String VALID_AMOUNT = "2000";
    public static String INVALID_AMOUNT = "-100";

    public static String VALID_CURRENCY = "usd";

    public static Object[][] invalidAmountData() {
        return new Object[][]{
                {"-100", "usd"},
                {"0", "usd"}
        };
    }
}
