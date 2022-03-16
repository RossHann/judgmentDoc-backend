package com.example.judgmentdoc.data.article;

import com.example.judgmentdoc.po.Article;
import com.example.judgmentdoc.po.Catalog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {
    List<Article> getAll(String keyword, String number, String crime, List<Long> catalogs);

    List<Catalog> getAllCatalog();
}
