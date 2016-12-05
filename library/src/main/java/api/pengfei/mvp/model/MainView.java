package api.pengfei.mvp.model;


import api.pengfei.mvp.ui.BaseView;

/**
 * Created by WuXiaolong on 2015/9/23.
 * 处理业务需要哪些方法
 */
public interface MainView<T> extends BaseView {

    void getDataSuccess(T model);

    void getDataFail(String msg);

}
