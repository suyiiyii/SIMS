package top.suyiiyii.sims.service;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.entity.Notification;
import top.suyiiyii.sims.entity.RevokeRequest;
import top.suyiiyii.sims.mapper.MpNotificationMapper;

/**
 * @Author tortoise
 * @Date 2024/9/8 20:50
 * @PackageName:top.suyiiyii.sims.service
 * @ClassName: NotificationService
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class NotificationService {
    @Autowired
    MpNotificationMapper mpNotificationMapper;
    public void addNotification(RevokeRequest revokeRequest) {
        Notification notification = new Notification();
        notification.setSenderId(revokeRequest.getUserId());
        notification.setTitle("申请撤销");
        notification.setContent(revokeRequest.getReason());
        notification.setType("申请");
        notification.setCreatedAt(revokeRequest.getRequestTime());
        notification.setStatus("未处理");
        notification.setTargetUserId(-1);
        mpNotificationMapper.insert(notification);
    }
}
