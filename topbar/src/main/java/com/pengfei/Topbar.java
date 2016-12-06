package com.pengfei;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.R.attr.visible;

/**
 * Created by pf on 16/12/6.
 */

public class Topbar extends RelativeLayout {
    /**
     * 所有的控件
     * 左右Button
     * centerLayout 中间布局
     * 中间标题TextView
     * imgView 下拉箭头
     */
    private Button leftButton, rightButton;

    private RelativeLayout centerLayout;
    private TextView tvTitle;
    private ImageView imgView;

    /**
     * 左Button属性
     */
    private int leftTextColor;
    private Drawable leftBackgund;
    private String leftText;
    /**
     * 右Button属性
     */
    private int rightTextColor;
    private Drawable rightBackgund;
    private String rightText;
    /**
     * 标题TextView属性
     */
    private float titleTextSize;
    private int titleTextColor;
    private String title;


    private Drawable imgScr;

    /**
     * 左右Button 中间标题TextView 的LayoutParams
     */
    private LayoutParams leftParams, rightParams, titleParams, imgParams, centerLayoutParams;
    /**
     * 所有的回调事件
     */
    private CallListener listener;

    public interface CallListener {
        void onLeftClick();

        void onRightClick();

//        void onTitleClick();
        void onCenterClick();
    }

    public void setCallListener(CallListener listener) {
        this.listener = listener;
    }

    /**
     * left=左Button
     * right=右Button
     * center=中间下拉按钮
     */
    public int left = 1;
    public int right = 2;
    public int center = 3;
    public int title1 = 4;


    @SuppressWarnings("ResourceType")
    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);

        /**获取attr定义的属性
         * get的类型对应atts format的类型*/
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);
        /**左btn的属性*/
        leftTextColor = ta.getColor(R.styleable.Topbar_leftTextColor, 0);
        leftBackgund = ta.getDrawable(R.styleable.Topbar_leftTextBackground);
        leftText = ta.getString(R.styleable.Topbar_leftText);
        /**右btn的属性*/
        rightTextColor = ta.getColor(R.styleable.Topbar_rightTextColor, 0);
        rightBackgund = ta.getDrawable(R.styleable.Topbar_rightTextBackground);
        rightText = ta.getString(R.styleable.Topbar_rightText);

        /**中间标题文字的属性*/
        titleTextColor = ta.getColor(R.styleable.Topbar_titleTextColor1, 0);
        title = ta.getString(R.styleable.Topbar_title);
        titleTextSize = ta.getDimension(R.styleable.Topbar_titleTextSize, 0);


        imgScr = ta.getDrawable(R.styleable.Topbar_imgviewSrc);
        /**垃圾回收*/
        ta.recycle();
        /**创建具体控件对象*/
        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);
        centerLayout = new RelativeLayout(context);
        imgView = new ImageView(context);

        /**左btn 设置具体的属性*/
        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(null);
        leftButton.setText(leftText);

        /**右btn 设置具体的属性*/
        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(null);
        rightButton.setText(rightText);

        /**标题 设置具体的属性*/
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setText(title);
        tvTitle.setGravity(Gravity.CENTER);

        /**自定义控件具体的背景色*/
        setBackgroundColor(0xFFF59595);

        /**设置左右Btm控件具体的LayoutParams
         * 并addView*/
        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);//
        addView(leftButton, leftParams);

        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);//
        addView(rightButton, rightParams);


        centerLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        centerLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, TRUE);//
//        centerLayout.setBackgroundColor(0xFFF11111);



        /**
         * 设置中间标题，下拉图片，enterLayout 具体的LayoutParams
         * 然后把中间标题，下拉图片addView到centerLayout中
         * 再将centerLayout添加到topbar中*/


        tvTitle.setId(9);
        titleParams = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_HORIZONTAL|RelativeLayout.CENTER_VERTICAL, TRUE);
        centerLayout.addView(tvTitle, titleParams);

        imgView.setImageDrawable(imgScr);
        imgParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        imgParams.addRule(RelativeLayout.BELOW, 9);//
        imgParams.addRule(RelativeLayout.CENTER_HORIZONTAL, 9);
        centerLayout.addView(imgView, imgParams);


        addView(centerLayout, centerLayoutParams);

        /**各个控件的点击事件的回调机制*/
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onLeftClick();
            }
        });

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRightClick();
            }
        });


//        tvTitle.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onTitleClick();
//            }
//        });


        centerLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCenterClick();
            }
        });




    }

    /**
     * 各个控件的显示与隐藏
     * left左控件
     * right右控件
     * center中间下拉键
     */
    public void setBarVisibility(int type, boolean flag) {

        if (flag) {
            setVisibilitys(type, View.VISIBLE);
        } else {
            setVisibilitys(type, View.GONE);
        }
    }

    private void setVisibilitys(int type, int visible) {
        if (left == type) {
            leftButton.setVisibility(visible);
        }
        if (right == type) {
            rightButton.setVisibility(visible);
        }
        if (center == type) {
            imgView.setVisibility(visible);
        }
    }



    public void setBarText(int type, String str) {
        if (left == type) {
            leftButton.setText(str);
        }
        if (right == type) {
            rightButton.setText(str);
        }
        if (title1 == type) {
           tvTitle.setText(str);
        }
    }
}
