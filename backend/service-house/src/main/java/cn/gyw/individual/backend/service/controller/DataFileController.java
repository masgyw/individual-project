package cn.gyw.individual.backend.service.controller;

import cn.gyw.individual.backend.service.domain.house.query.HouseQuery;
import cn.gyw.individual.backend.service.api.response.DataFileResponse;
import cn.gyw.individual.backend.service.domain.house.service.DataFileService;
import cn.gyw.individual.backend.service.domain.house.service.IHouseService;
import cn.gyw.individual.backend.service.domain.house.vo.BaseHouseVO;
import cn.gyw.individual.backend.service.domain.house.vo.HouseVO;
import cn.gyw.individual.commons.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @date 2022/1/17 19:09
 */
@Slf4j
@RestController
@RequestMapping("datafile/v1")
public class DataFileController {

    @Resource(name = "dataFileService")
    private DataFileService dataFileService;
    @Resource
    private IHouseService houseService;

    @GetMapping("")
    public List<DataFileResponse> queryDataFile(String startDate, String endDate) {
        log.info("查询数据文件列表，日期：{}-{}", startDate, endDate);
        // 文件列表
        Set<String> fileSet = dataFileService.getFileNameList(startDate, endDate);

        HouseQuery houseQuery = new HouseQuery();
        houseQuery.setStartCrawlDate(DateUtil.parse(startDate, DateUtil.YYYYMMDD));
        houseQuery.setEndCrawlDate(DateUtil.parse(endDate, DateUtil.YYYYMMDD));
        List<HouseVO> sourceFileList = houseService.findByRange(houseQuery);
        Set<String> dbSet = sourceFileList.stream().map(BaseHouseVO::getSourceFile).collect(Collectors.toSet());
        return fileSet.stream()
                .sorted(Comparator.comparing(s -> s))
                .map(name -> {
                    DataFileResponse resp = new DataFileResponse();
                    resp.setFileName(name);
                    resp.setDbFlag(dbSet.contains(name));
                    return resp;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/import")
    public boolean importFile(@RequestParam("fileName") String fileName) {
        return dataFileService.importFile(fileName);
    }
}
