// ---Auto Generated ---
package cn.gyw.backend.template.api.request;

import cn.gyw.individual.commons.model.DictValue;
import cn.gyw.individual.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

@Schema
public class SelectDictCreateRequest implements Request {
  @Schema(
      title = "字典编码"
  )
  private String code;

  @Schema(
      title = "名称"
  )
  private String name;

  @Schema(
      title = "描述信息"
  )
  private String description;

  @Schema(
      title = "字典类型"
  )
  private Integer dictType;

  @Schema(
      title = "http 接口地址"
  )
  private String httpUrl;

  @Schema(
      title = "选择值列表"
  )
  private List<DictValue> selectValues;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getDictType() {
    return dictType;
  }

  public void setDictType(Integer dictType) {
    this.dictType = dictType;
  }

  public String getHttpUrl() {
    return httpUrl;
  }

  public void setHttpUrl(String httpUrl) {
    this.httpUrl = httpUrl;
  }

  public List<DictValue> getSelectValues() {
    return selectValues;
  }

  public void setSelectValues(List<DictValue> selectValues) {
    this.selectValues = selectValues;
  }
}
