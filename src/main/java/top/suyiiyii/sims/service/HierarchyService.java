package top.suyiiyii.sims.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.entity.HierarchyRelation;
import top.suyiiyii.sims.entity.User;

import java.util.List;

@Service
public interface HierarchyService extends IService<HierarchyRelation> {
    public User getSuperiorUser(int UserId);

    public List<User> getSubordinateUser(int userId);


    void setSuperiorUser(int userId, int superiorUserId);
}
