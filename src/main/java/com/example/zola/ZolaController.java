package com.example.zola;

import com.example.zola.model.ZolaConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ZolaController {

    @Autowired
    private ZolaConfiguration zolaConfiguration;

    @GetMapping("zola/v1/service")
    public String getProfileName() {
        return "Config " + zolaConfiguration.getProfileName();
    }
}
