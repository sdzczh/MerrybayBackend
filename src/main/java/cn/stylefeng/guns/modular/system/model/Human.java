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
 * @author wxw
 * @since 2019-05-07
 */
@TableName("human")
public class Human extends Model<Human> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 0:技术,1:销售,2:行政
     */
    private Integer category;
    /**
     * 招聘地点
     */
    private String place;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * 招聘人数
     */
    private Integer num;
    /**
     * 工作经验(年数)
     */
    private String year;
    /**
     * 岗位名称
     */
    @TableField("jobs_name")
    private String jobsName;
    /**
     * 岗位要求
     */
    @TableField("jobs_require")
    private String jobsRequire;
    /**
     * 岗位职责
     */
    @TableField("jobs_duty")
    private String jobsDuty;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getJobsName() {
        return jobsName;
    }

    public void setJobsName(String jobsName) {
        this.jobsName = jobsName;
    }

    public String getJobsRequire() {
        return jobsRequire;
    }

    public void setJobsRequire(String jobsRequire) {
        this.jobsRequire = jobsRequire;
    }

    public String getJobsDuty() {
        return jobsDuty;
    }

    public void setJobsDuty(String jobsDuty) {
        this.jobsDuty = jobsDuty;
    }

    public Integer getAa() {
        return aa;
    }

    public void setAa(Integer aa) {
        this.aa = aa;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
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
        return "Human{" +
        ", id=" + id +
        ", category=" + category +
        ", place=" + place +
        ", email=" + email +
        ", phone=" + phone +
        ", num=" + num +
        ", year=" + year +
        ", jobsName=" + jobsName +
        ", jobsRequire=" + jobsRequire +
        ", jobsDuty=" + jobsDuty +
        ", aa=" + aa +
        ", bb=" + bb +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
