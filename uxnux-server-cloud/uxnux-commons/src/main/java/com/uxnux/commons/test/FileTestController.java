package com.uxnux.commons.test;

import com.uxnux.commons.ftp.FTPStatusEnum;
import com.uxnux.commons.ftp.FTPUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: 10785
 * @Date: 2019/12/5 19:47
 * @Version: 1.0
 */
@RestController
public class FileTestController {

    @RequestMapping("/testFileUpload")
    String testFileUpload() {
        FTPStatusEnum ftpStatusEnum = null;
        try {
            InputStream is = new FileInputStream("D:\\logo.jpg");
            ftpStatusEnum = FTPUtils.upload("test1.jpg", is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpStatusEnum.getMsg();
    }

    @RequestMapping("/testDownFile")
    void testDownFile(HttpServletResponse response) {
        FTPUtils.downFile("2019-12-05", "test.jpg", response, "测试.jpg");
    }

    @RequestMapping("/testDownFileToLocation")
    void testDownFileToLocation() {

    }

}
