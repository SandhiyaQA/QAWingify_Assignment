package objectManager;

import org.openqa.selenium.WebDriver;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;

public class ObjectManager {

    private final WebDriver driver;
    private HomePageObjects homePageObjects;
    private LoginPageObjects loginPageObjects;

    public ObjectManager(WebDriver driver)

    {
        this.driver=driver;
    }

    public HomePageObjects getHomePageObjects()
    {
        return (homePageObjects==null) ? homePageObjects = new HomePageObjects(driver): homePageObjects;
    }
    public LoginPageObjects getLoginPageObjects()
    {
        return (loginPageObjects==null) ? loginPageObjects = new LoginPageObjects(driver): loginPageObjects;
    }

}
