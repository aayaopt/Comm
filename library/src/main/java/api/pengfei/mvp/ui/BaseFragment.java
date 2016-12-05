package api.pengfei.mvp.ui;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import api.pengfei.mvp.retrofit.ApiService;
import api.pengfei.mvp.retrofit.AppClient;
import retrofit2.Call;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**

 */
public class BaseFragment extends Fragment {
    public Activity mActivity;
    public ApiService api = AppClient.retrofit().create(ApiService.class);
    private CompositeSubscription mCompositeSubscription;
    private List<Call> calls;



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ButterKnife.bind(this, view);
        mActivity = getActivity();
    }

    public Toolbar initToolBar(View view, String title, int toolbarId, int titleId) {
        Toolbar toolbar = (Toolbar) view.findViewById(toolbarId);
        TextView toolbar_title = (TextView) toolbar.findViewById(titleId);
        toolbar_title.setText(title);
        return toolbar;
    }


    public void toastShow(int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }

//    private CompositeSubscription mCompositeSubscription;

    public void onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscription) {
//        if (mCompositeSubscription == null) {
        mCompositeSubscription = new CompositeSubscription();
//        }
        mCompositeSubscription.add(subscription);
    }
}
