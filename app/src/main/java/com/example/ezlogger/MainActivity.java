package com.example.ezlogger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the CustomLogger with the application context
        CustomLogger.initialize(getApplicationContext());

        // Enable log filtering and set the minimum log level
        CustomLogger.setLogFilteringEnabled(true);
        CustomLogger.setMinimumLogLevel(CustomLogger.LogLevel.DEBUG);

        // Log messages with different levels
        CustomLogger.d("This is a debug message.");
        CustomLogger.i("This is an info message.");
        CustomLogger.w("This is a warning message.");
        CustomLogger.e("This is an error message.");
        CustomLogger.v("This is a verbose message.");
        CustomLogger.a("This is an assert message.");

        // Get the stored log messages
        String logs = CustomLogger.getLogs();
        System.out.println("Stored Logs:\n" + logs);

        // Clear the stored logs
        CustomLogger.clearLogs();
    }
}
