package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Shaker;
import sample.database.DBConnection;
import sample.Student;

import java.io.IOException;
import java.net.URL;

import java.sql.*;

import java.util.ResourceBundle;

public class RegisterOneController implements Initializable, DBConnection {
    @FXML
    private ImageView back;

    @FXML
    private TextField username;

    @FXML
    private PasswordField pass;

    @FXML
    private JFXButton submitButton;

    @FXML
    private JFXButton backButton;
    @FXML
    private Label toastError;

    @FXML
    private Label toastsucess;
    @FXML
    private Label toastfields;


    static private PreparedStatement preparedStatement;
   static private Connection connection;
    private Parent root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        back.setOnMouseClicked(mouseEvent ->{
            back.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/Login.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root,850,600));
            stage.showAndWait();
        });

        submitButton.setOnAction(event -> {

                String usernameTe=username.getText();
                String passsword=pass.getText();
                Student studenet=new Student();
                studenet.setUserName(usernameTe);
                studenet.setPassword(passsword);
                try{
                    if (username.getText().equals("") || pass.getText().equals("") ) {
                        Shaker shakerUsername = new Shaker(username);
                        Shaker shakerPassword = new Shaker(pass);
                        toastfields.setVisible(true);
                        Shaker shakerToast = new Shaker(toastfields);
                    }
                    else{

                        ResultSet userResults=signUserCheck(studenet);
                        int counter=0;
                        while(userResults.next()){
                            counter++;
                        }
                        if(counter==1){
                            System.out.println("Data already exists");
                            toastError.setVisible(true);
                            Shaker shakerToast=new Shaker(toastError);


                        }
                        else{
                            System.out.println("Sign up Successful");
                            toastsucess.setVisible(true);
                            Shaker shakerToast=new Shaker(toastsucess);

                            signUpUser(studenet);

                        }
                    }



                } catch (SQLException e) {
                    e.printStackTrace();
                }


        });


    }




    @Override
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "");
        System.out.println("Connection " + connection.getCatalog());
    }


    public void signUpUser(Student student){


        String insert = "INSERT INTO student(student_username,student_password)" + "VALUES(?,?)";
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(insert);
            preparedStatement.setString(1,student.getUserName());
            preparedStatement.setString(2, student.getPassword());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

    public ResultSet signUserCheck(Student student) throws SQLException {
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


        return resultset;
    }
}
