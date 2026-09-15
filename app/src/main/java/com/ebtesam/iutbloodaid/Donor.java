package com.ebtesam.iutbloodaid;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Donor {
    String userId;
    String email;
    String name;
    String sid;
    String bg;
    String phone;
    String status;
    String eligibility;

    Donor()
    {

    }


    public Donor(String userId, String email, String name, String sid, String bg, String phone, String status, String eligibiliy) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        email=user.getEmail();
        this.email=email;
        this.userId=userId;
        this.name = name;
        this.phone = phone;
        this.bg = bg;
        this.sid = sid;
        this.status=status;
        this.eligibility=eligibility;
    }

    public String getEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {

        return name;
    }
    public String getEligibility(){
        return eligibility;
    }
    public void setElig(String eligibility){
        this.eligibility=eligibility;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String blood_group) {
        this.bg = bg;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {
        this.status=status;
    }


}
