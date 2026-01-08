# Sauce Labs Sample App Mobile Automation

This project is an automated testing framework for the Sauce Labs Sample App (Android) using Appium 2.x, Java, TestNG, and Allure Reports.

## Project Goal
Build a scalable mobile automation project for functional testing (E2E, Regression, Negative scenarios).

## Tech Stack
- **Language**: Java 17
- **Framework**: TestNG
- **Mobile Automation**: Appium 2.x (UiAutomator2)
- **Reporting**: Allure Report
- **Build Tool**: Maven

## Prerequisites
1. **Java JDK 17+** installed and `JAVA_HOME` set.
2. **Appium Server 2.x** installed (`npm install -g appium`).
3. **Appium UiAutomator2 Driver** installed (`appium driver install uiautomator2`).
4. **Android SDK** installed and `ANDROID_HOME` set.
5. **Android Emulator** or Real Device configured.
6. **Maven** installed.

## Setup
1. Clone the repository.
2. Ensure you have the Sauce Labs Sample App APK.
   - Place the APK in `src/test/resources/apps/mda-2.0.0-22.apk` or update `src/test/resources/config.properties` with the correct path.
   - You can download it from [Sauce Labs Mobile App Demo](https://github.com/saucelabs/my-demo-app-rn/releases) (Recommended version: 2.0.0 or similar Android APK).

## Configuration
Update `src/test/resources/config.properties` if needed:
```properties
platformName=Android
deviceName=emulator-5554
appPath=src/test/resources/apps/mda-2.0.0-22.apk
...
```

## Running Tests
Run all tests via Maven:
```bash
mvn clean test
```
Or run directly using TestNG XML:
```bash
mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
```

## Generating Reports
After test execution, generate the Allure report:
```bash
mvn allure:serve
```
Or manually:
```bash
allure generate allure-results --clean -o allure-report
allure open allure-report
```

## CI/CD (GitHub Actions)
The project includes a GitHub Actions workflow `.github/workflows/ci.yml` that runs build checks and tests.
Note: Running Android Emulator on GitHub Actions takes time and might require hardware acceleration configuration. The current CI setup focuses on build and compilation, and attempts to run tests if the emulator starts.

## Project Structure
- `src/test/java`: Test code
  - `pages`: Page Object Models
  - `tests`: Test classes
  - `drivers`: Driver management
  - `utils`: Helpers
  - `config`: Configuration loader
- `src/test/resources`: Configuration and apps

## Troubleshooting
- **Appium Session Not Created**: Check if Appium Server is running (`appium`) and if `ANDROID_HOME` is correct.
- **Element not found**: Check implicit/explicit waits in `config.properties`.
- **APK not found**: Verify the path in `config.properties`.
