package dao;

import java.sql.*;

public class BaseDao {
        private String driver = "com.mysql.jdbc.Driver";
        private String url = "jdbc:mysql://39.106.66.138:3306/weixinxiaochengxu";
        private String username = "root";
        private String password = "caowenhao@CWH9808";
        public static Connection con = null;
        public static PreparedStatement pst = null;
        public static ResultSet rs = null;

        public Connection getConnection(){
            try {

                Class.forName(driver);
                con = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return con;
        }

        public void closeAll(Connection con, Statement st, ResultSet rs){
            try {
                if (null != rs) {
                    rs.close();
                }
                if(null != st){
                    st.close();
                }
                if(null != con){
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public int executeIUD(String sql, Object[] objects){
            synchronized (BaseDao.class) {
                PreparedStatement pst = null;
                con = getConnection();
                int count = 0;

                try {
                    pst = con.prepareStatement(sql);
                    if (null != objects) {
                        for (int i = 0; i < objects.length; i++) {
                            pst.setObject(i + 1, objects[i]);
                        }
                    }
                    count = pst.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    closeAll(con, pst, null);
                }

                return count;
            }
        }

        public ResultSet executeSelect(String sql, Object[] objects){
            synchronized (BaseDao.class) {
                con = getConnection();
                try {
                    pst = con.prepareStatement(sql);
                    if (null != pst && objects != null) {
                        for (int i = 0; i < objects.length; i++) {
                            pst.setObject(i + 1, objects[i]);
                        }
                    }

                    rs = pst.executeQuery();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return rs;
            }
        }

    /*
    public static void main(String[] args){
        DBDAO d = new DBDAO();
        if(d.getConnection() != null){
            System.out.println("success");
        }
    }
    */


}
