package com.lphr.enums;

/**
 * 平台枚举
 * 作者：limi
 * 时间：2026-09-16
 */
public enum PlatformEnum {

    /** 抖音，表字段默认值 */
    DOUYIN("douyin", "抖音");

    /**
     * 平台编码
     * 作者：limi
     * 时间：2026-09-16
     */
    private String code;

    /**
     * 平台名称
     * 作者：limi
     * 时间：2026-09-16
     */
    private String name;

    /**
     * 构造平台枚举
     * 作者：limi
     * 时间：2026-09-16
     *
     * @param code 平台编码
     * @param name 平台名称
     */
    PlatformEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取平台编码
     * 作者：limi
     * 时间：2026-09-16
     *
     * @return 平台编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置平台编码
     * 作者：limi
     * 时间：2026-09-16
     *
     * @param code 平台编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取平台名称
     * 作者：limi
     * 时间：2026-09-16
     *
     * @return 平台名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置平台名称
     * 作者：limi
     * 时间：2026-09-16
     *
     * @param name 平台名称
     */
    public void setName(String name) {
        this.name = name;
    }
}
