package com.example.judgmentdoc.bl.model;

import com.example.judgmentdoc.vo.ResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(url = "${model.url}", name = "${model.name}")
public interface ModelService {

    @GetMapping(value = "/check")
    ResponseVO check(@RequestParam(value = "text") String text, @RequestParam(value = "crime", defaultValue = "traffic") String crime);
}
