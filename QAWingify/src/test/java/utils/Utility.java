package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

class Utility {

    public static String takeScreenhot(WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String path="Result/"+System.currentTimeMillis()+".png";
        File destination = new File(path);
        FileHandler.copy(source, destination);
        return destination.getAbsolutePath();
    }


}

