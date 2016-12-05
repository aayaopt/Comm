package mvp.pengfei.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import api.pengfei.mvp.Presenter.MainPresenter;
import api.pengfei.mvp.model.MainModel;
import api.pengfei.mvp.model.MainView;
import api.pengfei.mvp.ui.MvpActivity;
import mvp.pengfei.R;
/**
 * MVP 小架构
 * model 具体bean
 * presenter 代理类
 * ui 具体类
 * api 具体网路api实现类
 * */

public class SampleAct extends MvpActivity<MainPresenter> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sample);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("PF","出发点击事件，访问网路");
                mvpPresenter.loadDataByRetrofitRxjava("101310222");
            }
        });
    }

    @Override
    protected MainPresenter createPresenter() {
       return new MainPresenter(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getDataSuccess(Object model) {
        Log.i("PF","执行 getDataSuccess() ");
        if(model instanceof MainModel){
            Log.i("PF","访问成功－－－－－> (MainModel)model");
            dataSuccess((MainModel)model);
        }
//        if(model instanceof ListAdamins){
//            Log.i("PF","访问成功－－－－－> (ListAdamins)model");
//        }
    }

    @Override
    public void getDataFail(String msg) {
        Log.i("PF","执行 getDataFail() "+msg);
    }

    private void dataSuccess(MainModel model) {

       TextView tv = (TextView) findViewById(R.id.tv);
        MainModel.WeatherinfoBean weatherinfo = model.weatherinfo;
        String showData = getResources().getString(R.string.city) + weatherinfo.city
                + getResources().getString(R.string.wd) + weatherinfo.WD
                + getResources().getString(R.string.ws) + weatherinfo.WS
                + getResources().getString(R.string.time)
                + weatherinfo.time;
        tv.setText(showData);
    }
}
