package com.uxnux.commons.export;

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

    /**
     * 初始化zip文件
     * @param fileName 可以是文件名称，也可以是文件路径
     * @return 返回 zipEntry 实例对象
     */
    private static ZipEntry initZipEntry(String fileName) {
        ZipEntry zipEntry = new ZipEntry(fileName);
        return zipEntry;
    }

    /**
     * 初始化ZipOutputStream流
     * @param os 需要输出的流
     * @return 返回zipOutputStream对象
     */
    private static ZipOutputStream initZipOutputStream(OutputStream os) {
        ZipOutputStream zipOutputStream = null;
        zipOutputStream = new ZipOutputStream(os);
        return zipOutputStream;
    }

    /**
     * zipOutputStream添加zipEntry实例，将多个zipEntry实例打包成一个zip
     * @param zipOutputStream
     * @param zipEntry
     * @return
     */
    private static ZipOutputStream putEntry(ZipOutputStream zipOutputStream, ZipEntry zipEntry) {
        try {
            zipOutputStream.putNextEntry(zipEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zipOutputStream;
    }


    /**
     * 把InputStream的内容写到ZipOutputStream
     * @param is InputStream
     * @param zipOutputStream zipOutputStream
     */
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

    /**
     * 将单个输入流打成zip压缩
     * @param fileName 压缩文件的名称
     * @param zipOutputStream 初始化指挥的zip流
     * @param is InputStream
     */
    private static void zipFile(String fileName, ZipOutputStream zipOutputStream, InputStream is) {
        ZipEntry zipEntry = initZipEntry(fileName);
        zipOutputStream = putEntry(zipOutputStream, zipEntry);
        writeToZipOutputStream(is, zipOutputStream);
    }

    /**
     * 将多个文件进行压缩并下周
     * @param osList 多个文件流的集合
     * @param os 下载流
     * @param fileNames 多个文件名集合
     */
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
            close(zipOutputStream);
        }

    }

    /**
     * 单个文件压缩下载
     * @param fileName 文件名称
     * @param os 下载的流
     * @param is 文件流
     */
    private static void zipFile(String fileName, OutputStream os, InputStream is) {
        ZipEntry zipEntry = initZipEntry(fileName);
        ZipOutputStream zipOutputStream = initZipOutputStream(os);
        zipOutputStream = putEntry(zipOutputStream, zipEntry);
        writeToZipOutputStream(is, zipOutputStream);
        if (zipOutputStream != null) {
            close(zipOutputStream);
        }
    }

    /**
     * 关闭 zipOutputStream 流
     * @param zipOutputStream 流
     */
    private static void close(ZipOutputStream zipOutputStream) {
        try {
            zipOutputStream.close();
        } catch (IOException e) {
            log.error("----------zipOutputStream流关闭错误");
            e.printStackTrace();
        }
    }

    /**
     * 下载单个文件zip
     * @param fileName 文件名称
     * @param response 响应
     * @param is 需要下载文件的流
     * @param zipFileName 压缩文件的名称
     */

    public static void zipFile(String fileName, HttpServletResponse response, InputStream is, String zipFileName) {
        save(fileName, response, is, zipFileName);
    }

    /**
     * 多个文件下载
     * @param files 文件数组
     * @param os 输出流
     */
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
            close(zipOutputStream);
        }
    }

    /**
     * 做个文件下载
     * @param files 文件数组
     * @param savePath 保存地址 /xxx/xxx.zip
     */
    public static void zipFiles(File[] files, String savePath) {
        try {
            OutputStream os = new FileOutputStream(savePath);
            zipFiles(files, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多文件下载
     * @param files 文件数组
     * @param response 响应
     * @param zipFileName 输出zip格式名称 xxx.zip
     */
    public static void zipFiles(File[] files, HttpServletResponse response, String zipFileName) {
        OutputStream os = responseToOutputStream(response, zipFileName);
        zipFiles(files, os);
    }

    /**
     * 多文件下载
     * @param osList 文件流集合
     * @param fileNames 文件名称
     * @param savePath 保存地址  xxx/xxx.zip
     */
    public static void zipFiles(List<InputStream> osList, List<String> fileNames, String savePath) {
        try {
            OutputStream os = new FileOutputStream(savePath);
            zipFiles(osList, os, fileNames);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多文件下载
     * @param osList 文件流集合
     * @param os 流
     * @param fileNames 需要打包的多文件名称集合
     * @param zipFileName 最后下载的zip文件名称xxx.zip
     */
    public static void zipFiles(List<InputStream> osList, OutputStream os, List<String> fileNames, String zipFileName) {
        zipFiles(osList, os, fileNames);
    }

    /**
     * 保存文件
     * @param is 文件流
     * @param savePath 保存的路径
     * @param zipFileName 文件名称
     * @throws IOException
     */
    private static void save(InputStream is, String savePath, String zipFileName) throws IOException {
        OutputStream os = new FileOutputStream(savePath);
        zipFile(zipFileName, os, is);
    }

    /**
     * 保存文件
     * @param fileName 文件名称
     * @param response response
     * @param is 输入流
     * @param zipFileName 自拍名称
     */
    private static void save(String fileName, HttpServletResponse response, InputStream is, String zipFileName) {
        OutputStream os = responseToOutputStream(response, zipFileName);
        zipFile(fileName, os, is);
    }

    /**
     * 从response中获取输出流，并进行格式设置和文件名称设置
     * @param response 响应
     * @param zipFileName 输出文件名称 xxx.zip
     * @return
     */
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
