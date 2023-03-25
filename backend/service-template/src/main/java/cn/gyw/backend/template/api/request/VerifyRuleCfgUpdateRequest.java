// ---Auto Generated ---
package cn.gyw.backend.template.api.request;

import cn.gyw.backend.template.domain.verifyrule.rule.VerifyRule;
import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;
import java.util.List;

@Schema
public class VerifyRuleCfgUpdateRequest implements Request {
  @Schema(
      title = "描述信息"
  )
  private String descInfo;

  @Schema(
      title = "规则名称"
  )
  private String name;

  @Schema(
      title = "模板项ID"
  )
  private Long itemId;

  @Schema(
      title = "校验规则列表"
  )
  private List<VerifyRule> ruleList;

  private Long id;

  public String getDescInfo() {
    return descInfo;
  }

  public void setDescInfo(String descInfo) {
    this.descInfo = descInfo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  public List<VerifyRule> getRuleList() {
    return ruleList;
  }

  public void setRuleList(List<VerifyRule> ruleList) {
    this.ruleList = ruleList;
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
