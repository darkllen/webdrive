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

    public static void updatePopularity() throws SQLException {
        Connect connect = new Connect();
        Database database = new Database(connect.connect());

        ArrayList<String> hrefs = database.getHrefs();

        hrefs.forEach(x->{
            try {
                Document document = Jsoup.connect(x).get();
                Elements element = document.getElementsByClass("voting-wjt__counter voting-wjt__counter_positive  js-score");
                element.addAll(document.getElementsByClass("voting-wjt__counter  voting-wjt__counter_negative js-score"));
                element.addAll(document.getElementsByClass("voting-wjt__counter   js-score"));
                String score = element.get(0).text().replaceAll("–", "-");
                int vote = Integer.parseInt(score);
                database.updatePopularity(vote,x);
                System.out.println(vote);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }



    public static void getToBash() throws InterruptedException, ParseException, SQLException, IOException {

        Connect connect = new Connect();
        Database database = new Database(connect.connect());
        Document doc = Jsoup.connect("https://bash.im/").get();

        SimpleDateFormat dateFormat = new SimpleDateFormat("y.MM.dd HH.mm");

        List<String[]> formatData = new LinkedList<String[]>();
        List<String> dateInText = new LinkedList<String>();

        Elements time = doc.getElementsByClass("quote__header_date");
        Elements hrefs = doc.getElementsByClass("quote__header_permalink");

        for (int i = 0; i < time.size(); i++) {
            formatData.add(time.get(i).text().split(" "));
                Date date = new Date();
                String dateF = dateFormat.format(date);
                formatData.get(i)[0] = dateF;
                formatData.get(i)[2] = formatData.get(i)[2].replace(':', '.');
                dateInText.add(formatData.get(i)[0] + " " + formatData.get(i)[2]);
        }


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y.MM.dd HH.mm");
        Date currentDate = simpleDateFormat.parse(database.getLastTime(1));

        List<Date> dateOfHabr = new LinkedList<Date>();
        for (int i = 0; i < dateInText.size(); i++) {
            dateOfHabr.add(i, simpleDateFormat.parse(dateInText.get(i)));
        }


        for (int i = dateInText.size() - 1; i >= 0; i--) {
            if (dateOfHabr.get(i).compareTo(currentDate) > 0) {
                String href = "https://bash.im" +hrefs.get(i).attr("href");
                String name = "bash";
                database.insertNew("bash",href,name,dateInText.get(i));
                database.updatePopularity(300, href);
                database.updateLastDate(dateInText.get(i), 1);
            }
        }

    }
}

