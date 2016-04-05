package com.gouuse.checkin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gouuse.checkin.R;

/**
 * Created by zhengjiong on 16/2/3.
 */
public class CheckinTypeView extends LinearLayout {
    private boolean isSelected;
    private String mContent;
    private Drawable mLeftDrawableSelect;
    private Drawable mLeftDrawableUnSelect;

    private ImageView mImgLeft;
    private TextView mTxtContent;

    public CheckinTypeView(Context context) {
        this(context, null);
    }

    public CheckinTypeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckinTypeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.checkin_type_layout, this, true);

        mImgLeft = (ImageView) findViewById(R.id.img_left);
        mTxtContent = (TextView) findViewById(R.id.content);

        init(context, attrs);

        if (isSelected) {
            mImgLeft.setImageDrawable(mLeftDrawableSelect);
            mTxtContent.setTextColor(0xFF82AD50);
        } else {
            mImgLeft.setImageDrawable(mLeftDrawableUnSelect);
            mTxtContent.setTextColor(0xFFAEB2B6);
        }
        mTxtContent.setText(mContent);

    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckinTypeView);

        mContent = typedArray.getString(R.styleable.CheckinTypeView_content);
        mLeftDrawableSelect = typedArray.getDrawable(R.styleable.CheckinTypeView_left_drawable);
        mLeftDrawableUnSelect = typedArray.getDrawable(R.styleable.CheckinTypeView_left_drawable_unselect);
        isSelected = typedArray.getBoolean(R.styleable.CheckinTypeView_checkin_type_checked, false);

        typedArray.recycle();
    }

    public void setCheckinType(boolean selected){
        this.isSelected = selected;
        if (isSelected) {
            mImgLeft.setImageDrawable(mLeftDrawableSelect);
            mTxtContent.setTextColor(0xFF82AD50);
        } else {
            mImgLeft.setImageDrawable(mLeftDrawableUnSelect);
            mTxtContent.setTextColor(0xFFAEB2B6);
        }
    }
}
