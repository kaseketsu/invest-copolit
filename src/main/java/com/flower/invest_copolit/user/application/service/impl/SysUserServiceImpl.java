package com.flower.invest_copolit.user.application.service.impl;

import com.flower.invest_copolit.user.infrastructure.persistence.dataobject.SysUser;
import com.flower.invest_copolit.user.infrastructure.persistence.mapper.SysUserMapper;
import com.flower.invest_copolit.user.application.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author F1ower
 * @since 2026-04-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
