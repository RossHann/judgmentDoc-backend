package com.example.judgmentdoc.bl.editor;

import com.example.judgmentdoc.vo.DocInfoVO;

public interface EditorService {
    String exportPdf(DocInfoVO docInfoVO);
}
