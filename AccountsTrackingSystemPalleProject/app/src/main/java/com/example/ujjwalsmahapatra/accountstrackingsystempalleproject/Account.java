package com.example.ujjwalsmahapatra.accountstrackingsystempalleproject;

public class Account {
    private String id,acno,bank,branch,holder;

    public Account(String id, String acno, String bank, String branch, String holder) {
        this.id = id;
        this.acno = acno;
        this.bank = bank;
        this.branch = branch;
        this.holder = holder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }
}
