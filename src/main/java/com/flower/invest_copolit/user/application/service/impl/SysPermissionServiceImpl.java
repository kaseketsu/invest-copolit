package com.flower.invest_copolit.user.application.service.impl;

import com.flower.invest_copolit.user.infrastructure.persistence.dataobject.SysPermission;
import com.flower.invest_copolit.user.infrastructure.persistence.mapper.SysPermissionMapper;
import com.flower.invest_copolit.user.application.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统权限表 服务实现类
 * </p>
 *
 * @author F1ower
 * @since 2026-04-04
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

}
