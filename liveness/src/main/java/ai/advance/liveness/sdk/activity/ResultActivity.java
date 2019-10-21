package ai.advance.liveness.sdk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.win.base_module.MainActivity;
import com.win.base_module.RouteUtils;

import org.json.JSONException;
import org.json.JSONObject;

import ai.advance.common.IMediaPlayer;
import ai.advance.liveness.lib.http.entity.ResultEntity;
import ai.advance.liveness.sdk.R;

public class ResultActivity extends AppCompatActivity {
    public static final String EXTRA_DATA = "data";
    private ImageView mResultImageView;
    private TextView mResultTextView;
    private TextView mTipTextView;
    private View mTryAgainView;
    private View mRootView;
    private IMediaPlayer mIMediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        findViews();
        initViews();
    }

    private void findViews() {
        mResultImageView = findViewById(R.id.detection_result_image_view);
        mResultTextView = findViewById(R.id.detection_result_text_view);
        mTryAgainView = findViewById(R.id.try_again_view);
        mRootView = findViewById(R.id.root_view_activity_result);
        mTipTextView = findViewById(R.id.detection_tip_text_view);
    }

    /**
     * 04-08 14:29:52.231 24233-24233/com.yndk.yndk I/cvffgfgfn: initViews: id{"livenessId":"cb3e843a-599e-4637-9c1c-b4ec4dd7d196"}
     */
    private void initViews() {
        int lightColor = getResources().getColor(R.color.liveness_color_light);
        int accentColor = getResources().getColor(R.color.liveness_accent);
        mRootView.setBackgroundColor(getResources().getColor(lightColor == accentColor ? R.color.liveness_camera_bg_light : R.color.liveness_camera_bg));
        Intent intent = getIntent();
        ResultEntity entity = intent.getParcelableExtra(EXTRA_DATA);
        boolean isSuccess = entity.success;
        try {
            if (isSuccess) {
                Log.i("TAG","成功");
                mTipTextView.setText("Liveness score：" + entity.livenessScore);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(entity.data);
                    String livenessId = jsonObject.getString("livenessId");
                    Log.i("cvffgfgfn", "initViews: id"+ jsonObject);
                    commit(livenessId);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
/*            // 获取 bitmap 格式的图片
            Bitmap bitmap = LivenessBitmapCache.getLivenessBitmap();
            // 获取 base64 格式的图片
            String base64Str = LivenessBitmapCache.getLivenessBase64Str();
            // 获取图片之后可以调用此方法释放资源
            LivenessBitmapCache.clearCache();*/
            } else {
                mTipTextView.setText(entity.message);
            }
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT);
        }

        mResultImageView.setImageResource(isSuccess ? R.drawable.icon_liveness_success : R.drawable.icon_liveness_fail);
        mResultTextView.setText(isSuccess ? R.string.liveness_detection_success : R.string.liveness_detection_fail);

        mTryAgainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, LivenessActivity.class));
                finish();
            }
        });
        mIMediaPlayer = new IMediaPlayer(this);
        mIMediaPlayer.doPlay(isSuccess ? R.raw.detection_success : R.raw.detection_failed, false, 0);
    }

    private void commit(String livenessId) {
        Log.i("TAG","成功人脸");
        ARouter.getInstance().build(RouteUtils.User_Activity_Auth).withString("faceId",livenessId).navigation();
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("faceId",livenessId);
//        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if (mIMediaPlayer != null) {
            mIMediaPlayer.close();
        }
        super.onDestroy();
    }
}
