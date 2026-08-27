# QVision Challenge

This project is a Java + Maven + Serenity automation suite. It reads input data from the `src/test/resources/data` folder and runs the main execution flow through `RunnerTest`.

## 1) Clone the repository

```bash
git clone https://github.com/dennis20202021/QVision-Challenge.git
cd QVision-Challenge
```

## 2) Prerequisites

Make sure you have the following installed:

- Java JDK 22 or newer
- Maven 3.9+
- A supported browser and WebDriver if the tests open the browser automatically

## 3) Update the test data

Before running the suite, update the files located in:

```text
src/test/resources/data/
```

For example, if the project uses an Excel file, replace or edit the file in that folder with the correct information for your scenario.

Important:
- Keep the file names consistent with the Java code that loads the resources.
- Verify the data structure matches what the automation expects.

## 4) Run from the IDE

Open the project in IntelliJ IDEA or another Java IDE and run:

```text
src/test/java/qvision/runner/RunnerTest.java
```

The test method `runTests()` is the entry point for the suite.

## 5) Run from the command line

From the project root, execute:

```bash
mvn clean verify
```

This command will compile the project, execute the test suite, and validate the final result.

## Notes

- If you only want to run the suite without a full verification pass, you can also use:

```bash
mvn test
```

- If the automation fails, check the data files under `src/test/resources/data` first, as these usually drive the scenario inputs.
