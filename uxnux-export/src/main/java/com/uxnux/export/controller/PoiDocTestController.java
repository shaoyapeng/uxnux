package com.uxnux.export.controller;

import com.deepoove.poi.data.PictureRenderData;
import com.uxnux.export.utils.PoiDocTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 10785
 * @Date: 2019/12/3 12:41
 * @Version: 1.0
 */
@RestController
public class PoiDocTestController {

    @RequestMapping(value = "/poiDocTest")
    public void poiDocTest(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("header", "红古区人大代表建议承办情况征询意见表");
        map.put("title", "红古区人大代表建议承办情况");
        map.put("number", "HG-201901287");
        map.put("deputy_name", "邵亚鹏");
        map.put("deputy_company", "中国电信中电万维公司");
        map.put("undertake_idea", "测试测试测试测试测试测试测试测试测试测试测试测试测" +
                "试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测" +
                "试测试测试测试测试测试测试测试测试测试测试");
        map.put("management_idea", "区政府分管领导意见区政府分管领导意见区政府分管领导" +
                "意见区政府分管领导意见区政府分管领导意见区政府分" +
                "管领导意见区政府分管领导意见区政府分管领导意见");
        map.put("proposal_require", "建议要求建议要求建议要求建议要求建议要求建议要求建" +
                "议要求建议要求建议要求建议要求建议要求建议要求建议要求建议要求建议要求建议要" +
                "求建议要求建议要求建议要求建议要求建议要求建议要求建议要" +
                "求建议要求建议要求建议要求建议要求建议要求");
        map.put("satisfied", "√");
        map.put("management_autograph", new PictureRenderData(100, 30, "D:\\autograph.png"));
        map.put("undertake_autograph", new PictureRenderData(100, 30, "D:\\autograph.png"));
        map.put("proposal_autograph", new PictureRenderData(100, 30, "D:\\autograph.png"));
        String templateName = "红古区人大代表建议承办情况征询意见表.docx";
        String fileName = "poi测试文档导出.doc";
        try {
            PoiDocTemplateUtils.generate(templateName, map, response, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
