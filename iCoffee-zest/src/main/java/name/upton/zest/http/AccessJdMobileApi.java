package name.upton.zest.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class AccessJdMobileApi {

    public static void main(String[] args) throws ClientProtocolException, IOException {
        getCouponAndBalance();
        cart();
    }

    public static void getCouponAndBalance() throws ClientProtocolException, IOException {
        String httpMethod = "POST";
        String url = "http://gw.m.360buy.com/client.action?functionId=getCouponAndBalance&uuid=356381041896337-C8AA211E6D6D&clientVersion=2.5.0&client=android&osVersion=2.3.7&screen=960*540&partner=hiapk001&networkType=wifi";
        String postParameter = null;
        String charSet = "UTF-8";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Charset", "UTF-8");
        headers.put("Accept-Encoding", "gzip,deflate");
        headers.put("Cookie", "_applogin_=PRIAMZE7HBJC5D45DOLY2SYNB3IPZ25P53W4P3LU7MUPC3CVPWPUU4I7RVJ5DALEDG62TKWYSB5T72R4UDAMRCIGWPVPU2ROBZJ6Y4F7MCAIHME4LKF56J3FD2EPALBTXDFHD7C4N5HV42GBHWZ7IZ4WUGH43DMDP573LGI");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("User-Agent", "Dalvik/1.4.0 (Linux; U; Android 2.3.7; MB860 Build/GWK74)");
        headers.put("Host", "gw.m.360buy.com");

        //callApi(httpMethod, url, postParameter, charSet, headers);
    }

    public static void cart() throws ClientProtocolException, IOException {
        String httpMethod = "POST";
        String url = "http://gw.m.360buy.com/client.action?functionId=cart&uuid=356381041896337-C8AA211E6D6D&clientVersion=2.5.0&client=android&osVersion=2.3.7&screen=960*540&partner=hiapk001&networkType=wifi&encrypted=false&body=%7B%22cartuuid%22%3A%22356381041896337-C8AA211E6D6D663bef10-fc30-427d-ba9f-a28293a857fb%22%2C%22syntype%22%3A%221%22%7D";
        String postParameter = null;
        String charSet = "UTF-8";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Charset", "UTF-8");
        //headers.put("Accept-Encoding", "gzip,deflate");
        headers.put("Cookie", "_applogin_=PRIAMZE7HBJC5D45DOLY2SYNB3IPZ25P53W4P3LU7MUPC3CVPWPUU4I7RVJ5DALEDG62TKWYSB5T72R4UDAMRCIGWPVPU2ROBZJ6Y4F7MCAIHME4LKF56J3FD2EPALBTXDFHD7C4N5HV42GBHWZ7IZ4WUGH43DMDP573LGI");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("User-Agent", "Dalvik/1.4.0 (Linux; U; Android 2.3.7; MB860 Build/GWK74)");
        headers.put("Host", "gw.m.360buy.com");

        callApi(httpMethod, url, postParameter, charSet, headers);
    }

    public static HttpRequestBase createMethod(String httpMethod, String url, String postParameter, String charSet,
            Map<String, String> headers) throws UnsupportedEncodingException {
        HttpRequestBase method = null;

        if ("GET".equalsIgnoreCase(httpMethod)) {
            method = new HttpGet(url);
        } else if ("POST".equalsIgnoreCase(httpMethod)) {
            method = new HttpPost(url);

            // 设置POST参数
            if (postParameter != null && !"".equals(postParameter.trim())) {
                List<NameValuePair> params = new LinkedList<NameValuePair>();
                String[] postParameterArray = postParameter.split("&");
                for (int i = 0; i < postParameterArray.length; i++) {
                    String perKeyValue = postParameterArray[i];
                    if (perKeyValue.indexOf("=") > -1) {
                        int index = perKeyValue.indexOf("=");
                        String key = perKeyValue.substring(0, index);
                        String value = perKeyValue.substring(index + 1);
                        params.add(new BasicNameValuePair(key, value));
                    }
                }

                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, charSet);
                ((HttpPost) method).setEntity(entity);
            }
        } else {
            method = new HttpGet(url); // 默认get方式请求
        }

        // 设置Header
        if (headers != null) {
            for (Entry<String, String> header : headers.entrySet()) {
                method.addHeader(header.getKey(), header.getValue());
            }
        }

        return method;
    }

    public static HttpClient getHttpClient() {
        // 初始化 Client
        DefaultHttpClient client = new DefaultHttpClient();

        // 设置连接超时时间
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        // 设置读取超时时间
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
        // 设置获取连接的超时时间
        client.getParams().setParameter(ClientPNames.CONN_MANAGER_TIMEOUT, 10000L);
        // 设置user agent
        // client.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
        // "Mozilla/5.0 (X11; Linux i686 on x86_64; rv:10.0.2) Gecko/20100101 Firefox/10.0.2");
        // 设置允许重定向
        client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        client.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true);
        client.getParams().setParameter(ClientPNames.MAX_REDIRECTS, 10);
        client.getParams().setParameter(ClientPNames.REJECT_RELATIVE_REDIRECT, false);
        // 设置cookie策略为兼容
        client.getParams().setParameter(ClientPNames.COOKIE_POLICY, "compatibility");
        // 设置content编码
        // client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET,
        // SurvivalMonitorUtil.DEFAULT_ENCODE);
        // 设置元素编码
        // client.getParams().setParameter(CoreProtocolPNames.HTTP_ELEMENT_CHARSET,
        // SurvivalMonitorUtil.DEFAULT_ENCODE);

        return client;
    }

    public static void callApi(String httpMethod, String url, String postParameter, String charSet, Map<String, String> headers)
            throws ClientProtocolException, IOException {
        HttpRequestBase method = createMethod(httpMethod, url, postParameter, charSet, headers);
        HttpClient client = getHttpClient();

        HttpResponse httpResponse = client.execute(method);

        // 获取返回码
        int respCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("respCode=" + respCode);

        // 读取页面文本内容
        String content = EntityUtils.toString(httpResponse.getEntity());
        System.out.println("content=" + content);
    }
}