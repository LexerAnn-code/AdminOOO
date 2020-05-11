package sample;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable,DBConnection {
    Stage stage;
    Scene scene;
    Parent root;
    static private PreparedStatement preparedStatement;
    static private Connection connection;
    @FXML
    private TextField IDtext;

    @FXML
    private TextField PasswordText;

    @FXML
    private JFXButton loginButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            loginButton.setOnAction(actionEvent -> {
                loginStudentIn();
            });
    }

    @Override
    public void connect() throws SQLException {
        ControllerConnect();
    }
    private void ControllerConnect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "");
        System.out.println("Connection " + connection.getCatalog());
    }
    public void loginStudentIn () {
        String userName = IDtext.getText();
        String password = PasswordText.getText();
        Admin admin = new Admin();
        admin.setUserName(userName);
        admin.setPassword(password);
        try {
            ResultSet userRes = getUsers(admin);
            int counter = 0;
            while (userRes.next()) {
                String userName_res = userRes.getString("adminid");
                String password_res = userRes.getString("adminpassword");
                System.out.println("Welcome " + userName_res + " "+ "password-> " + password_res);
                System.out.println("Passed Checked");

                counter++;
            }
            if (counter == 1) {
                loginButton.getScene().getWindow().hide();
                System.out.println("Login Successful");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/sample/AdminPage.fxml"));

                try {
                    fxmlLoader.setRoot(fxmlLoader.getRoot());
                    fxmlLoader.load();
                } catch (IOException e) {

                }

                Parent root = fxmlLoader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();


            } else {
                System.out.println("Wrong Data");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public ResultSet getUsers(Admin admin) throws SQLException {
        ResultSet resultset = null;
        if (!IDtext.getText().equals("") || !PasswordText.getText().equals("")) {
            String query = "SELECT * FROM admintable WHERE adminid=? AND adminpassword=?";
            try {
                preparedStatement = (PreparedStatement) connection.prepareStatement(query);
                preparedStatement.setString(1, admin.getUserName());
                preparedStatement.setString(2, admin.getPassword());
                resultset = (ResultSet) preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Reading from ALL Admin  " + resultset);
        return resultset;
    }
}
