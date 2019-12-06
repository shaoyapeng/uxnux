package com.uxnux.commons.export;

import lombok.Data;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 自己写的word导出类，使用的模板是${}，只能替换普通的文本，基本无用
 * @Author: 10785
 * @Date: 2019/12/2 18:55
 * @Version: 1.0
 */
public class PoiDocUtils {

    private static final String DEFAULT_REGEX = "\\$\\{[^{}]+\\}";

    private static final String LEFT_REGEX = "${";

    private static final String RIGHT_REGEX = "}";

    /**
     * 生成document并保存到固定路径下
     * @param templateName
     * @param model
     * @param savePath
     */
    public static void compile(String templateName, Map<String, Object> model, String savePath) {
        try {
            XWPFDocument saveDocument = compile(templateName, model);
            saveDocument(saveDocument, savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * 生成document并以http相应输出
     * @param templateName
     * @param model
     * @param response
     */
    public static void compile(String templateName, Map<String, Object> model, HttpServletResponse response)  {
        try {
            OutputStream os = response.getOutputStream();
            XWPFDocument saveDocument = compile(templateName, model);
            saveDocument.write(os);
            saveDocument.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据模板和传入的参数生成XWPFDocument对象
     * @param templateName
     * @param model
     */
    public static XWPFDocument compile(String templateName, Map<String, Object> model) throws IOException {
        XWPFDocument document = getTemplateDocument(templateName);
        List<TagInfo> tagInfoList = new ArrayList<TagInfo>();
        Iterator<String> iterator = model.keySet().iterator();
        // 将穿的参数当作TagInfo对象
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = (String)model.get(key);
            TagInfo tagInfo = new PoiDocUtils().getTagInfoInstance(key, value);
            tagInfoList.add(tagInfo);
        }
        replaceAllTag(document, tagInfoList, DEFAULT_REGEX);
        return document;
    }

    /**
     * 把XWPFDocument保存到固定路径
     * @param document
     * @param savePath
     */
    public static void saveDocument(XWPFDocument document, String savePath) {
        OutputStream os;
        try {
            os = new FileOutputStream(savePath);
            if (os != null) {
                document.write(os);
                os.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取模板全路径
     * @param templateName
     * @return
     */
    private static File getTemplatePath(String templateName) throws IOException {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "templates/" + templateName);
        return file;
    }

    /**
     * 过去模板的XWPFDocument对象
     * @param templateName
     * @return
     * @throws IOException
     */
    private static XWPFDocument getTemplateDocument(String templateName) throws IOException {
        File file = getTemplatePath(templateName);
        InputStream ios = new FileInputStream(file);
        XWPFDocument document = new XWPFDocument(ios);
        return document;
    }

    /**
     * 替换标签
     * @param document
     * @param tagInfoList
     * @param regex
     */
    private static void replaceAllTag(XWPFDocument document, List<TagInfo> tagInfoList, String regex) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            replaceInParagraph(tagInfoList, paragraph, regex);
        }
    }

    /**
     * 替换单个标签
     * @param tagInfoList
     * @param paragraph
     * @param regex
     */
    private static void replaceInParagraph(List<TagInfo> tagInfoList, XWPFParagraph paragraph, String regex) {
        if (regex == null || regex == "") {
            regex = DEFAULT_REGEX;
        }
        List<XWPFRun> runs = paragraph.getRuns();
        for (TagInfo tagInfo : tagInfoList) {
            String find = regTeg(tagInfo.tagText);
            String replValue = tagInfo.tagValue;
            TextSegment found = paragraph.searchText(find,
                    new PositionInParagraph());
            if (found != null) {
                // 判断查找内容是否在同一个Run标签中
                if (found.getBeginRun() == found.getEndRun()) {
                    XWPFRun run = runs.get(found.getBeginRun());
                    String runText = run.getText(run.getTextPosition());
                    String replaced = runText.replace(find, replValue);
                    run.setText(replaced, 0);
                } else {
                    // 存在多个Run标签
                    StringBuilder sb = new StringBuilder();
                    for (int runPos = found.getBeginRun(); runPos <= found
                            .getEndRun(); runPos++) {
                        XWPFRun run = runs.get(runPos);
                        sb.append(run.getText((run.getTextPosition())));
                    }
                    String connectedRuns = sb.toString();
                    String replaced = connectedRuns.replace(find, replValue);
                    XWPFRun firstRun = runs.get(found.getBeginRun());
                    firstRun.setText(replaced, 0);
                    // 删除后边的run标签
                    for (int runPos = found.getBeginRun() + 1; runPos <= found
                            .getEndRun(); runPos++) {
                        // 清空其他标签内容
                        XWPFRun partNext = runs.get(runPos);
                        partNext.setText("", 0);
                    }
                }
            }
        }
    }

    /**
     * 获得TagInfo对象
     * @param tagText
     * @param tagValue
     * @return
     */
    private TagInfo getTagInfoInstance(String tagText, String tagValue) {
        return new TagInfo(tagText, tagValue);
    }

    /**
     * 获得需要替换的全部标签的内容
     * @param fund
     * @return
     */
    private static String regTeg(String fund){
        String result = "";
        result = LEFT_REGEX + fund + RIGHT_REGEX;
        return result;
    }

    /**
     * TagInfo 内部类，标签和值一一对应
     */
    @Data
    class TagInfo {
        private String tagText;
        private String tagValue;
        protected TagInfo() {}
        protected TagInfo(String tagText, String tagValue) {
            this.tagText = tagText;
            this.tagValue = tagValue;
        }
    }
    public static void main(String[] args) {
    }
}
