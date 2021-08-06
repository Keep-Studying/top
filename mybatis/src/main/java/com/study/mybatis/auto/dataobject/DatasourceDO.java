package com.study.mybatis.auto.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.study.mybatis.base.ToString;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * mybatis-plus-数据源表
 * </p>
 *
 * @author boyan
 * @since 2021-07-18
 */
@Data
  @EqualsAndHashCode(callSuper = true)
    @TableName("mbp_datasource")
@ApiModel(value="DatasourceDO对象", description="mybatis-plus-数据源表")
public class DatasourceDO extends ToString {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty(value = "创建时间")
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime gmtCreate;

      @ApiModelProperty(value = "创建者")
      private String createOperator;

      @ApiModelProperty(value = "修改时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime gmtModified;

      @ApiModelProperty(value = "修改者")
      private String modifiedOperator;

      @ApiModelProperty(value = "数据源ID")
      private String datasourceId;

      @ApiModelProperty(value = "连接服务器(IP)")
      private String server;

      @ApiModelProperty(value = "端口")
      private String port;

      @ApiModelProperty(value = "项目名称")
      private String project;

      @ApiModelProperty(value = "账户")
      private String userName;

      @ApiModelProperty(value = "密码")
      private String password;

      @ApiModelProperty(value = "名字")
      private String name;

      @ApiModelProperty(value = "数据源类型(MYSQL/KYLIN/*)")
      private String type;

      @ApiModelProperty(value = "备注")
      private String remarks;

      @ApiModelProperty(value = "关联的VIP对应的datasourceId")
      private String relation;


}
