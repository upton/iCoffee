package name.upton.zest.httpclient;

import java.util.Arrays;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpHeaders;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.protocol.ExecutionContext;

/**
 * The factory to create HTTP-related objects.
 * 
 */
public class HttpFactory {

    /**
     * Creates a HttpAsyncClient instance.
     * 
     * @param config
     *            Client configuration.
     * @param context
     *            Execution context.
     * @return HttpClient instance.
     * @throws IOReactorException
     */
    public static CloseableHttpAsyncClient createHttpAsyncClient(ClientConfiguration config) {
        // Set HTTP params.
        // Create I/O reactor configuration
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setIoThreadCount(config.getIoReactorThreadCount())
                .setConnectTimeout(config.getConnectionTimeout()).setSoTimeout(config.getSocketTimeout())
                .setSoKeepAlive(config.isSoKeepAlive()).build();

        // Create a custom I/O reactort
        ConnectingIOReactor ioReactor;
        try {
            ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        } catch (IOReactorException e) {
            throw new RuntimeException(e);
        }

        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
        HttpAsyncClientBuilder httpClientBuilder = HttpAsyncClients.custom().setConnectionManager(connManager);

        RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BEST_MATCH).setExpectContinueEnabled(true)
                .setStaleConnectionCheckEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).setConnectTimeout(config.getConnectionTimeout())
                .setSocketTimeout(config.getSocketTimeout()).setExpectContinueEnabled(config.isExceptContinue()).build();

        httpClientBuilder.setDefaultRequestConfig(defaultRequestConfig);
        httpClientBuilder.setMaxConnTotal(config.getMaxConnections());
        httpClientBuilder.setUserAgent(config.getDefaultUserAgent());
        CloseableHttpAsyncClient httpclient = httpClientBuilder.build();

        return httpclient;
    }

    /**
     * Creates a HttpRequestBase instance.
     * 
     * @param request
     *            Request message.
     * @return HttpRequestBase instance.
     */
    public static HttpRequestBase createHttpRequest(Request request) {

        String uri = request.getUri();
        HttpMethod method = request.getMethod();
        HttpRequestBase httpRequest;
        if (method == HttpMethod.POST) {
            // POST
            HttpPost postMethod = new HttpPost(uri);

            if (request.getContent() != null) {
                //postMethod.setEntity(request);
            }

            httpRequest = postMethod;
        } else if (method == HttpMethod.PUT) {
            // PUT
            HttpPut putMethod = new HttpPut(uri);

            if (request.getContent() != null) {
                //putMethod.setEntity(new RepeatableInputStreamEntity(request));
            }

            httpRequest = putMethod;
        } else if (method == HttpMethod.GET) {
            // GET
            httpRequest = new HttpGet(uri);
        } else if (method == HttpMethod.DELETE) {
            // DELETE
            httpRequest = new HttpDelete(uri);
        } else if (method == HttpMethod.HEAD) {
            httpRequest = new HttpHead(uri);
        } else if (method == HttpMethod.OPTIONS) {
            httpRequest = new HttpOptions(uri);
        } else {
            throw new IllegalArgumentException(String.format("Unsupported HTTP method：%s.", request.getMethod().toString()));
        }

        configureRequestHeaders(request, httpRequest);
        // httpRequest.addHeader("User-Agent","aliyun-java-sdk");
        return httpRequest;
    }

    private static void configureRequestHeaders(Request request, HttpRequestBase httpRequest) {
        // Copy headers in the request message to the HTTP request
        for (Entry<String, String> entry : request.getHeaders().entrySet()) {
            // HttpClient fills in the Content-Length,
            // and complains if add it again, so skip it as well as the Host
            // header.
            if (entry.getKey().equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH) || entry.getKey().equalsIgnoreCase(HttpHeaders.HOST)) {
                continue;
            }

            httpRequest.addHeader(entry.getKey(), entry.getValue());
        }
    }
}