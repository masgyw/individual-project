package cn.gyw.individual.backend.service.shedule;

import cn.gyw.individual.backend.service.creator.HouseCreator;
import cn.gyw.individual.backend.service.dto.HouseInfoDto;
import cn.gyw.individual.backend.service.query.HouseQuery;
import cn.gyw.individual.backend.service.service.IHouseService;
import cn.gyw.individual.backend.service.vo.HouseVO;
import cn.gyw.individual.commons.model.PageRequestWrapper;
import cn.gyw.individual.commons.utils.DateUtil;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 房屋信息CSV数据文件读取入库
 *
 * @date 2021/11/9 17:59
 */
@Slf4j
@Component
public class HouseInfoCsvReader {
    /**
     * 数据文件存储目录
     */
    @Value("${house.dir.storage}")
    private String csvStorageDir;
    @Value("${house.dir.backup}")
    private String csvBackupDir;

    private IHouseService houseInfoService;

    /**
     * 读取本地数据文件并保存DB
     *
     * @param fileName 文件名
     * @return true/false
     */
    public boolean readAndSaveDbByFile(String fileName) {
        if (isImported(fileName, null)) {
            log.info("文件[{}]数据已经入库，无需再次入库！", fileName);
            return false;
        }
        List<File> fileList = findCsvFile(fileName, null);
        return readAndSaveDB(fileList);
    }

    /**
     * 读取昨天数据文件并保存DB
     *
     * @return true/false
     */
    public boolean readAndSaveDbByYesterday() {
        LocalDate dayOfYesterday = DateUtil.getDateOfYesterday();
        String yesterdayDateStr = DateUtil.formatDate(dayOfYesterday);
        if (isImported(null, dayOfYesterday)) {
            log.info("日期[{}]数据已经入库，无需再次入库！", yesterdayDateStr);
            return false;
        }
        List<File> fileList = findCsvFile(null, yesterdayDateStr);
        return readAndSaveDB(fileList);
    }

    private boolean readAndSaveDB(List<File> fileList) {
        // 1. 校验数据文件列表
        if (CollectionUtils.isEmpty(fileList)) {
            log.error("数据文件不存在...");
            return false;
        }
        boolean isSuccess = false;
        for (File file : fileList) {
            // 2. 读取前一天的数据(不读当天的)
            List<HouseInfoDto> dataList = readCsvData(file);
            if (CollectionUtils.isEmpty(dataList)) {
                log.error("文件数据不存在或读取失败！");
                return false;
            }
            // 3. 存储到关系型数据库
            isSuccess = saveDB(dataList);
            log.info("存储文件[{}],结果：{}", file.getName(), isSuccess);
            // 4. 文件处理成功后，移动文件到已处理目录
            if (isSuccess) {
                // 5. 备份源数据文件
                backupDataSourceFile(file.toPath());
            }
        }
        return isSuccess;
    }

    private void backupDataSourceFile(Path srcPath) {
        Path backupDir = Paths.get(csvBackupDir);
        // 目标目录是否存在
        if (!Files.exists(backupDir, LinkOption.NOFOLLOW_LINKS)) {
            try {
                Files.createDirectory(backupDir);
            } catch (IOException e) {
                log.error("备份源文件目录创建异常：", e);
            }
        }
        Path target = Paths.get(backupDir.toString(), srcPath.getFileName().toString());
        try {
            Files.move(srcPath, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("文件移动失败：", e);
        }
    }

    private boolean saveDB(List<HouseCreator> dataList) {
        return houseInfoService.batchInsert(dataList);
    }

    private List<HouseInfoDto> readCsvData(File file) {
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String[] cnHeaders = csvReader.readNext();
            String[] enHeaders = csvReader.readNext();
            if (Objects.isNull(cnHeaders) || Objects.isNull(enHeaders)) {
                log.error("Csv file headers is null, check data content!");
                return Collections.emptyList();
            }
            List<HouseInfoDto> houseInfoDtoList = new ArrayList<>();
            String[] data;
            while ((data = csvReader.readNext()) != null) {
                houseInfoDtoList.add(buildHouseInfoDto(enHeaders, data, file.getName()));
            }
            return houseInfoDtoList;
        } catch (Exception e) {
            log.error("error :", e);
        } finally {
            try {
                if (Objects.nonNull(csvReader)) {
                    csvReader.close();
                }
            } catch (IOException ex) {
                log.error("close csvReader error :", ex);
            }
        }
        return Collections.emptyList();
    }

    private HouseInfoDto buildHouseInfoDto(String[] enHeaders, String[] data, String source) {
        HouseInfoDto houseInfoDto = new HouseInfoDto();
        // 文件名
        houseInfoDto.setSourceFile(source);
        // 文件数据
        for (int i = 0, len = enHeaders.length; i < len; i++) {
            String prop = enHeaders[i];
            try {
                PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(houseInfoDto, prop);
                pd.getWriteMethod().invoke(houseInfoDto, data[i]);
            } catch (Exception e) {
                log.error("write filed error [" + prop + "] , error :", e);
            }
        }
        return houseInfoDto;
    }

    private List<File> findCsvFile(final String fileName, String crawlDate) {
        Path storageDir = Paths.get(Paths.get(csvStorageDir).toUri());
        try {
            return Files.walk(storageDir)
                    .peek(path -> log.debug("访问文件path :{}", path.toString()))
                    // 文件后缀过滤
                    .filter(path -> path.getFileName().toString().endsWith(".csv"))
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

    /**
     * 校验是否导入
     *
     * @param fileName  文件名
     * @param crawlDate 爬取日期
     * @return 是否已导入
     */
    private boolean isImported(String fileName, LocalDate crawlDate) {
        if (StringUtils.isEmpty(fileName) && Objects.isNull(crawlDate)) {
            throw new RuntimeException("文件名和爬取日期不能同时为空");
        }
        PageRequestWrapper<HouseQuery> wrapper = new PageRequestWrapper<>();
        wrapper.setPage(1);
        wrapper.setPageSize(1);
        HouseQuery houseQuery = new HouseQuery();
        houseQuery.setSourceFile(fileName);
        houseQuery.setCrawlDate(crawlDate);
        wrapper.setBean(houseQuery);
        Page<HouseVO> voPage = houseInfoService.findByPage(wrapper);
        return voPage.hasContent();
    }

    @Autowired
    public void setHouseInfoService(IHouseService houseInfoService) {
        this.houseInfoService = houseInfoService;
    }
}
