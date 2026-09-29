package ru.saucedemo.utils;

public final class Config {
    private Config() {}

    public static final String BASE_URL = System.getProperty("baseUrl", "https://www.saucedemo.com/");
    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_USER = "locked_out_user";
    public static final String PASSWORD = "secret_sauce";
    public static final int TIMEOUT_SECONDS = 10;
}
