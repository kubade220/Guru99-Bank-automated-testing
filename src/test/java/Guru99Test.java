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
    public WebDriver driver;
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
    public void addUser1Test()
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

    public String addUser2(String customerName, boolean isFemale, String dob, String address, String city, String state, String pin, String telephone, String email, String password)
            {
                customerName = "Seba";
                isFemale = false;
                dob = "25/02/2005";
                address = "ul Testowa 1";
                city = "Testowo";
                state = "Texas";
                pin = "123456";
                telephone = "123456789";
                email = "testet@com";
                password = "test123";
                return null;
    }

    @Test
        public void addUser2Test() {

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

        // Create an array with the data in the same order in which you expect to be filled in the web form. Add a gender in the separate variable.
        String[] customerData = {"Seba", "25/02/2005", "ul Testowa 1", "Testowo", "Texas", "123456", "123456789", "mam2@dada.com", "test123"};
        boolean isFemale = false;

        WebElement customerNameTxtFld = driver.findElement(By.name("name"));
        customerNameTxtFld.sendKeys(customerData[0]);
        if (isFemale) {
            WebElement genderRadioBtn = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[2]"));
            genderRadioBtn.click();
        } else {
            WebElement genderRadioBtn = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[1]"));
            genderRadioBtn.click();
        }
        WebElement dobTxtFld = driver.findElement(By.id("dob"));
        dobTxtFld.sendKeys(customerData[1]);
        WebElement addressTxtFld = driver.findElement(By.name("addr"));
        addressTxtFld.sendKeys(customerData[2]);
        WebElement cityTxtFld = driver.findElement(By.name("city"));
        cityTxtFld.sendKeys(customerData[3]);
        WebElement stateTxtFld = driver.findElement(By.name("state"));
        stateTxtFld.sendKeys(customerData[4]);
        WebElement pinTxtFld = driver.findElement(By.name("pinno"));
        pinTxtFld.sendKeys(customerData[5]);
        WebElement mobileNumberTxtFld = driver.findElement(By.name("telephoneno"));
        mobileNumberTxtFld.sendKeys(customerData[6]);
        WebElement emailTxtFld = driver.findElement(By.name("emailid"));
        emailTxtFld.sendKeys(customerData[7]);
        WebElement passwordTxtFld = driver.findElement(By.name("password"));
        passwordTxtFld.sendKeys(customerData[8]);
        WebElement submitBtn = driver.findElement(By.name("sub"));
        submitBtn.click();
    }

    @After
//                metoda o tym atrybucie bedzie wykonana Po teście

    /*
    Assertions
    1. Sprawdź czy po dodaniu klienta, w tabeli z klientami, pojawia się nowy klient
    2. Sprawdź czy po dodaniu klienta, można edytować jego dane w tabeli z klientami
    3. Sprawdź zgodność wprowadzonych danych z danymi w tabeli z klientami.
        3a. wyskoczy reklama, trzeba ją zamknąć
     */

    public void testDown ()
    {
        driver.close();
    }
}