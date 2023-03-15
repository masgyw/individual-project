// ---Auto Generated ---
package cn.gyw.individual.backend.service.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema
public class HouseQueryRequest implements Request {
  @Schema(
      title = "crawlDate"
  )
  private LocalDate crawlDate;

  @Schema(
      title = "province"
  )
  private String province;

  @Schema(
      title = "city"
  )
  private String city;

  @Schema(
      title = "villageName"
  )
  private String villageName;

  @Schema(
      title = "price"
  )
  private BigDecimal price;

  @Schema(
      title = "sourceFile"
  )
  private String sourceFile;

  public LocalDate getCrawlDate() {
    return crawlDate;
  }

  public void setCrawlDate(LocalDate crawlDate) {
    this.crawlDate = crawlDate;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getVillageName() {
    return villageName;
  }

  public void setVillageName(String villageName) {
    this.villageName = villageName;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getSourceFile() {
    return sourceFile;
  }

  public void setSourceFile(String sourceFile) {
    this.sourceFile = sourceFile;
  }
}
