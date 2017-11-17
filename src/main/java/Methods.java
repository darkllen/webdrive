import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by user on 28.10.2017.
 */
public class Methods {

    public WebDriver getDriver(){
        System.setProperty("webdriver.chrome.driver", "D:\\IDEA\\webdrive\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public void getDefaultSite(WebDriver driver) throws InterruptedException {
        driver.get("https://betscsgo.net/login/");
        Thread.sleep(10000);
    }

    public void LogIn(WebDriver driver) throws InterruptedException {
        WebElement element = driver.findElement(By.id("steamAccountName"));
        element.sendKeys("kking_of_dark");
        element = driver.findElement(By.id("steamPassword"));
        element.sendKeys("0987654321ist");
        element = driver.findElement(By.id("imageLogin"));
        element.click();
        Thread.sleep(15000);
    }

    public int Transform(String percent){
    String  sub = percent.substring(0, percent.length()-1);

        int result = Integer.parseInt(sub);
        System.out.println("percent = " +result);
        return result;
    }

    public boolean CheckTime(String time){
        if (time.matches("")){
            return false;
        }
        if (time.matches("1д")){
            return false;
        }
        if (time.matches("Скоро старт")){
            System.out.println("Soon");
            return true;
        }
        time = time.replaceAll("\\D", "");
        int result = Integer.parseInt(time);
       /* System.out.println("Left " +result);*/
        if (result<10){
            return true;
        } else {
            return false;
        }
    }
}
