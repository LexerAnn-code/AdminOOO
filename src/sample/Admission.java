package sample;

public class Admission {
    int admissionID;

    public int getAdmissionID() {
        return admissionID;
    }

    public void setAdmissionID(int admissionID) {
        this.admissionID = admissionID;
    }

    String preschool;
    String schooladdress;
    String firstname;

    public Admission(String preschool, String schooladdress, String firstname, String lastname) {
        this.preschool = preschool;
        this.schooladdress = schooladdress;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Admission(String preschool, String schooladdress) {
        this.preschool = preschool;
        this.schooladdress = schooladdress;
    }

    public String getPreschool() {
        return preschool;
    }

    public void setPreschool(String preschool) {
        this.preschool = preschool;
    }

    public Admission() {
    }

    public String getSchooladdress() {
        return schooladdress;
    }

    public void setSchooladdress(String schooladdress) {
        this.schooladdress = schooladdress;
    }
}
