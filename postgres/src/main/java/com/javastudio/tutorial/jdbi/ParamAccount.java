package com.javastudio.tutorial.jdbi;

import java.util.Date;

public class ParamAccount {
    private String accountId;
    private String walletGuid;
    private Date enteredDate;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getWalletGuid() {
        return walletGuid;
    }

    public void setWalletGuid(String walletGuid) {
        this.walletGuid = walletGuid;
    }

    public Date getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }
}
