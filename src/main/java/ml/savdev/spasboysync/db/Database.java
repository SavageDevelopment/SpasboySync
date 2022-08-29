package ml.savdev.spasboysync.db;

import java.sql.*;

public class Database {

    private Connection connection;

    public Connection getConnection() throws SQLException {

        if(connection != null){
            return connection;
        }

        //Try to connect to my MySQL database running locally
        String url = "jdbc:mysql://145.239.5.126:3306/mc148833";
        String user = "mc148833";
        String password = "50a4c1415d";

        Connection connection = DriverManager.getConnection(url, user, password);

        this.connection = connection;

        System.out.println("Connected to database.");

        return connection;
    }

    public void initializeDatabase() throws SQLException {

        Statement statement = getConnection().createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS _spasboysync (CODE VARCHAR(100),UUID VARCHAR(100),PRIMARY KEY (CODE))";

        statement.execute(sql);

        statement.close();

    }

    public void newCode(String uuid, String code) throws SQLException {

        PreparedStatement statement = getConnection()
                .prepareStatement("INSERT IGNORE INTO _spasboysync (CODE,UUID) VALUES (?,?)");
        statement.setString(1, code);
        statement.setString(2, uuid);

        statement.executeUpdate();

        statement.close();

    }


    public void delCode(String uuid) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("DELETE FROM _spasboysync WHERE UUID = '?'");
        statement.setString(1, uuid);

        statement.executeUpdate();

        statement.close();

    }

}