import dao.Connect;
import dao.Database;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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


   public static void getToHabr() throws SQLException, ParseException, IOException {

        Connect connect = new Connect();
        Database database = new Database(connect.connect());

        database.updatePopularity(50, "https://habr.com/ru/post/450182/");

       Document doc = Jsoup.connect("https://habr.com/ru/all/").get();
       Elements names = doc.getElementsByClass("post__title_link");
       Elements time = doc.getElementsByClass("post__time");

        SimpleDateFormat dateFormat = new SimpleDateFormat("y.MM.dd");

       List<String[]> formatData = new LinkedList<String[]>();
       List<String> dateInText = new LinkedList<String>();

       for (int i = 0; i < time.size(); i++) {
           formatData.add(time.get(i).text().split(" "));
           if (formatData.get(i)[0].matches("сегодня")) {
               Date date = new Date();
               String dateF = dateFormat.format(date);
               formatData.get(i)[0] = dateF;
               formatData.get(i)[2] = formatData.get(i)[2].replace(':', '.');
               dateInText.add(formatData.get(i)[0] + " " + formatData.get(i)[2]);
           }
       }


       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y.MM.dd HH.mm");
       Date currentDate = simpleDateFormat.parse(database.getLastTime(2));

       List<Date> dateOfHabr = new LinkedList<Date>();
       for (int i = 0; i < dateInText.size(); i++) {
           dateOfHabr.add(i, simpleDateFormat.parse(dateInText.get(i)));
       }


       for (int i = dateInText.size() - 1; i >= 0; i--) {
           if (dateOfHabr.get(i).compareTo(currentDate) > 0) {
               String href = names.get(i).attr("href");
               String name = names.get(i).text();
               database.insertNew("habr",href,name,dateInText.get(i));
              database.updateLastDate(dateInText.get(i), 2);
           }
       }
    }

    public void updatePopularity(){

    }



/*    public void getToBash(WebDriver driver) throws InterruptedException, ParseException, SQLException, IOException {
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
      *//*      if (dateOfBash.get(i).compareTo(currentDate) == 0) {
                String name = dateOfBashForFile.get(i) + "(2)" + " bash";
                database.insertNew("bash",hreh.get(i).getAttribute("href"),"bash",dateOfBashForFile.get(i));

                database.updateLastDate(dateOfBashForFile.get(i), 1);
            }*//*
        }
    }*/
}

