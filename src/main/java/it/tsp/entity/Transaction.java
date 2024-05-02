package it.tsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@NamedQueries({
    @NamedQuery(name = Transaction.FIND_BY_ACCOUNT_ID, query = "select e from Transaction e where e.account.id= :accountId")
})

@Entity
@Table(name = "transaction")
public class Transaction extends BaseEntity implements Serializable{

    public static final String FIND_BY_ACCOUNT_ID = "Transaction.findByAccountId";

    public Transaction(){}

    public Transaction(Account sender, Account receiver, BigDecimal amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    @ManyToOne(optional = false)
    private Account sender;
    @ManyToOne(optional = false)
    private Account receiver;
    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDate performedOn = LocalDate.now();
    
    public Account getSender() {
        return sender;
    }
    public void setSender(Account sender) {
        this.sender = sender;
    }
    public Account getReceiver() {
        return receiver;
    }
    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public LocalDate getPerformedOn() {
        return performedOn;
    }
    public void setPerformedOn(LocalDate performedOn) {
        this.performedOn = performedOn;
    }
    @Override
    public String toString() {
        return "Transaction [sender=" + sender + ", id=" + id + ", receiver=" + receiver + ", amount=" + amount
                + ", performedOn=" + performedOn + "]";
    }


}
