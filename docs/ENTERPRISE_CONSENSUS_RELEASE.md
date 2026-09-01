# 企业共识计划发布

`POST /api/enterprise/ibp/consensus-plan-release` 对需求、供应、产能、库存和财务计划执行统一发布检查。

- `PUBLISH`：计划已跨职能对齐且关键控制完整，可以发布。
- `CONSENSUS`：没有硬性阻断，但仍需需求、供应、库存、情景或管理层确认。
- `BLOCKED`：周期关闭、产能不可行、财务未对齐、职责分离或审计条件不满足。

规则结果可供 S&OP 会议、计划工作台、消息通知和外部 ERP/APS 适配器调用。
