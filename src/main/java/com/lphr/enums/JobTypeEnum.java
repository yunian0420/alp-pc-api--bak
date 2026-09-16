package com.lphr.enums;

/**
 * 场景任务类型枚举
 * 作者：limi
 * 时间：2026-09-16
 */
public enum JobTypeEnum {

    /** 链接类型，蓝词等场景记录 url */
    URL("url", "链接"),

    /** 关键词类型 */
    KEYWORD("keyword", "关键词");

    /**
     * 类型编码
     * 作者：limi
     * 时间：2026-09-16
     */
    private String code;

    /**
     * 类型名称
     * 作者：limi
     * 时间：2026-09-16
     */
    private String name;

    /**
     * 构造任务类型枚举
     * 作者：limi
     * 时间：2026-09-16
     *
     * @param code 类型编码
     * @param name 类型名称
     */
    JobTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取类型编码
     * 作者：limi
     * 时间：2026-09-16
     *
     * @return 类型编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置类型编码
     * 作者：limi
     * 时间：2026-09-16
     *
     * @param code 类型编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取类型名称
     * 作者：limi
     * 时间：2026-09-16
     *
     * @return 类型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类型名称
     * 作者：limi
     * 时间：2026-09-16
     *
     * @param name 类型名称
     */
    public void setName(String name) {
        this.name = name;
    }
}
