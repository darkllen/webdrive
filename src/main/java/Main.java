
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;

/**
 * Created by user on 27.10.2017.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {


        Methods methods = new Methods();

        WebDriver driver = methods.getDriver();

        methods.getDefaultSite(driver);

        methods.LogIn(driver);

        int iteration = 0;
        int count = 0;

while (true){

    iteration++;

    System.out.println("");
    System.out.println("Iteration " + iteration);
    System.out.println("");

    if (count%2 == 0){
        methods.getDefaultSite(driver);
    }
    count++;
    Thread.sleep(5000);
    WebElement element = driver.findElement(By.id("bets-next"));

    System.out.println("Bets-next " + element);
    ArrayList<WebElement> games = (ArrayList<WebElement>) element.findElements(By.className("b-game"));
    System.out.println("games size " + games.size());

    WebElement team1 = null;
    WebElement team2 = null;

    for (int i=0; i<games.size(); i++){
        element = driver.findElement(By.id("bets-next"));
        games = (ArrayList<WebElement>) element.findElements(By.className("b-game"));

        System.out.println("games size " + games.size());
        System.out.println("game " + i +" =" + games.get(i));

        team1 = games.get(i).findElement(By.className("b-team1"));
        System.out.println("team1 = " +team1);

        team2 = games.get(i).findElement(By.className("b-team2"));
        System.out.println("team2 = " +team2);


        int percent1 = methods.Transform(team1.findElement(By.className("stat-proc")).getAttribute("innerText"));
        int percent2 = methods.Transform(team2.findElement(By.className("stat-proc")).getAttribute("innerText"));
        System.out.println(games.get(i).findElement(By.className("bet-time-left")).getAttribute("innerText"));

        if (percent1>69  & methods.CheckTime(games.get(i).findElement(By.className("bet-time-left")).getAttribute("innerText"))) {
            team1.findElement(By.className("b-koef")).click();
            team1 = driver.findElement(By.className("bet-input"));
            if (team1.getAttribute("value").matches("100")){
                Thread.sleep(500);
                driver.findElement(By.className("bet-pop-closepopup")).click();
            }else {
            team1.sendKeys("100");
            Thread.sleep(500);

            team1 = driver.findElement(By.className("btn-success"));
                team1.click();}
            Thread.sleep(1000);


        }

        if (percent2>69 & methods.CheckTime(games.get(i).findElement(By.className("bet-time-left")).getAttribute("innerText"))){
            team2.findElement(By.className("b-koef")).click();
            team2 = driver.findElement(By.className("bet-input"));
            if (team2.getAttribute("value").matches("100")){
                Thread.sleep(500);
                driver.findElement(By.className("bet-pop-closepopup")).click();
            }else {
            team2.sendKeys("100");
            Thread.sleep(499);

            team2 = driver.findElement(By.className("btn-success"));
            team2.click();}
            Thread.sleep(999);


        }

    }

    Thread.sleep(540000);

}



        }
    }


