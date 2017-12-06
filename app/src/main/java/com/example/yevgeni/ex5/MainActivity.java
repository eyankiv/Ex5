package com.example.yevgeni.ex5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yevgeni.ex5.Hit;
import com.example.yevgeni.ex5.ImageSearchResult;

import java.util.ArrayList;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.TlsVersion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private PixabayService pixService;

    private RecyclerView hitsRecycleView;
    private HitsAdapter hitsAdapter;
    private LinearLayoutManager mLayoutManager;

    private TextView hitsDescription;
    private EditText searchEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchEditText = findViewById(R.id.et_searchbar);
        hitsRecycleView = findViewById(R.id.hitsRecyclerView);
        hitsDescription = findViewById(R.id.hitsDescription);
        //mLayoutManager = new LinearLayoutManager(this);
                        mLayoutManager = new GridLayoutManager(this,2);


        hitsRecycleView.setLayoutManager(mLayoutManager);
        retrofit = new Retrofit.Builder().baseUrl(pixService.Base_Url).addConverterFactory(GsonConverterFactory.create()).build();
        pixService = retrofit.create(PixabayService.class);

        //test ON : https://pixabay.com/api/?key=7221344-71feb8e4e8a98b183d21affe6&q=yellow+flowers&image_type=photo




    }

    public void searchImageBtn(View view) {
        String imageNameQuery = searchEditText.getText().toString();
        searchImages(view);

    }

    public void searchImages(View view) {
        hitsDescription.setText(R.string.searching);

        Call<ImageSearchResult> call = pixService.searchImage(searchEditText.getText().toString(),PixabayService.IMAGE_TYPE_ALL);

        call.enqueue(new Callback<ImageSearchResult>() {
            @Override
            public void onResponse(Call<ImageSearchResult> call, Response<ImageSearchResult> response) {
                if (response.code()==200){
                    hitsDescription.setText(response.body().getTotalHits().toString());
                    //hitsDescription.setText(String.format("hits found",response.body().getTotalHits(),searchEditText.getText()));
                    ImageSearchResult data = response.body();
                    ArrayList<Hit> hitList = (ArrayList<Hit>) data.getHits();
                    hitsAdapter = new HitsAdapter(hitList);
                    hitsRecycleView.setAdapter(hitsAdapter);//
                }else{
                    hitsDescription.setText("Error with code: "+response.code());
                }


            }

            @Override
            public void onFailure(Call<ImageSearchResult> call, Throwable t) {
                Log.e(MainActivity.class.getName(), "onFailure: ImageSearch call failed",t );
                hitsDescription.setText("Call for image searvh failed due to: "+t);
            }
        });

    }
}
