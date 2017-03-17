package com.example.admin.ict_interns;

/**
 * Created by Admin on 2017/02/23.
 */

public class UserInformation {

    public String fullname;
    public String idno;
    public String contact;
    public String nameofschool;
    public String district;

    public String getFullname() {
        return fullname;
    }

    public String getIdno() {
        return idno;
    }

    public String getContact() {
        return contact;
    }

    public String getNameofschool() {
        return nameofschool;
    }

    public String getDistrict() {
        return district;
    }

    public String getMentordetails() {
        return mentordetails;
    }

    public String getmContact() {
        return mContact;
    }

    public String mentordetails;
    public String mContact;

    public  UserInformation()
    {

    }

    public UserInformation(String fullname, String idno, String contact, String nameofschool, String district, String mentordetails, String mContact) {
        this.fullname = fullname;
        this.idno = idno;
        this.contact = contact;
        this.nameofschool = nameofschool;
        this.district = district;
        this.mentordetails = mentordetails;
        this.mContact = mContact;
    }

}
