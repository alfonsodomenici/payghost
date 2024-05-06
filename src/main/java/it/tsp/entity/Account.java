package it.tsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "account")
public class Account extends BaseEntity implements Serializable {

    public Account() {
    }

    public Account(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    public Account(String fname, String lname,
            @Email(message = "la proprietà email non contiene un indirizzo email valido") String email,
            @NotBlank(message = "la proprietà pwd non può avere solo spazi") @Size(min = 4, message = "la proprietà pwd deve avere almeno 4 caratteri") String pwd) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pwd = pwd;
    }

    private String fname;
    private String lname;

    @Email(message = "la proprietà email non contiene un indirizzo email valido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "la proprietà pwd non può avere solo spazi")
    @Size(min = 4, message = "la proprietà pwd deve avere almeno 4 caratteri")
    @Column(nullable = false)
    private String pwd;

    @PositiveOrZero(message = "La proprietà credit deve essere >= 0")
    @Column(precision = 6, scale = 2)
    private BigDecimal credit = BigDecimal.ZERO;

    public void increaseCredit(BigDecimal amount) {
        this.credit = this.credit.add(amount);
    }

    public void decreaseCredit(BigDecimal amount) {
        this.credit = this.credit.subtract(amount);
    }

    public boolean hasSufficientCredit(BigDecimal amount) {
        return this.credit.compareTo(amount) > 0;
    }

    public String getFullname(){
        return lname + " " + fname;
    }
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
