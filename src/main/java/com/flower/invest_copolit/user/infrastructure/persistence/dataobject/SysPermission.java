package com.flower.invest_copolit.user.infrastructure.persistence.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统权限表
 * </p>
 *
 * @author F1ower
 * @since 2026-04-04
 */
@Getter
@Setter
@TableName("sys_permission")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("parent_id")
    private Long parentId;

    @TableField("permission_code")
    private String permissionCode;

    @TableField("permission_name")
    private String permissionName;

    @TableField("permission_type")
    private String permissionType;

    @TableField("resource_path")
    private String resourcePath;

    @TableField("component")
    private String component;

    @TableField("http_method")
    private String httpMethod;

    @TableField("api_path")
    private String apiPath;

    @TableField("icon")
    private String icon;

    @TableField("sort")
    private Integer sort;

    @TableField("visible")
    private Byte visible;

    @TableField("status")
    private String status;

    @TableField("is_builtin")
    private Byte isBuiltin;

    @TableField("remark")
    private String remark;

    @TableField("is_deleted")
    @TableLogic
    private Byte isDeleted;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("creator")
    private String creator;

    @TableField("modifier")
    private String modifier;
}
