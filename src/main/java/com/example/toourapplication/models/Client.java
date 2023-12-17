package com.example.toourapplication.models;

public class Client {
    private Long id;
    private String lname,fname,email,phone,username,passid,password;

    public Client(Long id, String lname, String fname, String email, String phone, String username, String passid, String password) {
        this.id = id;
        this.lname = lname;
        this.fname = fname;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.passid = passid;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassid() {
        return passid;
    }

    public void setPassid(String passid) {
        this.passid = passid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
