package olasılıklar;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Yada {
    WebDriver driver;
    @Test
    public void test01(){

        WebDriverManager.edgedriver().setup();
        driver= new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://www.passo.com.tr/tr/giris");

        String sifreXpath= "//*[contains(@placeholder,'Şifre') and (class=\"quick-input form-control mb-2 border-red ng-touched\")]";

        WebElement şifreButonu= driver.findElement(By.xpath(sifreXpath));

        şifreButonu.sendKeys("oldu");



    }

    public static void baglantıyaGitveSatınAlAlternatif(){

        boolean flag=true;

        WebElement satınAl;
        int sayac1=0;
        while (flag=true){

/*

            try {
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
                // satınAl= driver.findElement(By.xpath("//*[text()='SATIN AL']"));
                satınAl= driver.findElement(By.xpath("(//button[@class='red-btn'])[1]"));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                jse.executeScript("arguments[0].scrollIntoView();",satınAl);
                jse.executeScript("arguments[0].click();",satınAl);
                break;

            } catch (Exception e) {
                sayac1++;
                if(sayac1%20==0) System.out.println("Webelement bulunamadı");

            }

 */

        }






    }
}
