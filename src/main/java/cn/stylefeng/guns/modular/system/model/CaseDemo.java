package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wxw
 * @since 2019-05-07
 */
@Data
@TableName("case_demo")
public class CaseDemo extends Model<CaseDemo> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 对应服务表id
     */
    @TableField("service_id")
    private Integer serviceId;
    /**
     * 详情
     */
    private String details;
    /**
     * 图片地址
     */
    @TableField("img_link")
    private String imgLink;
    /**
     * 预留字段1
     */
    private Integer aa;
    /**
     * 预留字段2
     */
    private String bb;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 标题
     */
    @TableField("title")
    private String title;
    /**
     * 描述
     */
    @TableField("describe")
    private String describe;

    @Override
    public String toString() {
        return "CaseDemo{" +
        ", id=" + id +
        ", serviceId=" + serviceId +
        ", details=" + details +
        ", imgLink=" + imgLink +
        ", aa=" + aa +
        ", bb=" + bb +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
