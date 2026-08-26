/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ibp;
import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; import org.springframework.http.MediaType; import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic; import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc class EnterpriseIbpApiTests { @Autowired MockMvc mvc;

 @Test void constrainedPlanProducesShortageException() throws Exception {mvc.perform(post("/api/enterprise/ibp/balance-plan").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"productNo":"P-001","demand":1000,"supply":100,"onHand":100,"safetyStock":100,"capacity":700,"unitRevenue":20,"unitCost":12}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.plannedProduction").value(700)).andExpect(jsonPath("$.data.shortage").value(200)).andExpect(jsonPath("$.data.decision").value("EXCEPTION"));}
 @Test void balancedPlanReturnsFinancialProjection() throws Exception {mvc.perform(post("/api/enterprise/ibp/balance-plan").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"productNo":"P-002","demand":500,"supply":400,"onHand":200,"safetyStock":100,"capacity":0,"unitRevenue":20,"unitCost":12}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.projectedInventory").value(100)).andExpect(jsonPath("$.data.projectedMargin").value(4000.00)).andExpect(jsonPath("$.data.decision").value("BALANCED"));}
}

