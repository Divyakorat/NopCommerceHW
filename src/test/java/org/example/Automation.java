package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class Automation
{
    //Using inbuilt class webDriver
    static WebDriver driver;

    //reusable methods
    //reusable method to wait until element is clickable on elements
    public static void waituntilElementIsclickable(By by, int time)
    {
        WebDriverWait wait = new WebDriverWait(driver, time);

        wait.until(elementToBeClickable(by));
    }
    //reusable method for sleep
    public static void sleep1(int n)
    {
        try
        {
            Thread.sleep(n * 1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    //reusable method to click on elements
    public static void clickOnElement(By by)
    {
        driver.findElement(by).click();
    }
    //reusable method to type text elements
    public static void typeText(By by, String text)
    {
        driver.findElement(by).sendKeys(text);
    }
    //reusable method to get text from elements
    public static String getTextFromElement(By by)
    {
        return driver.findElement(by).getText();
    }
    //Time stamp
    public static long timestamp()
    {
        return (System.currentTimeMillis());

    }
    //reusable method for select from dropdown by visible text
    public static void selectFromDropDownByVisibleText(By by, String text)
    {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }
    //reusable method for select from dropdown by text value
    public static void selectFromDropDownByTextvalue(By by, String value)
    {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(value);
    }
    //reusable method to get String text
    public static String getTextFromEliment(By by, String text)
    {
        return driver.findElement(by).getText();
    }
    public static void login(){
        clickOnElement(By.className("ico-login"));
        getTextFromEliment(By.id("Email"),"divyapatel@gmail.com");
        getTextFromEliment(By.id("Password"),"dp123456");
        clickOnElement(By.xpath("//input[@value=\"Log in\"]"));
    }


    @BeforeMethod
    //method to set up browser
    public static void chroBrowser()
    {
        //path and property for chrome driver
        System.setProperty("webdriver.chrome.driver", "C:\\soft\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://demo.nopcommerce.com/");
    }
    @AfterMethod
    //reusable method for closing the window
    public static void closeBrowser()
    {
        driver.close();
    }


    @Test
    //method for registration
    public void userShouldBeAbleToRegisterSucssefully()
    {

        //click on registration link on homepage
        clickOnElement(By.xpath("//a[@href=\"/register?returnUrl=%2F\"]"));
        //select gender
        clickOnElement(By.xpath("//div[@class=\"gender\"]"));
        //enter name
        typeText(By.xpath("//input[@id=\"FirstName\"]"),"Divya");
        //Enter surname
        driver.findElement(By.xpath("//input[@id=\"LastName\"]")).sendKeys("Patel");
        //Enter Date of birth
        selectFromDropDownByTextvalue(By.xpath("//select[@name=\"DateOfBirthDay\"]"), "6");
        //Enter Month of birth
        selectFromDropDownByVisibleText(By.xpath("//select[@name=\"DateOfBirthMonth\"]"), "May");
        //enter year of birth
        selectFromDropDownByVisibleText(By.xpath("//select[@name=\"DateOfBirthYear\"]"), "1993");
        //Enter email address
        typeText(By.id("Email"), "divyapatel"+timestamp()+"@gmail.com");
        //printing statement Email address
        System.out.println("test+" + timestamp() + "@gmail.com");
        //Enter company name
        typeText(By.xpath("//input[@id=\"Company\"]"), "abcdLtd");
        //click on checkbox
        clickOnElement(By.xpath("//input[@type=\"checkbox\"]"));
        //Enter password
        typeText(By.xpath("//input[@id=\"Password\"]"), "dp123456");
        //confirming password
        typeText(By.xpath("//input[@id=\"ConfirmPassword\"]"), "dp123456");
        //click on submit button
        clickOnElement(By.xpath("//input[1][@type=\"submit\"]"));
        //compering actual result with expected result
        String expectedText = "Your registration completed";
        String actualText = getTextFromElement(By.xpath("//div[@class=\"result\"]"));
        Assert.assertEquals(actualText, expectedText);


    }
    @Test
    //Method to refer product to friend
    public void userShouldBeAbleToSendFriendEmailSuccesfully ()
    {
        //using login method
        userShouldBeAbleToRegisterSucssefully();
        //click on computer category
        clickOnElement(By.xpath("//a[text()=\"Computers \"]"));
        //click on desktop
        clickOnElement((By.xpath("//a[text()=\" Desktops \"]")));
        //click on Build on own computer link
        clickOnElement(By.xpath("//img[@alt=\"Picture of Build your own computer\"]"));
        //click on email friend email button
        clickOnElement(By.xpath("//input[@value=\"Email a friend\"]"));
        //Enter friend email
        typeText(By.xpath("//input[@data-val-required=\"Enter friend's email\"]"),"abc+"+timestamp()+"@gmail.com");
        //enter personal message to friend
        typeText(By.xpath("//textarea[@id=\"PersonalMessage\"]"),"abcdefghijklmnopqrstuvwxyz");
        //click on send email button
        clickOnElement(By.xpath("//input[@name=\"send-email\"]"));
        //compering actual result with expected result
        String expectedText = "Your message has been sent.";
        String actual = getTextFromElement(By.className("result"));
        Assert.assertEquals(actual, expectedText);

    }
    @Test

    //method for user should be able to add product to basket successfully
    public void userShouldBeAbleToAddProductToBasketSuccessfully()
    {
        //click on Book category
        clickOnElement(By.xpath("//ul[@class=\"top-menu notmobile\"]/li[5]/a"));
        //click on fahrenheit book add to cart button
        clickOnElement(By.xpath(" //div[@class=\"item-grid\"]/div[1]/div/div[2]/div[3]/div[2]/input[1]"));
        ////using reusabble method wait untill find the element
        waituntilElementIsclickable(By.xpath("//div[@class=\"item-grid\"]/div[3]/div/div[2]/div[3]/div[2]/input[1]"),10);
        //click on 2nd book
        clickOnElement(By.xpath("//div[@class=\"item-grid\"]/div[3]/div/div[2]/div[3]/div[2]/input[1]"));
        //using inbuild method to sleep until you click on element
        sleep1(20);
        //click on shopping cart button
        clickOnElement(By.xpath("//a[@class=\"ico-cart\"]"));
        //compering actual result with expected result
        String expectedText=getTextFromElement(By.xpath("//tbody/tr/td[4]/a[@href=\"/fahrenheit-451-by-ray-bradbury\"]"));
        String actualText=getTextFromElement(By.xpath("//tbody/tr/td[4]/a[@href=\"/fahrenheit-451-by-ray-bradbury\"]"));
        Assert.assertEquals(actualText,expectedText);
        String expected = getTextFromElement(By.xpath("//tbody/tr/td[4]/a[@href=\"/pride-and-prejudice\"]"));
        String actual = getTextFromElement(By.xpath("//tbody/tr/td[4]/a[@href=\"/pride-and-prejudice\"]"));

    }
}




