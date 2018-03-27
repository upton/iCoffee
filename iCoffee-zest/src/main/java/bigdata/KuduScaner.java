package bigdata;

import java.util.ArrayList;
import java.util.List;

import org.apache.kudu.ColumnSchema;
import org.apache.kudu.ColumnSchema.ColumnSchemaBuilder;
import org.apache.kudu.Type;
import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;
import org.apache.kudu.client.KuduPredicate;
import org.apache.kudu.client.KuduScanner;
import org.apache.kudu.client.KuduTable;
import org.apache.kudu.client.RowResult;
import org.apache.kudu.client.RowResultIterator;
import org.apache.kudu.client.KuduPredicate.ComparisonOp;

public class KuduScaner {
    private static KuduClient client = new KuduClient.KuduClientBuilder("bigdata-195:7051,bigdata-218:7051,bigdata-221:7051").build();

    public static void main(String[] args) throws KuduException {
        long start = System.currentTimeMillis();

        scan("impala_test_table5");

        System.out.println(System.currentTimeMillis() - start);
    }

    private static void scan(String tableName) throws KuduException {
        List<String> projectColumns = new ArrayList<>(1);
        projectColumns.add("id");
        projectColumns.add("name");
        projectColumns.add("kudu_partiton_key");

        ColumnSchema col = new ColumnSchemaBuilder("kudu_partiton_key", Type.STRING).build();
        KuduPredicate predicate = KuduPredicate.newComparisonPredicate(col, ComparisonOp.EQUAL, "20161008");

        KuduTable table = client.openTable(tableName);
        KuduScanner scanner = client.newScannerBuilder(table).setProjectedColumnNames(projectColumns).addPredicate(predicate).build();

        long sum = 0;

        while (scanner.hasMoreRows()) {
            RowResultIterator results = scanner.nextRows();
            while (results.hasNext()) {
                RowResult result = results.next();
                sum += result.getLong(0);
            }
        }

        client.shutdown();
        
        System.out.println("sum = " + sum);
    }
}
