package dbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection = null;
    private final String USERNAME = "admin";
    private final String PASSWORD = "";
    private final String IP = "localhost";
    private final String PORT = "5432";
    private String url = "jdbc:postgresql://"+IP+":"+PORT+"/unina";

    private DBConnection() throws SQLException {
        //Properties props = new Properties();
        //props.setProperty("user", USERNAME);
        //props.setProperty("pwd", PASSWORD);

        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);

        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null)
        {
            instance = new DBConnection();
        }
        else
            if (instance.getConnection().isClosed())
            {
                instance = new DBConnection();
            }

        return instance;
    }

    /*private void createDatabase(String dbname)
    {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE " + dbname);
        }
        catch (SQLException sqlException) {
            if (sqlException.getErrorCode() == 1007) {
                System.out.println("Database " + dbname + " already exists: " + sqlException.getMessage());
            } else {
                // Some other problems, e.g. Server down, no permission, etc
                System.out.println("SqlException: " + sqlException.getMessage());
            }
        }
    }*/
}
