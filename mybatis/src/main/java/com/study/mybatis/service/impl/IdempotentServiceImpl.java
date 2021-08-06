package com.study.mybatis.service.impl;

import com.study.mybatis.auto.dataobject.IdempotentDO;
import com.study.mybatis.auto.daointerface.IdempotentMapper;
import com.study.mybatis.service.IIdempotentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * mybatis-plus-幂等表 服务实现类
 * </p>
 *
 * @author boyan
 * @since 2021-07-18
 */
@Service
public class IdempotentServiceImpl extends ServiceImpl<IdempotentMapper, IdempotentDO> implements IIdempotentService {

}
