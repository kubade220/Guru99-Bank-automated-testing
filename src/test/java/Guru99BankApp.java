import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class Guru99BankApp {
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

    public void logIn(String logIn, String pswd)
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
                email = "teste2t@com";
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
        String[] customerData = {"Seba", "25/02/2005", "ul Testowa 1", "Testowo", "Texas", "123456", "123456789", "mamd2@dada.com", "test123"};
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