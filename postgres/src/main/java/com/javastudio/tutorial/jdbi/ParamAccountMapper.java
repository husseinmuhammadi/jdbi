package com.javastudio.tutorial.jdbi;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ParamAccountMapper implements RowMapper<ParamAccount> {

    @Override
    public ParamAccount map(ResultSet rs, StatementContext ctx) throws SQLException {
        ParamAccount paramAccount = new ParamAccount();
        paramAccount.setAccountId(rs.getString("account_id"));
        paramAccount.setWalletGuid(rs.getString("wallet_guid"));
        if (rs.getTimestamp("entered_date") != null)
            paramAccount.setEnteredDate(new Date(rs.getTimestamp("entered_date").getTime()));

        return paramAccount;
    }
}
