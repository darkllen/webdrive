
import dao.Connect;
import dao.Database;
import jdk.internal.util.xml.impl.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 27.10.2017.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException, ParseException, IOException {


        Methods methods = new Methods();

        WebDriver driver = methods.getDriver();



        while (true){
            methods.getToHabr(driver);
            methods.getToBash(driver);
        }




    }



}






