package com.zqs_permission_util;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

public class PermissionActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvAboutPermission;
    String   permissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        initView();
    }

    private void initView() {
        tvAboutPermission = findViewById(R.id.tv_about_permission);
        findViewById(R.id.btn_open_app_settings).setOnClickListener(this);
        findViewById(R.id.btn_request_calendar).setOnClickListener(this);
        findViewById(R.id.btn_request_record_audio).setOnClickListener(this);

        StringBuilder sb = new StringBuilder();
        for (String s : PermissionUtils.getPermissions()) {
            sb.append(s.substring(s.lastIndexOf('.') +1 )).append('\n');
        }
        permissions = sb.toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAboutPermission();
    }

    private void updateAboutPermission() {
        tvAboutPermission.setText(permissions);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open_app_settings:
                PermissionUtils.launchAppDetailsSettings();
                break;
            case R.id.btn_request_calendar:
                PermissionUtils.permission(PermissionConstants.CALENDAR)
                        .rationale(new PermissionUtils.OnRationaleListener() {
                            //拒绝一次, 弹权限的解释
                            @Override
                            public void rationale(ShouldRequest shouldRequest) {
                                DialogHelper.showRationaleDialog(shouldRequest);
                            }
                        })
                        .callback(new PermissionUtils.FullCallback() {
                            @Override
                            public void onGranted(List<String> permissionsGranted) {

                            }

                            //不在提醒勾选后,forver否定权限,进行另一个解释
                            @Override
                            public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                                if (!permissionsDeniedForever.isEmpty()) {
                                    DialogHelper.showOpenAppSettingDialog();
                                }
                                Log.d("zqs", permissionsDenied.toString());
                            }
                        })
                        .theme(new PermissionUtils.ThemeCallback() {
                            @Override
                            public void onActivityCreate(Activity activity) {
                                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                                        | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                            }
                        })
                        .request();
                break;
            case R.id.btn_request_record_audio:
                PermissionUtils.permission(PermissionConstants.MICROPHONE)
                        .rationale(new PermissionUtils.OnRationaleListener() {
                            @Override
                            public void rationale(final ShouldRequest shouldRequest) {
                                DialogHelper.showRationaleDialog(shouldRequest);
                            }
                        })
                        .callback(new PermissionUtils.FullCallback() {
                            @Override
                            public void onGranted(List<String> permissionsGranted) {
                                updateAboutPermission();
                            }

                            @Override
                            public void onDenied(List<String> permissionsDeniedForever,
                                                 List<String> permissionsDenied) {
                                if (!permissionsDeniedForever.isEmpty()) {
                                    DialogHelper.showOpenAppSettingDialog();
                                }
                            }
                        })
                        .request();
                break;
        }
    }
}
