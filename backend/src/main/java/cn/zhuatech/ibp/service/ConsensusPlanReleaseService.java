/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ibp.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ConsensusPlanReleaseService {
    public Result assess(Request request) {
        var blockers = new ArrayList<String>();
        var actions = new ArrayList<String>();
        if (request.planId() == null || request.planId().isBlank()) blockers.add("计划编号不能为空");
        if (!request.periodOpen()) blockers.add("计划周期已关闭");
        if (!request.capacityFeasible()) blockers.add("关键产能不可行");
        if (!request.financeReconciled()) blockers.add("数量计划与财务计划未对齐");
        if (!request.decisionMakerSeparated()) blockers.add("计划编制与发布未职责分离");
        if (!request.auditReady()) blockers.add("共识计划审计证据不完整");
        if (!request.demandApproved()) actions.add("完成需求计划确认");
        if (!request.supplyFeasible()) actions.add("修订供应响应方案");
        if (!request.inventoryPolicyAligned()) actions.add("对齐库存策略");
        if (!request.scenarioCompared()) actions.add("完成情景方案比较");
        if (!request.executiveSignoff()) actions.add("取得管理层共识签批");
        var decision = !blockers.isEmpty() ? Decision.BLOCKED : actions.isEmpty() ? Decision.PUBLISH : Decision.CONSENSUS;
        return new Result(decision, List.copyOf(blockers), List.copyOf(actions));
    }

    public enum Decision { PUBLISH, CONSENSUS, BLOCKED }
    public record Request(String planId, boolean demandApproved, boolean supplyFeasible,
                          boolean capacityFeasible, boolean financeReconciled,
                          boolean inventoryPolicyAligned, boolean scenarioCompared,
                          boolean executiveSignoff, boolean periodOpen,
                          boolean decisionMakerSeparated, boolean auditReady) {}
    public record Result(Decision decision, List<String> blockers, List<String> actions) {}
}
