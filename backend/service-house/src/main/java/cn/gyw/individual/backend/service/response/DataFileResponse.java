package cn.gyw.individual.backend.service.response;

import lombok.Data;

/**
 * @date 2023/3/11
 */
@Data
public class DataFileResponse {

    // 文件名
    private String fileName;
    // 是否已入库
    private Boolean dbFlag;

}
