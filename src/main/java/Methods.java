import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dao.Connect;
import dao.Database;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 28.10.2017.
 */
class Methods {

   static void getToHabr() throws SQLException, IOException {
       SimpleDateFormat dateFormat = new SimpleDateFormat("y-MM-dd");
       Connect connect = new Connect();
       Database database = new Database(connect.connect());
       Document doc = Jsoup.connect("https://habr.com/ru/all/").get();
       Elements names = doc.getElementsByClass("post__title_link");
       Elements time = doc.getElementsByClass("post__time");

       List<String> dateInText = new LinkedList<>();
       for (Element element : time) {
           String[] splitTime = element.text().split(" ");
          if (splitTime[0].matches("сегодня")) {
               splitTime[0] = dateFormat.format(new Date());
               dateInText.add(splitTime[0] + " " + splitTime[2]);
           } else  if (splitTime[0].matches("вчера")) {
               splitTime[0] = dateFormat.format(java.sql.Date.valueOf(LocalDate.now().minusDays(1)));
               dateInText.add(splitTime[0] + " " + splitTime[2]);
           }
       }
        //add to db
       for (int i = 0; i <dateInText.size(); i++) {
               String href = names.get(i).attr("href");
               String name = names.get(i).text();
               String date = dateInText.get(i);
               try {
                   database.insertNew("habr", href, name, date);
               } catch (MySQLIntegrityConstraintViolationException e){
                  System.out.println("duplicate");
               }

       }
       database.updateLastDate(dateInText.get(0), 2);
    }

    static void getToBash() throws SQLException, IOException {
       //init
        SimpleDateFormat dateFormat = new SimpleDateFormat("y-MM-dd");
        Connect connect = new Connect();
        Database database = new Database(connect.connect());
        Document doc = Jsoup.connect("https://bash.im/").get();
        Elements time = doc.getElementsByClass("quote__header_date");
        Elements hrefs = doc.getElementsByClass("quote__header_permalink");


        List<String> dateInText = new LinkedList<>();
        for (Element element : time) {
            String[] timeSplit = element.text().split(" ");
            timeSplit[0] = dateFormat.format(new Date());
            dateInText.add(timeSplit[0] + " " + timeSplit[2]);
        }

        for (int i = 0; i <dateInText.size(); i++) {
                String href = "https://bash.im" +hrefs.get(i).attr("href");
                String name = "bash";
                String date = dateInText.get(i);
                try{
                    database.insertNew("bash",href,name,date);
                }
                catch (MySQLIntegrityConstraintViolationException e){
                    System.out.println("duplicate");
                }
                database.updatePopularity(300, href);
        }
        database.updateLastDate(dateInText.get(0), 1);
    }


    static void updatePopularity() throws SQLException {
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
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });

    }

}

