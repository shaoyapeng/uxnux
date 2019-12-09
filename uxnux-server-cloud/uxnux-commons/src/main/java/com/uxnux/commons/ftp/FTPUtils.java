package com.uxnux.commons.ftp;

import com.uxnux.commons.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 10785
 * @Date: 2019/12/5 12:11
 * @Version: 1.0
 */
@Slf4j
@Component
public class FTPUtils {

    private static FTPProperties properties;

    /**
     * 问题：静态变量无法直接使用@Autowired 进行注入，但是可以通过 set方法进行注入。注意：set方法不能是静态的。
     * 同时FTPProperties必须加@Component注解进行扫描
     * 因为静态变量是类的属性，所以在类初始化的时候已经进行加载，所以在bean注入之前就已经加载，导致注入失败
     * @param properties properties
     */
    @Autowired
    private void setProperties(FTPProperties properties) {
        FTPUtils.properties = properties;
    }

    /**
     * 初始化ftp链接
     * @return
     */
    private static FTPClient initFTPClient() {
        FTPClient client = null;
        try {
            client = new FTPClient();
            client.connect(properties.getHost(), properties.getPort());
            log.info("Connected to " + properties.getHost() + client.getReplyString());
            client.login(properties.getUsername(), properties.getPassword());
            if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
                log.error(FTPStatusEnum.CONNECT_FAIL.getMsg());
                client.disconnect();
                return null;
            }
            log.info(FTPStatusEnum.CONNECT_SUCCESS.getMsg());
        } catch (IOException e) {
            log.error(FTPStatusEnum.ERROR.getMsg());
            e.printStackTrace();
        }
        return client;
    }

    /**
     * ftp 文件上传
     * @param fileName
     * @param is
     * @param pathName
     * @return
     */
    public static FTPStatusEnum upload(String fileName, InputStream is, String pathName) {
        FTPClient client = null;
        try {
            client = initFTPClient();
            if (client == null) {
                return FTPStatusEnum.CONNECT_FAIL;
            }
            // BINARY_FILE_TYPE 用于指示正​​在传输的文件的常量应被视为二进制图像，即不应执行任何翻译。以结尾的所有常量均FILE_TYPE用于指示文件类型。
            client.setFileType(FTPClient.BINARY_FILE_TYPE);
            createDirectory(client, pathName);
            Boolean storeState = client.storeFile(fileName, is);
            is.close();
            client.logout();
            if (storeState) {
                return FTPStatusEnum.OPERATION_SUCCESS;
            }
            return FTPStatusEnum.OPERATION_FAIL;
        } catch (IOException e) {
            e.printStackTrace();
            return FTPStatusEnum.OPERATION_ERROR;
        } finally {
            try {
                if (client != null && client.isConnected()) {
                    client.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据当前天创建文件夹存放文件
     * @param fileName
     * @param is
     * @return
     */
    public static FTPStatusEnum upload(String fileName, InputStream is) {
        String pathName = initPathName();
        FTPStatusEnum ftpStatusEnum = upload(fileName, is, pathName);
        return ftpStatusEnum;
    }

    /**
     * 用当前时间去初始化文件路径
     * @return
     */
    private static String initPathName() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(properties.getDefaultPathNameFormat());
        String pathName = format.format(date);
        return pathName;
    }

    /**
     * 创建文件夹 -- 单个文件夹
     * @param client
     * @param pathName
     * @return
     */
    private static Boolean createDirectory(FTPClient client, String pathName){
        Boolean flag = false;
        try {
            // 规定默认路径
            client.changeWorkingDirectory(properties.getBaseUrl());
            // 创建路径
            flag = client.makeDirectory(pathName);
            client.changeWorkingDirectory(pathName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 从ftp获得文件流
     * @param pathName
     * @param fileName
     * @return
     */
    private static InputStream loadFile(String pathName, String fileName) {
        InputStream is = null;
        FTPClient client = null;
        try {
            client = initFTPClient();
            if (client == null) {
                return null;
            }
            client.changeWorkingDirectory(properties.getBaseUrl());
            if (!StringUtils.isEmpty(pathName)) {
                client.changeWorkingDirectory(pathName);
            }
            is = client.retrieveFileStream(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null && client.isConnected()) {
                    client.logout();
                    client.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return is;
    }

    /**
     * 下载文件到固定路径
     * @param ftpPathName
     * @param ftpFileName
     * @param savePath
     * @param saveName
     * @return
     */
    public static FTPStatusEnum downFile(String ftpPathName, String ftpFileName, String savePath, String saveName) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = loadFile(ftpPathName, ftpFileName);
            if (is == null) {
                return FTPStatusEnum.OPERATION_FAIL;
            }
            byte[] fileBytes = new byte[is.available()];
            os = new FileOutputStream(savePath + File.pathSeparator + saveName);
            while (is.read(fileBytes, 0, fileBytes.length) != -1) {
                os.write(fileBytes, 0, fileBytes.length);
            }
            return FTPStatusEnum.OPERATION_SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            return FTPStatusEnum.OPERATION_ERROR;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过http下载文件
     * @param ftpPathName
     * @param ftpFileName
     * @param response
     * @param outFileName
     * @return
     */
    public static FTPStatusEnum downFile(String ftpPathName, String ftpFileName, HttpServletResponse response, String outFileName) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = loadFile(ftpPathName, ftpFileName);
            if (is == null) {
                return FTPStatusEnum.OPERATION_FAIL;
            }
            byte[] fileBytes = new byte[1024];
            os = response.getOutputStream();
            // 设置相应格式
            response.setContentType("application/x-msdownload");
            // 设置相应头
            response.setHeader("Content-Disposition","attachment;filename=" +
                    new String(outFileName.getBytes("UTF-8"),"iso-8859-1"));
            while (is.read(fileBytes, 0, fileBytes.length) != -1) {
                os.write(fileBytes, 0, fileBytes.length);
            }
            is.close();
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FTPStatusEnum.OPERATION_SUCCESS;
    }

    public static FTPStatusEnum delFile() {

        return FTPStatusEnum.OPERATION_SUCCESS;
    }
}
