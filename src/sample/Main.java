package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.*;
import sample.database.DBConnection;
import sample.database.MySQLConnection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/Splash.fxml"));
        primaryStage.setTitle("UMS ");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();

        DBConnection dbConnection=new AdminController();
        DBConnection dbConnectionDecline=new DeclineController();
        DBConnection dbConnectionLogin=new LoginController();
        DBConnection dbConnectionExtension=new Extension();
        DBConnection dbConnectionAfterLogin=new AfterLoginController();
        DBConnection dbConnectionAccept=new ApproveController();
        DBConnection dbConnectionAcceptDetails=new AcceptDetailsController();
        DBConnection dbConnection1=new RegisterOneController();

        DBConnection dbConnectionRegisterProfile=new RegisterController();

        MySQLConnection mySQLConnection=new MySQLConnection();

        mySQLConnection.connectSQL(dbConnection);
        mySQLConnection.connectSQL(dbConnectionAcceptDetails);
        mySQLConnection.connectSQL(dbConnectionAfterLogin);
        mySQLConnection.connectSQL(dbConnectionDecline);
        mySQLConnection.connectSQL(dbConnectionExtension);
        mySQLConnection.connectSQL(dbConnectionRegisterProfile);
        mySQLConnection.connectSQL(dbConnectionLogin);
        mySQLConnection.connectSQL(dbConnectionAccept);
        mySQLConnection.connectSQL(dbConnection1);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
