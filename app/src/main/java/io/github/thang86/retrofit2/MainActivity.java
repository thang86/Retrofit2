package io.github.thang86.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.thang86.retrofit2.adaptert.AnswersAdapter;
import io.github.thang86.retrofit2.utils.ApiUtils;
import io.github.thang86.retrofit2.data.AnswerService;
import io.github.thang86.retrofit2.model.Item;
import io.github.thang86.retrofit2.model.AnswersResponse;
import io.github.thang86.retrofit2.utils.PreLoadingLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "THANG86";
    private AnswersAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private AnswerService mAnswerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAnswerService = ApiUtils.getAnswerService();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_answers);
        mAdapter = new AnswersAdapter(this, new ArrayList<Item>(0), new AnswersAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this, "ID:  " + id, Toast.LENGTH_LONG).show();
            }
        });

        PreLoadingLayout preLoadingLayout = new PreLoadingLayout(this, 2);

        mRecyclerView.setLayoutManager(preLoadingLayout);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

        loadAnswers();
    }

    private void loadAnswers() {
        mAnswerService.getAnswers().enqueue(new Callback<AnswersResponse>() {
            @Override
            public void onResponse(Call<AnswersResponse> call, Response<AnswersResponse> response) {
                Log.d(TAG, " - " + response.body());
                if (response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body().getItems());
                    Log.d(TAG, "posts loaded from API");
                }
            }

            @Override
            public void onFailure(Call<AnswersResponse> call, Throwable t) {
                Log.d(TAG, "error loading from API");

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAnswers();
    }
}
