package com.eoc.dong.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 
 * @author ZhengJingle
 *
 */

public class AutoUtils {

	private static int displayWidth;//屏幕的宽
	private static int displayHeight;//屏幕的高
	
	public static int designWidth;
	public static int designHeight;
	
	private static double textPixelsRate;

	/**
	 * 对整个Util的宽高进行初始化
	 * @param designWidth 设计图中屏幕宽度
	 * @param designHeight 设计图中屏幕高度
	 */
	public static void initUtils(int designWidth, int designHeight){
		if (designWidth<1 || designHeight<1){
			throw new RuntimeException("designWidth < 0 or designHeight < 0");
		}

		AutoUtils.designWidth=designWidth;
		AutoUtils.designHeight=designHeight;
	}
	
	public static void setSize(Activity act, boolean hasStatusBar, int designWidth, int designHeight){
		initUtils(designWidth,designHeight);
		setSize(act,hasStatusBar);
	}

	public static void setSize(Activity act, boolean hasStatusBar){
		if(act==null || designWidth<1 || designHeight<1)return;

		Display display = act.getWindowManager().getDefaultDisplay();
		int width = display.getWidth();//屏幕的宽
		int height = display.getHeight();//屏幕的高

		if (hasStatusBar) {
			height -= getStatusBarHeight(act);
		}

		AutoUtils.displayWidth=width;
		AutoUtils.displayHeight=height;

		double displayDiagonal=Math.sqrt(Math.pow(AutoUtils.displayWidth, 2)+Math.pow(AutoUtils.displayHeight, 2));
		double designDiagonal=Math.sqrt(Math.pow(AutoUtils.designWidth, 2)+Math.pow(AutoUtils.designHeight, 2));
		AutoUtils.textPixelsRate=displayDiagonal/designDiagonal;
	}
	
    public static int getStatusBarHeight(Context context)
    {
		int result = 0;
		try {
			int resourceId = context.getResources().getIdentifier(
					"status_bar_height", "dimen", "android");
			if (resourceId > 0) {
				result = context.getResources().getDimensionPixelSize(
						resourceId);
			}
		} catch (Resources.NotFoundException e) {
			e.printStackTrace();
		}
		return result;
    }
    
    public static void auto(Activity act){
    	if(act==null || displayWidth<1 || displayHeight<1)return;
    	
    	View view=act.getWindow().getDecorView();
    	auto(view);
    }
    
    public static void auto(View view){
    	if(view==null || displayWidth<1 || displayHeight<1)return;
    	
    	AutoUtils.autoTextSize(view);
    	AutoUtils.autoSize(view);
    	AutoUtils.autoPadding(view);
    	AutoUtils.autoMargin(view);
    	
    	if(view instanceof ViewGroup){
    		auto((ViewGroup)view);
    	}
    	
    }
    
    private static void auto(ViewGroup viewGroup){
    	int count = viewGroup.getChildCount();
    	
		for (int i = 0; i < count; i++) {

			View child = viewGroup.getChildAt(i);
			
			if(child!=null){
				auto(child);
			}
		}
    }
    
    public static void autoMargin(View view){
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
            return;

        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if(lp == null)return ;

        lp.leftMargin = getDisplayWidthValue(lp.leftMargin);
        lp.topMargin = getDisplayHeightValue(lp.topMargin);
        lp.rightMargin = getDisplayWidthValue(lp.rightMargin);
        lp.bottomMargin = getDisplayHeightValue(lp.bottomMargin);
        
    }

    public static void autoPadding(View view){
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();

        l = getDisplayWidthValue(l);
        t = getDisplayHeightValue(t);
        r = getDisplayWidthValue(r);
        b = getDisplayHeightValue(b);

        view.setPadding(l, t, r, b);
    }

    public static void autoSize(View view){
        ViewGroup.LayoutParams lp = view.getLayoutParams();

        if (lp == null) return;
        
        if(lp.width>0){
        	lp.width = getDisplayWidthValue(lp.width);
        }

        if(lp.height>0){
        	lp.height = getDisplayHeightValue(lp.height);
        }
        
    }
    
    public static void autoTextSize(View view){
    	if(view instanceof TextView){
    		double designPixels=((TextView) view).getTextSize();
			if(designPixels==39.0){//默认大小 字体不设置大小  大坑 莫踩
				designPixels=30;
			}
    		double displayPixels=textPixelsRate*designPixels;
    		((TextView) view).setIncludeFontPadding(false);
    		((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) displayPixels);
    	}
    }

    public static int getDisplayWidthValue(int designWidthValue){
    	if(designWidthValue<2){
    		return designWidthValue;
    	}
        return designWidthValue * displayWidth / designWidth;
    }

    public static int getDisplayHeightValue(int designHeightValue){
    	if(designHeightValue<2){
    		return designHeightValue;
    	}
        return designHeightValue * displayHeight / designHeight;
    }
}
