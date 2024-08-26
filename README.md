# SIMS

Super Invincible Management System

开发流程:
1. git fetch: 拉取远程仓库的最新代码
2. git checkout origin/main: 切换到远程仓库的 main 分支
3. git switch -c xxx : 创建并切换到新的分支
4. commit ..... : 进行开发
5. git fetch origin && git merge origin/main: 拉取远程仓库的最新代码并合并到当前分支
6. git push origin xxx: 推送当前分支到远程仓库
7. 提 PR
8. require review: 请求reviewpush
9. merge: 合并 PR
10. delete: 删除分支

1. 基础rbac的五张表: user, role, permission, user_role, role_permission
2. 然后奖惩记录这张表，通过用户id来查到，里面有相应的记录， 有一个 奖惩类别ID是对应到奖惩类型去的
3. 有一个上下级关系表，想着是用户明确查上下级就可以用查，
4. 然后就是附件，是通过奖惩记录的id来存一个路径，然后前端通过这个路径来获取附件,同样的上传的时候也是
5. 通知是这样子想的，有上传的人的id和接收的人的id，然后有状态（已读，未读），然后有附件的路径，然后有通知内容，然后有通知时间，然后有通知类型（奖惩，通知）
6. RevokeRequest:  存普通成员提出的撤销申请,跟踪申请状态 RevokedRecord: 存储管理员对奖惩记录的撤销信息，包括撤销原因