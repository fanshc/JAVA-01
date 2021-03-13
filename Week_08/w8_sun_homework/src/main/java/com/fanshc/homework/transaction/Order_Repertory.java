package com.fanshc.homework.transaction;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.AtomikosDataSourceBean;

import javax.transaction.SystemException;
import java.sql.*;
import java.util.Properties;

/**
 * @Description:
 * @Auther: fanshc
 * @Date: 2021/03/13/11:26 下午
 */
public class Order_Repertory {

    private static AtomikosDataSourceBean createAtomikosDataSourceBean(String dbName) {
        // 连接池基本属性
        Properties p = new Properties();
        p.setProperty("url", "jdbc:mysql://localhost:3306/" + dbName+"?serverTimezone=UTC&characterEncoding=utf8&rewriteBatchedStatements=true");
        p.setProperty("user", "root");
        p.setProperty("password", "59863633Kangboa");

        // 使用AtomikosDataSourceBean封装com.mysql.jdbc.jdbc2.optional.MysqlXADataSource
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        //atomikos要求为每个AtomikosDataSourceBean名称，为了方便记忆，这里设置为和dbName相同
        ds.setUniqueResourceName(dbName);
        ds.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        ds.setXaProperties(p);
        return ds;
    }

    public static void main(String[] args) throws SystemException {

        AtomikosDataSourceBean ds1 = createAtomikosDataSourceBean("ds_0");
        AtomikosDataSourceBean ds2 = createAtomikosDataSourceBean("ds_1");

        Connection conn1 = null;
        Connection conn2 = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        UserTransactionImp userTransaction = new UserTransactionImp();
        try {
            userTransaction.begin();
            conn1 = ds1.getConnection();
            ps1 = conn1.prepareStatement("insert into t_order(user_id,status) values (?,?)");
            ps1.setInt(1,1);
            ps1.setString(2,"OK");
            ps1.executeUpdate();

            conn2 = ds2.getConnection();
            ps2 = conn2.prepareStatement("insert into t_order_item(user_id,order_id,status) values (?,?,?)");
            ps2.setInt(1,1);
            ps2.setInt(2,2);
            ps2.setString(3,"FAIL");
            ps2.executeUpdate();

            userTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            userTransaction.rollback();
        }finally{
            try{
                DbUtil.closeJDBC(null,ps1,conn1);
            }finally{
                DbUtil.closeJDBC(null,ps2,conn2);
            }


        }
    }
}
