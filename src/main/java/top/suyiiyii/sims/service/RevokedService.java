package top.suyiiyii.sims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.mapper.MpRevRecordMapper;
import top.suyiiyii.sims.mapper.RoleMapper;

/**
 * @Author tortoise
 * @Date 2024/9/6 10:06
 * @PackageName:top.suyiiyii.sims.service
 * @ClassName: RevokedService
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class RevokedService {
    @Autowired
    MpRevRecordMapper mpRevRecordMapper;
}
