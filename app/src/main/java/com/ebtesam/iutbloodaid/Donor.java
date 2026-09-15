package com.ebtesam.iutbloodaid;

public class Donor {
    private String userId;
    private String email;
    private String name;
    private String sid;
    private String bg;
    private String phone;
    private String status;
    private String eligibility;

    public Donor() {
    }

    public Donor(
            String userId,
            String email,
            String name,
            String sid,
            String bg,
            String phone,
            String status,
            String eligibility
    ) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.sid = sid;
        this.bg = bg;
        this.phone = phone;
        this.status = status;
        this.eligibility = eligibility;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSid() { return sid; }
    public void setSid(String sid) { this.sid = sid; }

    public String getBg() { return bg; }
    public void setBg(String bg) { this.bg = bg; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getEligibility() { return eligibility; }
    public void setEligibility(String eligibility) { this.eligibility = eligibility; }
}
