package com.eoc.dong;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class AdViewpagerUtil {

    private Context context;
    private ViewPager viewPager;
    private AdPagerAdapter mimageViewPagerAdapter;
    private List<ImageView> mImageViews=new ArrayList<>();
    private List<String> urls;
    private LinearLayout dotlayout;
    private ImageView[] dotViews;

    private boolean isRight = true; // 判断viewpager是不是向右滑动
    private int lastPosition = 1; // 记录viewpager从哪个页面滑动到当前页面，从而区分滑动方向
    private int autoIndex = 1; // 自动轮询时自增坐标，能确定导航圆点的位置
    private int currentIndex = 0; //当前item
    private int delayTime = 5000; //自动轮播的时间间隔
    private int imgsize = 0; //图片的数量，item的数量
    private boolean isLoop = true;//轮播开关

    private OnAdPageChangeListener onAdPageChangeListener; //pagechange回调
    private OnAdItemClickListener onAdItemClickListener; //点击事件回调

    private int dotsize = 8; //小圆点的大小宽度
    private int dotoffset = 4; //小圆点的间距

    /**
     * 不带小圆点
     *
     * @param context
     * @param viewPager
     * @param urls
     */
    public AdViewpagerUtil(Context context, ViewPager viewPager, List<String> urls) {
        this.context = context;
        this.viewPager = viewPager;
        this.urls = urls;
        initVps();
    }

    /**
     * 有小圆点
     *
     * @param context
     * @param viewPager
     * @param dotlayout
     * @param dotsize
     * @param dotoffset
     * @param urls
     */
    public AdViewpagerUtil(Context context, ViewPager viewPager, LinearLayout dotlayout, int dotsize, int dotoffset, List<String> urls) {
        this.context = context;
        this.viewPager = viewPager;
        this.dotlayout = dotlayout;
        this.dotsize = dotsize;
        this.urls = urls;
        initVps();
    }

    public AdViewpagerUtil(Context context, ViewPager viewPager, LinearLayout dotlayout, List<String>urls) {
        this.context = context;
        this.viewPager = viewPager;
        this.dotlayout=dotlayout;
        this.urls = urls;
        initVps();
    }

    /**
     * 监听滑动
     *
     * @param onAdPageChangeListener
     */
    public void setOnAdPageChangeListener(OnAdPageChangeListener onAdPageChangeListener) {
        this.onAdPageChangeListener = onAdPageChangeListener;
    }

    /**
     * 监听点击
     *
     * @param onAdItemClickListener
     */
    public void setOnAdItemClickListener(OnAdItemClickListener onAdItemClickListener) {
        this.onAdItemClickListener = onAdItemClickListener;
    }

    /**
     * 初始化图片
     *
     * @param urls
     */
    private void initAdimgs(List<String> urls) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        int length = urls.size() + 2;
        mImageViews.clear();
        for (int i = 0; i < length; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageViews.add(imageView);
        }
        setImg(length, urls);
    }

    /**
     * 显示图片
     *
     * @param length
     * @param urls
     */
    private void setImg(int length, final List<String> urls) {
        if (urls.size() > 0) {
            imgsize = length;
            for (int i = 0; i < length; i++) {
                if (i < length - 2) {
                    final int index = i;
                   final String url = urls.get(i);
                     ImgLoadUtil.display(context, mImageViews.get(i+1), url);
                    mImageViews.get(i + 1).setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (onAdItemClickListener != null) {
                                onAdItemClickListener.onItemClick(mImageViews.get(index + 1), index, url);
                            }
                        }
                    });
                }
            }
          //  ImgLoadUtil.display(context, mImageViews.get(0), urls.get(urls.size() - 1));
          //  ImgLoadUtil.display(context, mImageViews.get(length - 1), urls.get(0));
        }
    }

    /**
     * 初始化viewpager
     */
    public void initVps() {
        initAdimgs(urls);
        initDots(urls.size());
        mimageViewPagerAdapter = new AdPagerAdapter(context, mImageViews);
        viewPager.setAdapter(mimageViewPagerAdapter);
        viewPager.setOffscreenPageLimit(mImageViews.size());
        startLoopViewPager();
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        if(isLoop){
                            stopLoopViewPager();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        if(isLoop){
                            startLoopViewPager();
                        }

                    default:
                        break;
                }
                return false;
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
                if (isRight) {
                    if (arg0 != 1) {
                        if (lastPosition == 0) {
                            viewPager.setCurrentItem(imgsize - 2, false);
                        } else if (lastPosition == imgsize - 1) {
                            viewPager.setCurrentItem(1, false);
                        }
                    }
                }

                if (onAdPageChangeListener != null) {
                    onAdPageChangeListener.onPageScrollStateChanged(arg0);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                if (!isRight) {
                    if (arg1 < 0.01) {
                        if (arg0 == 0) {
                            viewPager.setCurrentItem(imgsize - 2, false);
                        } else if (arg0 == imgsize - 1) {
                            viewPager.setCurrentItem(1, false);
                        }
                    }
                }
                if (onAdPageChangeListener != null) {
                    onAdPageChangeListener.onPageScrolled(arg0, arg1, arg2);
                }
            }

            @Override
            public void onPageSelected(int arg0) {
                autoIndex = arg0;
                if (lastPosition < arg0 && lastPosition != 0) {
                    isRight = true;
                } else if (lastPosition == imgsize - 1) {
                    isRight = true;
                }
                if (lastPosition > arg0 && lastPosition != imgsize - 1) {
                    isRight = false;
                } else if (lastPosition == 0) {
                    isRight = false;
                }
                lastPosition = arg0;

                if (arg0 == 0) {
                    currentIndex = imgsize - 2;
                } else if (arg0 == imgsize - 1) {
                    currentIndex = 1;
                } else {
                    currentIndex = arg0;
                }
               if(dotViews!=null){
                   for (int i = 0; i < dotViews.length; i++) {
                       if (i == currentIndex - 1) {
                           dotViews[i].setSelected(true);
                       } else {
                           dotViews[i].setSelected(false);
                       }
                   }
               }


                if (onAdPageChangeListener != null) {
                    onAdPageChangeListener.onPageSelected(arg0);
                }
            }

        });
        viewPager.setCurrentItem(1);// 初始化时设置显示第一页（ViewPager中索引为1）
    }

    /**
     * 初始化标识点
     *
     * @param length
     */
    public void initDots(int length) {
        if (dotlayout == null||length==1)
            return;
        dotlayout.removeAllViews();
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(dip2px(context, dotsize), dip2px(context, dotsize));
        mParams.setMargins(dip2px(context, dotoffset), 0, dip2px(context, dotoffset), 0);//设置小圆点左右之间的间隔

        dotViews = new ImageView[length];

        for (int i = 0; i < length; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(mParams);
            imageView.setImageResource(R.drawable.dot_selector);
           // imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            if (i == 0) {
                imageView.setSelected(true);//默认启动时，选中第一个小圆点
            } else {
                imageView.setSelected(false);
            }
            dotViews[i] = imageView;//得到每个小圆点的引用，用于滑动页面时，（onPageSelected方法中）更改它们的状态。
            dotlayout.addView(imageView);//添加到布局里面显示
        }
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // 要做的事情
            if (viewPager.getChildCount() > 0) {
                handler.postDelayed(this, delayTime);
                autoIndex++;
                viewPager.setCurrentItem(autoIndex % imgsize, true);

            }
        }
    };
    /**
     * 是否可以轮播
     */
    public void isCanLoop(boolean loop){
        isLoop=loop;
    }

    /**
     * 开始自动轮播
     */
    public void startLoopViewPager() {
        stopLoopViewPager();
        if (viewPager != null && handler != null) {
            handler.postDelayed(runnable, delayTime);// 每两秒执行一次runnable.
            //handler.post(runnable);
        }

    }

    /**
     * 停止自动轮播
     */
    public void stopLoopViewPager() {
        if ( viewPager != null && handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    public void noticeAdapter(List<String> list) {
        urls=list;
        initAdimgs(urls);
        initDots(urls.size());
        viewPager.setCurrentItem(1);
        mimageViewPagerAdapter.notifyDataSetChanged();
        viewPager.setOffscreenPageLimit(mImageViews.size());
        mimageViewPagerAdapter.notifyDataSetChanged();
        startLoopViewPager();
    }

    public interface OnAdItemClickListener {
        void onItemClick(View v, int position, String url);
    }

    public interface OnAdPageChangeListener {
        void onPageScrollStateChanged(int arg0);

        void onPageScrolled(int arg0, float arg1, int arg2);

        void onPageSelected(int arg0);
    }

    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 广告适配器
     */
    private static class AdPagerAdapter extends PagerAdapter {

        private List<ImageView> imageViews;
        private int size;
        private Context context;

        public AdPagerAdapter(Context context, List<ImageView> imageViews) {
            this.context = context;
            this.imageViews = imageViews;
            size = imageViews.size();
        }

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//		((ViewPager) container).removeView((View) object);// 完全溢出view,避免数据多时出现重复现象
            container.removeView(imageViews.get(position));//删除页卡
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position), 0);
            return imageViews.get(position);
        }
    }

}
