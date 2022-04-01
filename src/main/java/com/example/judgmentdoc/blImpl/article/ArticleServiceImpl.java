package com.example.judgmentdoc.blImpl.article;

import com.example.judgmentdoc.bl.article.ArticleService;
import com.example.judgmentdoc.data.article.ArticleMapper;
import com.example.judgmentdoc.po.Catalog;
import com.example.judgmentdoc.util.tree.TreeUtil;
import com.example.judgmentdoc.vo.PageVO;
import com.example.judgmentdoc.vo.ResponseVO;
import com.example.judgmentdoc.vo.TreeVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public ResponseVO getAll(String keyword, String number, String crime, List<Long> catalogs, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return ResponseVO.buildSuccess(PageVO.restPage(articleMapper.getAll(keyword, number, crime, catalogs)));
    }

    @Override
    public ResponseVO getCatalogue() {
        List<TreeVO> catalogs = new ArrayList<>();
        for (Catalog catalog : articleMapper.getAllCatalog()) {
            catalogs.add(new TreeVO(catalog));
        }
        List<TreeVO> tree = TreeUtil.buildTree(catalogs);
        return ResponseVO.buildSuccess(tree);
    }
}
