package cn.gyw.backend.house.domain.house.service;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * 数据文件服务
 *
 * @date 2022/1/17 19:16
 */
public interface DataFileService {

    Set<String> getFileNameList(String startDate, String endDate);

    List<File> findFile(final String fileName, String crawlDate);

    boolean importFile(String fileName);
}
