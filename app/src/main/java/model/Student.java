package model;

import com.google.gson.annotations.SerializedName;

public class Student {
    @SerializedName("Id")
    private Integer Id;
    @SerializedName("Name")
    private String Name;
    @SerializedName("Email")
    private String Email;
    @SerializedName("FacultyId")
    private Integer FacultyId;

    public Student(String name, String email, Integer facultyId) {
        Name = name;
        Email = email;
        FacultyId = facultyId;
    }

    public Student(Integer id ,String name, String email, Integer facultyId) {
        Id = id;
        Name = name;
        Email = email;
        FacultyId = facultyId;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getFacultyId() {
        return FacultyId;
    }

    public void setFacultyId(Integer facultyId) {
        FacultyId = facultyId;
    }
}
