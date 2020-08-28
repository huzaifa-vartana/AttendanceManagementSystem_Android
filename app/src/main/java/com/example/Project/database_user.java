package com.example.Project;

public class database_user {
    private String sname;
    private String sID;
    private String cid;

    public database_user() {
    }

    public database_user(String sname, String sID, String cid) {
        this.sname = sname;
        this.sID = sID;
        this.cid = cid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
