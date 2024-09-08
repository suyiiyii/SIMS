package top.suyiiyii.sims.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.suyiiyii.sims.service.RevokedService;

/**
 * @Author tortoise
 * @Date 2024/9/6 10:03
 * @PackageName:top.suyiiyii.sims.controller
 * @ClassName: RevokedController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/revoked")
@Slf4j
public class RevokedController {
    @Autowired
    RevokedService revokedService;
    //TODO 普通成员向管理员申请撤销

    //TODO 管理员查看所有撤销申请

    //TODO 管理员可以撤销某一成员的奖励或惩罚记录，需填写撤销原因，撤销备注
}
