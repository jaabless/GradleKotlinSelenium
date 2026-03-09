package com.automation.tests

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GetStartedTest : BaseTest() {

    @Test
    @DisplayName("Test Get Started Navigation Contains Docs URL")
    fun testGetStartedNavigation() {
        logger.info("===== Starting Get Started Navigation Test =====")

        try {
            // Step 1: Verify we are on the Playwright website
            logger.info("Step 1: Verifying Playwright website is loaded...")
            val currentUrl = homePage.getCurrentUrl()
            logger.info("Current URL: $currentUrl")
            assert(currentUrl.contains("playwright.dev", ignoreCase = true)) {
                "URL should contain 'playwright.dev', but got: $currentUrl"
            }

            // Step 2: Click on the "Get started" link
            logger.info("Step 2: Clicking Get started link...")
            homePage.clickGetStarted()

            // Step 3: Verify that the URL contains "docs"
            logger.info("Step 3: Verifying URL contains 'docs'...")
            val newUrl = homePage.getCurrentUrl()
            logger.info("New URL: $newUrl")
            assert(newUrl.contains("docs", ignoreCase = true)) {
                "URL should contain 'docs', but got: $newUrl"
            }

            logger.info("===== Test Completed Successfully! =====")
        } catch (e: Exception) {
            logger.error("Test failed with exception: ${e.message}", e)
            throw e
        }
    }
}
