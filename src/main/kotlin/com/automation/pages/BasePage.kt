package com.automation.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.slf4j.LoggerFactory

abstract class BasePage(protected val driver: WebDriver) {
    protected val logger = LoggerFactory.getLogger(javaClass)

    init {
        PageFactory.initElements(driver, this)
        logger.info("${this::class.simpleName} initialized with PageFactory")
    }

    fun getCurrentUrl(): String {
        return driver.currentUrl
    }

    fun getPageTitle(): String {
        return driver.title
    }

    fun navigateTo(url: String) {
        logger.info("Navigating to: $url")
        driver.navigate().to(url)
    }
}

