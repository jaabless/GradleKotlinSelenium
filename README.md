# Gradle Kotlin Selenium Project

A test automation framework using Gradle, Kotlin, and Selenium WebDriver with POM (Page Object Model) and Page Factory pattern.


## Project Structure

```
GradleKotlinSelenium/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/automation/
│   │   │       ├── config/
│   │   │       │   └── ConfigManager.kt          # Configuration management
│   │   │       ├── driver/
│   │   │       │   └── DriverManager.kt          # WebDriver singleton
│   │   │       ├── pages/
│   │   │       │   ├── BasePage.kt               # Base page class
│   │   │       │   ├── PlaywrightHomePage.kt      # Page object for Playwright site
│   │   │       │   └── ExamplePageTemplate.kt    # Template for new page objects
│   │   │       └── utils/
│   │   │           └── WaitUtils.kt              # Wait utilities
│   │   └── resources/
│   │       └── logback.xml                       # Logging configuration
│   └── test/
│       └── kotlin/
│           └── com/automation/tests/
│               ├── BaseTest.kt                   # Base test class (common setup/teardown)
│               ├── PlaywrightTest.kt             # Java docs navigation test
│               ├── GetStartedTest.kt             # Get Started navigation test
│               └── ExampleTestTemplate.kt        # Template for new test classes
├── build.gradle.kts                              # Gradle build configuration
├── settings.gradle.kts                           # Gradle settings
├── gradlew / gradlew.bat                         # Gradle wrapper scripts
├── gradle.sh / gradle.bat                        # Helper scripts
├── README.md                                     # Full documentation
├── QUICKSTART.md                                 # Quick start guide
└── .gitignore                                    # Git ignore rules
```

## Features

- **Gradle Build System**: Modern build tool for Java/Kotlin projects
- **Kotlin Language**: Concise and expressive syntax
- **Selenium WebDriver**: Browser automation
- **Page Object Model (POM)**: Maintainable test code organization
- **Page Factory Pattern**: Automatic element initialization
- **Base Test Class**: Shared setup and teardown via inheritance
- **WebDriver Manager**: Automatic driver management
- **SLF4J + Logback**: Comprehensive logging
- **JUnit 5**: Modern testing framework

## Prerequisites

- Java 21 or higher
- Gradle (optional - uses Gradle Wrapper)

## Setup

1. Navigate to the project directory
2. Run tests with:
   ```bash
   ./gradlew test
   ```

## Configuration

Configure the following environment variables:

- `BASE_URL`: Base URL for tests (default: https://playwright.dev/)
- `BROWSER`: Browser to use - chrome or firefox (default: chrome)
- `HEADLESS`: Run browser in headless mode (default: false)
- `IMPLICIT_WAIT`: Implicit wait time in seconds (default: 10)
- `PAGE_LOAD_TIMEOUT`: Page load timeout in seconds (default: 30)

## Test Architecture

### BaseTest

All test classes extend `BaseTest`, which provides:
- `protected val logger` — SLF4J logger scoped to the subclass name
- `protected lateinit var homePage` — initialized `PlaywrightHomePage` instance
- `@BeforeEach setUp()` — starts the WebDriver and navigates to `BASE_URL`
- `@AfterEach tearDown()` — quits the WebDriver after each test

```kotlin
abstract class BaseTest {
    protected val logger = LoggerFactory.getLogger(javaClass)
    protected lateinit var homePage: PlaywrightHomePage

    @BeforeEach fun setUp() { ... }
    @AfterEach  fun tearDown() { ... }
}
```

New test classes simply extend `BaseTest` and focus on test logic only:

```kotlin
class MyTest : BaseTest() {
    @Test
    fun myTest() {
        // driver, homePage and logger are ready to use
    }
}
```

## Test Cases

### PlaywrightTest
Tests the Playwright website Java documentation navigation:
1. Open the Playwright website
2. Hover over the language selector dropdown
3. Click on the "Java" option
4. Click on the "Get started" link
5. Verify the URL contains "java"
6. Verify the text "Installing Playwright" is not visible
7. Verify Maven modules text is visible on the page

### GetStartedTest
Tests the Get Started navigation flow:
1. Open the Playwright website
2. Click on the "Get started" link
3. Verify the URL contains "docs"

## Running Tests

```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests "com.automation.tests.PlaywrightTest"
./gradlew test --tests "com.automation.tests.GetStartedTest"

# Compile and package without running tests
./gradlew assemble

# Run with specific browser
BROWSER=firefox ./gradlew test

# Run in headless mode
HEADLESS=true ./gradlew test
```

## Key Features

✅ **Gradle Build System** - Modern, efficient build management
✅ **Kotlin Language** - Concise, expressive, interoperable with Java
✅ **Selenium WebDriver** - Industry-standard browser automation
✅ **Page Object Model** - Maintainable, scalable test architecture
✅ **Page Factory Pattern** - Automatic element initialization
✅ **Base Test Class** - Shared lifecycle via inheritance, eliminating boilerplate
✅ **Configuration Management** - External, environment-based configuration
✅ **Comprehensive Logging** - Console and file logging with SLF4J + Logback
✅ **WebDriver Manager** - Automatic driver download and management
✅ **Explicit Waits** - Proper synchronization with dynamic content
✅ **JUnit 5** - Modern testing framework with annotations

## Page Object Model Pattern

Each page is represented as a class extending `BasePage`:
- Elements are defined using `@FindBy` annotations
- Methods encapsulate page interactions
- Page Factory automatically initializes elements

## Project Statistics

- **Test Files**: 4 (BaseTest, PlaywrightTest, GetStartedTest, ExampleTemplate)
- **Page Objects**: 3 (PlaywrightHomePage + 2 templates)
- **Dependencies**: 9 (Selenium, Kotlin, JUnit, JUnit Launcher, Logging, etc.)
- **Configuration Options**: 5 (Browser, URL, Headless, Timeouts)

## Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| Build Tool | Gradle | 9.3.1+ |
| Language | Kotlin | 1.9.22 |
| Test Framework | JUnit 5 | 5.10.0 |
| Browser Automation | Selenium | 4.15.0 |
| Logging | SLF4J + Logback | 2.0.9 / 1.4.11 |
| Driver Management | WebDriver Manager | 5.6.3 |
| Java Version | Java | 21+ |

## Best Practices Implemented

1. **Separation of Concerns**: Page objects separate test logic from element interactions
2. **DRY Principle**: Common test lifecycle in `BaseTest`; common page logic in `BasePage`
3. **Configuration Management**: External configuration via `ConfigManager`
4. **Logging**: Comprehensive logging for debugging
5. **Wait Strategies**: Explicit waits for element interaction
6. **Driver Management**: Centralized driver lifecycle management
7. **JavaScript Executor**: Used for click interactions that CSS overlays would otherwise intercept

## Troubleshooting

1. **WebDriver not found**: WebDriverManager will automatically download the appropriate driver
2. **Element not found**: Check XPath in page objects and verify element visibility
3. **Timeout issues**: Increase wait times in `ConfigManager`
4. **JUnit Platform error on Gradle 9+**: Ensure `testRuntimeOnly("org.junit.platform:junit-platform-launcher")` is declared in `build.gradle.kts`

## Future Enhancements

- Add more page objects for different sections
- Implement data-driven testing
- Add parallel test execution
- Integrate with CI/CD pipelines
- Add screenshot capture on failure
