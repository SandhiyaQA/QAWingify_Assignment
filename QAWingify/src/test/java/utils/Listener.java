package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Testcase started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Testcase started");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test got failed" + iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test got Skipped" +iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but some part of the test is passed "+ iTestResult.getName());
    }


    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("testcase started");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("testcase finished");
    }
}
