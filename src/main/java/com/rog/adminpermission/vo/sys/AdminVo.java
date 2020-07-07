package com.rog.adminpermission.vo.sys;

import com.rog.adminpermission.configuration.validationconfiguration.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/5/24 15:38
 **/
@Data
@ApiModel(value = "测试参数实体")
public class AdminVo {
    @ApiModelProperty(value = "用户名")
    private Integer id;

    private String roleName;
    @Pattern(regexp = "/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/",message = "身份证格式有误")
    private Boolean isDeleted;

    @Phone
    private Date createTime;

    @Email(message = "邮箱格式有误")
    @Length(min = 6,message = "密码长度不能小区6位")


    private Date updateTime;
}
