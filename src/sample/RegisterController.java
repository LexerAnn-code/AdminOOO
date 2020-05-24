package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private JFXButton RegisterNextButton;

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



    static private PreparedStatement preparedStatement;
    static private Connection connection;
    byte[] pdfData;

    private int userIDPass;
    public static String filename;
    public static int id;

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
            registerAdmission.setPreviousSchool(previousSchool);
            registerAdmission.setSchoolAddress(schoolAddress);

            id = getUserIDPass();
            try {
                writeToAdmissionDB(registerAdmission);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    }


    @Override
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "");
        System.out.println("Connection " + connection.getCatalog());
    }

    private void writeToAdmissionDB(RegisterAdmission registerAdmission) throws SQLException {
        String insert = "INSERT INTO admissionlist(studentid,first_name,last_name,middle_name,father_name,mother_name,contact_number,address,course,level,previous_school,school_address,document)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";


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

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
