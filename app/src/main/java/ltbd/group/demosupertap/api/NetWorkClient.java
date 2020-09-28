package ltbd.group.demosupertap.api;

import io.reactivex.Observable;
import ltbd.group.demosupertap.mocdata.MockInterceptor;
import ltbd.group.demosupertap.models.MainData;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by ltbd on 9/26/20.
 */
public class NetWorkClient {
    private static final String BASE_URL = "https://SECRET.mockapi.io/";

    public static ApiService create() {
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create()).client(getHttpClient().build());
        return builder.build().create(ApiService.class);
    }

    public static OkHttpClient.Builder getHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new MockInterceptor());
        return httpClient;
    }

    public interface ApiService {

        @GET("data")
        Observable<Response<MainData>> fetchData();
    }

}
