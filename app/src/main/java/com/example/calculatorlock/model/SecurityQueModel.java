package com.example.calculatorlock.model;

public class SecurityQueModel {

    String security_que;
    String security_ans;
    String recovery_email;

    public String getRecovery_email() {
        return recovery_email;
    }

    public String getSecurity_ans() {
        return security_ans;
    }

    public String getSecurity_que() {
        return security_que;
    }

    public void setRecovery_email(String recovery_email) {
        this.recovery_email = recovery_email;
    }

    public void setSecurity_ans(String security_ans) {
        this.security_ans = security_ans;
    }

    public void setSecurity_que(String security_que) {
        this.security_que = security_que;
    }

    public SecurityQueModel(String security_que, String security_ans, String recovery_email)
    {
        this.security_que = security_que;
        this.security_ans = security_ans;
        this.recovery_email = recovery_email;
    }
}
