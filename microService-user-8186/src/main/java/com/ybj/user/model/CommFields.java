package com.ybj.user.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ybj.api.constant.ConfigConstants;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通用属性
 * @author caicai.gao
 */
@Data
public class CommFields implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 创建者
     */
    @TableField(value = "CREATED_BY")
    private String createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = ConfigConstants.DATE_FORMAT)
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @TableField(value = "UPDATED_BY")
    private String updatedBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = ConfigConstants.DATE_FORMAT)
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
