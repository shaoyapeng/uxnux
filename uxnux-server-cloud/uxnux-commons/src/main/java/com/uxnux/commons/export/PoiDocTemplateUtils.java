package com.uxnux.commons.export;

import com.deepoove.poi.XWPFTemplate;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 使用poi-tl的文档导出，支持文本、图片、表格、列表、文档等导出
 * @Author: 10785
 * @Date: 2019/12/2 20:33
 * @Version: 1.0
 */
public class PoiDocTemplateUtils {

    private static final String DEFAULT_TEMPLATE_PATH = "templates/";


    private static String templatePath;

    /**
     * 设置模板路径
     * @param templatePath 模板路径
     */
    public static void setTemplatePath(String templatePath) {
        PoiDocTemplateUtils.templatePath = templatePath;
    }

    /**
     * 获取模板路径
     * @return templatePath
     */
    public static String getTemplatePath() {
       return PoiDocTemplateUtils.templatePath;
    }

    /**
     * 生成文档到固定路径
     * @param templateName 模板名称
     * @param model 数据
     * @param savePath 保存路径
     * @throws IOException
     */
    public static void generate(String templateName, Map model, String savePath) throws IOException {
        File file = getTemplatePath(templateName);
        XWPFTemplate template = createResultByTemplate(file, model);
        save(template, savePath);
    }

    /**
     * 生成文档并用response输出
     * @param templateName 模板名称
     * @param model 数据
     * @param response 响应
     * @param fileName 输出文件名称
     * @throws IOException
     */
    public static void generate(String templateName, Map model, HttpServletResponse response, String fileName)
            throws IOException {
        File file = getTemplatePath(templateName);
        XWPFTemplate template = createResultByTemplate(file, model);
        save(template, response, fileName);
    }

    /**
     * 生成文档到固定路径
     * @param file
     * @param model
     * @param savePath
     * @throws IOException
     */
    public static void generate(File file, Map model, String savePath) throws IOException {
        XWPFTemplate template = createResultByTemplate(file, model);
        save(template, savePath);
    }

    /**
     * 生成文档并用response输出
     * @param file 模板文件
     * @param model 数据
     * @param response 响应
     * @param fileName 输出文件名称
     * @throws IOException
     */
    public static void generate(File file, Map model, HttpServletResponse response, String fileName)
            throws IOException {
        XWPFTemplate template = createResultByTemplate(file, model);
        save(template, response, fileName);
    }


    /**
     * 根据模板名称获取模板文件
     * @param templateName
     * @return
     * @throws IOException
     */
    public static File getTemplatePath(String templateName) throws IOException {
        templatePath = templatePath == null || templatePath == "" ? DEFAULT_TEMPLATE_PATH : templatePath;
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + templatePath + templateName);
        return file;
    }

    /**
     * 根据模板文件和数据生成XWPFTemplate
     * @param file
     * @param model
     * @return
     */
    public static XWPFTemplate createResultByTemplate(File file, Map model) {
        XWPFTemplate template = XWPFTemplate.compile(file).render(model);
        return template;
    }

    /**
     * 保存
     * @param template 模板
     * @param savePath 保存路径
     * @throws IOException
     */
    private static void save(XWPFTemplate template, String savePath) throws IOException {
        OutputStream os = new FileOutputStream(savePath);
        template.write(os);
        os.flush();
        os.close();
        template.close();
    }

    private static void save(XWPFTemplate template, HttpServletResponse response, String fileName)
            throws IOException {
        // 设置相应格式
        response.setContentType("application/x-msdownload");
        // 设置相应头
        response.setHeader("Content-Disposition","attachment;filename=" + new String
                (fileName.getBytes("UTF-8"),"iso-8859-1"));
        OutputStream os = response.getOutputStream();
        template.write(os);
        os.flush();
        os.close();
        template.close();
    }

    private static InputStream getInputStreamToTemplate(XWPFTemplate template, String fileName) {
        InputStream is = null;
        ByteArrayOutputStream os = null;
        try {
            List<Byte> byteList = new ArrayList<Byte>();
            os = new ByteArrayOutputStream();
            template.write(os);
            is = new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     *
     * @param fileName
     * @param templateName
     * @param model
     * @param response
     * @param zipFileName
     * @throws IOException
     */
    public static void generateZip(String fileName, String templateName, Map model, HttpServletResponse response, String zipFileName)
            throws IOException {
        File file = getTemplatePath(templateName);
        XWPFTemplate template = createResultByTemplate(file, model);
        InputStream is = getInputStreamToTemplate(template, zipFileName);
        ZipMultiFileUtils.zipFile(zipFileName, response, is, zipFileName);
    }

    /**
     * 多文件导出压缩包
     * @param templateName
     * @param models
     * @param response
     * @param zipFileName
     * @throws IOException
     */
    public static void generateZip(String templateName, List<Map<String, Object>> models, HttpServletResponse response, String zipFileName)
            throws IOException {
        List<InputStream> isList = new ArrayList<>();
        List<String> fileNames = new ArrayList<>();
        for (Map map: models) {
            Map model = (Map) map.get("model");
            String fileName = (String) map.get("fileName");
            File file = getTemplatePath(templateName);
            XWPFTemplate template = createResultByTemplate(file, model);
            InputStream is = getInputStreamToTemplate(template, zipFileName);
            isList.add(is);
            fileNames.add(fileName);
        }
        ZipMultiFileUtils.zipFiles(isList, response, fileNames, zipFileName);

    }
}
