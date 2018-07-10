package com.example.dida;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by herr.wang on 2017/6/16.
 * <p>
 * make sure remove all the message bound to this out of its lifecycle
 */

public class UIHandler {
    private volatile static Handler handler;

    public static Handler get() {
        if (handler == null) {
            synchronized (UIHandler.class) {
                if (handler == null) {
                    handler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return handler;
    }

    public static void post(Runnable task) {
        get().post(task);
    }

    public static void post(Runnable task, long delayMillis) {
        get().postDelayed(task,delayMillis);
    }

    public static boolean isUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
