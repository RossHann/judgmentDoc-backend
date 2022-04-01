package com.example.judgmentdoc.bl.model;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FeignClient(url = "http://localhost:8000/model", name = "model")
public interface ModelService {

    @PostMapping(value = "/train")
    JSONObject train();
}
