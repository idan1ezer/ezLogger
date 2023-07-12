package com.example.ezlogger;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomLogger {
    private static final String TAG = "CustomLogger";
    private static final String PREF_NAME = "CustomLoggerPrefs";
    private static final String KEY_LOGS = "logs";
    private static boolean loggingEnabled = true;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private static SharedPreferences sharedPreferences;


    private static boolean logFilteringEnabled = false;
    private static LogLevel minimumLogLevel = LogLevel.DEBUG;


    public enum LogLevel {
        DEBUG("\u001B[36m"),   // Cyan color
        INFO("\u001B[32m"),    // Green color
        WARNING("\u001B[33m"), // Yellow color
        ERROR("\u001B[31m"),   // Red color
        VERBOSE("\u001B[37m"), // White color
        ASSERT("\u001B[35m");  // Purple color

        private final String colorCode;

        LogLevel(String colorCode) {
            this.colorCode = colorCode;
        }

        public String getColorCode() {
            return colorCode;
        }
    }

    public static void initialize(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void setLoggingEnabled(boolean enabled) {
        loggingEnabled = enabled;
    }

    public static void setLogFilteringEnabled(boolean enabled) {
        logFilteringEnabled = enabled;
    }

    public static void setMinimumLogLevel(LogLevel level) {
        minimumLogLevel = level;
    }

    public static void d(String message) {
        log(LogLevel.DEBUG, message);
    }

    public static void i(String message) {
        log(LogLevel.INFO, message);
    }

    public static void w(String message) {
        log(LogLevel.WARNING, message);
    }

    public static void e(String message) {
        log(LogLevel.ERROR, message);
    }

    public static void v(String message) {
        log(LogLevel.VERBOSE, message);
    }

    public static void a(String message) {
        log(LogLevel.ASSERT, message);
    }

    public static void log(LogLevel level, String message) {
        if (loggingEnabled && isLogLevelEnabled(level)) {
            String logMessage = formatLogMessage(message, level);
            Log.println(levelToPriority(level), TAG, logMessage);
            saveLogMessage(logMessage);
        }
    }

    public static void clearLogs() {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_LOGS, "");
            editor.apply();
        }
    }

    public static String getLogs() {
        if (sharedPreferences != null) {
            return sharedPreferences.getString(KEY_LOGS, "");
        }
        return "";
    }

    private static void saveLogMessage(String logMessage) {
        if (sharedPreferences != null) {
            String logs = sharedPreferences.getString(KEY_LOGS, "");
            logs += logMessage + "\n";
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_LOGS, logs);
            editor.apply();
        }
    }

    private static String formatLogMessage(String message, LogLevel level) {
        String timestamp = dateFormat.format(new Date());
        String colorCode = level.getColorCode();
        return String.format("%s[%s] [%s]: %s\u001B[0m", colorCode, timestamp, level.toString(), message);
    }

    private static int levelToPriority(LogLevel level) {
        switch (level) {
            case DEBUG:
                return Log.DEBUG;
            case INFO:
                return Log.INFO;
            case WARNING:
                return Log.WARN;
            case ERROR:
                return Log.ERROR;
            case VERBOSE:
                return Log.VERBOSE;
            case ASSERT:
                return Log.ASSERT;
            default:
                return Log.DEBUG;
        }
    }

    private static boolean isLogLevelEnabled(LogLevel level) {
        if (!logFilteringEnabled) {
            return true;
        }

        return level.ordinal() >= minimumLogLevel.ordinal();
    }

}
