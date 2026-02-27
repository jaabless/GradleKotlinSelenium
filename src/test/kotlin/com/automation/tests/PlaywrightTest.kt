package com.automation.tests

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import com.automation.config.ConfigManager
import com.automation.driver.DriverManager
import com.automation.pages.PlaywrightHomePage

class PlaywrightTest {
    private val logger = LoggerFactory.getLogger(javaClass)
    private lateinit var homePage: PlaywrightHomePage

    @BeforeEach
    fun setUp() {
        logger.info("Setting up test...")
        val driver = DriverManager.getDriver()
        driver.navigate().to(ConfigManager.baseUrl)
        homePage = PlaywrightHomePage(driver)
        logger.info("Test setup completed")
    }

    @AfterEach
    fun tearDown() {
        logger.info("Tearing down test...")
        DriverManager.quitDriver()
        logger.info("Test teardown completed")
    }

    @Test
    @DisplayName("Test Playwright Java Documentation Navigation")
    fun testPlaywrightJavaNavigation() {
        logger.info("===== Starting Playwright Java Navigation Test =====")

        try {
            // Step 1: Navigate to Playwright website
            logger.info("Step 1: Opening Playwright website...")
            val currentUrl = homePage.getCurrentUrl()
            logger.info("Current URL: $currentUrl")
            assert(currentUrl.contains("playwright.dev", ignoreCase = true)) {
                "URL should contain 'playwright.dev', but got: $currentUrl"
            }

            // Step 2: Mouse hover the language option
            logger.info("Step 2: Hovering over language option...")
            homePage.hoverLanguageOption()

            // Step 3: Click on the "Java" option
            logger.info("Step 3: Clicking Java option...")
            homePage.clickJavaOption()

            // Step 4: Click on the "Get started" link
            logger.info("Step 4: Clicking Get started link...")
            homePage.clickGetStarted()

            // Step 5: Verify that the URL contains "java"
            logger.info("Step 5: Verifying URL contains 'java'...")
            val url = homePage.getCurrentUrl()
            val containsJava = url.contains("java", ignoreCase = true)
            logger.info("URL: $url")
            logger.info("URL contains 'java': $containsJava")
            assert(containsJava) { "URL should contain 'java', but got: $url" }

            // Step 6: Check that the text "Installing Playwright" is not visible on the page
            logger.info("Step 6: Checking 'Installing Playwright' text is NOT visible...")
            val installingPlaywrightNotVisible = homePage.isTextNotVisible("Installing Playwright")
            logger.info("'Installing Playwright' is not visible: $installingPlaywrightNotVisible")

            // Step 7: Check that the text about Maven modules is visible
            logger.info("Step 7: Checking Maven modules text is visible...")
            val mavenTextVisible = homePage.isTextVisible("Playwright is distributed as a set of Maven modules")
            logger.info("Maven modules text is visible: $mavenTextVisible")
            assert(mavenTextVisible) { "Maven modules text should be visible on the page" }

            logger.info("===== Test Completed Successfully! =====")
        } catch (e: Exception) {
            logger.error("Test failed with exception: ${e.message}", e)
            throw e
        }
    }
}


