package olasılıklar;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;
import utilites.Driver;

import java.time.Duration;

public class C03 {

    @Test
    public void t01(){

        WebDriverManager.edgedriver().setup();
        WebDriver driver= new EdgeDriver();
        driver.get("https://www.passo.com.tr/tr/etkinlik/turkcellkadinlarfinali-abbgomfetgecnlikspor-fenerbahce-mac-bileti/5014320");

        WebElement yakında= null;
        boolean boolea=true;
        while (boolea==true){
            try {
                yakında = driver.findElement(By.xpath("//*[contains(text(),'Yakında')]"));
                System.out.println(yakında.isEnabled()); //true
                boolea=false;

            } catch (Exception e) {
                System.out.println("Web Element bulunamadı");
            }
        }

        // (//button[@class='red-btn'])[1]



    }

    public WebElement elementBul (String xPath, WebDriver driver){


        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
        boolean boolea=true;
        WebElement element= null;

        while (boolea==true){
            try {
                element= driver.findElement(By.xpath("//*[contains(text(),'Yakında')]"));

                boolea=false;

            } catch (Exception e) {

            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return element;
    }
}
