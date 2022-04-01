package com.example.judgmentdoc.bl.document;

import com.example.judgmentdoc.vo.ResponseVO;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface DocumentService {
    ResponseVO getAll(String keyword, String courtName, String name, String number, String start, String end, List<Long> catalogs, Integer pageNum, Integer pageSize) throws ParseException;

    ResponseVO getCatalogue();

    ResponseVO getDocumentById(Long id);
}
