package com.easycode.retrofit;

import com.easycode.retrofit.model.BaseCallModel;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
//import rx.Observable;

/**
 * Created by yzz on 2016/8/31.
 */
public interface RetrofitService {

//    @POST("/{url}")
//    Call<Pojo> createCommitPojo(@Url("/Retrofit2") String url,@QueryMap Map<String,String> options);

    @POST("{url}")
    Call<ResponseBody> createCommitResponseBody(@Url String url, @QueryMap Map<String, String> options);

//    @Multipart
//    @POST("/upload")
//    Call<ResponseBody> upload(@Part("description") RequestBody description,
//                              @Part MultipartBody.Part file);

//    @Multipart
//    @POST("/upload")
//    Call<ResponseBody> upload(@Part("description") RequestBody description,
//                              //注意这里的参数 "aFile" 之前是在创建 MultipartBody.Part 的时候传入的
//                              @Part("aFile") File file);

    @Multipart
    @POST("mupload")
    Call<ResponseBody> upload(@Part("fileName") String description,
                              @Part("file\"; filename=\"image.jpg")RequestBody imgs);

    @Multipart
    @POST("mupload")
    Call<ResponseBody> upload3(@Part("fileName") String description,
                              @PartMap Map<String,RequestBody> params);

    @Multipart
    @POST("groupline/fileUpload/uploadFiles")
    Call<ResponseBody> upload2(@Part("fileName") String description,
                        @Part("file\"; filename=\"image.jpg")RequestBody imgs);

    @Streaming
    @POST
    Call<ResponseBody> downloadFile(@Url String fileUrl);


    @POST("{url}")
    Observable<BaseCallModel> createRx(@Url String url, @QueryMap Map<String, String> options);


    @GET("{url}")
    Observable<BaseCallModel> createRx2(@Url String url, @QueryMap Map<String, String> options);


    @GET("{url}")
    Observable<BaseCallModel> executeGet(
            @Url String url,
            @QueryMap Map<String, String> maps
    );


    @POST("{url}")
    Observable<BaseCallModel> executePost(
            @Url String url,
            //  @Header("") String authorization,
            @QueryMap Map<String, String> maps);

    @POST("user/{url}")
    Observable<BaseCallModel> scanexecutePost(
            @Url String url,
            //  @Header("") String authorization,
            @QueryMap Map<String, String> maps);




}
