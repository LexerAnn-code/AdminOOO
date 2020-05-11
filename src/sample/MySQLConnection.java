package sample;

import java.sql.SQLException;

public class MySQLConnection {
    public void connectSQL(DBConnection dbConnection) throws SQLException {
        dbConnection.connect();
    }
}
