package com.example.judgmentdoc.data.document;

import com.example.judgmentdoc.po.Catalog;
import com.example.judgmentdoc.po.Document;
import com.example.judgmentdoc.po.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface DocumentMapper {
    List<Document> getAll(String keyword, String courtName, String name, String number, Date startDate, Date endDate, List<Long> catalogs);

    List<Catalog> getAllCatalog();

    Document getDocumentById(Long id);

    List<Member> getMembersByDocumentId(Long documentId);
}
