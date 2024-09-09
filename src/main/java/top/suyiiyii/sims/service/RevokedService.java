package top.suyiiyii.sims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.controller.RevokedController;
import top.suyiiyii.sims.entity.RevokeRequest;
import top.suyiiyii.sims.mapper.MpRevRecord;
import top.suyiiyii.sims.mapper.MpRevRequestMapper;

import java.util.List;
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
    MpRevRequestMapper mpRevRequestMapper;
    @Autowired
    MpRevRecord mpRevRecord;
    public void addRevokeRequest(RevokeRequest revokeRequest) {
        revokeRequest.setStatus("待审核");
        mpRevRequestMapper.insert(revokeRequest);

    }
        public List<RevokeRequest> getAll(int page, int size){
        return mpRevRequestMapper.selectList(page,size);
    }


    public void updateRevokeRequest(Integer id, String status, String adminRemark, String reason, Long handleTime) {

        mpRevRequestMapper.update(id, status, adminRemark, reason, handleTime);
    }

    public void addRevokedRecord(Integer id, String userId, String reason, Long handleTime) {
        mpRevRecord.addRevokedRecord(id, userId, reason, handleTime);
    }
}
