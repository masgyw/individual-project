// ---Auto Generated ---
package cn.gyw.backend.system.api.request;

import cn.gyw.backend.system.domain.permission.resource.ResourceType;
import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;
import java.math.BigDecimal;

@Schema
public class ResourceUpdateRequest implements Request {
  @Schema(
      title = "资源名称"
  )
  private String name;

  @Schema(
      title = "资源链接"
  )
  private String url;

  @Schema(
      title = "资源编码"
  )
  private String code;

  @Schema(
      title = "前端路由"
  )
  private String router;

  @Schema(
      title = "父节点"
  )
  private Long pid;

  @Schema(
      title = "排序号"
  )
  private BigDecimal sortNum;

  @Schema(
      title = "icon class"
  )
  private String iconClass;

  @Schema(
      title = "资源类型"
  )
  private ResourceType resourceType;

  @Schema(
      title = "资源描述"
  )
  private String resourceDesc;

  @Schema(
      title = "平台id"
  )
  private Long platformId;

  private Long id;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getRouter() {
    return router;
  }

  public void setRouter(String router) {
    this.router = router;
  }

  public Long getPid() {
    return pid;
  }

  public void setPid(Long pid) {
    this.pid = pid;
  }

  public BigDecimal getSortNum() {
    return sortNum;
  }

  public void setSortNum(BigDecimal sortNum) {
    this.sortNum = sortNum;
  }

  public String getIconClass() {
    return iconClass;
  }

  public void setIconClass(String iconClass) {
    this.iconClass = iconClass;
  }

  public ResourceType getResourceType() {
    return resourceType;
  }

  public void setResourceType(ResourceType resourceType) {
    this.resourceType = resourceType;
  }

  public String getResourceDesc() {
    return resourceDesc;
  }

  public void setResourceDesc(String resourceDesc) {
    this.resourceDesc = resourceDesc;
  }

  public Long getPlatformId() {
    return platformId;
  }

  public void setPlatformId(Long platformId) {
    this.platformId = platformId;
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
