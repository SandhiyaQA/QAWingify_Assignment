package dataProviders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private static Properties properties;
    private static final String path="configFile.properties";
    static
    {
        FileInputStream fileInputStream;
        try
        {
            fileInputStream = new FileInputStream("src/test/resources/configProperties/configFile.properties");
            properties = new Properties();
            properties.load(fileInputStream);

        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException("Config.properties file not found");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    public static  String getUrl()
    {
        String url=properties.getProperty("url");
        if(url!=null)
            return url;
        else
            throw new RuntimeException("ConfigFile.Properties file not found"+path);
    }

    public static String getMode()
    {
        String mode=properties.getProperty("headless");
        if(mode!=null)
            return mode;
        else
            throw new RuntimeException("Headless mode not specified in" +path);
    }
    public static String getBrowser()
    {
        String browser = properties.getProperty("browser");
        if(browser !=null)
            return browser;
        else
            throw new RuntimeException("Browser not specified in config.properties file");

    }
    public static String getImplicitWait()
    {
        String waitTime = properties.getProperty("implicitWait");
        if(waitTime !=null)
            return waitTime;
        else
            throw new RuntimeException("implicitWait not specified in config.properties file");
    }

   public static String getUserName()
   {
       String username=properties.getProperty("UserName");
        return username;
   }

    public static String getPassword()
    {
        String password=properties.getProperty("password");
        return password;
    }

    public static String getTestDataFile()
    {
        String testDataPath = properties.getProperty("testDataFilePath");
        if(testDataPath !=null)
            return testDataPath;
        else
            throw new RuntimeException("Test data file path not specified in config.properties file");
    }

}
