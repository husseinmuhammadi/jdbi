package com.javastudio.tutorial;

import com.javastudio.tutorial.jdbi.GeneralDao;
import com.javastudio.tutorial.jdbi.ParamAccount;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.JdbcInterceptor;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.JDBCType;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

public class ProcedureCallTest {

    private Logger logger = LoggerFactory.getLogger(ProcedureCallTest.class);

    @Test
    void name() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://172.25.25.117:5432/dev_sp_walletservice?currentSchema=wallet");
        ds.setUsername("dev_sp_walletservice_usr");
        ds.setPassword("MwBSCqZudw");
        ds.setInitialSize(1);
        ds.setMaxWait(100);
        ds.setMaxActive(100);
        ds.setMaxIdle(100);
        ds.setValidationQuery("select 1;");
        //ds.setInitSQL("set time zone "+cfg.getConfig("DB_TIME_ZONE"));

        Jdbi jdbi = Jdbi.create(ds);
        jdbi.installPlugin(new SqlObjectPlugin());
        GeneralDao generalDao = jdbi.onDemand(GeneralDao.class);
        generalDao.saveParamAccount("1002", "guid", 10L, new Date());

        try {
            ParamAccount paramAccount = generalDao.findParamAccount("1003");
            System.out.println(paramAccount == null ? "no date found" : paramAccount.getAccountId());
        } catch (Exception e) {
            PSQLException psqlException = whatCausePSQLException(e);

            if (psqlException != null) {
                logger.info(psqlException.getSQLState());
                logger.info(psqlException.getMessage());
            } else {
                e.printStackTrace();
            }
        }


        ds.close(true);
    }

    private PSQLException whatCausePSQLException(Throwable e) {
        if (e instanceof PSQLException)
            return (PSQLException) e;

        if (e.getCause() == null)
            return null;

        return whatCausePSQLException(e.getCause());
    }
}
