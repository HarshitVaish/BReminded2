package com.example.android.breminded.model;

/**
 * Created by harshit009 on 10/4/2016.
 */
public class Birthday {
    private String name, date, message;
    private String phoneNo,group;

    public Birthday(String name, String date,String group, String phoneNo, String message) {
        this.name = name;
        this.date = date;
        this.phoneNo = phoneNo;
        this.message=message;
        this.group=group;

    }
   public String getName(){
       return name;
   }
    public String getDate(){
        return date;
    }
    public String getGroup(){return group;}
    public String getphoneNo(){
        return phoneNo;
    }
    public String getMessage(){
        return message;
    }
}
