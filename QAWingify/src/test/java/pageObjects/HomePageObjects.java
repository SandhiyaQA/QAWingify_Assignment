package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePageObjects {
    static List<String> elements=new ArrayList<>();
    WebDriver driver;

    public HomePageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//th[5]")
    private WebElement amount;

    @FindBy(xpath="//td[5]")
    private List<WebElement> totalAmounts;

    public void clickamount()
    {
        amount.click();
    }

    public List<String> gettotalElementsInAmount()
    {
        for(WebElement element: totalAmounts)
        {
            String elementsInString=element.getText();
            elements.add(elementsInString);

            }

            return elements;

    }

}