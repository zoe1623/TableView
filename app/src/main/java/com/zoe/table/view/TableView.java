package com.zoe.table.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zoe.table.R;
import com.zoe.table.bean.UserModel;

import java.util.List;

/**
 * 表格 两行
 */
public class TableView extends View {
    public TableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        init();
    }

    public TableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        init();
    }

    public TableView(Context context) {
        super(context);
        init();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TableView);
        mTextSize = ta.getDimensionPixelSize(R.styleable.TableView_text_size, 30);
        mTextColor = ta.getColor(R.styleable.TableView_text_color, Color.GRAY);
        mFrameColor = ta.getColor(R.styleable.TableView_frame_color, Color.GRAY);
        mStrokeWidth = ta.getDimensionPixelSize(R.styleable.TableView_frame_width, 2);
        ta.recycle();  //注意回收
    }
    private Paint mPaint = new Paint();
    private int mWidth, mHeight;
    private int mStrokeWidth = 2;//px
    private float density;
    private int mTextSize;
    private int mTextColor, mFrameColor;
    private void init() {
        density = getContext().getResources().getDisplayMetrics().density;
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setAntiAlias(true);

        if(mTextColor == 0){
            mTextColor = Color.GRAY;
        }
        mPaint.setStrokeWidth(mStrokeWidth);
        if(mStrokeWidth % 2 == 1){
            mStrokeWidth++;
        }
        if(mTextSize == 0){
            mTextSize = (int) (10*density);
        }
        mPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mFrameColor);
        canvas.drawLine(0,mStrokeWidth/2,mWidth, mStrokeWidth/2, mPaint);
        canvas.drawLine(0,mHeight/2,mWidth, mHeight/2, mPaint);
        canvas.drawLine(0,mHeight,mWidth, mHeight, mPaint);

        canvas.drawLine(mStrokeWidth/2,0,mStrokeWidth/2, mHeight, mPaint);
        canvas.drawLine(mWidth,0,mWidth, mHeight, mPaint);

        int len = mList.size();
        int unit = mWidth / len;
        for(int i = 0; i < len; i ++){
            if(i < len - 1) {
                mPaint.setColor(mFrameColor);
                canvas.drawLine(unit * (1 + i), 0, unit * (1 + i), mHeight, mPaint);
            }
            mPaint.setColor(mTextColor);
            UserModel user = mList.get(i);
            String name = user.name;
            mPaint.setTextSize(mTextSize);
            setTextSize(name, unit - 2, mTextSize);
            canvas.drawText(name, unit * i + unit / 2, mHeight/4 + mTextSize/2, mPaint);
            String age = user.age;
            setTextSize(age, unit - 2, mTextSize);
            canvas.drawText(age, unit * i + unit / 2, mHeight*3/4 + mTextSize/2, mPaint);
        }
    }

    /**
     * 动态设置文字大小
     * @param text 文字
     * @param width 单元格宽度
     * @param size 文字默认大小
     */
    private void setTextSize(String text, int width, int size){
        mPaint.setTextSize(size);
        float measureText = mPaint.measureText(text);
        if (measureText - width > 50){
            setTextSize(text, width, size - 12);
        }else if(measureText - width > 40){
            setTextSize(text, width, size - 8);
        }else if(measureText - width > 30){
            setTextSize(text, width, size - 6);
        }else if(measureText - width > 20){
            setTextSize(text, width, size - 4);
        }else if(measureText - width > 5){
            setTextSize(text, width, size - 2);
        }else if(measureText > width){
            setTextSize(text, width, size -1);
        }
    }

    private List<UserModel> mList;
    public void setData(@NonNull List<UserModel> list){
        mList = list;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom){
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth()- mStrokeWidth;
        mHeight = getHeight() - mStrokeWidth;
    }
}
