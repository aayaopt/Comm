package api.pengfei.mvp.Presenter;


import api.pengfei.mvp.model.MainModel;
import api.pengfei.mvp.model.MainView;
import api.pengfei.mvp.retrofit.ApiCallback;
import api.pengfei.mvp.ui.BasePresenter;

/**
 */
public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view) {
        attachView(view);
    }

    public void loadDataByRetrofitRxjava(String cityId) {
        mvpView.showLoading();
        addSubscription(api.loadDataByRetrofitRxjava(cityId),
                new ApiCallback<MainModel>() {
                    @Override
                    public void onSuccess(MainModel model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.getDataFail(msg);
                    }


                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }

                });
    }

//
//    public void getUsersTel() {
//
//        addSubscription(api.getUsersTel(),
//                new ApiCallback<ListAdamins>() {
//                    @Override
//                    public void onSuccess(ListAdamins model) {
//                        mvpView.getDataSuccess(model);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        mvpView.getDataFail(msg);
//                    }
//
//
//                    @Override
//                    public void onFinish() {
//                        mvpView.hideLoading();
//                    }
//
//                });
//    }
//



}
