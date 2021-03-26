package Model;

import java.sql.*;
import java.util.Scanner;

public class User {
    private static int userNo;
    protected  int id;
    protected String firstName;
    protected String lastName;
    protected String userName;
    protected String password;
    protected String email;
    protected Date dateOfBirth;
    protected String city;
    protected String state;

    User(){
        id=generateUserID();
    }

    User(String email,String password){
        id=generateUserID();
        this.email=email;
        this.password=password;
    }



    User(String email, String password, int id ){
        this.id=id;
        this.email=email;
        this.password=password;
    }


    private int generateUserID(){
        userNo++;
        return (userNo);
    }

    public int getId() {return id; }

    public void setId(int id) { this.id = id; }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\n' +
                ", lastName='" + lastName + '\n' +
                ", userName='" + userName + '\n' +
                ", password='" + password + '\n' +
                ", email='" + email + '\n' +
                ", dateOfBirth=" + dateOfBirth +
                ", city='" + city + '\n' +
                ", state='" + state + '\n' +
                '}';
    }




}
