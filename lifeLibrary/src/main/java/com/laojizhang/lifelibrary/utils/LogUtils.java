package com.laojizhang.lifelibrary.utils;

import android.util.Log;

import com.laojizhang.lifelibrary.BuildConfig;


public class LogUtils {

    private static final String TAG = LogUtils.class.getSimpleName();

    private LogUtils() {
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, msg);
        }
//        LogToFile.getInstance().log(TAG + " : " + msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
//        LogToFile.getInstance().log(tag + " : " + msg);
    }

    public static void invocationPath(String tag, int methodCount) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        String level = "";
        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i;
            if (stackIndex >= trace.length) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            builder.append("║ ")
                    .append(level)
                    .append(getSimpleClassName(trace[stackIndex].getClassName()))
                    .append(".")
                    .append(trace[stackIndex].getMethodName())
                    .append(" ")
                    .append(" (")
                    .append(trace[stackIndex].getFileName())
                    .append(":")
                    .append(trace[stackIndex].getLineNumber())
                    .append(")");
            level += "   ";
//            KCLogger.i(tag, builder.toString());
        }
    }

    private static String getSimpleClassName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
//        LogToFile.getInstance().log(tag + " : " + msg);
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
//        LogToFile.getInstance().log(tag + " : " + msg);
    }

    public static void e(String tag, String msg, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg, throwable);
        }
//        LogToFile.getInstance().log(tag + " : " + msg + " \n throwable = " + (throwable == null ? "null" : throwable.getMessage()));
    }

    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg);
        }
//        LogToFile.getInstance().log(tag + " : " + msg);
    }

    public static void notToFileLog(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg);
//            LogToFile.getInstance().log(tag + " : " + msg);
        }
    }
}

