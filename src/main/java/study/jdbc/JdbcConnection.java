package study.jdbc;

import java.sql.*;

/**
 * Created by kh.jin on 2020. 3. 22.
 */
public class JdbcConnection {
    private final static String dburl = "jdbc:mysql://therich.ckjqyzrxoccj.ap-northeast-2.rds.amazonaws.com:3306/therich";
    private final static String dbUser = "admin";
    private final static String dbpasswd = "therich1983";

    private Connection conn = null;
    private Statement s = null;
    private PreparedStatement ps = null;
    private ResultSet rs1 = null;
    private ResultSet rs2 = null;


    public void connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // connection
            conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
        } catch (ClassNotFoundException e) {
            System.out.println("not found. mysql driver.");
        } catch (SQLException e) {
            System.out.println("SQL state :" + e.getSQLState());
            System.out.println("SQL error code : " + e.getErrorCode());
        }
    }

    public Integer selectCount(int annual) {
        String sql = "";
        sql += "select count(*) as cnt   \n";
        sql += "  from tb_monthly \n";
        sql += " where annual = ?";
        System.out.println("[SQL]");
        System.out.println("====================");
        System.out.println(sql);
        System.out.println("====================");

        this.connection();
        int result = 0;
        try {
            int idx = 0;
            ps = conn.prepareStatement(sql);
            ps.setInt(++idx, annual);
            rs1 = ps.executeQuery();
            if (rs1 == null) {
                System.out.println("null?");
            }

            while (rs1.next()) {
                int colIdx = 0;
                result = rs1.getInt(++colIdx);
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            try {
                rs1.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Failed resource close.");
                e.printStackTrace();
            }
        }
        System.out.println("result : " + result);
        return  result;
    }

    public void selectMonthly(){

    }


}
