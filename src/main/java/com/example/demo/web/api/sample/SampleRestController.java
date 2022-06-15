package com.example.demo.web.api.sample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@RestController
public class SampleRestController {

    private ObjectMapper objectMapper;

    public SampleRestController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/api/v1/sample")
    public ResponseEntity<String> sample() {

        StringBuilder sb = new StringBuilder();

        if(objectMapper == null) {
            return ResponseEntity.internalServerError().body("BEAN CREATION ERROR");
        }

        try {

            URL url = new URL("https://jsonplaceholder.typicode.com/todos/1");
            Map map1 = objectMapper.readValue(url, Map.class);

            map1.forEach((k, v) -> {
                sb.append("k=");
                sb.append(k);
                sb.append("v=");
                sb.append(v);
                sb.append("\n");
            });

            return ResponseEntity.ok(sb.toString());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("JSON PARSE ERROR");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("URL CREATE ERROR");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("CALL IO ERROR");
        }

//        return ResponseEntity.ok("OK " + objectMapper.toString());
    }

}
