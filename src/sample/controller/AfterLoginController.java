package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Admission;
import sample.database.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AfterLoginController implements Initializable, DBConnection {
    static private PreparedStatement preparedStatement;
    static private Connection connection;
    @FXML
    private Button backButton;

    @FXML
    private Hyperlink link2;

    @FXML
    private Label userNameState;

    @FXML
    private Label admissionState;

    @FXML
    private Label hallState;

    @FXML
    private Label programState;

    @FXML
    private Label levelState;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ID AF->>" + LoginController.userID);
        Admission admission=new Admission();
        try {
            ResultSet userRes = ReadToDBs();

            while (userRes.next()) {
                //Loops through the queried data from the database
                admission.setFirstname(userRes.getString("first_name"));
                admission.setLastname(userRes.getString("last_name"));
                admission.setAdmissionID(userRes.getInt("admissionid"));
                admission.setLevel(userRes.getString("level"));
                admission.setCourse(userRes.getString("course"));
                admission.setAdmission(userRes.getString("status"));
                admission.setHall(userRes.getString("hall_residence"));
            }
            userNameState.setText(admission.getLastname());
            admissionState.setText(admission.getAdmission());
            levelState.setText(admission.getLevel());
            programState.setText(admission.getCourse());
            hallState.setText(admission.getHall());



        } catch (SQLException e) {
            e.printStackTrace();
        }



        backButton.setOnAction(actionEvent -> {
            backButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/view/Login.fxml"));

            try {
                fxmlLoader.setRoot(fxmlLoader.getRoot());
                fxmlLoader.load();
            } catch (IOException e) {

            }

            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
    private ResultSet ReadToDBs() throws SQLException {
        ResultSet resultSet = null;
        String insert = "SELECT * FROM admissionlist WHERE studentid=?";
        preparedStatement = (PreparedStatement) connection.prepareStatement(insert);
        preparedStatement.setInt(1,LoginController.userID);
        resultSet = preparedStatement.executeQuery();
        System.out.println("Data" + resultSet);
        return resultSet;
        //Returns a set of data queried into resultsSet
    }

    @Override
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "");
        System.out.println("Connection " + connection.getCatalog());
        //Makes a call to the database
    }
}
