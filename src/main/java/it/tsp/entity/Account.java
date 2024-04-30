package it.tsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account extends BaseEntity implements Serializable {

    public Account(){}
    
    public Account(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    private String fname;
    private String lname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String pwd;
    @Column(precision = 6, scale = 2)
    private BigDecimal credit;

    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public BigDecimal getCredit() {
        return credit;
    }
    public void setCredit(BigDecimal creadit) {
        this.credit = creadit;
    }
    
    @Override
    public String toString() {
        return "Account [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", pwd=" + pwd
                + ", credit=" + credit + "]";
    }

    

    
}
