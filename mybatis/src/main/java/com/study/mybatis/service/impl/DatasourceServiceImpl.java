package com.study.mybatis.service.impl;

import com.study.mybatis.auto.dataobject.DatasourceDO;
import com.study.mybatis.auto.daointerface.DatasourceMapper;
import com.study.mybatis.service.IDatasourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * mybatis-plus-数据源表 服务实现类
 * </p>
 *
 * @author boyan
 * @since 2021-07-18
 */
@Service
public class DatasourceServiceImpl extends ServiceImpl<DatasourceMapper, DatasourceDO> implements IDatasourceService {

}
