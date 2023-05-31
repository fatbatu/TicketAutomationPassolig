package tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class SonClass3 {

    static WebDriver driver;
    static Select selectKategori;
    static Select selectAdet;
    static Select selectBlok;
    Actions actions;
    static LocalTime start;
    static LocalTime end;



    // FIND ELEMENTS ÇOK ZAMAN HARCIYOR
    public static void main(String[] args) {
        String mailim = "batuhanisik34@gmail.com";
        String sifrem = "Haydegidelum1";

        girisYap(mailim,sifrem);

        String hangiTakım="Ankaragücü";  //sadece şehir ismi girersen hata verir
        String gidilecekLink= "https://www.passo.com.tr/tr/kategori/futbol-mac-biletleri/4615";

        baglantıyaGitveSatınAl(hangiTakım,gidilecekLink);

        String bonusCardTc= "deneme";
        String bonusCardNo= "deneme";

        //  öncelikliBiletEkranı(bonusCardTc,bonusCardNo); //FENERBAHÇE EKRANINDA SIKINTISIZ ÇALIŞIYOR vpn ile 5 saniye

        int ilkKategori=3;
        int sonKategori=1;

        kategoriDöngüsü(ilkKategori,sonKategori); // ÜYELİK TİPİ KISMI EKSİK OLABİLİR

        kisiSayisiSec();

        blokVeKoltuk(ilkKategori,sonKategori);

    }
    public void Base (){






    }


    public static void girisYap(String mailim, String sifrem){

        LocalTime time= LocalTime.now();
        System.out.println(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        Actions actions= new Actions(driver);

        driver.get("https://www.passo.com.tr/tr/giris");

        WebElement mailKutusu= driver.findElement(By.xpath("/html/body/app-root/app-layout/app-login/section/div/div/div/div/div[2]/div/div/div[1]/div/quick-form/div/quick-input[1]/input"));

        mailKutusu.sendKeys(mailim+ Keys.TAB+sifrem+Keys.TAB);

        System.out.println("a noktası : "+(LocalTime.now().getSecond()- time.getSecond()));
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

        String dinamikXpath= "(//*[contains(text(),'"+hangiTakım+"')])[1]";


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
        while (flag=true){



            try {
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
                // satınAl= driver.findElement(By.xpath("//*[text()='SATIN AL']"));
                satınAl= driver.findElement(By.xpath("(//button[@class='red-btn'])[1]"));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                jse.executeScript("arguments[0].scrollIntoView();",satınAl);
                jse.executeScript("arguments[0].click();",satınAl);
                break;

            } catch (Exception e) {
                System.out.println("Webelement bulunamadı");

            }

        }






    }

    public void öncelikliBiletEkranı(String bonusluTc,String bonusNo){

        JavascriptExecutor jse =(JavascriptExecutor) driver;

        WebElement logo= driver.findElement(By.xpath("(//*[@class='priority-sale-select-item-title'])[2]"));

        jse.executeScript("arguments[0].click();",logo);
        // logo.click();

        WebElement textBox1= driver.findElement(By.xpath("//input[contains(@placeholder,'TC')]"));
        textBox1.sendKeys(bonusluTc);

        //   WebElement textBox2= driver.findElement(By.xpath("//input[contains(@placeholder,'öncelikli')]"));
        WebElement textBox2= driver.findElement(By.xpath("//input[contains(@placeholder,'Kart')]"));
        textBox2.sendKeys(bonusNo);

        WebElement devamButon;


        try {
            WebElement cookieKapa= driver.findElement(By.xpath("//*[@class=\"cc-btn cc-dismiss\"]"));
            if (cookieKapa.isDisplayed())
                cookieKapa.click(); // bunu yapınca hızlı kapadı-2 saniye
        } catch (Exception e) {

        }

        boolean devamFlag=true;

        while (devamFlag==true){

            try {
                devamButon= driver.findElement(By.xpath("//button[text()='Devam']"));
                devamButon.click();
                devamFlag=false;
            } catch (Exception e) {

            }

        }


    }




    static WebElement kategori;

    public static void kategoriDöngüsü(int ilkKategori, int sonKategori){
        LocalTime kategoribaşı= LocalTime.now();



        if (kategori==null)
        kategori= driver.findElement(By.xpath("(//select[@class='form-control ng-untouched ng-pristine ng-valid'])[2]"));

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




    public static void kisiSayisiSec(){

        start= LocalTime.now();

        boolean deneme = true;
        WebElement kisiSayisi;
        List<WebElement> kisiSayisiListe;


        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        while (deneme==true){

            try {
                kisiSayisi = driver.findElement(By.xpath("//select[@class=\"form-control\"]"));
                selectAdet= new Select(kisiSayisi);
                selectAdet.selectByIndex(2);
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





    }







}

