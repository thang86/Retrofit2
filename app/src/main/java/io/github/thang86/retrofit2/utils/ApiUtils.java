package io.github.thang86.retrofit2.utils;

import io.github.thang86.retrofit2.data.AnswerService;
import io.github.thang86.retrofit2.data.RetrofitClient;

public class ApiUtils {
    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public static AnswerService getAnswerService() {
        return RetrofitClient.getClient(BASE_URL).create(AnswerService.class);
    }
}
