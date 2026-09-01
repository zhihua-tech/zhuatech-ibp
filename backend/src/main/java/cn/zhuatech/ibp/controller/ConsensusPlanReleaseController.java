/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ibp.controller;

import cn.zhuatech.ibp.common.ApiResponse;
import cn.zhuatech.ibp.service.ConsensusPlanReleaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enterprise/ibp")
public class ConsensusPlanReleaseController {
    private final ConsensusPlanReleaseService service;
    public ConsensusPlanReleaseController(ConsensusPlanReleaseService service) { this.service = service; }

    @PostMapping("/consensus-plan-release")
    public ApiResponse<?> assess(@RequestBody ConsensusPlanReleaseService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
