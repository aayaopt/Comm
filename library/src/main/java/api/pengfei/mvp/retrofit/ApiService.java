package api.pengfei.mvp.retrofit;


import api.pengfei.mvp.model.MainModel;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 */
public interface ApiService {
    //baseUrl
    String API_SERVER_URL = "http://www.weather.com.cn/";

//    //加载天气
//    @GET("adat/sk/{cityId}.html")
//    Call<MainModel> loadDataByRetrofit(@Path("cityId") String cityId);

    //加载天气
    @GET("adat/sk/{cityId}.html")
    Observable<MainModel> loadDataByRetrofitRxjava(@Path("cityId") String cityId);


    /**
     * 【我的】 获取反馈人列表 接口  （ 管理员列表）
     * http://qingtuo.36ve.com/Interface/mySelf/GetUsersTel/learn_id/8/user_type/1
     */
//    @GET("http://qingtuo.36ve.com/Interface/mySelf/GetUsersTel/learn_id/8/user_type/1")
//    Observable<ListAdamins> getUsersTel();
}
