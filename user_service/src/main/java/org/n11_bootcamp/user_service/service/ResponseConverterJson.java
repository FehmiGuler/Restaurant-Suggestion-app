package org.n11_bootcamp.user_service.service;

import org.springframework.stereotype.Component;

@Component
public class ResponseConverterJson implements ResponseConverter{
    public String convert(){
        return "JSON";
    }
}
