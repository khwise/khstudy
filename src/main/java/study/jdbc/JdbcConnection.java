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
    private PreparedStatement ps1 = null;
    private PreparedStatement ps2 = null;
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
            ps1 = conn.prepareStatement(sql);
            ps1.setInt(++idx, annual);
            rs1 = ps1.executeQuery();
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
                ps1.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Failed resource close.");
                e.printStackTrace();
            }
        }
        System.out.println("result : " + result);
        return  result;
    }

    public void selectMember(String memberNm){
        this.connection();
        String sql = "select * from tb_member where member_nm like ?";


        try {
            ps1 = conn.prepareStatement(sql);
            ps1.setString(1, "%"+memberNm+"%");
            rs1 = ps1.executeQuery();

            if(rs1 == null){
                System.out.println("null?");
            }

            while(rs1.next()){
                int memberNo = rs1.getInt("member_no");
                String email = rs1.getString("email");
                String memberName = rs1.getString("member_nm");
                String address = rs1.getString("address");
                String mobile = rs1.getString("mobile");
                String socialId = rs1.getString("social_id");
                String createdAt = rs1.getString("created_at");
                int createdBy = rs1.getInt("created_by");
                String updatedAt = rs1.getString("updated_at");
                int updatedBy = rs1.getInt("updated_by");

                System.out.println(memberNo + "\t" + email +  "\t" + memberName + "\t" + address + "\t" + mobile +  "\t" + socialId + "\t" + createdAt + "\t" + createdBy +  "\t" + updatedAt + "\t" + updatedBy);
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            try {
                rs1.close();
                ps1.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Failed resource close.");
                e.printStackTrace();
            }
        }

    }

    public void insertMember() throws SQLException {
        StringBuilder sql1 = new StringBuilder();
        StringBuilder sql2 = new StringBuilder();
        sql1.append("insert into ")
                .append(" tb_member ")
                .append("( ")
                .append(" email     ")
                .append(",member_nm ")
                .append(",address ")
                .append(",mobile ")
                .append(",social_id ")
                .append(",created_by ")
                .append(",updated_by ")
                .append(",created_at ")
                .append(",updated_at ")
                .append(" )          ")
                .append(" values ")
                .append(" ( ")
                .append(" ?, ?, ?, ?, ?, ? ,? ,NOW() ,NOW()")
                .append(" ) ")
        ;

        sql2.append("insert into ")
                .append(" tb_account ")
                .append("( ")
                .append(" email     ")
                .append(",password ")
                .append(",salt ")
                .append(",created_by ")
                .append(",updated_by ")
                .append(" )          ")
                .append(" values ")
                .append(" ( ")
                .append(" ?, ?, ?, ?, ?")
                .append(" ) ")
        ;

        this.connection();

        try {
            conn.setAutoCommit(false);
            ps1 = conn.prepareStatement(sql1.toString());
            ps2 = conn.prepareStatement(sql2.toString());
            int idx = 0;
            String email = "kbod10000@gmail.com";

            ps1.setString(++idx, email);
            ps1.setString(++idx, "홍길동");
            ps1.setString(++idx, "알아서 뭐하게 ㅎㅎ");
            ps1.setString(++idx, "010-123-4567");
            ps1.setString(++idx, "000101-1220039");
            ps1.setInt(++idx, 2);
            ps1.setInt(++idx, 2);

            int updateCnt = ps1.executeUpdate();
            // conn.setReadOnly(true);
            conn.commit();
            System.out.println("updateCnt = " + updateCnt);

            idx = 0;
            ps2.setString(++idx, email);
            ps2.setString(++idx, "123");
            ps2.setString(++idx, "123");
            ps2.setInt(++idx, 2);
            ps2.setInt(++idx, 2);

            updateCnt =  ps2.executeUpdate();
            System.out.println("updateCnt = " + updateCnt);
            this.fail();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            try {
                ps1.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Failed resource close.");
                e.printStackTrace();
            }
        }
    }

    private void fail() throws SQLException{
        throw new SQLException();
    }

}
