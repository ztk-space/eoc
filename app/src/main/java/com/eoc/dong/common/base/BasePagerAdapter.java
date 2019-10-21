package com.eoc.dong.common.base;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * CodeFatCat
 */

public class BasePagerAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
