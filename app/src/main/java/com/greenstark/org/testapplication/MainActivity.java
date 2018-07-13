package com.greenstark.org.testapplication;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.greenstark.org.testapplication.adapter.ListAdaptersample;
import com.greenstark.org.testapplication.modal.EntityValues;
import com.greenstark.org.testapplication.modal.GetallpopularalertsResponse;
import com.greenstark.org.testapplication.modal.UserDetails;
import com.greenstark.org.testapplication.rest.ApiClient;
import com.greenstark.org.testapplication.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivity";

    ListAdaptersample listAdaptersample;
    RecyclerView rcy_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcy_list = findViewById(R.id.rcy_list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcy_list.setHasFixedSize(true);
        rcy_list.setLayoutManager(layoutManager);


        getAllDetailsalert();

    }

    /*Attempt To LOGIN Process to Server........*/
    private void getAllDetailsalert() {

        /*Declaring ApiInterFace Class heaf....*/
        final ApiInterface apiService = ApiClient.getResponse().create(ApiInterface.class);
        final ProgressDialog loader = ProgressDialog.show(MainActivity.this, "", "Loading...", true);

        /*Send Parameters to the Api*/
        Call<GetallpopularalertsResponse> call = apiService.getalldetails(623,13.0827, 80.2707);
        /*Enqueue the Login Api......*/
        call.enqueue(new Callback<GetallpopularalertsResponse>() {
            @Override
            public void onResponse(Call<GetallpopularalertsResponse> call, Response<GetallpopularalertsResponse> response) {
                int statusCode = response.code();
                Log.d(TAG, "LoginResponse" +statusCode);
                /*Get Login Good Response...*/
                if (statusCode == 200) {

                    loader.dismiss();

                    List<EntityValues> entityValues = response.body().get_entity();

                    listAdaptersample = new ListAdaptersample(entityValues, R.layout.custome_layout,MainActivity.this);
                    rcy_list.setAdapter(listAdaptersample);
                }
                /*Get Login Bad Response...*/
                else if (statusCode == 400) {

                    loader.dismiss();
                    Toast.makeText(MainActivity.this, "Please Check Your Login Details!", Toast.LENGTH_LONG).show();


                } else {
                    loader.dismiss();
                    Toast.makeText(MainActivity.this, "Network Error. please try again!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<GetallpopularalertsResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("error", t.toString());
                loader.dismiss();
                Toast.makeText(MainActivity.this, "Network Error. please try again!", Toast.LENGTH_LONG).show();

            }
        });
    }

}
