package com.example.judgmentdoc.controller.editor;

import com.example.judgmentdoc.bl.editor.EditorService;
import com.example.judgmentdoc.util.pdf.PdfUtil;
import com.example.judgmentdoc.vo.DocInfoVO;
import com.example.judgmentdoc.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController()
@RequestMapping("/api/editor")
@Api(tags = "EditorController", description = "裁判文书编辑管理")
public class EditorController {

    private final static String CHECK_ERROR = "检验失败";

    @Autowired
    EditorService editorService;

    @ApiOperation("导出裁判文书pdf")
    @PostMapping("/export/pdf")
    public void exportPdf(@RequestBody DocInfoVO docInfoVO, HttpServletResponse response) {
        try {
            String pdfPath = editorService.exportPdf(docInfoVO);
            PdfUtil.returnPdfStream(response, pdfPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("检验裁判文书")
    @PostMapping("/check")
    public ResponseVO check(@RequestParam(value = "text", required = true) String text) {
        try {
            return editorService.check(text);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(CHECK_ERROR);
        }
    }
}
