/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ibp.domain;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();

    public DomainCatalog() {
        actions.put("SUBMIT", new WorkflowAction("SUBMIT", "提交协同", List.of("草案"), "部门协同", "OPERATOR"));
        actions.put("CONSENSUS", new WorkflowAction("CONSENSUS", "达成共识", List.of("部门协同"), "管理评审", "OPERATOR"));
        actions.put("APPROVE", new WorkflowAction("APPROVE", "批准计划", List.of("管理评审"), "已批准", "ADMIN"));
        actions.put("PUBLISH", new WorkflowAction("PUBLISH", "发布计划", List.of("已批准"), "已发布", "ADMIN"));
        actions.put("REPLAN", new WorkflowAction("REPLAN", "滚动重排", List.of("已发布"), "草案", "ADMIN"));
    }

    public String systemName() { return "知华科技集成业务计划 IBP"; }
    public String scene() { return "需求、供应、库存、产能、财务协同，支持 S&OP、场景模拟与执行纠偏"; }
    public String initialStatus() { return "草案"; }
    public String partyLabel() { return "产品/业务单元"; }
    public String amountLabel() { return "计划金额"; }
    public String quantityLabel() { return "计划数量"; }
    public String dueLabel() { return "计划期间"; }

    public List<ModuleDefinition> modules() {
        return List.of(
            new ModuleDefinition("MASTER_DATA", "计划主数据", "治理产品、组织、客户、地点和时间层级"),
            new ModuleDefinition("DEMAND", "需求计划", "融合订单、预测、促销和新品形成一致需求"),
            new ModuleDefinition("SUPPLY", "供应计划", "平衡采购、生产、委外和物流供应"),
            new ModuleDefinition("INVENTORY", "库存计划", "设置安全库存、服务水平和库存目标"),
            new ModuleDefinition("CAPACITY", "产能计划", "管理产线、班次、瓶颈资源和外协能力"),
            new ModuleDefinition("SOP", "S&OP 协同", "执行需求评审、供应评审和管理层评审"),
            new ModuleDefinition("SCENARIO", "场景模拟", "比较基准、乐观、保守及受限场景"),
            new ModuleDefinition("EXCEPTION", "例外管理", "识别缺口、超储、延期和利润偏差"),
            new ModuleDefinition("FINANCE", "财务整合", "将数量计划转换为收入、成本和现金流"),
            new ModuleDefinition("EXECUTION", "计划执行", "联动 ERP/MES/WMS 并追踪计划达成")
        );
    }

    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }

    public record ModuleDefinition(String code, String name, String description) {}
    public record WorkflowAction(String code, String label, List<String> from, String to, String requiredRole) {}
}
