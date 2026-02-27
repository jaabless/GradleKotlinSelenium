package com.automation.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

/**
 * Template page object for creating new page objects.
 * Copy this file and modify for your specific page.
 *
 * Benefits of Page Object Model:
 * - Encapsulation: Page logic isolated from tests
 * - Maintainability: Changes to page locators only affect page object
 * - Reusability: Common methods used across multiple tests
 * - Readability: Test code reads like natural language
 */
class ExamplePageTemplate(driver: WebDriver) : BasePage(driver) {

    // ==================== Web Elements ====================
    // Use @FindBy annotation for element location
    // Supports: id, name, className, css, xpath, linkText, partialLinkText, tagName

    @FindBy(id = "example_id")
    private lateinit var exampleElement: WebElement

    @FindBy(css = ".example-class")
    private lateinit var exampleByClass: WebElement

    @FindBy(xpath = "//button[@type='submit']")
    private lateinit var submitButton: WebElement

    // ==================== Page Methods ====================
    // These methods encapsulate page interactions
    // Keep methods focused and single-responsibility

    /**
     * Click on example element
     */
    fun clickExample() {
        logger.info("Clicking example element...")
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until(ExpectedConditions.elementToBeClickable(exampleElement))
        exampleElement.click()
        logger.info("Example element clicked successfully")
    }

    /**
     * Submit form by clicking submit button
     */
    fun submitForm() {
        logger.info("Submitting form...")
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until(ExpectedConditions.elementToBeClickable(submitButton))
        submitButton.click()
        logger.info("Form submitted successfully")
    }

    /**
     * Check if element is visible
     */
    fun isExampleElementVisible(): Boolean {
        logger.info("Checking if example element is visible...")
        return try {
            val wait = WebDriverWait(driver, Duration.ofSeconds(5))
            wait.until(ExpectedConditions.visibilityOf(exampleElement))
            true
        } catch (e: Exception) {
            logger.warn("Example element is not visible")
            false
        }
    }

    /**
     * Get text from element
     */
    fun getExampleText(): String {
        logger.info("Getting text from example element...")
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until(ExpectedConditions.visibilityOf(exampleElement))
        return exampleElement.text
    }

    /**
     * Type text in element (assumes it's an input field)
     */
    fun enterTextInExample(text: String) {
        logger.info("Entering text in example element: $text")
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until(ExpectedConditions.visibilityOf(exampleElement))
        exampleElement.clear()
        exampleElement.sendKeys(text)
        logger.info("Text entered successfully")
    }
}

