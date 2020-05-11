//package com.ybj.user.service.impl;
//
//import com.atoz.mpm.system.authentication.dao.OrgMapper;
//import com.atoz.mpm.system.authentication.dao.PermissionMapper;
//import com.atoz.mpm.system.authentication.dao.UserMapper;
//import com.atoz.mpm.system.authentication.dao.UserRoleMapper;
//import com.atoz.mpm.system.authentication.jwt.JwtUtil;
//import com.atoz.mpm.system.authentication.model.Permission;
//import com.atoz.mpm.system.authentication.model.User;
//import com.atoz.mpm.system.authentication.model.UserRole;
//import com.atoz.mpm.system.authentication.service.UserServiceI;
//import com.atoz.mpm.system.authentication.utils.Md5EncryptionUtil;
//import com.atoz.mpm.utils.common.ModelToDbUtils;
//import com.atoz.mpm.utils.constant.ConfigConstants;
//import com.atoz.mpm.utils.constant.MessageConstants;
//import com.atoz.mpm.utils.exception.BusinessException;
//import com.atoz.mpm.utils.exception.CustomGenericException;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
///**
// * @Author yabo.wang
// * @Date 2020/1/13 13:48
// * @Description
// * IService:mp提供的接口。ServiceImpl:mp提供的接口实现类。
// * ServiceImpl<BaseMapper<T>, T>是IService 的实现类。
// */
//@Slf4j
//@Service
//public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserServiceI{
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private UserRoleMapper userRoleMapper;
//
//    @Autowired
//    private PermissionMapper permissionMapper;
//
//    @Autowired
//    private OrgMapper orgMapper;
//
//    /**
//     * 查询所有用户
//     *
//     * @return 除管理员外所有用户信息
//     */
//    @Override
//    public IPage<User> getAll(Map<String, Object> searchParams) {
//        IPage<User> userPage ;
//        try {
//            // 分页信息
//            // 显示第几页
//            long page = Long.parseLong(searchParams.get("page").toString());
//            // 每页显示多少条
//            long perPage = Long.parseLong(searchParams.get("perPage").toString());
//            Page<User> pageInfo = new Page<>(page, perPage, true);
//            // 自动优化 COUNT SQL
//            pageInfo.setOptimizeCountSql(true);
//            // 将map中的key转换为数据库字段
//            ModelToDbUtils<User> mtd = new ModelToDbUtils<>();
//            Map<String, Object> params = mtd.convertToDb(new User(), searchParams);
//            // 排除系统管理员
//            params.put(ConfigConstants.ADMIN_USER, ConfigConstants.ADMIN_USER);
//            // xml自定义分页：getAll
//            userPage = userMapper.getAll(pageInfo, params);
//            log.info("查询成功");
//        }catch (Exception e){
//            log.trace(e.getMessage());
//            throw new CustomGenericException(ConfigConstants.ERROR_CODE_BUSINESS, MessageConstants.USER_MSG002);
//        }
//        return userPage;
//    }
//
//    /**
//     * 为用户分配角色
//     * @param roleIds 角色id数组
//     * @param userId 用户ID
//     */
//    @Override
//    @Transactional(rollbackFor=Exception.class)
//    public void assignRole(String[] roleIds, String userId) {
//        // 先全部删除
//        userRoleMapper.deleteById(userId);
//        UserRole userRole = new UserRole();
//        userRole.setUserId(userId);
//        try {
//            // 再分别插入
//            for (int i = 0; i < roleIds.length; i++) {
//                userRole.setRoleId(roleIds[i]);
//                userRoleMapper.insert(userRole);
//            }
//        } catch (Exception e){
//            log.error(e.getMessage());
//            throw new CustomGenericException(ConfigConstants.ERROR_CODE_BUSINESS, MessageConstants.USER_MSG004);
//        }
//    }
//
//    /**
//     * 保存用户信息
//     * @param flg 新增OR修改标识 0-新增 1-修改
//     * @param user 用户信息
//     * @return User 用户对象
//     */
//    @Override
//    public User saveUser(int flg, User user) {
//        int result;
//        if (flg == 0) {
//            User oldUser = new User();
//            oldUser.setLoginName(user.getLoginName());
//            // 检查名称是否已存在
//            if (checkUser(oldUser) !=null ) {
//                throw new BusinessException(ConfigConstants.ERROR_CODE_BUSINESS, MessageConstants.USER_MSG001);
//            }
//            user.setPassword(Md5EncryptionUtil.convertMd5(ConfigConstants.OAUTHTOKEN));
//            user.setStatus(1);
//            result = userMapper.insert(user);
//        } else {
//            result = userMapper.updateById(user);
//        }
//        if (result > 0) {
//            return user;
//        }
//        return null;
//    }
//
//    /**
//     * 获取用户权限
//     * @param userId 用户ID
//     * @return List<Permission> 权限列表
//     */
//    @Override
//    public List<Permission> selectPermissionByUser(String userId) {
//        return permissionMapper.selectPermissionByUser(userId);
//    }
//
//    /**
//     * 以用户名和密码查询用户
//     *
//     * @param  user 用户名或者密码或者两者都有
//     * @return 用户信息
//     */
//    @Override
//    public User checkUser(User user) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(StringUtils.isNotEmpty(user.getLoginName()),"LOGIN_NAME", user.getLoginName());
//        queryWrapper.ne(StringUtils.isNotEmpty(user.getPassword()), "PASSWORD", user.getPassword());
//        return userMapper.selectOne(queryWrapper);
//    }
//
//    /**
//     * 获取用户已选角色的ID列表
//     * @param userId 用户ID
//     * @return List<Integer> 返回类型
//     */
//    @Override
//    public List<String> getUserSelectedRole(String userId) {
//        return userRoleMapper.selectRoleIdByUser(userId);
//    }
//
//    /**
//     * 获取所有人员-用于审核
//     * @param type 权限（拥有审核XX权限的用户才会被选中）
//     * @return List<User>    返回类型
//     */
//    @Override
//    public List<User> searchAuditor(String type) {
//        // 参数Map
//        Map<String,Object> map = new HashMap<>();
//        // 管理员名
//        map.put("admin", ConfigConstants.ADMIN_USER);
//        map.put("type", type);
//        return userMapper.searchAuditor(map);
//    }
//
//    /**
//     * 获取所有人员-用于转交
//     * @return List<User>    返回类型
//     */
//    @Override
//    public List<User> searchUser() {
//        // 参数Map
//        Map<String,Object> map = new HashMap<>();
//        // 管理员名
//        map.put("admin", ConfigConstants.ADMIN_USER);
//        return userMapper.searchUser(map);
//    }
//
//    /**
//     * 判断用户是否觉有某个权限
//     * @param userId  用户ID
//     * @param permissionName  权限名称
//     * @return Boolean 是否有权限的标识
//     */
//    @Override
//    public Boolean hasPermission(String userId, String permissionName) {
//        int flg =  userMapper.hasPermission(userId, permissionName).size();
//        return flg != 0;
//    }
//
//    @Override
//    public List<User> getUserByOrg(String orgId) {
//        return userMapper.getUserByOrg(orgId);
//    }
//
//    @Override
//    public List<User> getAllUser() {
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.ne(User::getLoginName, ConfigConstants.ADMIN_USER);
//        return userMapper.selectList(queryWrapper);
//    }
//
//    @Override
//    public List<User> getAllPeopleByOrgId(String orgId) {
//        List<String> childOrgId= getChildOrgId(orgId);
//        List<User> userList=new ArrayList<>();
//        List<User> directlyPeopleByRootOrgId = getDirectlyPeopleByOrgId(orgId);
//        userList.addAll(directlyPeopleByRootOrgId);
//        for (String  Id : childOrgId) {
//            List <String> childOfChildOrgId=getChildOrgId(Id);
//            if(!childOfChildOrgId.isEmpty()){
//                userList.addAll(getAllPeopleByOrgId(Id));
//            } else{
//                userList.addAll(getDirectlyPeopleByOrgId(Id));
//            }
//        }
//        return userList;
//    }
//
//    @Override
//    public void alloteUserToOrg(String userIds, String orgId) {
//        String userId=null;
//        User user = new User();
//        user.setOrgId(orgId);
//        user.setUpdatedBy(JwtUtil.getUserId());
//        StringTokenizer userIdST = new StringTokenizer(userIds, ",");
//        while (userIdST.hasMoreTokens())
//        {
//            userId=String.valueOf(userIdST.nextToken());
//            user.setUserId(userId);
//            userMapper.updateById(user);
//        }
//    }
//
//    private List<User> getDirectlyPeopleByOrgId(String orgId) {
//        List<User> userList=null;
//        userList = userMapper.selectDirectlyUserByOrgId(orgId);
//        return userList;
//    }
//
//    private List<String> getChildOrgId(String orgId) {
//        return orgMapper.getChildOrgId(orgId);
//    }
//
//    /**
//     * 分页查询演示
//     */
//    public void selectPage (){
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("DELETED",0);
//        Page<User> page = new Page<>(1, 10, true);
//
//        // 分页查询方法一：selectPage
//        IPage<User> iPage = userMapper.selectPage(page, wrapper);
//        // 查询结果
//        List<User> userList = iPage.getRecords();
//        // 总页数
//        iPage.getPages();
//        // 总记录数
//        iPage.getTotal();
//        log.info(userList.toString());
//
//        /**
//         // 分页查询方法二：selectMapsPage
//         IPage<Map<String, Object>> iPageMap = userMapper.selectMapsPage(page, wrapper);
//         */
//    }
//
//
//}
