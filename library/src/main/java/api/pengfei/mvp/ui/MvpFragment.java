package api.pengfei.mvp.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;


/**

 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mvpPresenter = createPresenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
