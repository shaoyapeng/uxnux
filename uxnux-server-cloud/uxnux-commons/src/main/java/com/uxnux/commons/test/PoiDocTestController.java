package com.uxnux.commons.test;

import com.deepoove.poi.data.PictureRenderData;
import com.uxnux.commons.export.PoiDocTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * poi-tl导出测试
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
        // 图片
        map.put("management_autograph", new PictureRenderData(100, 30, "D:\\autograph.png"));
        map.put("undertake_autograph", new PictureRenderData(100, 30, "D:\\autograph.png"));
        map.put("proposal_autograph", new PictureRenderData(100, 30, "D:\\autograph.png"));
        String templateName = "红古区人大代表建议承办情况征询意见表.docx";
        String zipFileName = "poi测试文档导出.zip";
        String fileName = "poi测试文档导出.doc";
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map model = new HashMap();
        model.put("model", map);
        // 文件夹 test 下面的文件是test/poi测试文档导出1.doc
        model.put("fileName", "test/poi测试文档导出1.doc");
        mapList.add(model);
        model = new HashMap();
        model.put("model", map);
        // 没有文件夹，直接压缩
        model.put("fileName", "poi测试文档导出2.doc");
        mapList.add(model);
        try {
            // 导出多个并进行压缩
            PoiDocTemplateUtils.generateZip(templateName, mapList, response, zipFileName);
            // 导出一个并进行压缩
            // PoiDocTemplateUtils.generateZip(fileName, templateName, map, response, zipFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
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
        // 图片
        map.put("management_autograph", new PictureRenderData(100, 30, "D:\\autograph.png"));
        map.put("undertake_autograph", new PictureRenderData(100, 30, "D:\\autograph.png"));
        map.put("proposal_autograph", new PictureRenderData(100, 30, "D:\\autograph.png"));
        String templateName = "红古区人大代表建议承办情况征询意见表.docx";
        String zipFileName = "poi测试文档导出.zip";
        String fileName = "poi测试文档导出.doc";
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map model = new HashMap();
        model.put(PoiDocTemplateUtils.MODEL_KEY, map);
        // 文件夹 test 下面的文件是test/poi测试文档导出1.doc
        model.put(PoiDocTemplateUtils.FILE_NAME_KYE, "poi测试文档导出1.doc");
        mapList.add(model);
        model = new HashMap();
        model.put(PoiDocTemplateUtils.MODEL_KEY, map);
        // 没有文件夹，直接压缩
        model.put(PoiDocTemplateUtils.FILE_NAME_KYE, "poi测试文档导出2.doc");
        mapList.add(model);
        try {
            // 导出多个并进行压缩
            PoiDocTemplateUtils.generateZip(templateName, mapList, "D:/", zipFileName);
            // 导出一个并进行压缩
            // PoiDocTemplateUtils.generateZip(fileName, templateName, map, response, zipFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
