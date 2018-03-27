package upton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GzipTest {

    public static void main(String[] args) {
        System.out.println(str.length());
        System.out.println(gzip(str));
    }

    private static String str = "[{\"catagoryId\":\"10293\",\"couponAmount\":97,\"couponCodeAmount\":0,\"created\":1472883125000,\"customTag\":\"{}\",\"id\":94408493,\"itemNum\":\"6922477400586\",\"matnr\":\"235406\",\"modified\":1472883125000,\"orderId\":17275333499,\"pageSize\":20,\"promotionId\":\"\",\"promotionPrice\":0,\"promotionPriceList\":\"\",\"promotionType\":\"\",\"properties\":\"{\\\"chine\\\":\\\"盒\\\",\\\"decStkSource\\\":1,\\\"validDays\\\":270}\",\"shopId\":1485,\"skuId\":121131,\"skuType\":1,\"wareId\":121131,\"wareImgUrl\":\"20160216/aea1523c-4345-49b9-ad35-0427a2c9b56c\",\"wareName\":\"百吉福小三角奶酪 17.5g*8\",\"wareNum\":1,\"warePrice\":1517,\"warePromotionPrice\":1517,\"wareTag\":0,\"wareType\":1,\"wareWeight\":0.14,\"webuserId\":3913999,\"yn\":1},{\"catagoryId\":\"10320\",\"couponAmount\":29,\"couponCodeAmount\":0,\"created\":1472883125000,\"customTag\":\"{}\",\"id\":94408494,\"itemNum\":\"6909689900844\",\"matnr\":\"535684\",\"modified\":1472883125000,\"orderId\":17275333499,\"pageSize\":20,\"promotionId\":\"\",\"promotionPrice\":0,\"promotionPriceList\":\"\",\"promotionType\":\"\",\"properties\":\"{\\\"chine\\\":\\\"瓶\\\",\\\"decStkSource\\\":1,\\\"validDays\\\":1800}\",\"shopId\":1485,\"skuId\":100198592,\"skuType\":1,\"wareId\":10198686,\"wareImgUrl\":\"20160119/16eb0e83-46e2-45db-b1e0-373b30be2c97\",\"wareName\":\"六必居山西陈醋 480ml\",\"wareNum\":1,\"warePrice\":499,\"warePromotionPrice\":499,\"wareTag\":0,\"wareType\":1,\"wareWeight\":0.48,\"webuserId\":3913999,\"yn\":1},{\"catagoryId\":\"10400\",\"couponAmount\":0,\"couponCodeAmount\":0,\"created\":1472883125000,\"customTag\":\"{}\",\"id\":94408495,\"itemNum\":\"6923555210462\",\"matnr\":\"170035\",\"modified\":1472883125000,\"orderId\":17275333499,\"pageSize\":20,\"promotionId\":\"\",\"promotionPrice\":0,\"promotionPriceList\":\"\",\"promotionType\":\"\",\"properties\":\"{\\\"chine\\\":\\\"盒\\\",\\\"decStkSource\\\":1,\\\"validDays\\\":365}\",\"shopId\":1485,\"skuId\":128192,\"skuType\":1,\"wareId\":128192,\"wareImgUrl\":\"20160619/7fe87970-d143-4c83-bbd1-ad8e7677aced\",\"wareName\":\"汇源100%有盖苹果汁苗条装 1L\",\"wareNum\":1,\"warePrice\":1050,\"warePromotionPrice\":1050,\"wareTag\":0,\"wareType\":1,\"wareWeight\":1,\"webuserId\":3913999,\"yn\":1},{\"catagoryId\":\"10543\",\"couponAmount\":132,\"couponCodeAmount\":0,\"created\":1472883125000,\"customTag\":\"{}\",\"id\":94408496,\"itemNum\":\"6920174700725\",\"matnr\":\"482639\",\"modified\":1472883125000,\"orderId\":17275333499,\"pageSize\":20,\"promotionId\":\"\",\"promotionPrice\":0,\"promotionPriceList\":\"\",\"promotionType\":\"\",\"properties\":\"{\\\"chine\\\":\\\"袋\\\",\\\"decStkSource\\\":1,\\\"validDays\\\":1095}\",\"shopId\":1485,\"skuId\":122452,\"skuType\":1,\"wareId\":122452,\"wareImgUrl\":\"201503/47bb595c-e2be-4825-8245-19e1cada774b\",\"wareName\":\"立白天然柔护皂粉 1.6kg\",\"wareNum\":1,\"warePrice\":2065,\"warePromotionPrice\":2065,\"wareTag\":0,\"wareType\":1,\"wareWeight\":1.6,\"webuserId\":3913999,\"yn\":1},{\"catagoryId\":\"10262\",\"couponAmount\":138,\"couponCodeAmount\":0,\"created\":1472883125000,\"customTag\":\"{}\",\"id\":94408497,\"itemNum\":\"324280\",\"matnr\":\"324280\",\"modified\":1472883125000,\"orderId\":17275333499,\"pageSize\":20,\"promotionId\":\"\",\"promotionPrice\":0,\"promotionPriceList\":\"\",\"promotionType\":\"\",\"properties\":\"{\\\"chine\\\":\\\"KG\\\",\\\"decStkSource\\\":1,\\\"validDays\\\":7}\",\"shopId\":1485,\"skuId\":104271,\"skuType\":2,\"wareId\":104271,\"wareImgUrl\":\"20160707/d5301f9e-d82d-452e-adb8-ca2abefb9fad\",\"wareName\":\"硒砂瓜 约10千克\",\"wareNum\":1,\"warePrice\":2160,\"warePromotionPrice\":2160,\"wareTag\":0,\"wareType\":1,\"wareWeight\":10,\"webuserId\":3913999,\"yn\":1},{\"catagoryId\":\"10293\",\"couponAmount\":52,\"couponCodeAmount\":0,\"created\":1472883125000,\"customTag\":\"{}\",\"id\":94408498,\"itemNum\":\"6922477400876\",\"matnr\":\"298500\",\"modified\":1472883125000,\"orderId\":17275333499,\"pageSize\":20,\"promotionId\":\"\",\"promotionPrice\":0,\"promotionPriceList\":\"\",\"promotionType\":\"\",\"properties\":\"{\\\"chine\\\":\\\"盒\\\",\\\"decStkSource\\\":1,\\\"validDays\\\":180}\",\"shopId\":1485,\"skuId\":121146,\"skuType\":1,\"wareId\":121146,\"wareImgUrl\":\"20160116/9c689d31-73ad-4227-ac63-28ba6fa30552\",\"wareName\":\"百吉福奶酪布丁 柠檬味 80g\",\"wareNum\":2,\"warePrice\":400,\"warePromotionPrice\":400,\"wareTag\":0,\"wareType\":1,\"wareWeight\":0.08,\"webuserId\":3913999,\"yn\":1},{\"catagoryId\":\"10293\",\"couponAmount\":52,\"couponCodeAmount\":0,\"created\":1472883125000,\"customTag\":\"{}\",\"id\":94408499,\"itemNum\":\"6922477400883\",\"matnr\":\"298502\",\"modified\":1472883125000,\"orderId\":17275333499,\"pageSize\":20,\"promotionId\":\"\",\"promotionPrice\":0,\"promotionPriceList\":\"\",\"promotionType\":\"\",\"properties\":\"{\\\"chine\\\":\\\"盒\\\",\\\"decStkSource\\\":1,\\\"validDays\\\":180}\",\"shopId\":1485,\"skuId\":121147,\"skuType\":1,\"wareId\":121147,\"wareImgUrl\":\"20160829/2db19c50-7b33-45ba-ad38-4746972dafed\",\"wareName\":\"百吉福奶酪布丁 草莓味 80g\",\"wareNum\":2,\"warePrice\":400,\"warePromotionPrice\":400,\"wareTag\":0,\"wareType\":1,\"wareWeight\":0.08,\"webuserId\":3913999,\"yn\":1}]";

    /**
     * 
     * 使用gzip进行压缩
     */
    public static int gzip(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return 0;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return out.toByteArray().length;
        //return new sun.misc.BASE64Encoder().encode(out.toByteArray());
    }
}
