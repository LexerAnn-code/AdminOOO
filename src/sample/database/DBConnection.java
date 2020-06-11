package sample.database;

import java.sql.SQLException;

public interface DBConnection {
    //Programming to an interface since we don't want our classes to be hardly dependent on a single class of database.
    public void connect() throws SQLException;
}
