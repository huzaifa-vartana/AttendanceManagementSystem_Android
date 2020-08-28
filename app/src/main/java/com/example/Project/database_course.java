package com.example.Project;

public class database_course {

    private String cid;
    private String cname;
    private String sname;
    private String status;
    private String sid;
    private Long date;

    public database_course() {
    }

    public database_course(String cid, String cname, String sname, String sid, String status, Long date) {
        this.cid = cid;
        this.cname = cname;
        this.sname = sname;
        this.status = status;
        this.sid = sid;
        this.date = date;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Long getdate() {
        return date;
    }

    public void setdate(Long date) {
        this.date = date;
    }

}
