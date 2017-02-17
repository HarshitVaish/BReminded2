package com.example.android.breminded.model;

/**
 * Created by harshit009 on 10/14/2016.
 */
public class SendTextMessage {
    private String phoneNo,message;
    public SendTextMessage(String phoneNo,String message){
        this.phoneNo=phoneNo;
        this.message=message;
    }
    public String getPhoneNo(){
        return phoneNo;
    }
    public String getMessage(){
        return message;
    }
}
