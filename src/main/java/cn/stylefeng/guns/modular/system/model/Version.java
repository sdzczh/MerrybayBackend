package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaohe
 * @since 2019-06-13
 */
@TableName("sys_version")
public class Version extends Model<Version> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * apkHash
     */
    @TableField("apk_hash")
    private String apkHash;
    /**
     * apk大小
     */
    @TableField("apk_size")
    private Integer apkSize;
    /**
     * apkUrl
     */
    @TableField("apk_url")
    private String apkUrl;
    @TableField("update_flag")
    private Integer updateFlag;
    /**
     * 0，提示一次更新，1，每次都提示更新，2，强制更新
     */
    @TableField("update_type")
    private Integer updateType;
    /**
     * 版本号
     */
    @TableField("update_version")
    private Integer updateVersion;
    /**
     * 更新内容
     */
    private String content;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApkHash() {
        return apkHash;
    }

    public void setApkHash(String apkHash) {
        this.apkHash = apkHash;
    }

    public Integer getApkSize() {
        return apkSize;
    }

    public void setApkSize(Integer apkSize) {
        this.apkSize = apkSize;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public Integer getUpdateType() {
        return updateType;
    }

    public void setUpdateType(Integer updateType) {
        this.updateType = updateType;
    }

    public Integer getUpdateVersion() {
        return updateVersion;
    }

    public void setUpdateVersion(Integer updateVersion) {
        this.updateVersion = updateVersion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Version{" +
        ", id=" + id +
        ", apkHash=" + apkHash +
        ", apkSize=" + apkSize +
        ", apkUrl=" + apkUrl +
        ", updateFlag=" + updateFlag +
        ", updateType=" + updateType +
        ", updateVersion=" + updateVersion +
        ", content=" + content +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
