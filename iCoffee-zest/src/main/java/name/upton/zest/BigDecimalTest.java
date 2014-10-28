package name.upton.zest;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal commission = new BigDecimal(888).multiply(new BigDecimal(0)).setScale(2, RoundingMode.HALF_UP);
        
        System.out.println(commission);
    }

}
