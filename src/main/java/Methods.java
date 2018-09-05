import dao.Connect;
import dao.Database;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 28.10.2017.
 */
public class Methods {

    public WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yanki\\Desktop\\webdrive\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public void getToHabr(WebDriver driver) throws SQLException, ParseException, IOException {
        driver.get("https://habr.com/all/");

        Connect connect = new Connect();
        Database database = new Database(connect.connect());

        SimpleDateFormat dateFormat = new SimpleDateFormat("y.MM.dd");

        List<WebElement> articleButtons = driver.findElements(By.className("post__habracut-btn"));

        List<WebElement> data = driver.findElements(By.className("post__time"));
        List<WebElement> dataAnother = driver.findElements(By.className("preview-data__time-published"));
        data.addAll(dataAnother);

        List<WebElement> h1 = driver.findElements(By.className("post__title_link"));
        List<WebElement> h1Another = driver.findElements(By.className("preview-data__title-link"));
        h1.addAll(h1Another);

        List<String[]> formatData = new LinkedList<String[]>();
        List<String> dateInText = new LinkedList<String>();

        for (int i = 0; i < data.size(); i++) {
            formatData.add(data.get(i).getText().split(" "));
            if (formatData.get(i)[0].matches("сегодня")) {
                Date date = new Date();
                String dateF = dateFormat.format(date);
                formatData.get(i)[0] = dateF;
                formatData.get(i)[2] = formatData.get(i)[2].replace(':', '.');
                dateInText.add(formatData.get(i)[0] + " " + formatData.get(i)[2]);
            }
        }


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y.MM.dd HH.mm");
        Date currentDate = simpleDateFormat.parse(database.getLastDate(2));

        List<Date> dateOfHabr = new LinkedList<Date>();
        for (int i = 0; i < dateInText.size(); i++) {
            dateOfHabr.add(i, simpleDateFormat.parse(dateInText.get(i)));
        }


        for (int i = dateInText.size() - 1; i >= 0; i--) {
            if (dateOfHabr.get(i).compareTo(currentDate) > 0) {

                String href = h1.get(i).getText().replace('\'', '.');
                String name = dateInText.get(i) + " " + href + " habr";
              /*  File file = new File("D:\\IDEA\\webdrive\\information\\public_html\\files\\" + name);
                FileWriter writer = new FileWriter(file);
                writer.write(articleButtons.get(i).getAttribute("href"));
                writer.close();*/
                System.out.println(articleButtons.size());
                System.out.println(h1.size());
                System.out.println(dateInText.size());
                database.insertNew("habr", articleButtons.get(i).getAttribute("href"),href,dateInText.get(i));
                database.updateLastDate(dateInText.get(i), 2);
            }
        }

    }

    public void getToBash(WebDriver driver) throws InterruptedException, ParseException, SQLException, IOException {
        driver.get("https://bash.im/");

        Connect connect = new Connect();
        Database database = new Database(connect.connect());
        SimpleDateFormat dateFormat = new SimpleDateFormat("y.MM.dd HH.mm");
        List<WebElement> text = driver.findElements(By.className("text"));
        List<WebElement> data = driver.findElements(By.className("actions"));

        List<String[]> textsFormat = new LinkedList<String[]>();
        for (int i = 0; i < text.size(); i++) {
            textsFormat.add(text.get(i).getText().split("\n"));
        }

        List<String> dateOfBashForFile = new LinkedList<String>();
        List<Date> dateOfBash = new LinkedList<Date>();

        List<WebElement> hreh = driver.findElements(By.className("id"));

        for (int i = 0; i < data.size(); i++) {
            dateOfBashForFile.add(i, (data.get(i).getText().substring(data.get(i).getText().lastIndexOf(']') + 2, data.get(i).getText().indexOf('#') - 1)));
            dateOfBashForFile.set(i, dateOfBashForFile.get(i).replace('-', '.'));
            dateOfBashForFile.set(i, dateOfBashForFile.get(i).replace(':', '.'));
            dateOfBash.add(i, dateFormat.parse(dateOfBashForFile.get(i)));
        }


        int doub = 0;

        for (int i = dateOfBashForFile.size() - 1; i >= 0; i--) {
            Date currentDate = dateFormat.parse(database.getLastDate(1));
            if (dateOfBash.get(i).compareTo(currentDate) > 0) {

                String name = dateOfBashForFile.get(i) + " bash";

                database.insertNew("bash",hreh.get(i).getAttribute("href"),"bash",dateOfBashForFile.get(i));

                database.updateLastDate(dateOfBashForFile.get(i), 1);
            }
      /*      if (dateOfBash.get(i).compareTo(currentDate) == 0) {
                String name = dateOfBashForFile.get(i) + "(2)" + " bash";
                database.insertNew("bash",hreh.get(i).getAttribute("href"),"bash",dateOfBashForFile.get(i));

                database.updateLastDate(dateOfBashForFile.get(i), 1);
            }*/
        }
    }
}

