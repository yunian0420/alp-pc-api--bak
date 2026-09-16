package com.lphr.enums;

/**
 * 场景任务状态枚举
 * 作者：limi
 * 时间：2026-09-16
 */
public enum JobStatusEnum {

    /** 提交 */
    SUBMITTED("submitted", "提交"),

    /** 运行 */
    RUNNING("running", "运行"),

    /** 超时 */
    TIMEOUT("timeout", "超时"),

    /** 成功 */
    SUCCESS("success", "成功"),

    /** 失败 */
    FAIL("fail", "失败");

    /**
     * 状态编码
     * 作者：limi
     * 时间：2026-09-16
     */
    private String code;

    /**
     * 状态名称
     * 作者：limi
     * 时间：2026-09-16
     */
    private String name;

    /**
     * 构造任务状态枚举
     * 作者：limi
     * 时间：2026-09-16
     *
     * @param code 状态编码
     * @param name 状态名称
     */
    JobStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取状态编码
     * 作者：limi
     * 时间：2026-09-16
     *
     * @return 状态编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置状态编码
     * 作者：limi
     * 时间：2026-09-16
     *
     * @param code 状态编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取状态名称
     * 作者：limi
     * 时间：2026-09-16
     *
     * @return 状态名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置状态名称
     * 作者：limi
     * 时间：2026-09-16
     *
     * @param name 状态名称
     */
    public void setName(String name) {
        this.name = name;
    }
}
