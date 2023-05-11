import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    @After
    public void ripAndTear ()
    {
        driver.close();
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
//    public static class randomMailGen {
////            @Test
//        // this is random mail generator class
//        public void randomMailGen() {
//            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//            StringBuilder salt = new StringBuilder();
//            Random rnd = new Random();
//            while (salt.length() < 10) { // length of the random string.
//                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
//                salt.append(SALTCHARS.charAt(index));
//            }
//            String saltStr = salt.toString();
//            // make assertion that the string is not null
//            Assert.assertNotNull(saltStr);
//            System.out.println(saltStr+"@gmail.com");
//
//        }
//    }
    // create a public String with customerData as an array,
    // that defines the data types of the variables and can be initialised in another method
    public String [] customerData;

    {
        /* data types like here:
        String customerName,String dateOfBirth, String address, String city, String state,
        String pin, String telephone, String email, String password
         * Can be initialised in another method
         */
        customerData = new String[]{"Seba", "25/02/2005", "ul Testowa 1", "Testowo", "Texas", "123456", "123456789", "mail.tsasda@gmail.com", "test123"};
        // call a method in which you get a randomly generated mail
        // add +@gmail.com to it
        // update customerData[7] with it
        // create a public void that will return updated customerData[7]
        // call it in the method below
    }
//    @Test
        public void randomMailGenToSend(){
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 10) { // length of the random string.
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }
            String saltStr = salt.toString();
            customerData[7] = saltStr + "@gmail.com";
            // make assertion that the string is not longer than 20 characters
            Assert.assertTrue(customerData[7].length() <= 20);
            System.out.println(customerData[7]);
        }
    public boolean isFemale;
    {
        isFemale = false;
    }
    @Test
    public void addUser() {
        logIn("mngr495031", "UzEbyvE");
        WebElement newCustomerBtn = driver.findElement(By.linkText("New Customer")); //zagrożenie, gdyby jezyk był zmieniony, to trzeba by było zmienić linktext w kodzie
        newCustomerBtn.click();
        driver.get("https://demo.guru99.com/v4/manager/addcustomerpage.php");
        // Waiting 5 seconds for an element to be present on the page, checking
        // for its presence once every 2 seconds.
        // If element is not visible for 5 seconds, then go to the next step.
        // If element is visible, then click on it and go to the next step.

        Wait<WebDriver> fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        try {
            WebElement Advertisement = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dismiss-button")));
            if (driver.findElements(By.id("dismiss-button")).size() > 0) {
                WebElement closeBtn = driver.findElement(By.id("dismiss-button"));
                closeBtn.click();
            }
        } catch (Exception e) {
            System.out.println("No advertisement. Good!");
        }

        /*
        DEPRECATED! MOVED TO ANOTHER METHOD
        Create an array with the data in the same order in which you expect to be filled in the web form.
        Add a gender in the separate variable.
        customerData = {"Seba", "25/02/2005", "ul Testowa 1", "Testowo", "Texas", "123456", "123456789", "testdf3d@dada.com", "test123"};
        */
        // call randomMailGenToSend method and update customerData[7] with it

        // create a public void that will return updated customerData[7]
        // call it in the method below
        randomMailGenToSend();
        System.out.println(customerData[7]);
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

//         Make assertion whether the customer ID was generated or not
        String customerID = driver.findElement(By.xpath("//*[@id=\"customer\"]/tbody/tr[1]/td/p")).getText();
        // assertEqual where if the customerID element contains the text inside xPath then the test is passed
        assertTrue(customerID.contains("Customer Registered Successfully!!!"));
        System.out.println("Customer ID: " + customerID);
    }
//                metoda o tym atrybucie bedzie wykonana Po teście

    /*
    Assertions
    1. Sprawdź czy po dodaniu klienta, w tabeli z klientami, pojawia się nowy klient
    2. Sprawdź czy po dodaniu klienta, można edytować jego dane w tabeli z klientami
    3. Sprawdź zgodność wprowadzonych danych z danymi w tabeli z klientami.
        3a. wyskoczy reklama, trzeba ją zamknąć
     */
}