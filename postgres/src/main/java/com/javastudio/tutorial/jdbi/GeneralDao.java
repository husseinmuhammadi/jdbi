package com.javastudio.tutorial.jdbi;

import org.jdbi.v3.core.statement.OutParameters;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.QueryTimeOut;
import org.jdbi.v3.sqlobject.statement.SqlCall;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface GeneralDao {

    @SqlCall("{call sp_save_param_account(:account_id, :wallet_guid, :transfer_amount, :entered_date)}")
    @QueryTimeOut(value = 2)
    OutParameters saveParamAccount(
            @Bind("account_id") String accountId,
            @Bind("wallet_guid") String walletGuid,
            @Bind("transfer_amount") Long transferAmount,
            @Bind("entered_date") Date enteredDate
    );

    @SqlQuery("select * from sp_find_param_account(:account_id)")
    @QueryTimeOut(value = 2)
    @RegisterRowMapper(ParamAccountMapper.class)
    ParamAccount findParamAccount (
            @Bind("account_id") String accountId
    );


}
