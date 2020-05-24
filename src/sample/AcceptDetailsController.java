package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AcceptDetailsController implements Initializable,DBConnection {
    @FXML
    private Label firstname;

    @FXML
    private Label lastname;

    @FXML
    private TextField hall;

    @FXML
    private Button submit;
    static private PreparedStatement preparedStatement;
    static private Connection connection;
    Stage stage;
    Scene scene;
    Parent root;
    private static String lastnames;
    private static String firstnames;
    private static int ID;
    public void setAdmissionID(int ID){
        this.ID=ID;
    }
    public int getID(){
        return ID;
    }

    public void setFirstname(String firstnames,String lastnames)
    {
        this.firstnames = firstnames;
        this.lastnames=lastnames;
    }

    public String getFirstName() {
        System.out.println("GET" + firstnames);
        return firstnames +  " " + lastnames;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String fn=getFirstName();
        firstname.setText(fn);
        submit.setOnAction(actionEvent -> {

            try {
                submitDetails(hall.getText().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    private void submitDetails(String hall) throws SQLException {
        String querys = "UPDATE  admissionlist SET hall_residence= ?" + "WHERE admissionid= ?";
        preparedStatement = (PreparedStatement) connection.prepareStatement(querys);
        preparedStatement.setInt(2,ID);
        preparedStatement.setString(1,hall);
       preparedStatement.execute();


    }

    @Override
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "");
        System.out.println("Connection " + connection.getCatalog());
    }
}
