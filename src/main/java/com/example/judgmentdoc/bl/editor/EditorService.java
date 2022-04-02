package com.example.judgmentdoc.bl.editor;

import com.example.judgmentdoc.vo.DocInfoVO;
import com.example.judgmentdoc.vo.ResponseVO;
import org.codehaus.jettison.json.JSONException;

public interface EditorService {
    String exportPdf(DocInfoVO docInfoVO);

    String exportWord(DocInfoVO docInfoVO);

    ResponseVO check(String text, String crime) throws JSONException;
}
