package top.suyiiyii.sims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.entity.RevokeRequest;
import top.suyiiyii.sims.mapper.MpRevRequestMapper;

import java.util.List;

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

    public void addRevokeRequest(RevokeRequest revokeRequest) {
        revokeRequest.setStatus("待审核");
        mpRevRequestMapper.insert(revokeRequest);

    }
        public List<RevokeRequest> getAll(int page, int size){
        return mpRevRequestMapper.selectList(page,size);
    }
}
