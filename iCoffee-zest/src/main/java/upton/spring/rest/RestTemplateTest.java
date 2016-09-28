package upton.spring.rest;

import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {

    public static void main(String[] args) {
        RestTemplate rest = new RestTemplate();
        
        String resp = rest.getForObject("http://www.baidu.com", String.class);
        
        System.out.println(resp);
    }

}
