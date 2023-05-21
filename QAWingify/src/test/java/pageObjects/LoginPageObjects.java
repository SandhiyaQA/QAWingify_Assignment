package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {

    WebDriver driver;
    public LoginPageObjects(WebDriver d)
    {
        driver=d;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//h4[contains(text(),'Login')]")
    private WebElement pageHeading;

    @FindBy(css="#username")
    private WebElement username;

    @FindBy(css="#password")
    private WebElement password;

    @FindBy(css="#log-in")
    private WebElement login;

    @FindBy(css = "input.form-check-input")
    private WebElement rememberMe;

    /*@FindBy(id="random_id_0")

    private WebElement warningMessage;
     */

    @FindBy(id= "random_id_0")
    private WebElement warnMessage;


    public void enterUsername(String string)
    {
        username.sendKeys(string);
    }

    public void enterPassword(String string)
    {
        password.sendKeys(string);
    }

    public void clickLogin()
    {
        login.click();
    }

    public void tickRememberMe()
    {
        rememberMe.click();
    }

    public String getWarmMessage()
    {
        String warnMessageActual = warnMessage.getText();
        return warnMessageActual;
    }

    public String getPageHeading()
    {
        return pageHeading.getText();
    }


}



