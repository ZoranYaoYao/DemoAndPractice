package com.example.notification_demo.toast;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notification_demo.BaseApplication;
import com.example.notification_demo.MainActivity;
import com.example.notification_demo.R;
import com.example.notification_demo.SecondActivity;

import org.json.JSONObject;

import java.lang.reflect.Field;

/**
 * 应用在前台时接收到push展示横幅
 */

public class TipUtil {

    private static Toast toast;

    public static void show(final Context context, final String content, final String title, final JSONObject extra, final String trackerMsg) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (toast == null) {
                    toast = new Toast(context.getApplicationContext());
                }
                View rootView = LayoutInflater.from(context).inflate(R.layout.toast, null);
                rootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        MsgCenter.getInstance().openLoadingPage(context, extra, MsgCenter.MSG_TYPE_PUSH, trackerMsg);
                        //singleTop 模拟后台跳转清空
//                        startHomeActivity(context, new Intent());
                        /**
                         * info1: 后台启动时，需要standard标准的activity时复用
                         * 只能是对栈顶的复用
                         * intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_SINGLE_TOP);
                         * */
                        //service
                        Log.e("zqs", "start SecondActivity");
                        Intent intent = new Intent(context, SecondActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                        context.startActivity(intent);

                        /***
                         * ✨✨✨✨Info2:
                         * 后台弹框延迟5秒问题：
                         * https://blog.csdn.net/LikeSidehu/article/details/83041422
                         * 需要用PendingIntent 进行规避google 5秒延迟问题
                         */
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                        try {
                            pendingIntent.send();
                        } catch (PendingIntent.CanceledException e) {
                            e.printStackTrace();
                        }
                        toast.cancel();
                    }
                });
                TextView tv_title = (TextView) rootView.findViewById(R.id.messageTitle);
                TextView tv_content = (TextView) rootView.findViewById(R.id.messageContent);
                if (TextUtils.isEmpty(title)) {
                    tv_title.setVisibility(View.GONE);
                } else {
                    if (title.equals("嘀嗒出行")) {
                        tv_title.setVisibility(View.GONE);
                    } else {
                        tv_title.setVisibility(View.VISIBLE);
                        tv_title.setText(title);
                    }
                }
                tv_content.setText(content);
                toast.setView(rootView);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
                try {
                    Object mTN;
                    mTN = getField(toast, "mTN");
                    if (mTN != null) {
                        Object mParams = getField(mTN, "mParams");
                        if (mParams != null
                                && mParams instanceof WindowManager.LayoutParams) {
                            WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
                            /**
                             * Toast可点击
                             * WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                             * WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                             * */
                            params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                            | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                            //WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                            //| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            //设置viewgroup宽高
                            params.width = WindowManager.LayoutParams.MATCH_PARENT; //设置Toast宽度为屏幕宽度
                            params.height = WindowManager.LayoutParams.WRAP_CONTENT; //设置高度
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                toast.show();
            }
        });
    }

    /**
     * 反射字段
     *
     * @param object    要反射的对象
     * @param fieldName 要反射的字段名称
     */
    private static Object getField(Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            return field.get(object);
        }
        return null;
    }

    public static void hideToast() {
        if(toast != null) {
            toast.cancel();
        }
    }


    /**
     * @param context
     * @param intent
     */
    public static void startHomeActivity(Context context, Intent intent) {
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        }
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
//        if (context instanceof Activity) {
//            playActivityEnterAnimation((Activity) context);
//        }
    }
}
