package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Shaker;
import sample.database.DBConnection;
import sample.RegisterAdmission;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable, DBConnection {
    @FXML
    private JFXButton RegisterSaveButton;

    @FXML
    private JFXButton RegisterCloseButton;

    @FXML
    private TextField RegisterLastName;

    @FXML
    private TextField RegisterFirstName;

    @FXML
    private TextField RegisterMiddleName;

    @FXML
    private TextField RegisterAddress;

    @FXML
    private TextField RegisterFartherName;

    @FXML
    private TextField RegisterMotherName;

    @FXML
    private TextField RegisterConatctNum;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField course;

    @FXML
    private TextField level;

    @FXML
    private JFXButton schoolDocButton;

    @FXML
    private TextField registerPrevSchool;

    @FXML
    private TextField registerSchoolAddress;

    @FXML
    private ListView  registerListView;
    @FXML
    private DatePicker registerLastDate;

    @FXML
    private JFXCheckBox male;

    @FXML
    private JFXCheckBox female;

    @FXML
    private Label toastsucess;
    @FXML
    private Label toastfailure;





    static private PreparedStatement preparedStatement;
    static private Connection connection;
    byte[] pdfData;
    String gender;

    private int userIDPass;
    public static String filename;
    public static int id;
    private Parent root;


    public void setUserIDPass(int userIDPass) {
        this.userIDPass = userIDPass;
    }

    public int getUserIDPass() {
        System.out.println("GET" + userIDPass);
        return userIDPass;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        RegisterSaveButton.setOnAction(actionEvent -> {
            if(male.isSelected()){
                female.setSelected(false);
                gender="Male";
            }
            else {
                male.setSelected(false);
                gender="Female";
            }

            String contactNumber = RegisterConatctNum.getText();
            String motherName = RegisterMotherName.getText();
            String fatherName = RegisterFartherName.getText();
            String address = RegisterAddress.getText();
            String middleName = RegisterMiddleName.getText();
            String firstname = RegisterFirstName.getText();
            String courses = course.getText();
            String levelText = level.getText();
            String previousSchool = registerPrevSchool.getText();
            String schoolAddress = registerSchoolAddress.getText();
            String lastname = RegisterLastName.getText();
            RegisterAdmission registerAdmission = new RegisterAdmission();
            registerAdmission.setLastName(lastname);
            registerAdmission.setFirstName(firstname);
            registerAdmission.setMiddleName(middleName);
            registerAdmission.setAddress(address);
            registerAdmission.setFatherName(fatherName);
            registerAdmission.setMotherName(motherName);
            registerAdmission.setContact(contactNumber);
            registerAdmission.setCourse(courses);
            registerAdmission.setLevel(levelText);
            registerAdmission.setGender(gender);
            registerAdmission.setPreviousSchool(previousSchool);
            registerAdmission.setSchoolAddress(schoolAddress);




            id = getUserIDPass();
            


            try {
                if (RegisterLastName.getText().equals("") || RegisterFirstName.getText().equals("") || level.getText().equals("")|| course.getText().equals("")||  registerListView.getItems().equals("")){


                    Shaker fname=new Shaker(RegisterFirstName);
                    Shaker lname=new Shaker(RegisterLastName);
                    Shaker programme=new Shaker(course);
                    Shaker leveel=new Shaker(level);
                    Shaker schdoc=new Shaker(registerListView);
                   // Shaker genderr=new Shaker(gender);

                    toastfailure.setVisible(true);
                    Shaker shakerToast=new Shaker(toastfailure);



                }
                else{
                    toastsucess.setVisible(true);
                    Shaker shakerToast=new Shaker(toastsucess);

                    writeToAdmissionDB(registerAdmission);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

         



        });


        RegisterCloseButton.setOnAction(actionEvent -> {
            RegisterCloseButton.getScene().getWindow().hide();
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
        schoolDocButton.setOnAction(event -> {
            FileChooser fc= new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files","*.pdf"));
            File selectedfile=fc.showOpenDialog(null);


            if (selectedfile!=null){
                filename=selectedfile.getAbsolutePath();
                registerListView.getItems().add(selectedfile.getName());

            }
            else{
                System.out.println("File is not valid");


            }

        });
        dob.setOnAction(actionEvent -> {
            dob.getEditor().getText();
        });
        registerLastDate.setOnAction(actionEvent -> {
            registerLastDate.getEditor().getText();
        });

    }


    @Override
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "");
        System.out.println("Connection " + connection.getCatalog());
    }

    private void writeToAdmissionDB(RegisterAdmission registerAdmission) throws SQLException {
        String insert = "INSERT INTO admissionlist(studentid,first_name,last_name,middle_name,father_name,mother_name,contact_number,address,course,level,previous_school,school_address,document,dob,lastdate,gender)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        try {
            File file=new File(filename);
            FileInputStream fis=new FileInputStream(file);
            preparedStatement = (PreparedStatement) connection.prepareStatement(insert);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, registerAdmission.getFirstName());
            preparedStatement.setString(3, registerAdmission.getLastName());
            preparedStatement.setString(4, registerAdmission.getMiddleName());
            preparedStatement.setString(5, registerAdmission.getFatherName());
            preparedStatement.setString(6, registerAdmission.getMotherName());
            preparedStatement.setString(7, registerAdmission.getContact());
            preparedStatement.setString(8, registerAdmission.getAddress());
            preparedStatement.setString(9, registerAdmission.getCourse());
            preparedStatement.setString(10, registerAdmission.getLevel());
            preparedStatement.setString(11, registerAdmission.getPreviousSchool());
            preparedStatement.setString(12, registerAdmission.getSchoolAddress());
            preparedStatement.setBinaryStream(13,fis);
            preparedStatement.setString(14,(dob.getEditor()).getText());
            preparedStatement.setString(15,(registerLastDate.getEditor()).getText());
            preparedStatement.setString(16,registerAdmission.getGender());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
