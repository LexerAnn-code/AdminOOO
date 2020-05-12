package sample;

public class RegisterAdmission {
    String lastName,FirstName,MiddleName,UserName,Password,DOB,Address,FatherName,MotherName,Contact;
    String course,level,previousSchool,schoolAddress,lastDateAttended;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPreviousSchool() {
        return previousSchool;
    }

    public void setPreviousSchool(String previousSchool) {
        this.previousSchool = previousSchool;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getLastDateAttended() {
        return lastDateAttended;
    }

    public void setLastDateAttended(String lastDateAttended) {
        this.lastDateAttended = lastDateAttended;
    }

    public String getLastName() {
        return lastName;
    }

    public RegisterAdmission() {
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getMotherName() {
        return MotherName;
    }

    public void setMotherName(String motherName) {
        MotherName = motherName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public RegisterAdmission(String lastName, String firstName, String middleName, String userName, String password, String DOB, String address, String fatherName, String motherName, String contact) {
        this.lastName = lastName;
        FirstName = firstName;
        MiddleName = middleName;
        UserName = userName;
        Password = password;
        this.DOB = DOB;
        Address = address;
        FatherName = fatherName;
        MotherName = motherName;
        Contact = contact;
    }
}
