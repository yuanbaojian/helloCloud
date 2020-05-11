package com.ybj.auth.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author ybj
 * @since 2020-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("TBL_MENU")
public class Menu implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 菜单/按钮ID
     */
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 上级菜单ID
     */
    private String parentId;

    /**
     * 菜单/按钮名称
     */
    private String name;

    /**
     * 菜单URL
     */
    private String path;


    /**
     * 图标
     */
    private String icon;



    private List<Menu> treeNode;


}
