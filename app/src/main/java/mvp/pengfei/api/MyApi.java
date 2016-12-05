package mvp.pengfei.api;

import api.pengfei.mvp.retrofit.ApiService;
import mvp.pengfei.model.MainModel;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by pf on 16/12/5.
 * ApiService 的具体实现类
 */

public interface MyApi extends ApiService {

//    @Override
//    public Observable<MainModel> loadDataByRetrofitRxjava(@Path("cityId") String cityId) {
//        return null;
//    }
//
    @GET("adat/sk/{cityId}.html")
    Observable<MainModel> myLoad(@Path("cityId") String cityId);
}
