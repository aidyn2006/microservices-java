package org.example.booksservice.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    private final String CLOUD_NAME="dcawzx4kp";
    private final String API_KEY="963419412157174";

    private final String API_SECRET="of8ffqL1wIZlmczO6RGM28qiktY";

    @Bean
    public Cloudinary CloudinaryConfigurtation(){
        Map<String,String> map=new HashMap<>();

        map.put("cloud_name",CLOUD_NAME);
        map.put("api_key",API_KEY);
        map.put("api_secret",API_SECRET);

        return new Cloudinary(map);
    }
}
