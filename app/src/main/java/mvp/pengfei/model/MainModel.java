package mvp.pengfei.model;

/**
 * Created by WuXiaolong on 2015/9/23.
 * 业务具体处理，包括负责存储、检索、操纵数据等
 */
public class MainModel {

    public WeatherinfoBean weatherinfo;


    public void setWeatherinfo(WeatherinfoBean weatherinfo) {
        this.weatherinfo = weatherinfo;
    }

    public static class WeatherinfoBean {
        public String city;
        public String cityid;
        public String temp;
        public String WD;
        public String WS;
        public String SD;
        public String WSE;
        public String time;
        public String isRadar;
        public String Radar;
        public String njd;
        public String qy;

    }
}
