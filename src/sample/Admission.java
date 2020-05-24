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
    String admission;
    String level;
    String course;
    String hall;
String docFile;

    public String getDocFile() {
        return docFile;
    }

    public void setDocFile(String docFile) {
        this.docFile = docFile;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

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
