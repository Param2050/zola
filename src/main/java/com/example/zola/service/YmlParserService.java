package com.example.zola.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.wnameless.json.flattener.JsonFlattener;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

@Service
public class YmlParserService {

//    public String parseYml() {
//        Properties properties = new Properties();
//        try {
//            InputStream inputStream = new FileInputStream("D:\\project\\zola\\src\\main\\resources\\application-demo.properties");
//            properties.load(inputStream);
//            Enumeration<?> enumeration = properties.propertyNames();
//            while (enumeration.hasMoreElements()) {
//                String key = (String)enumeration.nextElement();
//                System.out.println("key " + key);
//                System.out.println("Value " + properties.getProperty(key));
//            }
//
////            for(Map.Entry<String, Object> entry: stringMap.entrySet()) {
////                System.out.println(entry.getKey());
////                System.out.println(entry.getValue());
////            }
//
//        }catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        return null;
//    }


    public String parseYml() {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(
                    "D:\\project\\zola\\src\\main\\resources\\application-demo.yml")));
            System.out.println("*********Content from YAML File ****************");
            System.out.println(content);
            String json = convertYamlToJson(content);
            System.out.println("*********Cnverted JSON from YAML File ****************");
            Map<String, Object> flattenJson = JsonFlattener.flattenAsMap(json);
            System.out.println(flattenJson);
            for(Map.Entry<String, Object> entry : flattenJson.entrySet()) {
                String key = (String) entry.getKey();
                Object value = (Object) entry.getValue();
                System.out.println(key + " : " + value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String convertYamlToJson(String yaml) {
        try {
            ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
            Object obj = yamlReader.readValue(yaml, Object.class);
            ObjectMapper jsonWriter = new ObjectMapper();
            return jsonWriter.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}


//
