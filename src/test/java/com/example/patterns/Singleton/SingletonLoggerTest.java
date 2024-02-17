package com.example.patterns.Singleton;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class SingletonLoggerTest {

    @Test
    public void getInstance() {
        SingletonLogger logger1 = SingletonLogger.getInstance();
        SingletonLogger logger2 = SingletonLogger.getInstance();

        assertSame(logger1, logger2);
    }

    @Test
    public void setLogLevel() {
        SingletonLogger logger1 = SingletonLogger.getInstance();
        SingletonLogger logger2 = SingletonLogger.getInstance();

        logger1.setLogLevel(SingletonLogger.LogLevel.DEBUG);
        assertEquals(SingletonLogger.LogLevel.DEBUG, logger1.getLogLevel());
        assertEquals(logger1.getLogLevel(), logger2.getLogLevel());

        logger2.setLogLevel(SingletonLogger.LogLevel.WARN);
        assertEquals(SingletonLogger.LogLevel.WARN, logger2.getLogLevel());
        assertEquals(logger2.getLogLevel(), logger1.getLogLevel());
    }

    @Test
    public void testLogs() {
        SingletonLogger logger = SingletonLogger.getInstance();

        logger.setLogLevel(SingletonLogger.LogLevel.TRACE);

        List<String> messages = List.of(
                "This is a DEBUG message",
                "This is an INFO message",
                "This is a WARN message",
                "This is an ERROR message"
        );


        logger.debug(messages.get(0));
        logger.info(messages.get(1));
        logger.warn(messages.get(2));
        logger.error(messages.get(3));

        List<String> logs = logger.getLogs();
        assertEquals(logs.size(), messages.size());
        for(int i = 0; i < logs.size(); ++i) {
            assertTrue(logs.get(i).contains(messages.get(i)));
        }

    }

}