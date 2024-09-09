package top.suyiiyii.sims.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.suyiiyii.sims.common.AuthAccess;
import top.suyiiyii.sims.common.JwtInterceptor;
import top.suyiiyii.sims.common.Result;
import top.suyiiyii.sims.dto.CommonResponse;
import top.suyiiyii.sims.dto.RecordDto;
import top.suyiiyii.sims.dto.RevokeRequestDto;
import top.suyiiyii.sims.entity.RevokeRequest;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.service.CategoryService;
import top.suyiiyii.sims.service.NotificationService;
import top.suyiiyii.sims.service.RecordService;
import top.suyiiyii.sims.service.RevokedService;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    NotificationService notificationService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    RecordService recordService;

    //TODO 普通成员向管理员申请撤销
    @AuthAccess(allowRoles = {"user"})
    @Operation(summary = "成员申请撤销")
    @PostMapping("")
    public Result<CommonResponse> revoked(@RequestBody Request request) {

        if(request.getReason().isBlank()) {
            throw new ServiceException("撤销原因不能为空");
        }
        RevokeRequest revokeRequest = modelMapper.map(request, RevokeRequest.class);

        revokedService.addRevokeRequest(revokeRequest);
        //发送通知给管理员
        notificationService.addNotification(revokeRequest);
        return Result.success(CommonResponse.factory("申请成功"));
    }
    //TODO 管理员查看所有撤销申请
    @AuthAccess(allowRoles = {"admin"})
    @Operation(summary = "管理员查看所有撤销申请")
    @GetMapping("")
    public Result<List<RevokeRequestDto>> revoked(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<RevokeRequest> revokeRequests = revokedService.getAll(page, size);
        List<RevokeRequestDto> revokeRequestDtos = new ArrayList<>();
        for (RevokeRequest revokeRequest : revokeRequests) {
            RevokeRequestDto revokeRequestDto = modelMapper.map(revokeRequest, RevokeRequestDto.class);
            revokeRequestDto.setCategoryName(categoryService.getCategoryName(recordService.getCategoryIdById(revokeRequest.getRecordId())));
            revokeRequestDto.setSubCategoryName(categoryService.getsubCategoryName(recordService.getCategoryIdById(revokeRequest.getRecordId())));
            revokeRequestDtos.add(revokeRequestDto);
        }
        return Result.success(revokeRequestDtos);
    }
    //TODO 管理员可以撤销某一成员的奖励或惩罚记录，需填写撤销原因，撤销备注

    @AuthAccess(allowRoles = {"admin"})
    @Operation(summary = "管理员处理撤销申请")
    @PutMapping("/{id}")
    public Result<CommonResponse> revoked(@PathVariable Integer id, RevokedRequest revokedRequest, HttpServletRequest request) {
        String userId = String.valueOf(JwtInterceptor.getUserIdFromReq(request));
        if(revokedRequest.getAdminRemark().isBlank()) {
            throw new ServiceException("撤销备注不能为空");
        }
        if(!"批准".equals(revokedRequest.getStatus()) && !"拒绝".equals(revokedRequest.getStatus()) ) {
            throw new ServiceException("状态不合法");
        }
/*        if("批准".equals(revokedRequest.getStatus())){
            recordService.update(id,userId,revokedRequest.getStatus(),revokedRequest.getAdminRemark(),
                    revokedRequest.getReason(),revokedRequest.getHandleTime());
        }*/

        revokedService.updateRevokeRequest(id,
                revokedRequest.getStatus(),revokedRequest.getAdminRemark(),
                revokedRequest.getReason(),revokedRequest.getHandleTime());
            //TODO 要加到记录里面去
            revokedService.addRevokedRecord(id,userId,revokedRequest.getReason(),revokedRequest.getHandleTime());

        return Result.success(CommonResponse.factory("申请成功"));
    }
    @AuthAccess(allowRoles = {"admin"})
    @Operation(summary = "撤销单个奖惩记录")
    @DeleteMapping("/admin/records/{id}")
    public Result<CommonResponse> adminDeleteRecord(@PathVariable Integer id, String reason, HttpServletRequest request) {
        Integer i = recordService.IsRecord(id);
        String userId = String.valueOf(JwtInterceptor.getUserIdFromReq(request));
        if(i==null) {
            throw new RuntimeException("该记录不存在");
        }
        recordService.revokeUpdate(id,reason,userId);
        return Result.msg("撤销成功");
    }


    @Data
    public static class Request {
        private Integer userId;
        private Integer recordId;
        private String reason;
        private Long requestTime;
    }
    @Data
    public static class RevokedRequest {
        private String status;
        private String adminRemark;
        private String reason;
        private Long handleTime;
    }
}
