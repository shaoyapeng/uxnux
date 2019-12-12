package com.uxnux.commons.export;

import com.deepoove.poi.XWPFTemplate;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author: 10785
 * @Date: 2019/12/12 13:02
 * @Version: 1.0
 */
@Slf4j
public class ZipMultiFileUtils {


    private static ZipEntry initZipEntry(String fileName) {
        ZipEntry zipEntry = new ZipEntry(fileName);
        return zipEntry;
    }

    private static ZipOutputStream initZipOutputStream(OutputStream os) {
        ZipOutputStream zipOutputStream = null;
        zipOutputStream = new ZipOutputStream(os);
        return zipOutputStream;
    }

    private static ZipOutputStream putEntry(ZipOutputStream zipOutputStream, ZipEntry zipEntry) {
        try {
            zipOutputStream.putNextEntry(zipEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zipOutputStream;
    }


    private static void writeToZipOutputStream(InputStream is, ZipOutputStream zipOutputStream) {
        try {
            byte[] fileBytes = new byte[1024];
            while (is.read(fileBytes, 0, fileBytes.length) != -1) {
                zipOutputStream.write(fileBytes, 0, fileBytes.length);
            }
        } catch (IOException e) {
            log.error("----------将输入流写入ZipOutputStream失败");
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                log.error("----------输入流关闭错误");
                e.printStackTrace();
            }
        }
    }

    private static void zipFile(String fileName, ZipOutputStream zipOutputStream, InputStream is) {
        ZipEntry zipEntry = initZipEntry(fileName);
        zipOutputStream = putEntry(zipOutputStream, zipEntry);
        writeToZipOutputStream(is, zipOutputStream);
    }

    private static void zipFiles(List<InputStream> osList, OutputStream os, List<String> fileNames) {
        ZipOutputStream zipOutputStream = initZipOutputStream(os);
        if (fileNames.size() <= osList.size()) {
            for (int i = 0;i < fileNames.size(); i++) {
                InputStream is = osList.get(i);
                String fileName = fileNames.get(i);
                zipFile(fileName, zipOutputStream, is);
            }
        } else {
            for (int i = 0;i < osList.size(); i++) {
                InputStream is = osList.get(i);
                String fileName = fileNames.get(i);
                zipFile(fileName, zipOutputStream, is);
            }
        }
        if (zipOutputStream != null) {
            try {
                zipOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void zipFile(String fileName, OutputStream os, InputStream is) {
        ZipEntry zipEntry = initZipEntry(fileName);
        ZipOutputStream zipOutputStream = initZipOutputStream(os);
        zipOutputStream = putEntry(zipOutputStream, zipEntry);
        writeToZipOutputStream(is, zipOutputStream);
        if (zipOutputStream != null) {
            try {
                zipOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static void zipFile(String fileName, HttpServletResponse response, InputStream is, String zipFileName) {
        save(fileName, response, is, zipFileName);
    }

    private static void zipFiles(File[] files, OutputStream os) {
        ZipOutputStream zipOutputStream = initZipOutputStream(os);
        try {
            for (File file: files) {
                String fileName = file.getName();
                InputStream is = new FileInputStream(file);
                zipFile(fileName, zipOutputStream, is);
            }
        } catch (IOException e) {
            log.error("----------从文件中读取流异常");
            e.printStackTrace();
        }
        if (zipOutputStream != null) {
            try {
                zipOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void zipFiles(File[] files, String savePath) {
        try {
            OutputStream os = new FileOutputStream(savePath);
            zipFiles(files, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(File[] files, HttpServletResponse response, String zipFileName) {
        OutputStream os = responseToOutputStream(response, zipFileName);
        zipFiles(files, os);
    }

    public static void zipFiles(List<InputStream> osList, List<String> fileNames, String savePath) {
        try {
            OutputStream os = new FileOutputStream(savePath);
            zipFiles(osList, os, fileNames);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(List<InputStream> osList, HttpServletResponse response, List<String> fileNames, String zipFileName) {
        OutputStream os = responseToOutputStream(response, zipFileName);
        zipFiles(osList, os, fileNames);
    }



    private static void save(InputStream is, String savePath, String zipFileName) throws IOException {
        OutputStream os = new FileOutputStream(savePath);
        zipFile(zipFileName, os, is);
    }

    private static void save(String fileName, HttpServletResponse response, InputStream is, String zipFileName) {
        OutputStream os = responseToOutputStream(response, zipFileName);
        zipFile(fileName, os, is);
    }

    private static OutputStream responseToOutputStream(HttpServletResponse response, String zipFileName) {
        OutputStream os = null;
        try {
            // 设置相应格式
            response.setContentType("application/x-msdownload");
            // 设置相应头
            response.setHeader("Content-Disposition","attachment;filename=" + new String
                    (zipFileName.getBytes("UTF-8"),"iso-8859-1"));
            os = response.getOutputStream();
        } catch (IOException e) {
            log.error("----------response读取流异常");
        }
        return os;
    }
}
