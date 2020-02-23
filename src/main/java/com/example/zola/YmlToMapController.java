package com.example.zola;


import com.example.zola.service.YmlParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parse")
public class YmlToMapController {

    @Autowired
    private YmlParserService ymlParserService;

    @GetMapping("/yml-to-java")
    public ResponseEntity<String> parseYmlToJava() {
//        String response = ymlParserService.customYmlParser();
        String response = ymlParserService.parseYml();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
