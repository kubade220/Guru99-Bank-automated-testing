import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
public class Guru99Test {
    private WebDriver driver;
    WebDriverWait wait;
    Logger logger;
    @Before
    public void setUp() {
        logger = Logger.getLogger("TestAutomation");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); //after update, it bypasses the error
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Window maximized");
        driver.get("https://demo.guru99.com/v4/index.php");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.switchTo().frame("gdpr-consent-notice");
        WebElement acceptCookies = driver.findElement(By.xpath("//*[@id='save']/span[1]/div/span"));
        acceptCookies.click();
    }

    @Test
    /*co mozna przetestowac */
    public void dummy ()
    {
        int test = 1;
//            Assert.assertEquals("test", "Test1");
    }
    @Test
    public void logInTest()
    {
        /*tworzymy zmienna do testowania
         * WebElement o nazwie logInTxtFld
         *
         * */
        WebElement logInTxtFld = driver.findElement(By.name("uid")); //*[@name='uid']  wpisujemy w konsoli w devtoolsach po f12 sprawdza czy nazwa jest unikalna
        logInTxtFld.sendKeys("mngr495031");

        WebElement passwordTxtFld = driver.findElement(By.name("password"));
        passwordTxtFld.sendKeys("UzEbyvE");

        WebElement loginButton = driver.findElement(By.name("btnLogin"));
        loginButton.click();
    }
    public void logIn (String logIn, String pswd)
        /* możemy też przetestowac rozne hasla poprzez wyznaczenie String ze zmiennymi logIn i pswd (password)
         *
         * */
    {
            logIn = "mngr495031";
            pswd = "UzEbyvE";

        WebElement logInTxtFld = driver.findElement(By.name("uid"));
        logInTxtFld.sendKeys(logIn);

        WebElement passwordTxtFld = driver.findElement(By.name("password"));
        passwordTxtFld.sendKeys(pswd);

        WebElement loginButton = driver.findElement(By.name("btnLogin"));
        loginButton.click();
    }

    @Test
    public void logInTest2()
    {
        logIn("mngr495031", "UzEbyvE");
        WebElement welcomeText = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td"));
        Assert.assertEquals(true, welcomeText.getText().contains("mngr495031"));
//            Assert.assertEquals("mngr495031", welcomeText.getText());
//           Albo alternatywna metoda

    }

    @Test
    public void logInTest3()
    {
//            co sie stanie gdy wpisze sie BLEDNE HASLO
        logIn("mngr495031", "mngr495031s");
        driver.switchTo().alert().accept();
        WebElement logInBtn = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnLogin")));
        Assert.assertEquals(true, logInBtn.isDisplayed());
    }
    public void verifyLogIn(String logIn, boolean areLoginDataCorrect)
    {
        if (areLoginDataCorrect) {
            WebElement welcomeText = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td"));
            Assert.assertEquals(true, welcomeText.getText().contains(logIn));
        } else {
            driver.switchTo().alert().accept();
            WebElement logInBtn = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnLogin")));
            Assert.assertEquals(true, logInBtn.isDisplayed());
        }
    }
    @Test
    public void logInCorrectTest()
    {
        String logIn = "mngr495031";
        logIn(logIn, "UzEbyvE");
        verifyLogIn(logIn, false);
    }
    //Napusz metodę testową, ktora przetestuje niepoprawne logowanie
    //Użyj metod logIn oraz verifyLogIn
    @Test
    public void addUser2()
    {
        logIn("mngr495031", "UzEbyvE");
        WebElement newCustomerBtn = driver.findElement(By.linkText("New Customer")); //zagrożenie, gdyby jezyk był zmieniony, to trzeba by było zmienić linktext w kodzie
        newCustomerBtn.click();
        driver.get("https://demo.guru99.com/v4/manager/addcustomerpage.php");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(driver.findElements(By.id("dismiss-button")).size()>0)
        {
            WebElement closeBtn = driver.findElement(By.id("dismiss-button"));
            closeBtn.click();
        }


        WebElement customerNameTxtFld = driver.findElement(By.name("name"));
        customerNameTxtFld.sendKeys("Seba");
        WebElement genderRadioBtn = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[1]"));
        genderRadioBtn.click();
        WebElement dobTxtFld = driver.findElement(By.id("dob"));
        dobTxtFld.sendKeys("25/02/2005");
        WebElement addressTxtFld = driver.findElement(By.name("addr"));
        addressTxtFld.sendKeys("ul Testowa 1");
        WebElement cityTxtFld = driver.findElement(By.name("city"));
        cityTxtFld.sendKeys("Testowo");
        WebElement stateTxtFld = driver.findElement(By.name("state"));
        stateTxtFld.sendKeys("Texas");
        WebElement pinTxtFld = driver.findElement(By.name("pinno"));
        pinTxtFld.sendKeys("123456");
        WebElement telephoneTxtFld = driver.findElement(By.name("telephoneno"));
        telephoneTxtFld.sendKeys("123456789");
        WebElement emailTxtFld = driver.findElement(By.name("emailid"));
        emailTxtFld.sendKeys("testerowy@test.com");
        WebElement passwordTxtFld = driver.findElement(By.name("password"));
        passwordTxtFld.sendKeys("test123");
        WebElement submitBtn = driver.findElement(By.name("sub"));
        submitBtn.click();

        // pobieramy ID klienta
        WebElement customerID = driver.findElement(By.xpath("//*[@id=\"customer\"]/tbody/tr[4]/td[2]"));
        String customerIDText = customerID.getText();

    }

    @Test
    public void addUserTest()
    {
        logIn("mngr495031", "UzEbyvE");
        WebElement newCustomerBtn = driver.findElement(By.linkText("New Customer")); //zagrożenie, gdyby jezyk był zmieniony, to trzeba by było zmienić linktext w kodzie
        newCustomerBtn.click();
        driver.get("https://demo.guru99.com/v4/manager/addcustomerpage.php");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(driver.findElements(By.id("dismiss-button")).size()>0)
        {
            WebElement closeBtn = driver.findElement(By.id("dismiss-button"));
            closeBtn.click();
        }

        WebElement customerNameTxtFld = driver.findElement(By.name("name"));
        customerNameTxtFld.sendKeys("Seba");
        WebElement genderRadioBtn = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[1]"));
        genderRadioBtn.click();
        WebElement dobTxtFld = driver.findElement(By.id("dob"));
        dobTxtFld.sendKeys("25/02/2005");
        WebElement addressTxtFld = driver.findElement(By.name("addr"));
        addressTxtFld.sendKeys("ul Testowa 1");
        WebElement cityTxtFld = driver.findElement(By.name("city"));
        cityTxtFld.sendKeys("Testowo");
        WebElement stateTxtFld = driver.findElement(By.name("state"));
        stateTxtFld.sendKeys("Texas");
        WebElement pinTxtFld = driver.findElement(By.name("pinno"));
        pinTxtFld.sendKeys("123456");
        WebElement telephoneTxtFld = driver.findElement(By.name("telephoneno"));
        telephoneTxtFld.sendKeys("123456789");
        WebElement emailTxtFld = driver.findElement(By.name("emailid"));
        emailTxtFld.sendKeys("testeroawdy@test.com");
        WebElement passwordTxtFld = driver.findElement(By.name("password"));
        passwordTxtFld.sendKeys("test123");
        WebElement submitBtn = driver.findElement(By.name("sub"));
        submitBtn.click();
    }

    @Test


//    @Test
//    public void addUser2()
//    {
//        String customerName = "Sebaaa";
//        boolean isFemale = false;
//        String dob = "25/02/2005";
//        String address = "ul Testowa 2";
//        String city = "Testowol";
//        String state = "Texas";
//        String pin = "123456";
//        String mobileNumber = "123456789";
//        String email = "testerowy2@test.com";
//        String password = "test1234";
//    }
//        public void addUser(customerName, isFemale, dob, address, city, state, pin, mobileNumber, email, password);
//        {
//            WebElement customerNameTxtFld = driver.findElement(By.name("name"));
//            customerNameTxtFld.sendKeys(customerName);
//            WebElement genderRadioBtn = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[2]"));
//            if (isFemale) {
//                genderRadioBtn.click();
//            }
//            WebElement dobTxtFld = driver.findElement(By.id("dob"));
//            dobTxtFld.sendKeys(dob);
//            WebElement addressTxtFld = driver.findElement(By.name("addr"));
//            addressTxtFld.sendKeys(address);
//            WebElement cityTxtFld = driver.findElement(By.name("city"));
//            cityTxtFld.sendKeys(city);
//            WebElement stateTxtFld = driver.findElement(By.name("state"));
//            stateTxtFld.sendKeys(state);
//            WebElement pinTxtFld = driver.findElement(By.name("pinno"));
//            pinTxtFld.sendKeys(pin);
//            WebElement mobileNumberTxtFld = driver.findElement(By.name("telephoneno"));
//            mobileNumberTxtFld.sendKeys(mobileNumber);
//            WebElement emailTxtFld = driver.findElement(By.name("emailid"));
//            emailTxtFld.sendKeys(email);
//            WebElement passwordTxtFld = driver.findElement(By.name("password"));
//            passwordTxtFld.sendKeys(password);
//            WebElement submitBtn = driver.findElement(By.name("sub"));
//            submitBtn.click();
//        }

    @After
//                metoda o tym atrybucie bedzie wykonana Po teście

    /*
    Assertions
    1. Sprawdź czy po dodaniu klienta, w tabeli z klientami, pojawia się nowy klient
    2. Sprawdź czy po dodaniu klienta, można edytować jego dane w tabeli z klientami
    3. Sprawdź zgodność wprowadzonych danych z danymi w tabeli z klientami.
     */

    public void testDown ()
    {
        driver.close();
    }
}