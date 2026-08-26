/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ibp.controller;
import cn.zhuatech.ibp.common.ApiResponse; import cn.zhuatech.ibp.service.EnterpriseIbpService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/enterprise/ibp") public class EnterpriseIbpController {
 private final EnterpriseIbpService service; public EnterpriseIbpController(EnterpriseIbpService service){this.service=service;}
 @PostMapping("/balance-plan") ApiResponse<?> execute(@Valid @RequestBody EnterpriseIbpService.BalanceRequest request){return ApiResponse.ok(service.balance(request));}
}

