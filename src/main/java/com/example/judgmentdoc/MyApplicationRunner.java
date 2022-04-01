package com.example.judgmentdoc;

import com.example.judgmentdoc.bl.model.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//@Component  // 项目启动时不重新训练模型，则注释此行
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    ModelService modelService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        modelService.train();
    }
}
