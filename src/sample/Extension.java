package sample;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.DBConnection;

import java.io.*;
import java.sql.*;


public class Extension implements DBConnection {
    Stage stage;
    Scene scene;
    Parent root;
    static private PreparedStatement preparedStatement;
    static private Connection connection;


    public void DenySelect(Admission admission) {
        System.out.println("ID DEL->>" + admission.getAdmissionID() + admission.getFirstname() + admission.getLastname());
        ResultSet resultSet = null;
        String querys = "UPDATE  admissionlist SET status= ?" + "WHERE admissionid=?";
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(querys);
            preparedStatement.setInt(2, admission.getAdmissionID());
            preparedStatement.setString(1, "Denied");
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AcceptSelect(Admission admission) {
        String querys = "UPDATE  admissionlist SET status= ?" + "WHERE admissionid=?";
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(querys);
            preparedStatement.setInt(2, admission.getAdmissionID());
            preparedStatement.setString(1, "Accepted");

            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void SelectAccept(CurrentSelected currentSelected) {


    }
    public void DocFile(Admission admission) throws SQLException, FileNotFoundException {
        //Querying for Pdf from the database using the id as the condition
        ResultSet resultSet=null;
        String selectSQL="SELECT document FROM admissionlist WHERE admissionid=?";
        preparedStatement = (PreparedStatement) connection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, admission.getAdmissionID());
        resultSet=preparedStatement.executeQuery();
        System.out.println("Admission_ID" + admission.getAdmissionID());

        try {

            while (resultSet.next()) {
                FileOutputStream fos=null;
                String filename = "C:\\Users\\NAANA\\Desktop\\adminPDF\\document_from_DB.pdf";
                //Path to the pdf when the admin clicks on the PDF button in each cell of the ListView

                File f = new File(filename);
                fos = new FileOutputStream(f);
                System.out.println("Writing BLOB to " + f.getAbsolutePath());
                InputStream input = resultSet.getBinaryStream("document");
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    fos.write(buffer);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

            //            Blob docfile= resultSet.getBlob("document");
//            InputStream in =docfile.getBinaryStream();
//            OutputStream out=new FileOutputStream("OutputFile");
//
//        }
//        File file=new File(resultSet)
    }

    @Override
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root", "");
        System.out.println("Connection " + connection.getCatalog());
    }
}
