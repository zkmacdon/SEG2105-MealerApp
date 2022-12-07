package com.app.mealer;

public class Complaint {
    private String cookName;
    private String complaint;
    private int timeSuspended;
    private String suspensionStatus;
    public Complaint(){

    }

    public Complaint(String name, String complaint){
        cookName = name;
        this.complaint = complaint;
        timeSuspended = -1;
        suspensionStatus = "pending";
    }

    public Complaint(String name, String complaint, int timesuspended){
        cookName = name;
        this.complaint = complaint;
        timeSuspended = timesuspended;
        suspensionStatus = "pending";

    }

    public String getName(){
        return cookName;
    }
    public void setName(String name){
        cookName = name;
    }

    public String getComplaint(){
        return complaint;
    }
    public void setComplaint(String complaint){
        this.complaint = complaint;
    }
    public int getSuspensionTime(){
        return timeSuspended;
    }
    public void setSuspensionTime(int suspensionTime){
        timeSuspended = suspensionTime;
    }
    public String getStatus(){
        return suspensionStatus;
    }
    public void setStatus(String status){
        suspensionStatus = status;
    }




}
