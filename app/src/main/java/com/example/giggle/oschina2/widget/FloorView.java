package com.example.giggle.oschina2.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by leiShifang on 2016/2/3 23:51.
 */
public class FloorView extends LinearLayout {

    private Context mContext;

    public FloorView(Context context) {
        super(context, null);
    }

    public FloorView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public FloorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }
}
