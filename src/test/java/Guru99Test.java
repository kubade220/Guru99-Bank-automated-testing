import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        logger.info("Window maximized");
        driver.get("https://demo.guru99.com/v4/index.php");
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
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
            WebElement logInTxtFld = driver.findElement(By.name("uid"));
            logInTxtFld.sendKeys("mngr481452");

            WebElement passwordTxtFld = driver.findElement(By.name("password"));
            passwordTxtFld.sendKeys("zYrypEs");

            WebElement loginButton = driver.findElement(By.name("btnLogin"));
            loginButton.click();
        }
        public void logIn (String logIn, String pswd)
        /* możemy też przetestowac rozne hasla poprzez wyznaczenie String ze zmiennymi logIn i pswd (password)
        *
        * */
        {
//            logIn = "mngr481452";
//            pswd = "zYrypEs";

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
            logIn("mngr481452", "zYrypEs");
            WebElement welcomeText = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td"));
            Assert.assertEquals(true, welcomeText.getText().contains("mngr481452"));
//            Assert.assertEquals("mngr481452", welcomeText.getText());
//           Albo alternatywna metoda

        }

        @Test
        public void logInTest3()
        {
//            co sie stanie gdy wpisze sie BLEDNE HASLO
            logIn("mngr481452", "22zYrypEs");
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
            String logIn = "mngr481452";
            logIn(logIn, "zYrypEs");
            verifyLogIn(logIn, false);
        }
        //Napusz metodę testową, ktora przetestuje niepoprawne logowanie
    //Użyj metod logIn oraz verifyLogIn
    @Test
    public void addUserTest()
    {
        logIn("mngr481452", "zYrypEs");
        WebElement newCustomerBtn = driver.findElement(By.linkText("New Customer"));
        newCustomerBtn.click();

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
        WebElement dobTxtFld = driver.findElement(By.id("dob"));
        dobTxtFld.sendKeys("25/02/2023");

    }
       @After
//                metoda o tym atrybucie bedzie wykonana Po teście

       public void testDown ()
        {
            driver.close();
        }
    }
