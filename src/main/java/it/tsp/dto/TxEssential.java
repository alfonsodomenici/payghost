package it.tsp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TxEssential(String sender, String receiver, BigDecimal amount, LocalDate when) {

    public TxEssential(String senderFname,String senderLname, String receiverFname, String receiverLname, BigDecimal amount, LocalDate when){
        this( senderFname + " " + senderLname, receiverFname + " " + receiverLname, amount, when );
    }

}
