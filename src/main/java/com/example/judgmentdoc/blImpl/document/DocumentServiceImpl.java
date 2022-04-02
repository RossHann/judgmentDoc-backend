package com.example.judgmentdoc.blImpl.document;

import com.example.judgmentdoc.bl.document.DocumentService;
import com.example.judgmentdoc.data.document.DocumentMapper;
import com.example.judgmentdoc.po.Catalog;
import com.example.judgmentdoc.po.Document;
import com.example.judgmentdoc.util.tree.TreeUtil;
import com.example.judgmentdoc.vo.PageVO;
import com.example.judgmentdoc.vo.ResponseVO;
import com.example.judgmentdoc.vo.TreeVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentMapper documentMapper;

    @Override
    public ResponseVO getAll(String keyword, String courtName, String name, String number, String start, String end, List<Long> catalogs, Integer pageNum, Integer pageSize) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null, endDate = null;
        if (!start.isEmpty()) {
            startDate = sdf.parse(start);
        }
        if (!end.isEmpty()) {
            endDate = sdf.parse(end);
        }
        PageHelper.startPage(pageNum, pageSize);
        return ResponseVO.buildSuccess(PageVO.restPage(documentMapper.getAll(keyword, courtName, name, number, startDate, endDate, catalogs)));
    }

    @Override
    public ResponseVO getCatalogue() {
        List<TreeVO> catalogs = new ArrayList<>();
        for (Catalog catalog : documentMapper.getAllCatalog()) {
            catalogs.add(new TreeVO(catalog));
        }
        List<TreeVO> tree = TreeUtil.buildTree(catalogs);
        return ResponseVO.buildSuccess(tree);
    }

    @Override
    public ResponseVO getDocumentById(Long id) {
        Document document = documentMapper.getDocumentById(id);
        document.setMembers(documentMapper.getMembersByDocumentId(id));
        return ResponseVO.buildSuccess(document);
    }
}
