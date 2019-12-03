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
        map.put("name", "邵亚鹏");
        map.put("sex", "男");
        map.put("img", new PictureRenderData(100, 100, "D:\\logo.jpg"));
        String templateName = "test.docx";
        String fileName = "poi测试文档导出.doc";
        try {
            PoiDocTemplateUtils.generate(templateName, map, response, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
