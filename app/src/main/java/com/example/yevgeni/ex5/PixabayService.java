package com.example.yevgeni.ex5;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yevgeni on 29/11/2017.
 */

public interface PixabayService {

    String Base_Url = "https://pixabay.com/api/";

    //API key
    String API_KEY = "7221344-71feb8e4e8a98b183d21affe6";
    String KeyQuery = "?key="+API_KEY;

    //search parameters:
    String IMAGE_TYPE = "image_type";
    String QUERY= "q";

    //image type parameters:
    String IMAGE_TYPE_ALL = "all";
    String IMAGE_TYPE_PHOTO = "photo";
    String IMAGE_TYPE_ILLUSTRATION= "illustration";
    String IMAGE_TYPE_VECTOR= "vector";

    @GET(KeyQuery)
    Call<ImageSearchResult>searchImage(@Query(QUERY) String queryValue,@Query(IMAGE_TYPE)String imageType);


}
