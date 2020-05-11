//package com.ybj.auth.service.impl;
//
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.ybj.auth.dao.RoleMapper;
//import com.ybj.auth.model.Role;
//import com.ybj.auth.service.RoleServiceI;
//import com.ybj.auth.utils.ModelToDbUtils;
//import com.ybj.utils.constant.ConfigConstants;
//import com.ybj.utils.constant.MessageConstants;
//import com.ybj.utils.exception.BusinessException;
//import com.ybj.utils.exception.CustomGenericException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Map;
//
///**
// *角色管理实现类
// * @author caicai.gao
// */
//@Slf4j
//@Service("roleService")
//public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleServiceI {
//
//	@Autowired
//	private RoleMapper roleMapper;
//
//	@Override
//	public IPage<Role> getAll(Map<String, Object> searchParams) {
//
//		IPage<Role> rolePage ;
//		try {
//			// 分页信息
//			// 显示第几页
//			long page = Long.parseLong(searchParams.get("page").toString());
//			// 每页显示多少条
//			long perPage = Long.parseLong(searchParams.get("perPage").toString());
//			Page<Role> pageInfo = new Page<>(page, perPage, true);
//			// 自动优化 COUNT SQL
//			pageInfo.setOptimizeCountSql(true);
//			// 将map中的key转换为数据库字段
//			ModelToDbUtils<Role> mtd = new ModelToDbUtils<>();
//			Map<String, Object> params = mtd.convertToDb(new Role(), searchParams);
//			// xml自定义分页：getAll
//			rolePage = roleMapper.getAll(pageInfo, params);
//			log.info("查询成功");
//		}catch (Exception e){
//			log.trace(e.getMessage());
//			throw new CustomGenericException(ConfigConstants.ERROR_CODE_BUSINESS, MessageConstants.ROLE_MSG002);
//		}
//		return rolePage;
//	}
//
//	@Override
//	public Role saveRole(int flg, String oid, String roleName, String remark, String createdBy, String updatedBy) {
//		Role role;
//		int result;
//		if (flg == 0) {
//			// 检查名称是否存在
//			QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
//			// 使用数据库字段名
//			queryWrapper.eq("ROLE_NAME",roleName);
//			if (roleMapper.selectCount(queryWrapper) > 0) {
//				throw new BusinessException(ConfigConstants.ERROR_CODE_BUSINESS, MessageConstants.ROLE_MSG001);
//			}
//			role = new Role();
//			role.setRoleName(roleName);
//			role.setRemark(remark);
//			role.setCreatedBy(createdBy);
//			role.setUpdatedBy(updatedBy);
//			role.setCreateTime(LocalDateTime.now());
//			role.setUpdateTime(LocalDateTime.now());
//			result = roleMapper.insert(role);
//		} else {
//			role = roleMapper.selectById(Long.valueOf(oid));
//			role.setRoleName(roleName);
//			role.setUpdateTime(LocalDateTime.now());
//			role.setUpdatedBy(updatedBy);
//			role.setRemark(remark);
//			result = roleMapper.updateById(role);
//		}
//		if (result > 0) {
//			return role;
//		} else {
//			return null;
//		}
//	}
//
//}
