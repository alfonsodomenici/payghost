package it.tsp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TxEssential(String fsnd, String lsnd, String frcv, String lrcv, BigDecimal amount, LocalDate when) {

    public String sender(){
        return fsnd + " " + lsnd;
    }

    public String receiver(){
        return frcv + " " + lrcv;
    }

    @Override
    public String toString() {
        return sender() + " " + receiver() + " " + amount + " " + when;
    }


}
