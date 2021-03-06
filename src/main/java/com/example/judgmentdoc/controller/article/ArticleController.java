package com.example.judgmentdoc.controller.article;

import com.example.judgmentdoc.bl.article.ArticleService;
import com.example.judgmentdoc.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/article")
@Api(tags = "ArticleController", description = "法条信息管理")
public class ArticleController {

    private static final String GET_ERROR = "查询失败";

    @Autowired
    ArticleService articleService;

    @ApiOperation(value = "根据条件分页查询法条")
    @GetMapping("/getAll")
    public ResponseVO getAll(@RequestParam(value = "keyword", required = false) String keyword,
                             @RequestParam(value = "number", required = false) String number,
                             @RequestParam(value = "crime", required = false) String crime,
                             @RequestParam(value = "catalogs", required = false) List<Long> catalogs,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        try {
            return articleService.getAll(keyword, number, crime,catalogs, pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(GET_ERROR);
        }
    }

    @ApiOperation(value = "获取树状目录")
    @GetMapping("/getCatalogue")
    public ResponseVO getCatalogue() {
        try {
            return articleService.getCatalogue();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(GET_ERROR);
        }
    }
}
