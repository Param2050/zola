package com.example.zola;

import com.example.zola.model.ZolaConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RefreshScope
@RestController
public class ZolaController {

    @Autowired
    private ZolaConfiguration zolaConfiguration;


    @GetMapping("/zola/test")
    public String zolaTest() {
        return "This is just for testing.";
    }

    @GetMapping("zola/v1/service")
    public String getProfileName() {
        return "Config " + zolaConfiguration.getProfileName();
    }

    @PostMapping("/zola/refresh-all")
    public String refreshAll() {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        restTemplate.exchange("http://localhost:8086/actuator/refresh", HttpMethod.POST, entity, String.class).getBody();
        return "Sucessfully refreshed !!";
    }
}
