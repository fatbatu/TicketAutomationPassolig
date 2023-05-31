package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilites.ConfigReader;
import utilites.Driver;

public class PassoLigPage {
    public PassoLigPage (){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    /*
    @FindBy (xpath= " ")
    public WebElement;
    */

    @FindBy (xpath= "/html/body/app-root/app-layout/app-login/section/div/div/div/div/div[2]/div/div/div[1]/div/quick-form/div/quick-input[1]/input ")
    public WebElement mailKutusu;

    @FindBy (xpath= "//*[@class=\"cc-btn cc-dismiss\"]")
    public WebElement cookieKapa;

    @FindBy (xpath= "//button[text()='KAPAT']")
    public WebElement reklamKapa;

}
