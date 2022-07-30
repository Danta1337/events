package cn.ch1tanda.event.model;

import lombok.Data;

import java.util.Date;

/**
 * 规约数据对象
 * 所有的数据库对象DO均继承于该对象
 * 切数据库表字段必须包含下列字段
 */
@Data
public class ConstraintDO {

    /**
     * 自增主键ID
     */
    private Integer pkId;

    /**
     * 创造时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 是否逻辑删除 0-未删除 1-已删除
     * 默认未删除
     */
    private Integer isDeleted = 0;

    /**
     * 备注
     */
    private String remark = "";
}
