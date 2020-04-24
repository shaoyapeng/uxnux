package com.uxnux.activiti.model;

import lombok.Data;

import java.util.List;

/**
 * @Author: 10785
 * @Date: 2019/12/26 17:48
 * @Version: 1.0
 */
@Data
public class ProcessImgFormat {

    private String imgType;

    private String activityFontName;

    private String labelFontName;

    private String annotationFontName;

    private ClassLoader customClassLoader;

    private double scaleFactor;

    private List<String> highLightedFlows;
}
