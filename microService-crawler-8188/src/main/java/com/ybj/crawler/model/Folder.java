package com.ybj.crawler.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author ybj
 * @since 2020-03-02
 */
@Data
@Accessors(chain = true)
public class Folder implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 文件夹ID
     */
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 文件夹名
     */
    @TableField("folderName")
    private String folderName;

    /**
     * 父文件夹ID
     */
    private String parentId;

    private List<Folder> treeNode;


}
