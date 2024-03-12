package com.example.myussd;






public class Dao {
    private String phoneNumber;
    private String result;

    public Dao() {
        phoneNumber = "";
        result = "Nothing is incoming";
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
