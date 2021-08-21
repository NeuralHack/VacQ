package com.example.vacq;

public class user {
    private String Age;
    private String City;
    private String Diseases;
    private String Gender;
    private String Name;
    private String Pass;
    private String Priority;
    private String Profession;
    private String Zone;

    public user(String age, String city, String diseases, String gender, String name, String pass, String priority, String profession, String zone) {
        Age = age;
        City = city;
        Diseases = diseases;
        Gender = gender;
        Name = name;
        Pass = pass;
        Priority = priority;
        Profession = profession;
        Zone = zone;
    }

    public user(String age, String city, String diseases, String gender, String name, String pass, String profession, String zone) {
        Age = age;
        City = city;
        Diseases = diseases;
        Gender = gender;
        Name = name;
        Pass = pass;
        Profession = profession;
        Zone = zone;
    }

    public user() {
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDiseases() {
        return Diseases;
    }

    public void setDiseases(String diseases) {
        Diseases = diseases;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String zone) {
        Zone = zone;
    }
}
