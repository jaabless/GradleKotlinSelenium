package com.automation.driver

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.slf4j.LoggerFactory
import com.automation.config.ConfigManager

object DriverManager {
    private val logger = LoggerFactory.getLogger(javaClass)
    private var driver: WebDriver? = null

    fun getDriver(): WebDriver {
        if (driver == null) {
            driver = initDriver()
        }
        return driver!!
    }

    private fun initDriver(): WebDriver {
        return when (ConfigManager.browser.lowercase()) {
            "chrome" -> initChromeDriver()
            "firefox" -> initFirefoxDriver()
            else -> {
                logger.warn("Unknown browser: ${ConfigManager.browser}. Using Chrome as default.")
                initChromeDriver()
            }
        }
    }

    private fun initChromeDriver(): WebDriver {
        logger.info("Initializing Chrome WebDriver...")
        WebDriverManager.chromedriver().setup()
        val options = ChromeOptions().apply {
            if (ConfigManager.isHeadless) {
                addArguments("--headless")
            }
            addArguments("--no-sandbox")
            addArguments("--disable-dev-shm-usage")
        }
        return ChromeDriver(options).also {
            logger.info("Chrome WebDriver initialized successfully")
        }
    }

    private fun initFirefoxDriver(): WebDriver {
        logger.info("Initializing Firefox WebDriver...")
        WebDriverManager.firefoxdriver().setup()
        val options = FirefoxOptions().apply {
            if (ConfigManager.isHeadless) {
                addArguments("--headless")
            }
        }
        return FirefoxDriver(options).also {
            logger.info("Firefox WebDriver initialized successfully")
        }
    }

    fun quitDriver() {
        if (driver != null) {
            driver?.quit()
            driver = null
            logger.info("WebDriver closed successfully")
        }
    }

    fun closeDriver() {
        if (driver != null) {
            driver?.close()
            logger.info("WebDriver window closed")
        }
    }
}

