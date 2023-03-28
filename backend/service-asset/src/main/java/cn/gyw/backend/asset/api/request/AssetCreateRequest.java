// ---Auto Generated ---
package cn.gyw.backend.asset.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class AssetCreateRequest implements Request {
  @Schema(
      title = "仓库id"
  )
  private Long houseId;

  @Schema(
      title = "资产名称"
  )
  private String name;

  @Schema(
      title = "唯一编码"
  )
  private String uniqueCode;

  @Schema(
      title = "skuId"
  )
  private Long skuId;

  public Long getHouseId() {
    return houseId;
  }

  public void setHouseId(Long houseId) {
    this.houseId = houseId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUniqueCode() {
    return uniqueCode;
  }

  public void setUniqueCode(String uniqueCode) {
    this.uniqueCode = uniqueCode;
  }

  public Long getSkuId() {
    return skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }
}
