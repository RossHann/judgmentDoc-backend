package com.example.judgmentdoc.controller.document;

import com.example.judgmentdoc.bl.document.DocumentService;
import com.example.judgmentdoc.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/document")
@Api(tags = "DocumentController", description = "裁判文书管理")
public class DocumentController {

    private static final String GET_ERROR = "查询失败";

    @Autowired
    DocumentService documentService;

    @ApiOperation(value = "根据条件分页查询裁判文书")
    @GetMapping("/getAll")
    public ResponseVO getAll(@RequestParam(value = "keyword", required = false) String keyword,
                             @RequestParam(value = "courtName", required = false) String courtName,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "number", required = false) String number,
                             @RequestParam(value = "start", required = false) String start,
                             @RequestParam(value = "end", required = false) String end,
                             @RequestParam(value = "catalogs", required = false) List<Long> catalogs,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        try {
            return documentService.getAll(keyword, courtName, name, number, start, end, catalogs, pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(GET_ERROR);
        }
    }

    @ApiOperation(value = "获取树状目录")
    @GetMapping("/getCatalogue")
    public ResponseVO getCatalogue() {
        try {
            return documentService.getCatalogue();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(GET_ERROR);
        }
    }

    @ApiOperation(value = "获取单条文书")
    @GetMapping("/get/{id}")
    public ResponseVO getDocumentById(@PathVariable("id") Long id) {
        try {
            return documentService.getDocumentById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(GET_ERROR);
        }
    }
}
