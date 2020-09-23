/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.spring.sample.model;

/**
 * @author study
 * @version : ServiceEnum.java, v 0.1 2020年09月24日 0:12 study Exp $
 */
public enum ServiceEnum {

    TR("TR","TR","TR服务","TR服务"),
    REST("REST","REST","REST服务","REST服务"),

    ;

    private String code;
    private String englishName;
    private String chineseName;
    private String description;

    ServiceEnum(String code, String englishName, String chineseName, String description) {
        this.code = code;
        this.englishName = englishName;
        this.chineseName = chineseName;
        this.description = description;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>englishName</tt>.
     *
     * @return property value of englishName
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * Getter method for property <tt>chineseName</tt>.
     *
     * @return property value of chineseName
     */
    public String getChineseName() {
        return chineseName;
    }

    /**
     * Getter method for property <tt>description</tt>.
     *
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }
}