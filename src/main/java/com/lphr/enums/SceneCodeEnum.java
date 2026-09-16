package com.lphr.enums;

/**
 * 场景编码枚举
 * 作者：limi
 * 时间：2026-09-16
 */
public enum SceneCodeEnum {

    /** 蓝词场景，任务类型记录 url */
    BLUE_WORD("blue_word", "蓝词");

    /**
     * 场景编码
     * 作者：limi
     * 时间：2026-09-16
     */
    private String code;

    /**
     * 场景名称
     * 作者：limi
     * 时间：2026-09-16
     */
    private String name;

    /**
     * 构造场景编码枚举
     * 作者：limi
     * 时间：2026-09-16
     *
     * @param code 场景编码
     * @param name 场景名称
     */
    SceneCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取场景编码
     * 作者：limi
     * 时间：2026-09-16
     *
     * @return 场景编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置场景编码
     * 作者：limi
     * 时间：2026-09-16
     *
     * @param code 场景编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取场景名称
     * 作者：limi
     * 时间：2026-09-16
     *
     * @return 场景名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置场景名称
     * 作者：limi
     * 时间：2026-09-16
     *
     * @param name 场景名称
     */
    public void setName(String name) {
        this.name = name;
    }
}
