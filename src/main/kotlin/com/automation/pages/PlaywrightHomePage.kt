package com.automation.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class PlaywrightHomePage(driver: WebDriver) : BasePage(driver) {

    @FindBy(xpath = "//button[contains(text(), 'Language')]")
    private lateinit var languageButton: WebElement

    @FindBy(xpath = "//a[contains(text(), 'Java')]")
    private lateinit var javaOption: WebElement

    @FindBy(xpath = "//a[contains(text(), 'Get started')]")
    private lateinit var getStartedLink: WebElement

    fun hoverLanguageOption() {
        logger.info("Hovering over Language option...")
        val actions = Actions(driver)
        actions.moveToElement(languageButton).perform()
        Thread.sleep(500) // Allow hover to take effect
        logger.info("Language option hovered successfully")
    }

    fun clickJavaOption() {
        logger.info("Clicking Java option...")
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until(ExpectedConditions.elementToBeClickable(javaOption))
        javaOption.click()
        logger.info("Java option clicked successfully")
    }

    fun clickGetStarted() {
        logger.info("Clicking Get started link...")
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until(ExpectedConditions.elementToBeClickable(getStartedLink))
        getStartedLink.click()
        logger.info("Get started link clicked successfully")
    }

    fun isTextVisible(text: String): Boolean {
        logger.info("Checking if text is visible: $text")
        return try {
            val wait = WebDriverWait(driver, Duration.ofSeconds(5))
            val escapedText = text.replace("'", "\\'")
            val xpathLocator = "//*[contains(text(), \"$escapedText\")]"
            val element = wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath(xpathLocator)
            ))
            element.isDisplayed
        } catch (e: Exception) {
            logger.warn("Text not found or not visible: $text")
            false
        }
    }

    fun isTextNotVisible(text: String): Boolean {
        logger.info("Checking if text is NOT visible: $text")
        return !isTextVisible(text)
    }
}


