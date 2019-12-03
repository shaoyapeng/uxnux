package com.uxnux.export;

import com.uxnux.export.utils.PoiDocUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class UxnuxExportApplicationTests {

    @Test
    void contextLoads() {
        String templateName = "test.docx";
        String savePath = "D:/test.doc";
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", "小仙女");
        model.put("end", "哈哈");
        PoiDocUtils.compile(templateName, model, savePath);
    }

}
