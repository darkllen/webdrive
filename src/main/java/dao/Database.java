package dao;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by user on 21.11.2017.
 */


public class Database {

    private static   Statement statement;

    public Database(Statement statement) {
        this.statement = statement;
    }

    public String getLastTime (int id) throws SQLException {
        String query = "SELECT `value` from time WHERE id = " + id;
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            return rs.getString("value");
        }
        return null;
    }

    public void updateLastDate(String lastTime, int id) throws SQLException {
        String query = "update time Set `value` = \"" +lastTime+ "\" where id =" + id;
        statement.executeUpdate(query);
    }

    public void insertNew (String source, String href, String name, String time) throws SQLException {
        String query = "INSERT INTO `information` (`source`, `href`, `name`, `time`) VALUES ('" +source +"', '" +href+"', '"+name+"', '" + time + "')";
        statement.execute(query);
    }

    public void updatePopularity(int popularity, String href) throws SQLException {
        String query = "update `information` Set `popularity` = \"" +popularity+ "\" where  href='" + href +"'";
        statement.executeUpdate(query);
    }

    public ArrayList<String> getHrefs() throws SQLException {
        ArrayList<String> hrefs = new ArrayList<String>();
        String query = "SELECT `href` from information where `source`!='bash'";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            hrefs.add( rs.getString("href"));
        }
        return hrefs;
    }
    }
