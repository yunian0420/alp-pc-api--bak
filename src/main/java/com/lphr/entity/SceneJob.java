package com.lphr.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 场景任务实体，对应表 t_scene_job
 * 作者：limi
 * 时间：2026-09-16
 */
@Data
@Table(name = "t_scene_job")
public class SceneJob {

    /**
     * 主键ID
     * 作者：limi
     * 时间：2026-09-16
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 接口返回的任务ID
     * 作者：limi
     * 时间：2026-09-16
     */
    private String jobId;

    /**
     * 场景编码，如蓝词 blue_word
     * 作者：limi
     * 时间：2026-09-16
     */
    private String sceneCode;

    /**
     * 主工单ID
     * 作者：limi
     * 时间：2026-09-16
     */
    private String taskId;

    /**
     * 平台，默认抖音 douyin
     * 作者：limi
     * 时间：2026-09-16
     */
    private String platform;

    /**
     * 任务类型：url-链接，keyword-关键词
     * 作者：limi
     * 时间：2026-09-16
     */
    private String type;

    /**
     * 链接地址，蓝词等场景且 type=url 时记录
     * 作者：limi
     * 时间：2026-09-16
     */
    private String url;

    /**
     * 关键词，type=keyword 时记录
     * 作者：limi
     * 时间：2026-09-16
     */
    private String keyword;

    /**
     * 任务状态：submitted-提交，running-运行，timeout-超时，success-成功，fail-失败
     * 作者：limi
     * 时间：2026-09-16
     */
    private String status;

    /**
     * 创建人
     * 作者：limi
     * 时间：2026-09-16
     */
    private String createBy;

    /**
     * 更新人
     * 作者：limi
     * 时间：2026-09-16
     */
    private String updateBy;

    /**
     * 删除标识：0未删除 1已删除
     * 作者：limi
     * 时间：2026-09-16
     */
    private Integer deleted;

    /**
     * 创建时间
     * 作者：limi
     * 时间：2026-09-16
     */
    private Date createTime;

    /**
     * 更新时间，随记录变更自动更新
     * 作者：limi
     * 时间：2026-09-16
     */
    private Date updateTime;
}
