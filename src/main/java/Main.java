
import dao.Connect;
import dao.Database;
//import jdk.internal.util.xml.impl.Input;

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

        Methods.updatePopularity();
        Methods.getToHabr();
        Methods.getToBash();





    }


}






