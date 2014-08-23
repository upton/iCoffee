package name.upton.zest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class TestJson {
    private int i = 5;
    private String s = "ss";
    private Long l;


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @JSONField(serialize = false)
    public Long getL() {
        return l;
    }

    public void setL(Long l) {
        this.l = l;
    }

    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(new TestJson()));
        //TestJson t = JSONObject.parseObject("{\"i\":5,\"s\":\"ss\"}", TestJson.class);
        //System.out.println(t);
        
        System.out.println(System.currentTimeMillis());
    }
}
