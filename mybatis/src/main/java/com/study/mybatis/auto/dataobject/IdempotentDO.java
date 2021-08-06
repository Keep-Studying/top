package com.study.mybatis.auto.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.study.mybatis.base.ToString;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * mybatis-plus-幂等表
 * </p>
 *
 * @author boyan
 * @since 2021-07-18
 */
@Data
  @EqualsAndHashCode(callSuper = true)
    @TableName("mbp_idempotent")
@ApiModel(value="IdempotentDO对象", description="mybatis-plus-幂等表")
public class IdempotentDO extends ToString {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty(value = "创建时间")
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime gmtCreate;

      @ApiModelProperty(value = "修改时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime gmtModified;

      @ApiModelProperty(value = "租户id")
      private String tntInstId;

      @ApiModelProperty(value = "分库分表键")
      private String dbSplitKey;

      @ApiModelProperty(value = "幂等健")
      private String idempotentKey;

      @ApiModelProperty(value = "状态")
      @TableLogic
    private String status;

    private String bizNo;


}
