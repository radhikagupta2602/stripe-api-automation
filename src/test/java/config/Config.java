package config;

public class Config {
	
	public static String BASE_URI = "https://api.stripe.com";
    public static String SECRET_KEY = System.getenv("STRIPE_SECRET_KEY");

}
