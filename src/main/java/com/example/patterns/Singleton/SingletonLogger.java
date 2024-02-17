package com.example.patterns.Singleton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SingletonLogger {
    public enum LogLevel {
        TRACE(0),
        DEBUG(1),
        INFO(2),
        WARN(3),
        ERROR(4);

        final int level;

        LogLevel(int level){
            this.level = level;
        }
    }

    // Logger properties
    private LogLevel logLevel;
    private List<String> logs;

    // Private constructor instead of the usual public
    private SingletonLogger() {
        logLevel = LogLevel.INFO;
        logs = new ArrayList<>();
    }

    // Static holder pattern avoids double-check-locking anti-pattern:
    /*  if (_instance == null) {
     *      synchronized (SingletonLogger.class) {
     *          if (_instance == null) {
     *              _instance = new SingletonLogger();
     *          }
     *      }
     *  }
     *  return _instance;
     */
    //It is threadsafe because of JVM lazy initialization and being staic
    private static final class _instanceHolder {
        private static final SingletonLogger _instance = new SingletonLogger();
    }

    // Retreive or create single instance
    public static SingletonLogger getInstance() {
        return _instanceHolder._instance;
    }

    // Anything with ordinal >= this ordinal will log
    public void setLogLevel(LogLevel level) {
        logLevel = level;
    }

    private void log(LogLevel level, String message) {
        String formattedMessage = String.format("[%s] [%s] %s", level, LocalDateTime.now(), message);
        if(level.ordinal() >= logLevel.ordinal()) {
            System.out.println(message);
        }
        logs.add(formattedMessage);
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }
    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    // For testing
    public void displayLogs() {
        for(String log : logs) {
            System.out.println(log);
        }
    }

    public List<String> getLogs() {
        return logs;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }
}
