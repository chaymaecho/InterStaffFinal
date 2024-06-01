package com.example.interimeprojet.model;

public class Employeee {

    public String Employname;
    public String contactnumber;
    public String adresse;
    public String link;
    public String email;
    public  String password;

    public Employeee(){}
    public Employeee(String Employname, String contactnumber, String adresse, String link, String email, String password) {
        this.Employname = Employname;
        this.contactnumber = contactnumber;
        this.adresse = adresse;
        this.link = link;
        this.email = email;
        this.password = password;
    }

    public String getEmployname() {
        return Employname;
    }

    public void setEmployname(String Employname) {
        this.Employname = Employname;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
}
