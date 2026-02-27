package com.automation.utils

import org.slf4j.LoggerFactory

object WaitUtils {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun waitSeconds(seconds: Long) {
        logger.debug("Waiting for $seconds seconds...")
        Thread.sleep(seconds * 1000)
    }

    fun waitMillis(millis: Long) {
        logger.debug("Waiting for $millis milliseconds...")
        Thread.sleep(millis)
    }
}

