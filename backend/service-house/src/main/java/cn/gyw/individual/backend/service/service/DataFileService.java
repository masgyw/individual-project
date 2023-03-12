package cn.gyw.individual.backend.service.service;

import java.util.Set;

/**
 * 数据文件服务
 *
 * @date 2022/1/17 19:16
 */
public interface DataFileService {

    Set<String> getFileList(String startDate, String endDate);

    boolean importFile(String fileName);
}
