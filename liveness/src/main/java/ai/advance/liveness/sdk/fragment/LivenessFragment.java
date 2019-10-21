package ai.advance.liveness.sdk.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import ai.advance.common.IMediaPlayer;
import ai.advance.common.utils.SensorUtil;
import ai.advance.liveness.lib.DetectionFrame;
import ai.advance.liveness.lib.Detector;
import ai.advance.liveness.lib.http.entity.ResultEntity;
import ai.advance.liveness.sdk.R;
import ai.advance.liveness.sdk.activity.ResultActivity;
import ai.advance.liveness.sdk.utils.ICamera;
import ai.advance.liveness.sdk.utils.IDetection;

public class LivenessFragment extends Fragment implements TextureView.SurfaceTextureListener, Camera.PreviewCallback, Detector.DetectionListener, Detector.DetectorInitCallback {

    private static final String NO_RESPONSE = "NO_RESPONSE";
    private Handler mHandler = new Handler();
    protected ImageView mmaskImageView;
    private ICamera mICamera;
    private Camera mCamera;
    private TextureView mTextureView;
    private Detector mDetector;
    private ImageView mTipImageView;
    private TextView mTipTextView;
    private View mBackView;
    private TextView mTimerView;
    private CheckBox mVoiceCheckBox;
    private View mMaskLayout;
    private View mDetectionLayout;
    private View mTipImageLayout;

    private View mProgressLayout;

    private IDetection mIDetection;
    private int mCurStep = 0;// 检测动作的次数
    private SensorUtil mSensor;
    private IMediaPlayer mIMediaPlayer;
    /**
     * init loading dialog
     */
    ProgressDialog mInitProgressDialog;

    public static LivenessFragment newInstance() {
        return new LivenessFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_liveness, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findViews();
        initData();
        initCameraParams();
    }

    private void initData() {
        mSensor = new SensorUtil(getActivity());
        mDetector = new Detector(getActivity());
        mTextureView.setSurfaceTextureListener(this);
        mIDetection = new IDetection(getActivity());
        mIDetection.detectionTypeInit();
        mIMediaPlayer = new IMediaPlayer(getActivity());
    }

    private void initDetectSession() {
        if (mICamera.mCamera == null)
            return;

        mCurStep = 0;
        mCurrentDetectionType = mIDetection.mDetectionSteps.get(mCurStep);

        mDetector.init(mCurrentDetectionType, this);
    }

    private void initCameraParams() {
        if (mICamera == null) {
            mICamera = new ICamera();
        }
        mCamera = mICamera.openCamera(getActivity());
        if (mCamera != null) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_BACK, cameraInfo);
            ViewGroup.LayoutParams param = mICamera.getLayoutParam();
            ViewGroup.LayoutParams maskImageViewParams = mmaskImageView.getLayoutParams();
            maskImageViewParams.width = param.width;
            maskImageViewParams.height = param.width;
            mmaskImageView.setLayoutParams(maskImageViewParams);
            ViewGroup.LayoutParams textureParams = mTextureView.getLayoutParams();
            textureParams.width = param.width;
            textureParams.height = param.height;
            mTextureView.setLayoutParams(textureParams);

            mMaskLayout.setMinimumHeight(param.height);
        }
    }

    protected void findViews() {
        mmaskImageView = getActivity().findViewById(R.id.mask_view);
        mTextureView = getActivity().findViewById(R.id.camera_texture_view);
        mTipImageView = getActivity().findViewById(R.id.tip_image_view);
        mBackView = getActivity().findViewById(R.id.back_view_camera_activity);
        mTipTextView = getActivity().findViewById(R.id.tip_text_view);
        mMaskLayout = getActivity().findViewById(R.id.mask_layout);
        mTimerView = getActivity().findViewById(R.id.timer_text_view_camera_activity);
        mProgressLayout = getActivity().findViewById(R.id.progress_layout);
        mDetectionLayout = getActivity().findViewById(R.id.detection_layout);
        mTipImageLayout = getActivity().findViewById(R.id.tip_image_layout);
        mVoiceCheckBox = getActivity().findViewById(R.id.voice_check_box);
        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        mVoiceCheckBox.setChecked(IMediaPlayer.isPlayEnable());
        mVoiceCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mIMediaPlayer != null) {
                    mIMediaPlayer.setPlayEnable(isChecked);
                }
                if (isChecked) {
                    playSound(getSoundRes(mCurrentDetectionType));
                }
            }
        });
    }

    // SurfaceTextureListener(start)
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mICamera.startPreview(mTextureView.getSurfaceTexture());
        mDetector.setDetectionListener(this);
        mICamera.actionDetect(this);

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    private boolean mSDKDetected;

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        if (mSensor.isVertical()) {
            if (!mSDKDetected) {
                mSDKDetected = true;
                initDetectSession();
            }
        }

    }
    //SurfaceTextureListener(end)

    //Camera的PreviewCallback(start)
    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Camera.Size previewSize = mICamera.getPreviewSize();
        mDetector.doDetection(data, previewSize);
    }
    //Camera的PreviewCallback(end)

    //Detector回调(start)
    @Override
    public Detector.DetectionType onDetectionSuccess(DetectionFrame validFrame) {
        if (isAdded()) {
            if (mCurStep >= mIDetection.mDetectionSteps.size()) {
                getLivenessData();
            } else {
                changeType(mIDetection.mDetectionSteps.get(mCurStep));
                playSound(getSoundRes(mCurrentDetectionType));
            }
            // 检测器返回值：如果不希望检测器检测则返回DetectionType.DONE，如果希望检测器检测动作则返回要检测的动作
            Detector.DetectionType detectionType = mCurStep >= mIDetection.mDetectionSteps.size() ? Detector.DetectionType.DONE
                    : mIDetection.mDetectionSteps.get(mCurStep);
            mCurStep++;
            return detectionType;
        } else {
            return Detector.DetectionType.DONE;
        }

    }

    /**
     * Get the audio resource id based on the action type
     *
     * @param detectionType action type
     * @return raw id ,err with -1
     */
    public int getSoundRes(Detector.DetectionType detectionType) {
        int resID = -1;
        switch (detectionType) {
            case POS_YAW:
                resID = R.raw.action_turn_head;
                break;
            case MOUTH:
                resID = R.raw.action_open_mouth;
                break;
            case BLINK:
                resID = R.raw.action_blink;
                break;
        }
        return resID;
    }

    public Detector.DetectionType mCurrentDetectionType;

    public void changeType(Detector.DetectionType detectiontype) {
        mCurrentDetectionType = detectiontype;
        if (mHandler != null) {
            AnimationDrawable mCurrentFrameAnimation = mIDetection.getDrawRes(mCurrentDetectionType);
            mTipImageView.setImageDrawable(mCurrentFrameAnimation);
            mCurrentFrameAnimation.start();

        }
    }

    /**
     * play sound
     */
    private void playSound(int rawId) {
        mIMediaPlayer.doPlay(rawId, true, 1500);
    }

    @Override
    public void onDetectionFailed(Detector.DetectionFailedType failedType) {
        if (isAdded()) {
            switch (failedType) {
                case WEAKLIGHT:
                    changeTipTextView(R.string.liveness_weak_light);
                    break;
                case STRONGLIGHT:
                    changeTipTextView(R.string.liveness_too_light);
                    break;
                default:
                    mDetector.setDetectionListener(null);
                    ResultEntity resultEntity = new ResultEntity();
                    switch (failedType) {
                        case FACEMISSING:
                            switch (mCurrentDetectionType) {
                                case MOUTH:
                                case BLINK:
                                    resultEntity.message = getString(R.string.liveness_failed_reason_facemissing_blink_mouth);
                                    break;
                                case POS_YAW:
                                    resultEntity.message = getString(R.string.liveness_failed_reason_facemissing_pos_yaw);
                                    break;
                            }
                            break;
                        case TIMEOUT:
                            resultEntity.message = getString(R.string.liveness_failed_reason_timeout);
                            break;
                        case MULTIPLEFACE:
                            resultEntity.message = getString(R.string.liveness_failed_reason_multipleface);
                            break;
                        case MUCHMOTION:
                            resultEntity.message = getString(R.string.liveness_failed_reason_muchaction);
                            break;
                    }
                    toResultActivity(resultEntity);
                    break;
            }
        }
    }

    public void toResultActivity(ResultEntity entity) {
        Intent intent = new Intent(getActivity(), ResultActivity.class);
        intent.putExtra(ResultActivity.EXTRA_DATA, entity);
        startActivity(intent);
        getActivity().finish();
    }

    private void changeTipTextView(int strResId) {
        mTipTextView.setText(strResId);
    }

    private void changeTipTextView(String str) {
        mTipTextView.setText(str);
    }


    private void updateTipUIView(DetectionFrame detectionFrame) {
        if (mHandler != null) {
            mTimerView.setBackgroundResource(TextUtils.isEmpty(mTimerView.getText()) ? 0 : R.drawable.liveness_shape_right_timer);
            if (!mSensor.isVertical()) {//phone not vertical
                changeTipTextView(R.string.liveness_hold_phone_vertical);
            } else {
                if (detectionFrame != null) {
                    switch (detectionFrame.getFaceWarnCode()) {
                        case FACEMISSING:
                            changeTipTextView(R.string.liveness_no_people_face);
                            break;
                        case FACESMALL:
                            changeTipTextView(R.string.liveness_tip_move_closer);
                            break;
                        case FACELARGE:
                            changeTipTextView(R.string.liveness_tip_move_furthre);
                            break;
                        case FACENOTCENTER:
                            changeTipTextView(R.string.liveness_move_face_center);
                            break;
                        case FACENOTFRONTAL:
                            changeTipTextView(R.string.liveness_frontal);
                            break;
                        case FACENOTSTILL:
                        case FACECAPTURE:
                            changeTipTextView(R.string.liveness_still);
                            break;
                        case FACEINACTION:
                            showActionTipUIView();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    /**
     * show current action tips
     */
    private void showActionTipUIView() {
        if (mCurrentDetectionType != null) {
            changeTipTextView(mIDetection.getDetectionName(mCurrentDetectionType));
            AnimationDrawable anim = mIDetection.getDrawRes(mCurrentDetectionType);
            mTipImageView.setImageDrawable(anim);
        }
    }

    @Override
    public void onFrameDetected(DetectionFrame detectionFrame) {
        if (mHandler != null && isAdded()) {
            updateTipUIView(detectionFrame);
        }
    }

    @Override
    public void onDetectionTimeout(long timeout) {
        if (mHandler != null && isAdded()) {
            final int mills = (int) (timeout / 1000);
            mTimerView.setText(mills + "s");
        }
    }

    //Detector callback(end)
    private void getLivenessData() {
        if (mIMediaPlayer != null) {
            mIMediaPlayer.close();
        }
        if (mHandler != null) {
            //显示loadingUI
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mTipImageLayout.setVisibility(View.GONE);
                    mDetectionLayout.setVisibility(View.GONE);
                    mTimerView.setVisibility(View.GONE);
                    mProgressLayout.setVisibility(View.VISIBLE);
                }
            });
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ResultEntity faceMetaData = mDetector.getFaceMetaData();
                if (mHandler != null) {
                    if (!faceMetaData.success && NO_RESPONSE.equals(faceMetaData.code)) {
                        faceMetaData.message = getString(R.string.liveness_failed_reason_bad_network);
                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            handleResult(faceMetaData);
                        }
                    });
                }
            }
        }).start();
    }

    /**
     * to ResultActivity
     */
    private void handleResult(ResultEntity entity) {
        if (entity != null) {
            toResultActivity(entity);
        }
    }

    @Override
    public void onDetach() {
        release();
        super.onDetach();

    }

    @Override
    public void onDetectorInitStart() {
        if (mHandler != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mInitProgressDialog = new ProgressDialog(getContext());
                    mInitProgressDialog.setMessage(getString(R.string.liveness_auth_check));
                    mInitProgressDialog.setCanceledOnTouchOutside(false);
                    mInitProgressDialog.show();
                }
            });
        }
    }

    /**
     * 释放 fragment 资源
     */
    public void release() {
        if (mInitProgressDialog != null) {
            mInitProgressDialog.dismiss();
        }
        if (mIMediaPlayer != null) {
            mIMediaPlayer.close();
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        mHandler = null;
        if (mICamera != null) {
            mICamera.closeCamera();
        }
        mCamera = null;
        if (mDetector != null) {
            mDetector.release();
        }
        if (mSensor != null) {
            mSensor.release();
        }

    }

    @Override
    public void onDetectorInitComplete(final boolean isValid, final String errorCode, final String message) {
        if (mHandler != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (mInitProgressDialog != null) {
                        mInitProgressDialog.dismiss();
                    }
                    if (isValid) {
                        mCurStep++;
                        updateTipUIView(null);
                    } else {
                        final String errorMessage;
                        if (NO_RESPONSE.equals(errorCode)) {
                            errorMessage = getString(R.string.liveness_failed_reason_auth_failed);
                        } else {
                            errorMessage = message;
                        }
                        new AlertDialog.Builder(getActivity()).setMessage(errorMessage).setPositiveButton(R.string.liveness_perform, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ResultEntity resultEntity = new ResultEntity();
                                resultEntity.message = errorMessage;
                                toResultActivity(resultEntity);
                                dialog.dismiss();
                            }
                        }).create().show();
                    }
                }
            });
        }
    }

    @Override
    public void onFaceReady() {
        if (isAdded()) {
            playSound(getSoundRes(mCurrentDetectionType));
            if (mHandler != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        changeType(mCurrentDetectionType);
                        mVoiceCheckBox.setVisibility(View.VISIBLE);
                    }
                });
            }
        }
    }

}
