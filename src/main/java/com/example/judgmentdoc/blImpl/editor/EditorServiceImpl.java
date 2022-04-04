package com.example.judgmentdoc.blImpl.editor;

import com.example.judgmentdoc.bl.editor.EditorService;
import com.example.judgmentdoc.bl.model.ModelService;
import com.example.judgmentdoc.data.article.ArticleMapper;
import com.example.judgmentdoc.util.pdf.PdfUtil;
import com.example.judgmentdoc.util.word.WordUtil;
import com.example.judgmentdoc.vo.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EditorServiceImpl implements EditorService {

    @Value("${directory.temporary}")
    private String TEMPORARY_PATH;

    @Autowired
    ModelService modelService;

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public String exportPdf(DocInfoVO docInfoVO) {
        Document document = new Document();
        try {
            //创建书写器,写入磁盘
            String pdfPathName = TEMPORARY_PATH + UUID.randomUUID().toString().replaceAll("-", "") + ".pdf";
            PdfWriter writer = PdfUtil.createPdfWriter(pdfPathName, document);
            //设置A4
            document.setPageSize(PageSize.A4);
            document.open();
            //设置法院名
            PdfUtil.setPdfTitle(docInfoVO.getCourtName(), document);
            //设置文书名字
            PdfUtil.setPdfTitle(docInfoVO.getName(), document);
            //设置案号
            PdfUtil.setPdfMainBody(docInfoVO.getNumber(), document, Element.ALIGN_RIGHT, 0, 0);
            //设置正文
            String[] contents = docInfoVO.getContent().split("\n");
            for (String para : contents) {
                PdfUtil.setPdfMainBody(para.replace((char) 12288, ' ').replace((char) 160, ' ').trim(), document, Element.ALIGN_LEFT, 20, 0);
            }
            //设置落款人员
            for (String member : docInfoVO.getMemberList()) {
                PdfUtil.setPdfMainBody(member, document, Element.ALIGN_RIGHT, 0, 0);
            }
            //设置落款日期
            PdfUtil.setPdfMainBody(docInfoVO.getChineseDate(), document, Element.ALIGN_RIGHT, 0, 0);

            document.close();
            writer.close();
            return pdfPathName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String exportWord(DocInfoVO docInfoVO) {
        XWPFDocument document = new XWPFDocument();
        try {
            String wordPathName = TEMPORARY_PATH + UUID.randomUUID().toString().replaceAll("-", "") + ".docx";

            //设置法院名
            WordUtil.createTitle(docInfoVO.getCourtName(), document, 20, "宋体");
            //设置文书名字
            WordUtil.createTitle(docInfoVO.getName(), document, 25, "宋体");
            //设置案号
            WordUtil.createParagraph(docInfoVO.getNumber(), document, ParagraphAlignment.RIGHT, 0, 15, "仿宋");
            //设置正文
            String[] contents = docInfoVO.getContent().split("\n");
            for (String para : contents) {
                WordUtil.createParagraph(para, document, ParagraphAlignment.BOTH, 550, 15, "仿宋");
            }
            //设置落款人员
            for (String member : docInfoVO.getMemberList()) {
                WordUtil.createParagraph(member, document, ParagraphAlignment.RIGHT, 0, 15, "仿宋");
            }
            //设置落款日期
            WordUtil.createParagraph(docInfoVO.getChineseDate(), document, ParagraphAlignment.RIGHT, 0, 15, "仿宋");

            WordUtil.saveDocument(document, wordPathName);

            return wordPathName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseVO check(String text, String crime) {
        ResponseVO response = modelService.check(text, crime);
        if (!response.getSuccess()) {  //检验失败
            return ResponseVO.buildFailure(response.getMessage());
        }
        List<Map<String, Object>> textList = (ArrayList) response.getContent();

        List<Text> texts = new ArrayList<>();
        for (Map<String, Object> textMap : textList) {
            Text curText = null;
            if ((int) textMap.get("type") == 0) {  //普通文本
                curText = new Text();
            } else {
                if ((int) textMap.get("type") == 1) {  //事实
                    curText = new Fact();
                    ((Fact) curText).setCount((int) textMap.get("count"));
                    List<Long> needs = (ArrayList<Long>) textMap.get("needs");
                    if (needs.size() > 0) {
                        ((Fact) curText).setNeeds(articleMapper.getArticleListByIds(needs));
                    }
                } else if ((int) textMap.get("type") == 2) {  //法条
                    curText = new Law();
                    ((Law) curText).setArticle(articleMapper.getArticleById(Long.valueOf(textMap.get("articleId").toString())));
                } else if ((int) textMap.get("type") == 3) {  //结论
                    curText = new Conclusion();
                    ((Conclusion) curText).setCount((int) textMap.get("count"));
                }
                for (String relation : (ArrayList<String>) textMap.get("relations")) {
                    curText.addRelation(relation);
                }
            }
            assert curText != null;
            curText.setId((String) textMap.get("id"));
            curText.setContent((String) textMap.get("content"));
            texts.add(curText);
        }

        return ResponseVO.buildSuccess(texts);
    }
}
