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
