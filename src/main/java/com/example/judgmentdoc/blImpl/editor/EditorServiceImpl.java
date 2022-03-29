package com.example.judgmentdoc.blImpl.editor;

import com.example.judgmentdoc.bl.editor.EditorService;
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
                WordUtil.createParagraph(para, document, ParagraphAlignment.BOTH , 550, 15, "仿宋");
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
    public ResponseVO check(String text) {
        List<Text> result = new ArrayList<>();

        Text text1 = new Text();
        text1.setId("text-1");
        text1.setContent("公诉机关XX市XX区人民检察院。\n被告人XX，于XX市XX区。\nXX市XX区人民检察院X检X诉XX号起诉书指控被告人XX犯交通肇事罪于XX年X月X日向本院提起公诉。本院审查后于当日受理，依法组成合议庭，公开开庭审理本案。XX市XX区人民检察院指派代理检察员XX出庭支持公诉，被告人XX到庭参加诉讼。现已审理终结。\nXX市XX区人民检察院指控，XX年X月X日晚，被告人XX驾驶XXX号小型客车，");
        result.add(text1);

        Fact fact1 = new Fact();
        fact1.setId("fact-1");
        fact1.setContent("在本市XX行驶中撞上XXX，致其死亡。");
        fact1.setCount(1);
        fact1.addRelation("law-1");
        result.add(fact1);

        Text text2 = new Text();
        text2.setId("text-2");
        text2.setContent("经公安机关认定，XX负事故的全部责任。\n经审理查明，XX年X月X日X时许，被告人XX驾驶牌照号为XXX的小型客车，在本市XX公路行驶至XX，撞上XXX，致其颅脑损伤合并创伤性休克死亡。次日，XX被抓获。经公安机关认定，XX负事故的全部责任。\n另查明，案发后，被告人XX赔偿被害人亲属各项经济损失X万元，并得到谅解。\n上述事实，有证人XXX证言，被告人XX供述及案件来源、到案经过、道路交通事故现场勘查笔录、交通司法鉴定意见书赔偿调解书、谅解书等证据予以证实。\n本院认为，被告人XX违反道路交通安全法的规定，发生致一人死亡的重大交通事故，且负事故的全部责任，其行为构成交通肇事罪。鉴于其在归案后能如实供述犯罪事实，当庭认罪，结合其赔偿被害人经济损失，并获得谅解的情节，可对其依法从轻处罚。依据");
        result.add(text2);

        Law law1 = new Law();
        law1.setId("law-1");
        law1.setContent("《中华人民共和国刑法》第一百三十三条");
        law1.setArticle(articleMapper.getArticleById(141l));
        law1.addRelation("fact-1");
        result.add(law1);

        Text text3 = new Text();
        text3.setId("text-3");
        text3.setContent("、");
        result.add(text3);

        Law law2 = new Law();
        law2.setId("law-2");
        law2.setContent("第六十七条");
        law2.setArticle(articleMapper.getArticleById(69l));
        result.add(law2);

        Text text4 = new Text();
        text4.setId("text-4");
        text4.setContent("第三款之规定，判决如下：\n");
        result.add(text4);

        Conclusion conclusion1 = new Conclusion();
        conclusion1.setId("conclusion-1");
        conclusion1.setContent("被告人XX犯交通肇事罪，判处有期徒刑三年。（刑期从判决执行之日起计算。判决执行以前先行羁押的，羁押一日折抵刑期一日。即从XX年X月X日起，至XX年X月X日止）。\n");
        conclusion1.setCount(1);
        conclusion1.addRelation("law-1");
        result.add(conclusion1);

        Text text5 = new Text();
        text5.setId("text-5");
        text5.setContent("如不服本判决，可在接到判决书的第二日起十日内，通过本院或者直接向天津市第一中级人民法院提出上诉。书面上诉的，应提交上诉状正本一份，副本二份。\n");
        result.add(text5);

        CheckResultVO checkResultVO = new CheckResultVO();
        checkResultVO.setAccuracy(78);
        checkResultVO.setFactLess(1);
        checkResultVO.setLawLess(0);
        checkResultVO.setLawError(0);
        checkResultVO.setTexts(result);

        return ResponseVO.buildSuccess(checkResultVO);
    }
}
