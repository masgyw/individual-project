package cn.gyw.backend.house.domain.house.service;

import cn.gyw.backend.house.shedule.HouseInfoCsvReader;
import cn.gyw.backend.house.config.HouseProperties;
import cn.gyw.backend.house.enums.HouseTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @date 2022/1/17 19:22
 */
@Slf4j
@Service("dataFileService")
public class DataFileServiceImpl implements DataFileService {

    private static final String DATA_FILE_SUFFIX = "csv";

    @Resource
    private HouseProperties houseProperties;
    @Resource
    private HouseInfoCsvReader houseInfoCsvReader;

    @Override
    public Set<String> getFileNameList(String startDate, String endDate) {
        String fileRoot = houseProperties.getStorageDir();
        try {
            return Files.walk(Paths.get(fileRoot), 2)
                    // 文件后缀过滤
                    .filter(path -> path.toString().endsWith(DATA_FILE_SUFFIX))
                    // houseType 过滤
                    .filter(path -> path.toString().contains(HouseTypeEnum.NEW.name().toLowerCase())
                            || path.toString().contains(HouseTypeEnum.OLD.name().toLowerCase()))
                    .peek(path -> log.debug("CSV文件：{}", path))
                    .map(path -> path.getFileName().toString())
                    // 根据起止日期过滤
                    .filter(fileName -> {
                        String date = fileName.substring(fileName.lastIndexOf("_") + 1, fileName.lastIndexOf("."));
                        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
                    })
                    .peek(fileName -> log.debug("过滤后CSV文件：{}", fileName))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            log.error("读取数据文件异常：", e);
        }
        return Collections.emptySet();
    }

    @Override
    public List<File> findFile(String fileName, String crawlDate) {
        String fileRoot = houseProperties.getStorageDir();
        try {
            return Files.walk(Paths.get(fileRoot), 1)
                    .peek(path -> log.info("访问文件path :{}", path.toString()))
                    // 文件名过滤
                    .filter(path -> {
                        if (StringUtils.isEmpty(fileName)) {
                            return true;
                        }
                        return path.getFileName().toString().equals(fileName);
                    })
                    // 爬取日期过滤
                    .filter(path -> {
                        if (StringUtils.isEmpty(crawlDate)) {
                            return true;
                        }
                        return path.getFileName().toString().contains(crawlDate);
                    })
                    .map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean importFile(String fileName) {
        return houseInfoCsvReader.readAndSaveDbByFile(fileName);
    }
}
