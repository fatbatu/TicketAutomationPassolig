package denemeler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class sade {

    static WebDriver driver;
    static Select selectKategori;
    static Select selectAdet;
    static Select selectBlok;
    Actions actions;
    static LocalTime start;
    static LocalTime end;

    public static void girisYap(String mailim, String sifrem){

        LocalTime time= LocalTime.now();
        System.out.println(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        WebDriverManager.edgedriver().setup();
        driver= new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        Actions actions= new Actions(driver);

        driver.get("https://www.passo.com.tr/tr/giris");


        WebElement mailKutusu= elementBul("/html/body/app-root/app-layout/app-login/section/div/div/div/div/div[2]/div/div/div[1]/div/quick-form/div/quick-input[1]/input",driver);


        // WebElement mailKutusu= driver.findElement(By.xpath("/html/body/app-root/app-layout/app-login/section/div/div/div/div/div[2]/div/div/div[1]/div/quick-form/div/quick-input[1]/input"));

        mailKutusu.sendKeys(mailim+ Keys.TAB+sifrem+Keys.TAB);

       zaman("başlangıç");

        WebElement cookieKapa= elementBul("//*[@class=\"cc-btn cc-dismiss\"]",driver);
        zaman("b");

        try {
            cookieKapa.click();
        } catch (Exception e) {
            System.out.println("cookie'ye basılamadı");
        }
        zaman("c");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
        try {
            WebElement reklamKapat= driver.findElement(By.xpath("//button[text()='KAPAT']"));
            reklamKapat.click();
        } catch (Exception e) {
            System.out.println("Reklam tespit edilemesi");

        }

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        zaman("d");

        while (driver.getCurrentUrl().equals("https://www.passo.com.tr/tr") ||
                driver.getCurrentUrl().equals("https://www.passo.com.tr/tr/giris") ){
        }



    }

    //satış sayfasında bekleyeceğimiz ve yenileme yapmayacağımız ihtimal

    public static void baglantıyaGitveSatınAl(String hangiTakım, String gidilecekLink){

        driver.get(gidilecekLink);
        JavascriptExecutor jse =(JavascriptExecutor) driver;
        WebElement satınAl= elementBul("//*[contains(text(),'SATIN')]",driver);
        jse.executeScript("arguments[0].scrollIntoView();",satınAl);
        jse.executeScript("arguments[0].click();",satınAl);


    }

    static WebElement kategori;


    public static void kategoriDöngüsü(int ilkKategori, int sonKategori){

        zaman("Kategori girişi : ");

        if (kategori==null)
            kategori= elementBul("(//select[@class='form-control ng-untouched ng-pristine ng-valid'])[2]",driver);

        selectKategori= new Select(kategori);
        List<WebElement> tamam;
        WebElement tamamtusu;
        List<WebElement> kategoriList= selectKategori.getOptions();


        for (int i = ilkKategori; i >sonKategori-1 ; i--) {


            selectKategori.selectByIndex(i);

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
            tamam= driver.findElements(By.xpath("//button[text()='Tamam']"));
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));


            //KATEGORİDE YER YOKSA BURASI ÇALIŞACAK
            if (tamam.size()>0){

                try {
                    System.out.println(kategoriList.get(i).getText()+" seçildi"+" // YER YOK ");
                    System.out.println(tamam.get(0).getText());
                } catch (Exception e) {
                }

                tamamtusu =elementBul("//button[text()='Tamam']",driver);
                tamamtusu.click();


            }


            //KATEGORİDE YER VARSA BURASI ÇALIŞACAK

            else {
                System.out.println(kategoriList.get(i).getText()+" seçildi");
                break;


            }



            //TÜM KATEGORİLER DOLUYSA BURASI ÇALIŞACAK
            if (i== sonKategori && tamam.size()>0 ){
                //    dokunulmusKategoriDöngüsü(ilkKategori,sonKategori);
                //   kategoriDöngüsü(ilkKategori,sonKategori);
                // dokunulmusKategoriDöngüsü2(,kategori);
                kategoriDöngüsü(ilkKategori,sonKategori);
            }

        }


    }
    public static void kisiSayisiSec(){

        WebElement kisiSayisi = elementBul("//select[@class=\"form-control\"]",driver);
        selectAdet= new Select(kisiSayisi);
        selectAdet.selectByIndex(2);

    }

    static WebElement blok;
    public static void blokVeKoltuk(int ilkKategori, int sonKategori){

        WebElement enIyiKoltuk;
        List<WebElement> tamamblok;


        if(blok==null)
            blok= driver.findElement(By.xpath("(//*[@class='form-control ng-untouched ng-pristine ng-valid'])[2]"));
        selectBlok= new Select(blok);
        List<WebElement> bloklar= selectBlok.getOptions();




        for (int i = 1; i<bloklar.size() ; i++) {

            selectBlok.selectByIndex(i);


            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
            tamamblok= driver.findElements(By.xpath("//button[text()='Tamam']"));
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

            //BLOKTA YER YOKSA BURASI ÇALIŞACAK
            if (tamamblok.size()>0){
                tamamblok.get(0).click();

            }

            //BLOKTA YER VARSA BURASI ÇALIŞACAK
            else {

                System.out.println("");
                System.out.println(bloklar.get(i).getText()+" seçildi");
                break;
            }

            //SON BLOKTA DA YER YOKSA BURASI ÇALIŞACAK
            if (i== bloklar.size()-1 && tamamblok.size()>0 ){
                //dokunulmusKategoriDöngüsü(ilkKategori,sonKategori);
                //  blokVeKoltuk(ilkKategori,sonKategori);
                kategoriDöngüsü(ilkKategori,sonKategori);
                blokVeKoltuk(ilkKategori,sonKategori);
            }

            System.out.println("BU YAZIYI ZATEN GÖRMEMEN LAZIM");
        }




        boolean bastik= true;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));


        while (bastik==true){

            //KOLTUK TUŞUNA HATA VERDİRMEDEN BASTIRMAYA ÇALIŞIYORUZ
            try {
                enIyiKoltuk= driver.findElement(By.xpath("//button[text()='En iyi koltuğu bul ']"));
                while(!enIyiKoltuk.isEnabled()){}
                enIyiKoltuk.click();
                bastik=false;
            } catch (Exception e) {
                System.out.println("Koltuğa basılamadı");

            }

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Bitiş : "+LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));





    }












    public static WebElement elementBul (String xPath, WebDriver driver){


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

    public static void zaman (String nokta){

        System.out.println(nokta+" noktası : "+LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));


    }
}
