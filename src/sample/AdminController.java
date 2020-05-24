package sample;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminController implements Initializable, DBConnection {
    Stage stage;
    Scene scene;
    Parent root;
    private static int id;
    static private PreparedStatement preparedStatement;
    static private Connection connection;
    @FXML
    private Label total_applicant;

    @FXML
    private Label declineNav;

    @FXML
    private Label approveNav;
    @FXML
    private JFXListView<Admission> listView;
    ObservableList<Admission> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = FXCollections.observableArrayList();
        int counter = 0;
        try {
            ResultSet userRes = ReadToDB();

            while (userRes.next()) {
                Admission admission=new Admission();
                admission.setFirstname(userRes.getString("first_name"));
                admission.setLastname(userRes.getString("last_name"));
                admission.setAdmissionID(userRes.getInt("admissionid"));


                list.addAll(admission);
                counter++;
            }
            total_applicant.setText(String.valueOf(counter));
            System.out.println("Count" + counter);

            listView.setItems(list);
            listView.setCellFactory(RecyclerViewController -> new RecyclerViewController());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        approveNav.setOnMouseClicked(mouseEvent -> {
            moveToApprove();
            System.out.println("Approve Nav");
        });
        declineNav.setOnMouseClicked(mouseEvent -> {
            moveToDecline();
        });
    }

    public void moveToApprove() {

        try {

            root = FXMLLoader.load(getClass().getResource("/sample/ApprovePage.fxml"));
            scene = new Scene(root, 850, 500);
            stage = (Stage) approveNav.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToDecline() {
        System.out.println("Data exists");
        try {

            root = FXMLLoader.load(getClass().getResource("/sample/DeclinePage.fxml"));
            scene = new Scene(root, 850, 500);
            stage = (Stage) approveNav.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "");
        System.out.println("Connection " + connection.getCatalog());
    }

    private ResultSet ReadToDB() throws SQLException {
        ResultSet resultSet = null;
        String insert = "SELECT * FROM admissionlist WHERE status=?";
        preparedStatement = (PreparedStatement) connection.prepareStatement(insert);
        preparedStatement.setString(1,"Progress");
        resultSet = preparedStatement.executeQuery();
        System.out.println("Data" + resultSet);
        return resultSet;
    }

    private static ResultSet DeleteToDB() throws SQLException {
        ResultSet resultSet = null;
        String insert = "DELETE FROM admission WHERE id";
        preparedStatement = (PreparedStatement) connection.prepareStatement(insert);
        resultSet = preparedStatement.executeQuery();
        System.out.println("Data" + resultSet);
        return resultSet;
    }
}
