package com.fanshc.homework.sharding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Description:
 * @Auther: fanshc
 * @Date: 2021/03/13/9:27 下午
 */
public class Order_Repertory {

    public void addOneRecordIntoOrder(){
        String insertSql = "insert into t_order(createDate,address,totalPrace,userid,orderState,receiveAddressId) values(?,?,?,?,?,?)";
        try{
            Connection conn = null;
            PreparedStatement ps = null;
            try{
                conn = DbUtil.getConnection();
                conn.setAutoCommit(false);
                ps = conn.prepareStatement(insertSql);
                ps.setLong(1,System.currentTimeMillis());
                ps.setString(2,"1111");
                ps.setInt(3,10000);
                ps.setInt(4,5);
                ps.setString(5,"1");
                ps.setString(6,"99999");
                ps.executeUpdate();
                conn.commit();
            }finally{
                try{
                    conn.setAutoCommit(true);
                }finally {
                    DbUtil.closeJDBC(null,ps,conn);
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addMultitudeRecordIntoOrder(){
        long start = System.currentTimeMillis();
        String insertSql = "insert into t_order(userid) values " +
                "(?),(?),(?),(?),(?),(?),(?),(?),(?),(?)," +
                "(?),(?),(?),(?),(?),(?),(?),(?),(?),(?)";
        try{
            Connection conn = null;
            PreparedStatement ps = null;
            try{
                conn = DbUtil.getConnection();
                conn.setAutoCommit(false);
                ps = conn.prepareStatement(insertSql);
                for (int i = 0; i < 50000 ; i++) {
                    ps.setInt(1,i);
                    ps.setInt(2,i);
                    ps.setInt(3,i);
                    ps.setInt(4,i);
                    ps.setInt(5,i);
                    ps.setInt(6,i);
                    ps.setInt(7,i);
                    ps.setInt(8,i);
                    ps.setInt(9,i);
                    ps.setInt(10,i);
                    ps.setInt(11,i);
                    ps.setInt(12,i);
                    ps.setInt(13,i);
                    ps.setInt(14,i);
                    ps.setInt(15,i);
                    ps.setInt(16,i);
                    ps.setInt(17,i);
                    ps.setInt(18,i);
                    ps.setInt(19,i);
                    ps.setInt(20,i);

                    ps.addBatch();
                    if(i %500 == 0){
                        ps.executeBatch();
                        ps.clearBatch();
                    }
                }
                ps.executeBatch();
                conn.commit();
            }finally{
                try{
                    conn.setAutoCommit(true);
                }finally {
                    DbUtil.closeJDBC(null,ps,conn);
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println((System.currentTimeMillis()-start)/1000);
    }



    public static void main(String[] args) {
        Order_Repertory dao = new Order_Repertory();
        dao.addMultitudeRecordIntoOrder();
    }

}
