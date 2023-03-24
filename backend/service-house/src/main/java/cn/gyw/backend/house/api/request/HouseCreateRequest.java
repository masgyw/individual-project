// ---Auto Generated ---
package cn.gyw.backend.house.api.request;

import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema
public class HouseCreateRequest implements Request {
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
      title = "priceUnit"
  )
  private String priceUnit;

  @Schema(
      title = "rooms"
  )
  private String rooms;

  @Schema(
      title = "area"
  )
  private String area;

  @Schema(
      title = "address"
  )
  private String address;

  @Schema(
      title = "district"
  )
  private String district;

  @Schema(
      title = "aroundPrice"
  )
  private String aroundPrice;

  @Schema(
      title = "sale"
  )
  private String sale;

  @Schema(
      title = "detailUrl"
  )
  private String detailUrl;

  @Schema(
      title = "tags"
  )
  private String tags;

  @Schema(
      title = "openTime"
  )
  private String openTime;

  @Schema(
      title = "houseType"
  )
  private Integer houseType;

  @Schema(
      title = "usage"
  )
  private Integer usage;

  @Schema(
      title = "originType"
  )
  private Integer originType;

  @Schema(
      title = "sourceFile"
  )
  private String sourceFile;

  @Schema(
      title = "创建时间"
  )
  private LocalDateTime createdTime;

  @Schema(
      title = "修改时间"
  )
  private LocalDateTime modifiedTime;

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

  public String getPriceUnit() {
    return priceUnit;
  }

  public void setPriceUnit(String priceUnit) {
    this.priceUnit = priceUnit;
  }

  public String getRooms() {
    return rooms;
  }

  public void setRooms(String rooms) {
    this.rooms = rooms;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getAroundPrice() {
    return aroundPrice;
  }

  public void setAroundPrice(String aroundPrice) {
    this.aroundPrice = aroundPrice;
  }

  public String getSale() {
    return sale;
  }

  public void setSale(String sale) {
    this.sale = sale;
  }

  public String getDetailUrl() {
    return detailUrl;
  }

  public void setDetailUrl(String detailUrl) {
    this.detailUrl = detailUrl;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getOpenTime() {
    return openTime;
  }

  public void setOpenTime(String openTime) {
    this.openTime = openTime;
  }

  public Integer getHouseType() {
    return houseType;
  }

  public void setHouseType(Integer houseType) {
    this.houseType = houseType;
  }

  public Integer getUsage() {
    return usage;
  }

  public void setUsage(Integer usage) {
    this.usage = usage;
  }

  public Integer getOriginType() {
    return originType;
  }

  public void setOriginType(Integer originType) {
    this.originType = originType;
  }

  public String getSourceFile() {
    return sourceFile;
  }

  public void setSourceFile(String sourceFile) {
    this.sourceFile = sourceFile;
  }

  public LocalDateTime getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  public LocalDateTime getModifiedTime() {
    return modifiedTime;
  }

  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }
}
