🔑🔒 This is a Java library for Android development that provides advanced utility functions for logs. 🔒🔑


## Description

The CustomLogger library is a powerful Java library for Android development that provides advanced logging capabilities. It allows you to log messages with different log levels, customize log formats, apply colors to log messages, store logs in shared preferences, and more. The library aims to enhance the logging experience in Android applications, making it easier to debug and track events during development and troubleshooting.


## How To Use

1.  Add the CustomLogger library to your project's dependencies.
    For Gradle:
3.  Initialize the CustomLogger with the application context in your Application class or MainActivity.
4.  Customize the logging settings as per your requirements.
5.  Start logging messages with different log levels using the provided methods.
6.  Retrieve and handle stored logs as needed.


## FAQ

Q: What are the available log levels?
A: The library supports the following log levels: DEBUG, INFO, WARNING, ERROR, VERBOSE, and ASSERT.

Q: Can I enable or disable logging?
A: Yes, you can enable or disable logging using the setLoggingEnabled() method provided by the library.

Q: Can I filter the logs based on log levels?
A: Yes, log filtering is available. You can enable log filtering using the setLogFilteringEnabled() method and set the minimum log level using setMinimumLogLevel().

Q: How are log messages stored?
A: By default, log messages are stored in shared preferences. You can retrieve the stored logs using the getLogs() method and clear the logs using clearLogs().

Q: Can I customize the log format?
A: Yes, you can customize the log format by modifying the formatLogMessage() method in the CustomLogger class. You can also customize timestamp formats, log colors, and other aspects based on your needs.
