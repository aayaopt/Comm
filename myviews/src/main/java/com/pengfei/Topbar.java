package com.pengfei;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by pf on 16/12/6.
 */

public class Topbar extends RelativeLayout {
    /**
     * 所有的控件
     * 左右Button/左右图片
     * centerLayout 中间布局
     * 中间标题TextView
     * imgCenter 下拉箭头
     */
    private ImageView imgCenter, imgLetf, imgRight;
    private Button rightButton;//leftButton,
    private RelativeLayout centerLayout;
    private TextView tvTitle;


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

    /**
     * 下拉箭头、左图片、右图片 属性
     */
    private Drawable drImgCenter, drImgLetf, drImgRight;


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

    /**
     * type 类型  是指 该view的显示样式
     * -----------有下拉--------------
     * 中间文字+下拉1
     * 中间文字+下拉+右btn2
     * 中间文字+下拉+右img3
     * 中间文字+下拉+右img+左图4
     * -----------无下拉--------------
     * 中间文字5
     * 中间文字+右btn6
     * 中间文字+右img7
     * 中间文字+右img+左图8
     */
    private int type;
    private TypedArray ta;

    public void setType(int t) {
        setLayout(t);
    }

    Context mCon;

    @SuppressWarnings("ResourceType")
    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCon = context;
        /**获取attr定义的属性
         * get的类型对应atts format的类型*/
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);


//        rightButton = new Button(context);
//        tvTitle = new TextView(context);
//        centerLayout = new RelativeLayout(context);
//        imgCenter = new ImageView(context);
//        imgLetf = new ImageView(context);
//        imgRight = new ImageView(context);


        /**获取所有属性
         * 中间文字+下拉1
         * 中间文字+下拉+右btn2
         * 中间文字+下拉+右btn+左图3
         * 中间文字+下拉+右img4
         * 中间文字+下拉+右img+左图5


         * 中间文字+右btn6
         * 中间文字+右btn+左图7
         * 中间文字+右img8
         * 中间文字+右img+左图9
         * 中间文字0
         * */
        int temp = getDatas(ta);

        Log.i("PF", "样式值--->" + temp);

        setLayout(temp);


        /**垃圾回收*/
        ta.recycle();
    }

    private void setLayout(int temp) {
        switch (temp) {
            case 0:
                createCenterViews(false);
                break;
            case 1:
                createCenterViews();

                centerOnclick();
                break;
            case 2:
                createCenterViews();
                createRightBtn();

                centerOnclick();
                btuOnclick();
                break;
            case 3:
                createCenterViews();
                createRightBtn();
                createLeftImg();

                centerOnclick();
                btuOnclick();
                backOnclick();
                break;
            case 4:
                createCenterViews();
                createRight();

                centerOnclick();
                imgOnclick();
                break;
            case 5:
                createCenterViews();
                createRight();
                createLeftImg();

                centerOnclick();
                backOnclick();
                imgOnclick();
                break;
            case 6:
                createCenterViews(false);
                createRightBtn();

                btuOnclick();
                break;
            case 7:
                createCenterViews(false);
                createRightBtn();
                createLeftImg();

                btuOnclick();
                backOnclick();
                break;
            case 8:
                createCenterViews(false);
                createRight();

                imgOnclick();
                break;
            case 9:
                createCenterViews(false);
                createRight();
                createLeftImg();

                imgOnclick();
                backOnclick();
                break;
            default:
                createCenterViews(false);
                break;
        }
    }
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    //////////////////////////点击事件///////////////////

    /**
     * 右边图片点击事件
     */
    private void imgOnclick() {
        imgRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRightClick();
            }
        });

    }

    /**
     * 返回键点击事件
     */
    private void backOnclick() {
        imgLetf.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onLeftClick();
            }
        });

    }

    /**
     * 中间布局点击事件
     */
    private void centerOnclick() {
        centerLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCenterClick();
            }
        });
    }

    /**
     * 右btn点击事件
     */
    private void btuOnclick() {
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRightClick();
            }
        });
    }


    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    /////////////////////////创建view///////////////////

    /**
     * create center layout and title and down imgge att
     * 中间布局 标题 下拉箭头
     */
    private void createCenterViews() {

        if (tvTitle == null && centerLayout == null&&imgCenter==null) {
            tvTitle = new TextView(mCon);
            imgCenter=new ImageView(mCon);
            centerLayout = new RelativeLayout(mCon);

            tvTitle.setGravity(Gravity.CENTER);

            centerLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            centerLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, TRUE);//
            /**
             * 设置中间标题，下拉图片，enterLayout 具体的LayoutParams
             * 然后把中间标题，下拉图片addView到centerLayout中
             * 再将centerLayout添加到topbar中*/


            tvTitle.setId(9);
            titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            titleParams.addRule(RelativeLayout.CENTER_HORIZONTAL | RelativeLayout.CENTER_VERTICAL,
                    TRUE);
            centerLayout.addView(tvTitle, titleParams);


            imgParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            imgParams.addRule(RelativeLayout.BELOW, 9);//
            imgParams.addRule(RelativeLayout.CENTER_HORIZONTAL, 9);
            centerLayout.addView(imgCenter, imgParams);

            addView(centerLayout, centerLayoutParams);
        }
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setText(title);
        imgCenter.setImageDrawable(drImgCenter);


    }

    private void createCenterViews(boolean flag) {
        createCenterViews();
        if (flag) {
        } else {
            imgCenter.setVisibility(GONE);
        }
    }


    /**
     * create left image att
     * 左image
     */
    private void createLeftImg() {

        /**设置左右Btm控件具体的LayoutParams
         * 并addView*/


        if (imgLetf == null) {
            imgLetf = new ImageView(mCon);


            leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
            leftParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
            leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);//
            leftParams.setMargins(30, 0, 0, 0);
            addView(imgLetf, leftParams);
        }
        imgLetf.setImageDrawable(drImgLetf);
    }

    /**
     * create right button att
     * 右button
     */
    private void createRightBtn() {
        Log.i("PF", "样式值--->createRightBtn");
        /**右btn 设置具体的属性*/


        if (rightButton == null) {
            rightButton = new Button(mCon);


            rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);//
            addView(rightButton, rightParams);
        }

        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(null);
        rightButton.setText(rightText);
    }

    /**
     * create right button att
     * 右button
     */
    private void createRight() {
        Log.i("PF", "样式值--->createRightBtn");

        if (imgRight == null) {
            imgRight = new ImageView(mCon);

            rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            rightParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
            rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);//
            rightParams.setMargins(0, 0, 30, 0);
            addView(imgRight, rightParams);
        }
        imgRight.setImageDrawable(drImgRight);


    }


    /**
     * 获取所有属性
     */
    private int getDatas(TypedArray ta) {
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

        /**三张图片的 属性*/
        drImgCenter = ta.getDrawable(R.styleable.Topbar_imageCenterSrc);
        drImgLetf = ta.getDrawable(R.styleable.Topbar_imageLeftSrc);
        drImgRight = ta.getDrawable(R.styleable.Topbar_imageRightSrc);


        type = ta.getInt(R.styleable.Topbar_styleType, 0);
        return type;
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
//            leftButton.setVisibility(visible);
        }
        if (right == type) {
            rightButton.setVisibility(visible);
        }
        if (center == type) {
            imgCenter.setVisibility(visible);
        }
    }


    public void setBarText(int type, String str) {
        if (left == type) {
//            leftButton.setText(str);
        }
        if (right == type) {
            rightButton.setText(str);
        }
        if (title1 == type) {
            tvTitle.setText(str);
        }
    }
}
