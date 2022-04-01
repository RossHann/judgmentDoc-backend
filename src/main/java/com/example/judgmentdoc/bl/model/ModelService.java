package com.example.judgmentdoc.bl.model;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(url = "${model.url}", name = "${model.name}")
public interface ModelService {

    @PostMapping(value = "/train")
    JSONObject train();

    @GetMapping(value = "/check")
    JSONObject check(@RequestParam(value = "text") String text, @RequestParam(value = "crime", defaultValue = "traffic") String crime);
}
