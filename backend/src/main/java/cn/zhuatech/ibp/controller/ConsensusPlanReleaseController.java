/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ibp.controller;

import cn.zhuatech.ibp.common.ApiResponse;
import cn.zhuatech.ibp.service.ConsensusPlanReleaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/ibp")
public class ConsensusPlanReleaseController {
    private final ConsensusPlanReleaseService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public ConsensusPlanReleaseController(ConsensusPlanReleaseService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/consensus-plan-release")
    public ApiResponse<?> assess(@RequestBody ConsensusPlanReleaseService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
