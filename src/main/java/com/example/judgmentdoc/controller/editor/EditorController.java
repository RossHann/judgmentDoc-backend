package com.example.judgmentdoc.controller.editor;

import com.example.judgmentdoc.bl.editor.EditorService;
import com.example.judgmentdoc.util.pdf.PdfUtil;
import com.example.judgmentdoc.vo.DocInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController()
@RequestMapping("/api/editor")
@Api(tags = "EditorController", description = "裁判文书编辑管理")
public class EditorController {

    @Autowired
    EditorService editorService;

    @ApiOperation("导出裁判文书pdf")
    @PostMapping("/exportPdf")
    public void downloadStruct(@RequestBody DocInfoVO docInfoVO, HttpServletResponse response) {
        try {
            String pdfPath = editorService.exportPdf(docInfoVO);
            PdfUtil.returnPdfStream(response, pdfPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
