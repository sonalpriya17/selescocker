package org.example;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleOutputCapturingListener implements ITestListener {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private PrintStream originalSysOut;

    @Override
    public void onStart(ITestContext context) {
        originalSysOut = System.out;
        System.setOut(new PrintStream(output));
    }

    @Override
    public void onFinish(ITestContext context) {
        System.setOut(originalSysOut);
    }

    @Override
    public void onTestStart(ITestResult result) {
        output.reset();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        reportOutput(result);
    }
    @Override
    public void onTestFailure(ITestResult result) {
        reportOutput(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        reportOutput(result);
    }

    private void reportOutput(ITestResult result) {
        String consoleOutput = output.toString();
        if (!consoleOutput.isEmpty()) {
            Reporter.log("Console output:");
            Reporter.log(consoleOutput);
        }
    }
}

