# Sauce Labs Sample App Mobile Automation

This project is an automated testing framework for the Sauce Labs Sample App (Android) using Appium 2.x, Java, and TestNG.

## Project Goal
Build a scalable mobile automation project for functional testing (E2E, Regression, Negative scenarios).

## Tech Stack
- **Language**: Java 17
- **Framework**: TestNG
- **Mobile Automation**: Appium 2.x (UiAutomator2)
- **Build Tool**: Maven

## Prerequisites

Before running the tests, ensure you have the following installed:

1.  **Java JDK 17+**
    *   Verify: `java -version`
    *   Set `JAVA_HOME` environment variable.
2.  **Node.js & npm** (Required for Appium)
    *   Download from [nodejs.org](https://nodejs.org/)
3.  **Appium Server 2.x**
    *   Install: `npm install -g appium`
    *   Verify: `appium -v`
4.  **Appium UiAutomator2 Driver**
    *   Install: `appium driver install uiautomator2`
5.  **Android SDK**
    *   Install via Android Studio.
    *   Set `ANDROID_HOME` environment variable (e.g., `~/Library/Android/sdk` on macOS).
    *   Add `$ANDROID_HOME/platform-tools` and `$ANDROID_HOME/tools` to your `PATH`.
6.  **Maven**
    *   Install: `brew install maven` (macOS) or via typical package managers.
    *   Verify: `mvn -v`

## Setup

1.  **Clone the repository**
    ```bash
    git clone <repository-url>
    cd sauce-sample-app-mobile-automation
    ```

2.  **App Setup**
    *   The project expects the Android APK file at `src/test/resources/apps/mda.apk`.
    *   The file is included in this repository.
    *   If you need to update it, download the latest `.apk` from the [Sauce Labs Mobile App Demo Releases](https://github.com/saucelabs/my-demo-app-rn/releases) and rename/place it correctly.

3.  **Start an Android Emulator**
    *   Open Android Studio -> Virtual Device Manager.
    *   Launch an emulator (e.g., Pixel 4 API 30).
    *   Verify it is connected:
        ```bash
        adb devices
        ```

4.  **Start Appium Server**
    *   Open a new terminal and run:
        ```bash
        appium
        ```
    *   You should see output indicating the server is running on port 4723.

## Configuration

The main configuration file is located at `src/test/resources/config.properties`.

| Property | Description | Default Value |
| :--- | :--- | :--- |
| `platformName` | Mobile OS | `Android` |
| `deviceName` | Name of the emulator/device | `emulator-5554` |
| `automationName` | Automation engine | `UiAutomator2` |
| `appPath` | Path to the APK under test | `src/test/resources/apps/mda.apk` |
| `appPackage` | App package name | `com.saucelabs.mydemoapp.android` |
| `appActivity` | Main activity to launch | `.view.activities.SplashActivity` |

**Note:** If your emulator name is different (check via `adb devices`), update the `deviceName` value in this file.

## Execution Steps

You can run tests using Maven commands from the project root.

### 1. Run All Tests
This executes all tests defined in `testng.xml`.
```bash
mvn clean test
```

### 2. Run Specific Test Class
To run a single test class (e.g., `LoginTest`):
```bash
mvn clean test -Dtest=LoginTest
```

### 3. Run Specific Test Method
To run a single method within a class:
```bash
mvn clean test -Dtest=LoginTest#successfulLogin
```

### 4. Run with Debug Logging
If tests fail, you can run with full stacktrace:
```bash
mvn clean test -e -X
```

## CI/CD (GitHub Actions)
The project includes a workflow `.github/workflows/ci.yml` that runs build checks and tests.
*   It automatically runs on `push` to `main` and PRs.
*   It sets up Java, Appium, and attempts to run the test suite.

## Project Structure
```text
src/test/java/com/saucedemo/
├── config/       # Configuration loader
├── drivers/      # Appium Driver initialization
├── pages/        # Page Object Models (POM) and locators
├── tests/        # TestNG test classes
└── utils/        # Reporting and Listeners

src/test/resources/
├── apps/         # .apk files
└── config.properties # Global configuration
```

## Troubleshooting
*   **`Encountered internal error running command: ...`**: Restart the Appium server.
*   **`Device not found`**: Ensure `adb devices` shows your emulator and `deviceName` in config matches.
*   **`ClassNotFoundException`**: Run `mvn clean` to remove stale targets.
