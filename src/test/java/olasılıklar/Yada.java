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

        driver.get("https://qa.tripandway.com/traveller/login");

        String sifreXpath= "/html/body/div[7]/div/div/div/div/form/div/div[1]/input";
       // sifreXpath="//*[@id=\"menu\"]/li[2]/a";

       WebElement sifreButonu= driver.findElement(By.xpath(sifreXpath));


        System.out.println(sifreButonu.getAriaRole());
        System.out.println(sifreButonu.getAccessibleName());
        System.out.println(sifreButonu.getTagName());
        sifreButonu.sendKeys("ana");
        System.out.println(sifreButonu.getText());


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
