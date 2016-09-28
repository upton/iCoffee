package bigdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ImpalaTest {

    public static void main(String[] args) throws Exception {
        selectInsert();
    }

    private static Connection getConn() throws Exception {
        String url = "jdbc:impala://192.168.8.218:21050/default";
        Class.forName("com.cloudera.impala.jdbc41.Driver");
        return DriverManager.getConnection(url);
    }

    private static void query() throws Exception {
        Statement stmt = getConn().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM hive_tab1");
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");

            System.out.println(id + "=" + name);
        }
    }

    private static void selectInsert() throws Exception {
        Statement stmt = getConn().createStatement();
        System.out.println(stmt.execute("set NUM_NODES=1"));
        int rows = stmt.executeUpdate("insert overwrite hive_tab3 select * from my_first_table");

        System.out.println(rows);
    }
}
