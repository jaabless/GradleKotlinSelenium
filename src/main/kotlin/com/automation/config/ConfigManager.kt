package com.automation.config

import org.slf4j.LoggerFactory

object ConfigManager {
    private val logger = LoggerFactory.getLogger(javaClass)

    val baseUrl: String = System.getenv("BASE_URL") ?: "https://playwright.dev/"
    val browser: String = System.getenv("BROWSER") ?: "chrome"
    val isHeadless: Boolean = System.getenv("HEADLESS")?.toBoolean() ?: false
    val implicitWait: Long = System.getenv("IMPLICIT_WAIT")?.toLong() ?: 10
    val pageLoadTimeout: Long = System.getenv("PAGE_LOAD_TIMEOUT")?.toLong() ?: 30

    init {
        logger.info("Configuration loaded:")
        logger.info("Base URL: $baseUrl")
        logger.info("Browser: $browser")
        logger.info("Headless: $isHeadless")
    }
}

