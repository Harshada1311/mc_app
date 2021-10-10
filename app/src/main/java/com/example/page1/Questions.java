package com.example.page1;

public class Questions {
    //private String sub_code;
    private String Qno;
    private String Qsn;
    private String optA;
    private String optB;
    private String optC;
    private String optD;
    private String ans;
    private String timer;

    public Questions() {}

    public Questions(String qno, String qsn, String optA, String optB, String optC, String optD, String ans, String timer) {
        //this.sub_code = sub_code;
        this.Qno = qno;
        this.Qsn = qsn;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.optD = optD;
        this.ans = ans;
        this.timer = timer;
    }

    /*public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }*/

    public String getQno() {
        return Qno;
    }

    public void setQno(String qno) {
        Qno = qno;
    }

    public String getQsn() {
        return Qsn;
    }

    public void setQsn(String qsn) {
        Qsn = qsn;
    }

    public String getOptA() {
        return optA;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public String getOptB() {
        return optB;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public String getOptC() {
        return optC;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public String getOptD() {
        return optD;
    }

    public void setOptD(String optD) {
        this.optD = optD;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }
}
