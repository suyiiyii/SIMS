# SIMS

Super Invincible Management System

开发流程:
1. git fetch: 拉取远程仓库的最新代码
2. git checkout origin/main: 切换到远程仓库的 main 分支
3. git switch -c xxx : 创建并切换到新的分支
4. commit ..... : 进行开发
5. git fetch origin && git merge origin/main: 拉取远程仓库的最新代码并合并到当前分支
5. git push origin xxx: 推送当前分支到远程仓库
6. 提 PR
7. require review: 请求review
8. merge: 合并 PR
9. delete: 删除分支
