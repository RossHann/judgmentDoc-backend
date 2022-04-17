package com.example.judgmentdoc.controller.editor;

import com.example.judgmentdoc.bl.editor.EditorService;
import com.example.judgmentdoc.util.file.FileUtil;
import com.example.judgmentdoc.vo.DocInfoVO;
import com.example.judgmentdoc.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.*;

import javax.servlet.http.HttpServletResponse;

@RestController()
@RequestMapping("/api/editor")
@Api(tags = "EditorController", description = "裁判文书编辑管理")
public class EditorController {

    private final static String CHECK_ERROR = "检验失败";

    @Autowired
    EditorService editorService;

    @ApiOperation("导出裁判文书pdf")
    @PostMapping("/export/pdf")
    public void exportPdf(@RequestBody DocInfoVO docInfoVO, HttpServletResponse response) {
        try {
            String pdfPath = editorService.exportPdf(docInfoVO);
            FileUtil.returnStream(response, pdfPath);
            FileUtil.delFile(pdfPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("导出裁判文书word")
    @PostMapping("/export/word")
    public void exportWord(@RequestBody DocInfoVO docInfoVO, HttpServletResponse response) {
        try {
            String wordPath = editorService.exportWord(docInfoVO);
            FileUtil.returnStream(response, wordPath);
            FileUtil.delFile(wordPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("检验裁判文书")
    @GetMapping("/check")
    public ResponseVO check(@RequestParam(value = "text") String text,
                            @RequestParam(value = "crime", defaultValue = "traffic") String crime) {
        try {
            System.out.println(text);
            System.out.println(crime);

            String strJson = "{\n" +
                    "    \"success\": true,\n" +
                    "    \"message\": null,\n" +
                    "    \"content\": [\n" +
                    "        {\n" +
                    "            \"id\": \"text-28\",\n" +
                    "            \"content\": \"\\n4、证人郭某甲的证言，其称段某甲是大辛庄乡人，段某甲说他的工人在其这里干完活在回去的路上发生了交通事故，来其这了解情况。\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-29\",\n" +
                    "            \"content\": \"其称段某甲的工人最后来干活的时间是2014年4月26日下午14时许，来其这干活。\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-33\",\n" +
                    "            \"content\": \"其爱人在电话中告诉其父亲发生车祸，其就和其兄弟段某丁从北京回来了。\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"fact-20\",\n" +
                    "            \"content\": \"10、现场勘查笔录、现场照片、现场图，证明现场的基本情况。\",\n" +
                    "            \"type\": 1,\n" +
                    "            \"relations\": [],\n" +
                    "            \"count\": 20,\n" +
                    "            \"needs\": [\n" +
                    "                {\n" +
                    "                    \"id\": 1149,\n" +
                    "                    \"content\": \"第六十四条　代理诉讼的律师和其他诉讼代理人有权调查收集证据，可以查阅本案有关材料。查阅本案有关材料的范围和办法由最高人民法院规定。\",\n" +
                    "                    \"number\": \"第六十四条\",\n" +
                    "                    \"crime\": \"\",\n" +
                    "                    \"catalogId\": 136,\n" +
                    "                    \"law\": \"中华人民共和国民事诉讼法\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-38\",\n" +
                    "            \"content\": \"\\n\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"fact-21\",\n" +
                    "            \"content\": \"11、交通事故责任认定书，证明苗某甲负事故的全部责任，段某乙不负事故责任。\",\n" +
                    "            \"type\": 1,\n" +
                    "            \"relations\": [],\n" +
                    "            \"count\": 21,\n" +
                    "            \"needs\": [\n" +
                    "                {\n" +
                    "                    \"id\": 1664,\n" +
                    "                    \"content\": \"第一条　从事交通运输人员或者非交通运输人员，违反交通运输管理法规发生重大交通事故，在分清事故责任的基础上，对于构成犯罪的，依照刑法第一百三十三条的规定定罪处罚。\",\n" +
                    "                    \"number\": \"第一条\",\n" +
                    "                    \"crime\": \"\",\n" +
                    "                    \"catalogId\": 222,\n" +
                    "                    \"law\": \"最高人民法院关于审理交通肇事刑事案件具体应用法律若干问题的解释\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-39\",\n" +
                    "            \"content\": \"\\n12、行政强制措施凭证，证明扣留事故车辆情况。\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-40\",\n" +
                    "            \"content\": \"\\n13、驾驶人信息查询结果单、驾驶证复印件，证明苗某甲准驾车型D。\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-41\",\n" +
                    "            \"content\": \"\\n14、购车证明，证明车辆是段某甲的车。\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-42\",\n" +
                    "            \"content\": \"\\n\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"fact-22\",\n" +
                    "            \"content\": \"15、法医学尸体检验意见书，证明死者面部及肢体损伤，分析为在运动物体外力作用下所致，死者鼻腔内有血性物体溢出，分析死因为颅脑损伤死亡。\",\n" +
                    "            \"type\": 1,\n" +
                    "            \"relations\": [],\n" +
                    "            \"count\": 22,\n" +
                    "            \"needs\": [\n" +
                    "                {\n" +
                    "                    \"id\": 1722,\n" +
                    "                    \"content\": \"第十四条　投保人允许的驾驶人驾驶机动车致使投保人遭受损害，当事人请求承保交强险的保险公司在责任限额范围内予以赔偿的，人民法院应予支持，但投保人为本车上人员的除外。\",\n" +
                    "                    \"number\": \"第十四条\",\n" +
                    "                    \"crime\": \"\",\n" +
                    "                    \"catalogId\": 227,\n" +
                    "                    \"law\": \"最高人民法院关于审理道路交通事故损害赔偿案件适用法律若干问题的解释\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-43\",\n" +
                    "            \"content\": \"\\n16、痕迹检验意见书，被检五征牌三轮汽车可形成现场路面车轮印痕。\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-44\",\n" +
                    "            \"content\": \"\\n17、户籍证明、现实表现，证明被告人基本身份信息及无前科。\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-45\",\n" +
                    "            \"content\": \"\\n上述证据已经庭审质证，本院予以确认。\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-46\",\n" +
                    "            \"content\": \"\\n本院认为，被告人苗某甲持准驾车型不符的驾驶证驾驶无牌照五征牌三轮汽车上路行驶，将前方段某乙驾驶的自行车撞倒，造成段某乙死亡的严重后果，事故发生后其驾车逃离现场，且负事故全部责任。被告人段某甲明知自己的车辆无牌照的情况下，仍指使苗某甲违章驾驶其车辆造成重大交通事故，苗某甲、段某甲的行为已构成交通肇事罪。检察机关的指控成立。鉴于被告人苗某甲、段某甲到案后如实供述，当庭自愿认罪；赔偿了被害人方的经济损失，取得了被害人方的谅解。苗某甲在交通肇事逃逸后自动投案，到案后如实供述自己的罪行，其构成自首。综上情节，本院决定对被告人苗某甲、段某甲从轻处罚，宣告缓刑对其所居住的社区亦无重大不良影响。本院决定对其从轻处罚并适用缓刑。根据本案犯罪事实、情节以及对社会危害程度，依照\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"law-1\",\n" +
                    "            \"content\": \"《中华人民共和国刑法》第一百三十三条\",\n" +
                    "            \"type\": 2,\n" +
                    "            \"relations\": [\n" +
                    "                \"fact-1\",\n" +
                    "                \"fact-4\",\n" +
                    "                \"fact-9\",\n" +
                    "                \"fact-13\",\n" +
                    "                \"fact-17\"\n" +
                    "            ],\n" +
                    "            \"article\": {\n" +
                    "                \"id\": 724,\n" +
                    "                \"content\": \"第一百三十三条　【交通肇事罪】违反交通运输管理法规，因而发生重大事故，致人重伤、死亡或者使公私财产遭受重大损失的，处三年以下有期徒刑或者拘役；交通运输肇事后逃逸或者有其他特别恶劣情节的，处三年以上七年以下有期徒刑；因逃逸致人死亡的，处七年以上有期徒刑。\",\n" +
                    "                \"number\": \"第一百三十三条\",\n" +
                    "                \"crime\": \"交通肇事罪\",\n" +
                    "                \"catalogId\": 99,\n" +
                    "                \"law\": \"中华人民共和国刑法\"\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-47\",\n" +
                    "            \"content\": \"、\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"law-2\",\n" +
                    "            \"content\": \"第六十七条\",\n" +
                    "            \"type\": 2,\n" +
                    "            \"relations\": [\n" +
                    "                \"fact-6\",\n" +
                    "                \"fact-8\",\n" +
                    "                \"fact-10\",\n" +
                    "                \"fact-11\",\n" +
                    "                \"fact-14\",\n" +
                    "                \"fact-19\"\n" +
                    "            ],\n" +
                    "            \"article\": {\n" +
                    "                \"id\": 652,\n" +
                    "                \"content\": \"第六十七条　【自首】犯罪以后自动投案，如实供述自己的罪行的，是自首。对于自首的犯罪分子，可以从轻或者减轻处罚。其中，犯罪较轻的，可以免除处罚。\\n被采取强制措施的犯罪嫌疑人、被告人和正在服刑的罪犯，如实供述司法机关还未掌握的本人其他罪行的，以自首论。\\n犯罪嫌疑人虽不具有前两款规定的自首情节，但是如实供述自己罪行的，可以从轻处罚；因其如实供述自己罪行，避免特别严重后果发生的，可以减轻处罚。\",\n" +
                    "                \"number\": \"第六十七条\",\n" +
                    "                \"crime\": \"自首\",\n" +
                    "                \"catalogId\": 90,\n" +
                    "                \"law\": \"中华人民共和国刑法\"\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-48\",\n" +
                    "            \"content\": \"、\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"law-3\",\n" +
                    "            \"content\": \"第七十二条\",\n" +
                    "            \"type\": 2,\n" +
                    "            \"relations\": [\n" +
                    "                \"fact-6\",\n" +
                    "                \"fact-12\",\n" +
                    "                \"fact-18\"\n" +
                    "            ],\n" +
                    "            \"article\": {\n" +
                    "                \"id\": 657,\n" +
                    "                \"content\": \"第七十二条　【适用条件】对于被判处拘役、三年以下有期徒刑的犯罪分子，同时符合下列条件的，可以宣告缓刑，对其中不满十八周岁的人、怀孕的妇女和已满七十五周岁的人，应当宣告缓刑：\\n（一）犯罪情节较轻；\\n（二）有悔罪表现；\\n（三）没有再犯罪的危险；\\n（四）宣告缓刑对所居住社区没有重大不良影响。\\n宣告缓刑，可以根据犯罪情况，同时禁止犯罪分子在缓刑考验期限内从事特定活动，进入特定区域、场所，接触特定的人。\\n被宣告缓刑的犯罪分子，如果被判处附加刑，附加刑仍须执行。\",\n" +
                    "                \"number\": \"第七十二条\",\n" +
                    "                \"crime\": \"适用条件\",\n" +
                    "                \"catalogId\": 92,\n" +
                    "                \"law\": \"中华人民共和国刑法\"\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-49\",\n" +
                    "            \"content\": \"、\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"law-4\",\n" +
                    "            \"content\": \"第七十三条\",\n" +
                    "            \"type\": 2,\n" +
                    "            \"relations\": [],\n" +
                    "            \"article\": {\n" +
                    "                \"id\": 658,\n" +
                    "                \"content\": \"第七十三条　【考验期限】拘役的缓刑考验期限为原判刑期以上一年以下，但是不能少于二个月。\\n有期徒刑的缓刑考验期限为原判刑期以上五年以下，但是不能少于一年。\\n缓刑考验期限，从判决确定之日起计算。\",\n" +
                    "                \"number\": \"第七十三条\",\n" +
                    "                \"crime\": \"考验期限\",\n" +
                    "                \"catalogId\": 92,\n" +
                    "                \"law\": \"中华人民共和国刑法\"\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-50\",\n" +
                    "            \"content\": \"、最高人民法院\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"law-5\",\n" +
                    "            \"content\": \"《关于审理交通肇事刑事案件具体应用法律若干问题的解释》第二条\",\n" +
                    "            \"type\": 2,\n" +
                    "            \"relations\": [\n" +
                    "                \"fact-1\",\n" +
                    "                \"fact-4\",\n" +
                    "                \"fact-9\",\n" +
                    "                \"fact-13\",\n" +
                    "                \"fact-15\",\n" +
                    "                \"fact-16\",\n" +
                    "                \"fact-17\"\n" +
                    "            ],\n" +
                    "            \"article\": {\n" +
                    "                \"id\": 1665,\n" +
                    "                \"content\": \"第二条　交通肇事具有下列情形之一的，处三年以下有期徒刑或者拘役：\\n（一）死亡一人或者重伤三人以上，负事故全部或者主要责任的；\\n（二）死亡三人以上，负事故同等责任的；\\n（三）造成公共财产或者他人财产直接损失，负事故全部或者主要责任，无能力赔偿数额在三十万元以上的。\\n交通肇事致一人以上重伤，负事故全部或者主要责任，并具有下列情形之一的，以交通肇事罪定罪处罚：\\n（一）酒后、吸食毒品后驾驶机动车辆的；\\n（二）无驾驶资格驾驶机动车辆的；\\n（三）明知是安全装置不全或者安全机件失灵的机动车辆而驾驶的；\\n（四）明知是无牌证或者已报废的机动车辆而驾驶的；\\n（五）严重超载驾驶的；\\n（六）为逃避法律追究逃离事故现场的。\",\n" +
                    "                \"number\": \"第二条\",\n" +
                    "                \"crime\": \"\",\n" +
                    "                \"catalogId\": 222,\n" +
                    "                \"law\": \"最高人民法院关于审理交通肇事刑事案件具体应用法律若干问题的解释\"\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-51\",\n" +
                    "            \"content\": \"、\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"law-6\",\n" +
                    "            \"content\": \"第七条\",\n" +
                    "            \"type\": 2,\n" +
                    "            \"relations\": [],\n" +
                    "            \"article\": {\n" +
                    "                \"id\": 1670,\n" +
                    "                \"content\": \"第七条　单位主管人员、机动车辆所有人或者机动车辆承包人指使、强令他人违章驾驶造成重大交通事故，具有本解释第二条规定情形之一的，以交通肇事罪定罪处罚。\",\n" +
                    "                \"number\": \"第七条\",\n" +
                    "                \"crime\": \"\",\n" +
                    "                \"catalogId\": 222,\n" +
                    "                \"law\": \"最高人民法院关于审理交通肇事刑事案件具体应用法律若干问题的解释\"\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-52\",\n" +
                    "            \"content\": \"之规定，判决如下：\\n\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"conclusion-1\",\n" +
                    "            \"content\": \"被告人苗某甲犯交通肇事罪，判处有期徒刑三年，缓刑三年。（缓刑考验期限从判决确定之日起计算）\\n被告人段某甲犯交通肇事罪，判处有期徒刑二年，缓刑二年。（缓刑考验期限从判决确定之日起计算）\",\n" +
                    "            \"type\": 3,\n" +
                    "            \"relations\": [\n" +
                    "                \"law-1\",\n" +
                    "                \"law-5\",\n" +
                    "                \"law-2\",\n" +
                    "                \"law-3\"\n" +
                    "            ],\n" +
                    "            \"count\": 1\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": \"text-53\",\n" +
                    "            \"content\": \"\\n如不服本判决，可在接到判决书的第二日起十日内，通过本院或者直接向邯郸市中级人民法院提出上诉。书面上诉的，应当提交上诉状正本一份，副本三份。\",\n" +
                    "            \"type\": 0,\n" +
                    "            \"relations\": []\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
            JSONObject tmp = JSONObject.parseObject(strJson);

//            return editorService.check(text, crime);
            ResponseVO temp = new ResponseVO();
            temp.setSuccess(true);
            temp.setMessage(null);
            temp.setContent(tmp);
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(CHECK_ERROR);
        }
    }
}
