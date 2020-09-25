package rest;

import androidx.annotation.Nullable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "http://vongu3-001-site1.ctempurl.com/";
//    public static final String BASE_URL_XML = "http://i3uganov-001-site1.ctempurl.com/";
//    public static OkHttpClient client = new OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .build();
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
