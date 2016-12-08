package com.pengfei;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by pf on 16/12/7.
 */

public class SettingItem extends RelativeLayout {

    /**left and right img
     * center title*/
    private ImageView imgLeftView,imgRightView;
    private TextView tvCenterTitle;
    /**left and right img src*/
    private Drawable drLeft,drRight;
    /**center title att*/
    private String tvName;
    private float tvSize;
    private int tvColor;
    private Drawable tvBackground;

    /**left,right,title LayoutParams*/
    private LayoutParams leftParams,rightParams,titleParams;
    private int wrap=ViewGroup.LayoutParams.WRAP_CONTENT;
    private int match=ViewGroup.LayoutParams.MATCH_PARENT;

    private TypedArray ta;

    public SettingItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        ta =context.obtainStyledAttributes(attrs, R.styleable.setting_item);
        /** get att datas*/
        getAttDatas(context, attrs);

        /** create views*/
        imgLeftView = new ImageView(context);
        imgRightView= new ImageView(context);
        tvCenterTitle= new TextView(context);
        /** seting att datea*/
        imgLeftView.setImageDrawable(drLeft);
        imgRightView.setImageDrawable(drRight);

        Log.i("PF", "SettingItem--->"+tvName);
        tvCenterTitle.setText("11111");
        tvCenterTitle.setTextSize(tvSize);
        tvCenterTitle.setTextColor(tvColor);
        tvCenterTitle.setBackground(tvBackground);



        /** seting LayoutParams*/
        leftParams = new LayoutParams(wrap,wrap);
        leftParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        imgLeftView.setId(1987);
//
//        rightParams = new LayoutParams(wrap,wrap);
//        rightParams.addRule(RelativeLayout.RIGHT_OF,1987);
//        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);

        titleParams = new LayoutParams(wrap,wrap);
        titleParams.addRule(RelativeLayout.RIGHT_OF,1987);
        titleParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
//        titleParams.addRule(RelativeLayout.CENTER_HORIZONTAL,TRUE);
        /** add view to SettingItem*/
        addView(imgLeftView,leftParams);
//        addView(imgRightView,rightParams);
        addView(tvCenterTitle,titleParams);




        ta.recycle();
    }

    /** get my views att datas*/
    private void getAttDatas(Context context, AttributeSet attrs) {

        drLeft= ta.getDrawable(R.styleable.setting_item_imgLeftBg);
        drRight= ta.getDrawable(R.styleable.setting_item_imgRightBg);

        tvName= ta.getString(R.styleable.setting_item_tvTitleName);
        tvColor= ta.getColor(R.styleable.setting_item_tvTitleColor,0);
        tvSize= ta.getFloat(R.styleable.setting_item_tvTitleSize,0);
    }
}
