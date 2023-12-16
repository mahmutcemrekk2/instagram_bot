package like.instagram;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {

    WebDriver driver;
    String BASE_URL = "https://www.instagram.com/";

    By userNameLocater = new By.ByCssSelector("input[name='username']");
    By passwordLocater = new By.ByCssSelector("input[name='password']");
    By buttonLoginLocater = new By.ByCssSelector("button[type='submit']");
    By instagramLogoLocater = new By.ByClassName("_aagx");
    By firsPostLocater = new By.ByClassName("_aagu");
    By likeButtonLocater = new By.ByClassName("_aamw");
    By infoLocater = new By.ByClassName("_ac2a");
    By htmlTag = By.tagName("html");

    public App(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
    }

    private void waitFor(By locater){
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locater));
    }

    public void login(String username, String password){

        waitFor(userNameLocater);
        driver.findElement(userNameLocater).sendKeys(username);
        driver.findElement(passwordLocater).sendKeys(password);
        driver.findElement(buttonLoginLocater).click();
        waitFor(instagramLogoLocater);
    }

    public void navigateToNewTargetProfile(String profileName){
        driver.navigate().to(BASE_URL.concat(profileName));
    }

    public void clickFirstPost(){
        waitFor(firsPostLocater);
        driver.findElements(firsPostLocater).get(0).click();
    }

    public void likePost(int count){
        while (count > 0){
            driver.findElement(likeButtonLocater).click();
            driver.findElement(htmlTag).sendKeys(Keys.ARROW_RIGHT);
            count --;
        }
    }

    public int getPostCount(){
        String count =  driver.findElements(infoLocater).get(0).getText();
        return Integer.parseInt(count);
    }
}
