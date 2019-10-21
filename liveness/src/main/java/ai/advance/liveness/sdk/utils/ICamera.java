package ai.advance.liveness.sdk.utils;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.view.Surface;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import ai.advance.common.utils.LogUtil;
import ai.advance.common.utils.ScreenUtil;

/**
 * Camera tools
 */
public class ICamera {
    public static final int cameraId = CameraInfo.CAMERA_FACING_FRONT;//Front camera

    public Camera mCamera;
    public int cameraWidth;
    public int cameraHeight;

    public static final float WIDTH_PERCENT_IN_SCREEN = 0.8f;

    public ICamera() {
    }

    private Camera.Size mPreviewSize;

    /**
     * Open the camera
     *
     * @param activity current Activity
     * @return front camera
     */
    public Camera openCamera(Activity activity) {
        try {
            mCamera = Camera.open(cameraId);
            CameraInfo cameraInfo = new CameraInfo();
            Camera.getCameraInfo(cameraId, cameraInfo);
            Camera.Parameters params = mCamera.getParameters();
//            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            mPreviewSize = calBestPreviewSize(mCamera.getParameters());
            cameraWidth = mPreviewSize.width;
            cameraHeight = mPreviewSize.height;
            params.setPreviewSize(cameraWidth, cameraHeight);
            mCamera.setDisplayOrientation(getCameraAngle(activity));
            mCamera.setParameters(params);
//            mCamera.cancelAutoFocus();
            return mCamera;
        } catch (Exception e) {
            return null;
        }
    }

    public Camera.Size getPreviewSize() {
        return mPreviewSize;
    }

    /**
     * Calculate layout parameters by screen parameters and camera preview size
     */
    public ViewGroup.LayoutParams getLayoutParam() {
        Camera.Size previewSize = mCamera.getParameters().getPreviewSize();
        int width = (int) (ScreenUtil.mScreenWidth * WIDTH_PERCENT_IN_SCREEN);
        int height = (int) (width * (previewSize.width / (float) previewSize.height));
        return new ViewGroup.LayoutParams(
                width, height);
    }

    /**
     * Start face detection
     *
     * @param callback the Preview callback
     */
    public void actionDetect(Camera.PreviewCallback callback) {
        if (mCamera != null) {
            mCamera.setPreviewCallback(callback);
        }
    }

    /**
     * preview
     *
     * @param surfaceTexture Attached textureView
     */
    public void startPreview(SurfaceTexture surfaceTexture) {
        if (mCamera != null) {
            try {
                mCamera.setPreviewTexture(surfaceTexture);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Turn off the camera and free up resources
     */
    public void closeCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    /**
     * Calculate the camera size closest to the value of the width and height passed in
     */
    private Camera.Size calBestPreviewSize(Camera.Parameters camPara) {
        //垂直选ratio最大的
        List<Camera.Size> allSupportedSize = camPara.getSupportedPreviewSizes();
        Camera.Size mBestSize = null;
        final float expectRatio = 1.25f;
        float currentBestRatio = Integer.MAX_VALUE;
        for (Camera.Size tmpSize : allSupportedSize) {
            float scale = tmpSize.width / (float) ScreenUtil.mScreenHeight;
            float ratio = tmpSize.width / (float) tmpSize.height;
            LogUtil.debugE("布局参数", "scale=" + scale + "," + tmpSize.width + "*" + tmpSize.height);

            if (scale < 0.55 || tmpSize.width < tmpSize.height) {//exclude low dpi and wrong w/h
                continue;
            }
            if (Math.abs(ratio - expectRatio) < Math.abs(currentBestRatio - expectRatio)) {
                currentBestRatio = ratio;
                mBestSize = tmpSize;
            }
        }
        return mBestSize;
    }


    /**
     * Obtain camera rotation Angle
     */
    public int getCameraAngle(Activity activity) {
        int rotateAngle = 90;
        CameraInfo info = new CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        if (info.facing == cameraId) {
            rotateAngle = (info.orientation + degrees) % 360;
            rotateAngle = (360 - rotateAngle) % 360; // compensate the mirror
        } else { // back-facing
            rotateAngle = (info.orientation - degrees + 360) % 360;
        }
        return rotateAngle;
    }
}