/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ibp.controller;
import cn.zhuatech.ibp.common.ApiResponse; import cn.zhuatech.ibp.service.EnterpriseIbpService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/enterprise/ibp") public class EnterpriseIbpController {
 private final EnterpriseIbpService service; /**
                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                              */
public EnterpriseIbpController(EnterpriseIbpService service){this.service=service;}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/balance-plan") ApiResponse<?> execute(@Valid @RequestBody EnterpriseIbpService.BalanceRequest request){return ApiResponse.ok(service.balance(request));}
}

