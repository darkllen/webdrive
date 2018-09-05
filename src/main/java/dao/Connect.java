
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by user on 01.11.2017.
 */
public class Connect  {
    private static final String url = "jdbc:mysql://db4free.net:3306/lendro";
    private static final String user = "darklen";
    private static final String password = "0987654321";
    public Connection connection;

    public Statement connect() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}