package top.suyiiyii.sims.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.entity.HierarchyRelation;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.mapper.HierarchyMapper;
import top.suyiiyii.sims.mapper.MpUserMapper;
import top.suyiiyii.sims.service.HierarchyService;

import java.util.List;

@Service
public class HierarchyServiceImpl extends ServiceImpl<HierarchyMapper, HierarchyRelation> implements HierarchyService {

    @Autowired
    MpUserMapper userMapper;
    @Override
    public User getSuperiorUser(int UserId) {
        return baseMapper.getSuperiorUser(UserId);
    }

    @Override
    public List<User> getSubordinateUser(int userId) {
        return baseMapper.getSubordinateUser(userId);
    }

    @Override
    public void setSuperiorUser(int userId, int superiorUserId) {
        if (userId == superiorUserId) {
            throw new ServiceException("不能设置自己为上级");
        }
        if (superiorUserId != 0 && userMapper.selectById(superiorUserId) == null) {
            throw new ServiceException("上级用户不存在");
        }

        // 检测是否存在循环上级
        User superiorUser = userMapper.selectById(superiorUserId);
        while (superiorUser != null) {
            if (superiorUser.getId() == userId) {
                throw new ServiceException("不能设置自己的下级为上级");
            }
            superiorUser = getSuperiorUser(superiorUser.getId());
        }

        baseMapper.update(new LambdaUpdateWrapper<HierarchyRelation>().eq(HierarchyRelation::getSubordinateUserId, userId)
                .set(HierarchyRelation::getSuperiorUserId, superiorUserId));
    }
}
