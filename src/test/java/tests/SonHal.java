package tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utilites.DriverCross;
import utilites.TestBaseCross;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class SonHal {

    static WebDriver driver;
    static Select selectKategori;
    static Select selectAdet;
    static Select selectBlok;
    Actions actions;
    static LocalTime start;
    static LocalTime end;





    // FIND ELEMENTS ÇOK ZAMAN HARCIYOR

    @Test

    public void test01(){
        String mailim = "batuhanisik34@gmail.com";
        String sifrem = "Haydegidelum1";

        girisYap(mailim,sifrem);

        String hangiTakım="Fenerbahçe";  //sadece şehir ismi girersen hata verir
        String gidilecekLink= "https://www.passo.com.tr/tr/kategori/futbol-mac-biletleri/4615";

        baglantıyaGitveSatınAl(hangiTakım,gidilecekLink);
        //  baglantıyaGitveSatınAlAlternatif();

        String bonusCardTc= "26942514610";
        String bonusCardNo= "deneme";



        int ilkKategori=2;
        int sonKategori=1;

        kategoriDöngüsü(ilkKategori,sonKategori);

        kisiSayisiSec(1);

        blokVeKoltuk(ilkKategori,sonKategori);
    }


    public static void girisYap(String mailim, String sifrem){

        LocalTime time= LocalTime.now();
        System.out.println(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        WebDriverManager.edgedriver().setup();
        driver= new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        Actions actions= new Actions(driver);

        driver.get("https://www.passo.com.tr/tr/giris");

        WebElement mailKutusu= driver.findElement(By.xpath("/html/body/app-root/app-layout/app-login/section/div/div/div/div/div[2]/div/div/div[1]/div/quick-form/div/quick-input[1]/input"));

        mailKutusu.sendKeys(mailim+ Keys.TAB+sifrem+Keys.TAB);

        System.out.println("Başlangıç : "+(LocalTime.now().getSecond()- time.getSecond()));
        System.out.println(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));

        WebElement cookieKapa= driver.findElement(By.xpath("//*[@class=\"cc-btn cc-dismiss\"]"));
        System.out.println("b noktası : "+(LocalTime.now().getSecond()- time.getSecond()));
        System.out.println(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));

        try {
            cookieKapa.click();
        } catch (Exception e) {
            System.out.println("cookie yok");
        }
        System.out.println("c noktası : "+(LocalTime.now().getSecond()- time.getSecond()));
        System.out.println(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));


        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
        try {
            WebElement reklamKapat= driver.findElement(By.xpath("//button[text()='KAPAT']"));
            reklamKapat.click();
        } catch (Exception e) {
            System.out.println("Reklam tespit edilemesi");

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        System.out.println("d noktası : "+(LocalTime.now().getSecond()- time.getSecond()));
        System.out.println(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));

        while (driver.getCurrentUrl().equals("https://www.passo.com.tr/tr") ||
                driver.getCurrentUrl().equals("https://www.passo.com.tr/tr/giris") ){
        }



    }

    public static void baglantıyaGitveSatınAl(String hangiTakım, String gidilecekLink){

        driver.get(gidilecekLink);
        String handle1= driver.getWindowHandle();

        //   String dinamikXpath= "(//*[contains(text(),'"+hangiTakım+"')])[1]";
        String  dinamikXpath= "//*[contains(text(),'"+hangiTakım+"') and contains(text(),'Galatasaray')]";


        JavascriptExecutor jse =(JavascriptExecutor) driver;
        boolean sayfaCiktiMi=true;

        while (sayfaCiktiMi==true){

            WebElement fenerbahçeliEtkinlik= null;
            try {
                fenerbahçeliEtkinlik = driver.findElement(By.xpath(dinamikXpath));
            } catch (Exception e) {
                System.out.println("etkinlik yog");

            }

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
            List<WebElement> fenerbaçeliEtkinlikList= driver.findElements(By.xpath(dinamikXpath));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


            System.out.println("buraya kadar geldik"); //buraya gelmesi yine vakit alıyor sanki (maç olmayınca)

            if (!(fenerbaçeliEtkinlikList.size()==0))

            {
                System.out.println(fenerbaçeliEtkinlikList.get(0).getText());
                jse.executeScript("arguments[0].scrollIntoView();",fenerbahçeliEtkinlik);
                jse.executeScript("arguments[0].click();",fenerbahçeliEtkinlik);
                System.out.println("Maça tıklandı");
                sayfaCiktiMi = false;
            }
            else {
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
                System.out.println("Maç yok, sayfa yenilendi");
                System.out.println(driver.getCurrentUrl());
                if (!(driver.getCurrentUrl()==gidilecekLink)) driver.get(gidilecekLink);
                driver.navigate().refresh();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            }

        }

        Set<String> handles= driver.getWindowHandles();
        String handle2=null;

        for (String each:handles
        ) {
            if (!(each==handle1)){ handle2=each;
            } }

        driver.switchTo().window(handle2);



        boolean flag=true;

        WebElement satınAl;
        int sayac1=0;
        int sayac2=0;
        while (flag=true){



            try {
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
                if (sayac2%6==0)  driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));

                satınAl= driver.findElement(By.xpath("//*[text()='SATIN AL']"));
                // satınAl= driver.findElement(By.xpath("(//button[@class='red-btn'])[1]"));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                jse.executeScript("arguments[0].scrollIntoView();",satınAl);
                jse.executeScript("arguments[0].click();",satınAl);
                break;

            } catch (Exception e) {
                sayac1++;
                sayac2++;
                if(sayac1%20==0) System.out.println("Webelement bulunamadı");
                System.out.println("Webelement bulunamadı");
                driver.navigate().refresh();

            } finally {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            }

        }


    }

    public static void baglantıyaGitveSatınAlAlternatif(){
        JavascriptExecutor jse =(JavascriptExecutor) driver;


        boolean flag=true;

        WebElement satınAl;
        int sayac1=0;
        while (flag=true){



            try {
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
                satınAl= driver.findElement(By.xpath("//*[text()='SATIN AL']"));
                //satınAl= driver.findElement(By.xpath("(//button[@class='red-btn'])[1]"));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                jse.executeScript("arguments[0].scrollIntoView();",satınAl);
                jse.executeScript("arguments[0].click();",satınAl);
                break;

            } catch (Exception e) {
                sayac1++;
                if(sayac1%20==0) System.out.println("Webelement bulunamadı");

            }

        }






    }

    public void öncelikliBiletEkranı(String bonusluTc,String bonusNo,int üyelikIndex){


        JavascriptExecutor jse =(JavascriptExecutor) driver;

        WebElement logo;
        System.out.println(" öncelikli Bilet Giriş : ,"+LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));

        WebElement üyelikTipi;
        Select üyelikSelect;
        try {


            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
            logo= driver.findElement(By.xpath("(//*[@class='priority-sale-select-item-title'])[2]"));
            try {
                logo = driver.findElement(By.xpath("//*[contains(text(),\"Bonus\") and @class='priority-sale-select-item-title']"));
            } catch (Exception e) {

            }
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

            jse.executeScript("arguments[0].click();",logo);


            boolean flag45=true;
            while (flag45==true){

                try {
                    WebElement textBox1= driver.findElement(By.xpath("//input[contains(@placeholder,'TC') or contains(@placeholder,'Tc') or contains(@placeholder,'kimlik') or contains(@placeholder,'Kimlik')]"));
                    textBox1.sendKeys(bonusluTc);
                    flag45=false;
                } catch (Exception e) {

                }

            }



            //   WebElement textBox2= driver.findElement(By.xpath("//input[contains(@placeholder,'öncelikli')]"));
           /*
            try {
                WebElement textBox2= driver.findElement
                        (By.xpath("//input[contains(@placeholder,'Kart') or contains(@placeholder,'Bonus') or contains(@placeholder,'Card') or contains(@placeholder,'BONUS') or contains(@placeholder,'kart')]"));
                textBox2.sendKeys(bonusNo);
            } catch (Exception e) {

            }
            */

            WebElement devamButon;



            try {
                WebElement cookieKapa= driver.findElement(By.xpath("//*[@class=\"cc-btn cc-dismiss\"]"));
                if (cookieKapa.isDisplayed())
                    cookieKapa.click(); // bunu yapınca hızlı kapadı-2 saniye
            } catch (Exception e) {

            }

            boolean devamFlag=true;
            int sayac1=0;

            while (devamFlag==true){

                try {
                    devamButon= driver.findElement(By.xpath("//button[text()='Devam']"));
                    devamButon.click();
                    devamFlag=false;
                } catch (Exception e) {
                    if(sayac1%20==0) System.out.println("Webelement bulunamadı");
                    try {
                        driver.findElement(By.xpath("(//select[@class='form-control ng-untouched ng-pristine ng-valid'])[2]")).isDisplayed();

                        break;
                    } catch (Exception ex) {

                    }

                }

            }


        } catch (Exception e) {

            üyelikTipi= driver.findElement(By.xpath("(//select[@class='form-control ng-untouched ng-pristine ng-valid'])[1]"));
            üyelikSelect=new Select(üyelikTipi);

            boolean flag2=true;
            while(flag2==true) {

                try {
                    üyelikSelect.selectByIndex(üyelikIndex);
                    flag2=false;
                } catch (Exception ex) {

                }

            }

            WebElement kimlikNO;

            try {
                kimlikNO= driver.findElement(By.xpath("//input[contains(@placeholder,'Kimlik')]"));
                kimlikNO.sendKeys(bonusluTc);
            } catch (Exception ex) {

            }
            System.out.println("Üyelik durumu seçildi");




        }
        System.out.println(" öncelikli Bilet çıkış : "+LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));





    }




    static WebElement kategori;

    public static void kategoriDöngüsü(int ilkKategori, int sonKategori){
        LocalTime kategoribaşı= LocalTime.now();

        System.out.println("kategoriye giriş :"+kategoribaşı.format(DateTimeFormatter.ISO_LOCAL_TIME));


        boolean sayac34=true;


        if (kategori==null){

            while (sayac34==true){

                try {
                    kategori= driver.findElement(By.xpath("(//select[@class='form-control ng-untouched ng-pristine ng-valid'])[2]"));
                    sayac34=false;

                } catch (Exception e) {
                }

            }

        }

        selectKategori= new Select(kategori);
        List<WebElement> tamam;
        WebElement tamamtusu;
        List<WebElement> kategoriList= selectKategori.getOptions();

        WebDriverWait wait3s= new WebDriverWait(driver,Duration.ofSeconds(3));

        boolean status= true;
        boolean tusCalismasi= true;



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



                while (tusCalismasi==true){


                    try {
                        tamamtusu =driver.findElement(By.xpath("//button[text()='Tamam']"));
                        System.out.println("tuş basılabilir mi : "+tamamtusu.isEnabled());
                        tamamtusu.click();
                        tusCalismasi=false;
                    } catch (Exception e) {
                        System.out.println("tuş calışmadı");
                    }


                }


            }


            //KATEGORİDE YER VARSA BURASI ÇALIŞACAK

            else {
                System.out.println("Kategori seçimi "+(LocalTime.now().getSecond()-kategoribaşı.getSecond())+" saniye sürdü"); //16
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




    public static void kisiSayisiSec(int sayi){

        start= LocalTime.now();

        boolean deneme = true;
        WebElement kisiSayisi;
        List<WebElement> kisiSayisiListe;


        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        while (deneme==true){

            try {
                kisiSayisi = driver.findElement(By.xpath("//select[@class=\"form-control\"]"));
                selectAdet= new Select(kisiSayisi);
                selectAdet.selectByIndex(sayi);
                System.out.println("Kişi sayısı seçildi");
                deneme=false;
            } catch (Exception e) {
                System.out.println("Kişi sayısı seçilemedi");

            }

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        end= LocalTime.now();
        System.out.println("Kişi seçimi "+(end.getSecond()-start.getSecond())+" saniye sürdü");



    }


    static WebElement blok;
    public static void blokVeKoltuk(int ilkKategori, int sonKategori){

        LocalTime kategoribaşı= LocalTime.now();


        WebElement enIyiKoltuk;
        List<WebElement> tamamblok, tamamkoltuk;



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

                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
                tamamkoltuk= driver.findElements(By.xpath("//button[text()='Tamam']"));
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

                //Koltukta YER YOKSA BURASI ÇALIŞACAK
                if (tamamkoltuk.size()>0){
                    tamamkoltuk.get(0).click();
                }

                //koltukta YER VARSA BURASI ÇALIŞACAK
                else {

                    System.out.println("Bilet başarıyla sepete eklendi");


                }

                //SON BLOK koltuğunda DA YER YOKSA BURASI ÇALIŞACAK
                if (i== bloklar.size()-1 && tamamblok.size()>0 ){
                    //dokunulmusKategoriDöngüsü(ilkKategori,sonKategori);
                    //  blokVeKoltuk(ilkKategori,sonKategori);
                    kategoriDöngüsü(ilkKategori,sonKategori);
                    blokVeKoltuk(ilkKategori,sonKategori);
                }




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





        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Bitiş : "+LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));





    }







}


