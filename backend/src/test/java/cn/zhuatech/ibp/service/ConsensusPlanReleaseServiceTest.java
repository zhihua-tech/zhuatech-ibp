/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ibp.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class ConsensusPlanReleaseServiceTest {
    private final ConsensusPlanReleaseService service = new ConsensusPlanReleaseService();

    @Test void publishesBalancedConsensusPlan() {
        var result = service.assess(new ConsensusPlanReleaseService.Request("IBP-100", true, true, true,
                true, true, true, true, true, true, true));
        assertThat(result.decision()).isEqualTo(ConsensusPlanReleaseService.Decision.PUBLISH);
    }

    @Test void requestsCrossFunctionalConsensus() {
        var result = service.assess(new ConsensusPlanReleaseService.Request("IBP-101", false, false, true,
                true, false, false, false, true, true, true));
        assertThat(result.actions()).hasSize(5);
        assertThat(result.decision()).isEqualTo(ConsensusPlanReleaseService.Decision.CONSENSUS);
    }

    @Test void blocksInfeasibleOrUncontrolledPlan() {
        var result = service.assess(new ConsensusPlanReleaseService.Request("", false, false, false,
                false, false, false, false, false, false, false));
        assertThat(result.blockers()).hasSize(6);
        assertThat(result.decision()).isEqualTo(ConsensusPlanReleaseService.Decision.BLOCKED);
    }
}
