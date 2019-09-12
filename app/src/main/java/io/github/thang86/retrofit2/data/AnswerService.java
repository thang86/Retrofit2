package io.github.thang86.retrofit2.data;

import io.github.thang86.retrofit2.model.AnswersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnswerService {

    /**
     * Get list answer
     *
     * @return a list answers
     */
    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<AnswersResponse> getAnswers();

    /**
     * Get list answer by key
     *
     * @param key
     * @return a list answers
     */
    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<AnswersResponse> getAnswers(@Query("key") String key);
}