package com.example.judgmentdoc.bl.article;

import com.example.judgmentdoc.vo.ResponseVO;

import java.util.List;

public interface ArticleService {
    ResponseVO getAll(String keyword, String number, String crime, List<Long> catalogs, Integer pageNum, Integer pageSize);

    ResponseVO getCatalogue();
}
