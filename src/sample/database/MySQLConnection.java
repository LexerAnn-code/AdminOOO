package sample.database;

import sample.database.DBConnection;

import java.sql.SQLException;

public class MySQLConnection {
    public void connectSQL(DBConnection dbConnection) throws SQLException {
        dbConnection.connect();

    }
}
