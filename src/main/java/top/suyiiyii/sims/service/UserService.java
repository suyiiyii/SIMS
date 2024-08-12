package top.suyiiyii.sims.service;



import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.mapper.UserMapper;
import top.suyiiyii.sims.utils.TokenUtils;

import java.util.List;

/**
 * @Author tortoise
 * @Date 2024/8/10 22:22
 * @PackageName:top.suyiiyii.sims.service
 * @ClassName: UserService
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void addUser(User user) {
        userMapper.addUser(user);
    }

    public User selectByUserId(int id) {
        return userMapper.selectByUserId(id);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public User login(User user) {
        User dbUser = userMapper.selectByUserName(user.getUsername());
        if (dbUser == null) {
            throw new ServiceException("账号不存在");
        }
        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new ServiceException("密码或用户名错误");
        }
        String token = TokenUtils.createToken(dbUser.getId().toString(), dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    public User register(User user) {

        User dbUser = userMapper.selectByUserId(user.getUserId());

        if (user.getUsername() == null || user.getUsername().equals("")) {
        throw new ServiceException("用户名不能为空");
    }
        if (dbUser != null) {
            throw new ServiceException("账号已经存在");
        }
        if (user.getUserId() == null || user.getUserId().equals("")) {
            throw new ServiceException("用户id不能为空");
        }
        if( user.getPassword() == null || user.getPassword().equals("")) {
            throw new ServiceException("密码不能为空");
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new ServiceException("邮箱不能为空");
        }
        if (user.getGroup() == null || user.getGroup().equals("")) {
            throw new ServiceException("组别不能为空");
        }

            userMapper.addUser(user);
            return user;


    }
}
