package ai.advance.liveness.sdk.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;

import ai.advance.common.utils.LogUtil;
import ai.advance.liveness.lib.Detector;
import ai.advance.liveness.sdk.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Detection tool class
 */
public class IDetection {
    private Context mContext;

    public ArrayList<Detector.DetectionType> mDetectionSteps;// Live detection action list
    private HashMap<Integer, AnimationDrawable> mDrawableCache;

    public IDetection(Context mContext) {
        this.mContext = mContext;
        mDrawableCache = new HashMap<>();
    }

    /**
     * Get the prompt picture/animation according to the action type
     *
     * @param detectionType Action type
     * @return Prompt picture/animation
     */
    public AnimationDrawable getDrawRes(Detector.DetectionType detectionType) {
        int resID = -1;
        switch (detectionType) {
            case POS_YAW:
                resID = R.drawable.anim_frame_turn_head;
                break;
            case MOUTH:
                resID = R.drawable.anim_frame_open_mouse;
                break;
            case BLINK:
                resID = R.drawable.anim_frame_blink;
                break;
        }
        AnimationDrawable cachedDrawAble = mDrawableCache.get(resID);
        if (cachedDrawAble != null)
            return cachedDrawAble;
        else {
            AnimationDrawable drawable = (AnimationDrawable) mContext.getResources().getDrawable(resID);
            mDrawableCache.put(resID, (drawable));
            return drawable;
        }
    }

    public String getDetectionName(Detector.DetectionType detectionType) {
        String detectionName = null;
        Resources resources = mContext.getResources();
        switch (detectionType) {
//            case POS_PITCH:
//                detectionName = resources.getString(R.string.liveness_pos_pitch);
//                break;
            case POS_YAW:
                detectionName = resources.getString(R.string.liveness_pos_raw);
                break;
            case MOUTH:
                detectionName = resources.getString(R.string.liveness_mouse);
                break;
            case BLINK:
                detectionName = resources.getString(R.string.liveness_blink);
                break;
//            case POS_YAW_LEFT:
//                detectionName = resources.getString(R.string.liveness_raw_left);
//                break;
//            case POS_YAW_RIGHT:
//                detectionName = resources.getString(R.string.liveness_raw_right);
//                break;
        }
        return detectionName;
    }

    /**
     * Initialize the check order with default action number
     */
    public void detectionTypeInit() {
        detectionTypeInit(Number.THREE);
    }

    public enum Number {
        ONE(1),
        TWO(2),
        THREE(3);
        private int count;

        Number(int count) {
            this.count = count;
        }
    }

    /**
     * Initialize the check order
     *
     * @param number set action number with two or three
     */
    public void detectionTypeInit(Number number) {
        ArrayList<Detector.DetectionType> tmpTypes = new ArrayList<Detector.DetectionType>();
        tmpTypes.add(Detector.DetectionType.POS_YAW);// 左右摇头
        tmpTypes.add(Detector.DetectionType.MOUTH);// 张嘴
        tmpTypes.add(Detector.DetectionType.BLINK);// 眨眼


        Collections.shuffle(tmpTypes);// 打乱顺序
        mDetectionSteps = new ArrayList<>();
        for (int i = 0; i < number.count; i++) {
            mDetectionSteps.add(tmpTypes.get(i));
        }
    }

    public void onDestroy() {
        mContext = null;
    }
}
