package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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

public class LoginController implements Initializable, DBConnection {
    Stage stage;
    Scene scene;
    Parent root;
    static private PreparedStatement preparedStatement;
    static private Connection connection;
    static int userID;
    String fname, lastname;

    @FXML
    private TextField IDtext;

    @FXML
    private TextField PasswordText;

    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXCheckBox studentCheck;

    @FXML
    private JFXCheckBox adminCheck;
    @FXML
    private JFXButton loginRegisterButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginRegisterButton.setOnAction(actionEvent -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/applyAdmission.fxml"));

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

        loginButton.setOnAction(event -> {
            if (studentCheck.isSelected()) {

                loginStudentIn();
            } else {
                loginAdmin();
            }
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

    public void loginAdmin() {
        String userName = IDtext.getText();
        String password = PasswordText.getText();
        Admin admin = new Admin();
        admin.setUserName(userName);
        admin.setPassword(password);
        try {
            ResultSet userRes = getAdmin(admin);
            int counter = 0;
            while (userRes.next()) {
                String userName_res = userRes.getString("adminid");
                String password_res = userRes.getString("adminpassword");
                System.out.println("Welcome " + userName_res + " " + "password-> " + password_res);

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

    public ResultSet getAdmin(Admin admin) throws SQLException {
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

    public void loginStudentIn() {
        String userName = IDtext.getText();
        String password = PasswordText.getText();
        Student student = new Student();
        student.setUserName(userName);
        student.setPassword(password);
        try {
            ResultSet userRes = getStudents(student);
            int counter = 0;
            while (userRes.next()) {
                String userName_res = userRes.getString("student_username");
                String password_res = userRes.getString("student_password");
                System.out.println("Welcome" + userName_res + "password->" + password_res);
                userID = userRes.getInt("studentid");
                System.out.println("Passed Checked ->>" + userID);

                counter++;
            }
            if (counter == 1) {

                loginButton.getScene().getWindow().hide();
                System.out.println("Login Successful");
                CheckAdmission();
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("/sample/sample.fxml"));
//
//                try {
//                    fxmlLoader.setRoot(fxmlLoader.getRoot());
//                    fxmlLoader.load();
//                } catch (IOException e) {
//
//                }
//
//                Parent root = fxmlLoader.getRoot();
//                Stage stage = new Stage();
//                stage.setScene(new Scene(root));
//                stage.show();
//                    StudentAdmissionController controller=fxmlLoader.getController();
//                    System.out.println("USERID->>"+ userID);
//                    controller.setUserIDPass(userID);

            } else {
                System.out.println("Wrong Data");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getStudents(Student student) throws SQLException {
        ResultSet resultset = null;
        String query = "SELECT * FROM student WHERE student_username=? AND student_password=?";
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, student.getUserName());
            preparedStatement.setString(2, student.getPassword());
            resultset = (ResultSet) preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("Reading from ALL students" + resultset);
        return resultset;
    }

    public ResultSet CheckAdmission() throws SQLException {
        ResultSet resultset = null;
        String query = "SELECT * FROM admissionlist WHERE studentid=?";
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);

            resultset = (ResultSet) preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("Reading from ALL newstudents" + resultset);
        int counter = 0;
        while (resultset.next()) {

            counter++;
        }
        if (counter == 1) {
            System.out.println("Data exists");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/afterlogin.fxml"));

            try {
                fxmlLoader.setRoot(fxmlLoader.getRoot());
                fxmlLoader.load();
            } catch (IOException e) {

            }
            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root,850,500));
            stage.show();

        } else {
            System.out.println("No data");

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/Newregister.fxml"));

            try {
                fxmlLoader.setRoot(fxmlLoader.getRoot());
                fxmlLoader.load();
            } catch (IOException e) {

            }

            Parent root = fxmlLoader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            RegisterController controller = fxmlLoader.getController();
            System.out.println("USERID->>" + userID);
            controller.setUserIDPass(userID);
        }

        return resultset;
    }
}
