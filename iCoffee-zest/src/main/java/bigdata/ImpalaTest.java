package bigdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ImpalaTest {

    public static void main(String[] args) throws Exception {
//        insertWithCN();
        query();
    }

    private static Connection getConn() throws Exception {
//        String url = "jdbc:impala://192.168.8.221:21050/;AuthMech=3;UID=user1;PWD=user1";
//        Class.forName("com.cloudera.impala.jdbc41.Driver");

        String url = "jdbc:hive2://192.168.8.215:10000/";
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        return DriverManager.getConnection(url,"ddw_trade","540E5D7A-81BA-4003-9FBA-732E0CB958C5");
    }

    public static void insertWithCN() throws SQLException, Exception {
        Connection conn = getConn();
        PreparedStatement stmt = conn.prepareStatement("insert into impala_test values(?,?)");

        int startId = 0;
        for (int i = 0; i < 10; i++) {
            stmt.setLong(1, startId++);
            stmt.setString(2, startId + "-中文");

            stmt.addBatch();
        }

        stmt.executeBatch();
        conn.commit();

        conn.close();
    }

    public static void insertWithPKHash(int rowSize) throws SQLException, Exception {
        Connection conn = getConn();
        PreparedStatement stmt = conn.prepareStatement("insert into impala_test_table5(`id`,`name`,mark,kudu_partiton_key) values(?,?,?,?)");

        int startId = 0;
        for (int m = 0; m < rowSize / 4000; m++) {
            for (int i = 0; i < 4000; i++) {
                stmt.setLong(1, startId++);
                stmt.setString(2, startId + "-name");
                stmt.setString(3, startId + "-中文");
                stmt.setString(4, String.valueOf(getPK()));

                stmt.addBatch();
            }

            stmt.executeBatch();
            conn.commit();

            if (startId % 100000 == 0) {
                System.out.println("inserted " + startId);
            }
        }

        conn.close();
    }

    private static Random rd = new Random();

    private static int getPK() {
        return 20161001 + rd.nextInt(31);
    }

    private static void query() throws Exception {
        Statement stmt = getConn().createStatement();
        ResultSet rs = stmt.executeQuery("show databases");
        while (rs.next()) {
//            String id = rs.getString("comment");
            String name = rs.getString(1);

//            System.out.println("id = " + id);
            System.out.println("name = " + name);
        }
    }

    private static void queryAvg() throws Exception {
        Statement stmt = getConn().createStatement();
        ResultSet rs = stmt.executeQuery("select avg(user_id) as avg_uid from coupon_parquet where id > 0 group by code limit 100");
        while (rs.next()) {
            System.out.println(rs.getString("avg_uid"));
        }
    }

    private static void selectInsert() throws Exception {
        Statement stmt = getConn().createStatement();
        System.out.println(stmt.execute("set NUM_NODES=1"));
        int rows = stmt.executeUpdate("insert overwrite hive_tab3 select * from my_first_table");

        System.out.println(rows);
    }
}
