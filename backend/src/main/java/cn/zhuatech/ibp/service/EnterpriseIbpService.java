/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.ibp.service;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.stereotype.Service; import java.math.*; import java.util.*;
@Service public class EnterpriseIbpService {
 public BalanceResult balance(@Valid BalanceRequest r){
  BigDecimal available=r.onHand().add(r.supply()); BigDecimal netRequirement=r.demand().add(r.safetyStock()).subtract(available).max(BigDecimal.ZERO);
  BigDecimal constrainedProduction=netRequirement.min(r.capacity()); BigDecimal projected=available.add(constrainedProduction).subtract(r.demand());
  BigDecimal shortage=r.safetyStock().subtract(projected).max(BigDecimal.ZERO); BigDecimal fulfilled=r.demand().subtract(shortage.min(r.demand()));
  BigDecimal service=r.demand().signum()==0?BigDecimal.valueOf(100):fulfilled.multiply(BigDecimal.valueOf(100)).divide(r.demand(),2,RoundingMode.HALF_UP);
  BigDecimal revenue=fulfilled.multiply(r.unitRevenue()).setScale(2,RoundingMode.HALF_UP); BigDecimal margin=revenue.subtract(fulfilled.multiply(r.unitCost())).setScale(2,RoundingMode.HALF_UP);
  List<String> exceptions=new ArrayList<>(); if(shortage.signum()>0) exceptions.add("供应能力不足"); if(projected.compareTo(r.safetyStock().multiply(BigDecimal.valueOf(2)))>0) exceptions.add("期末库存偏高");
  return new BalanceResult(r.productNo(),netRequirement,constrainedProduction,projected,shortage,service,revenue,margin,exceptions,exceptions.isEmpty()?"BALANCED":"EXCEPTION");
 }
 public record BalanceRequest(@NotBlank String productNo,@NotNull @DecimalMin("0") BigDecimal demand,@NotNull @DecimalMin("0") BigDecimal supply,@NotNull @DecimalMin("0") BigDecimal onHand,@NotNull @DecimalMin("0") BigDecimal safetyStock,@NotNull @DecimalMin("0") BigDecimal capacity,@NotNull @DecimalMin("0") BigDecimal unitRevenue,@NotNull @DecimalMin("0") BigDecimal unitCost){}
 public record BalanceResult(String productNo,BigDecimal netRequirement,BigDecimal plannedProduction,BigDecimal projectedInventory,BigDecimal shortage,BigDecimal serviceLevel,BigDecimal projectedRevenue,BigDecimal projectedMargin,List<String>exceptions,String decision){}
}

