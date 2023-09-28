package cn.gyw.backend.house.domain.house.service.fileresolver;

import cn.gyw.backend.house.domain.house.creator.HouseCreator;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 文本文件读取
 *
 * @date 2023/9/28
 */
@Slf4j
public class LianJiaDataFileResolver extends AbstractDataFileResolver {

    private static final String DATA_SPLIT = ",";
    // E:\Temp\python\lj-house_20230915_070201.csv

    public List<String> readFile(String filePath) {
        Path path = Paths.get(filePath);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String[] headers = br.readLine().split(",");
            handleHeaders(headers);
            System.out.println("headers = " + Arrays.toString(headers));
            String line;
            while (Objects.nonNull(line = br.readLine())) {
                HouseCreator houseCreator = buildHouseCreator(headers, line.split(DATA_SPLIT), filePath);
                System.out.println(JSON.toJSONString(houseCreator));
            }
        } catch (Exception e) {
            log.error("文件读取异常：", e);
        }
        return null;
    }

    private void handleHeaders(String[] headers) {

    }


    public static void main(String[] args) {
        String filePath = "E:\\Temp\\python\\lj-house_20230915_070201.csv";
        new LianJiaDataFileResolver().readFile(filePath);
    }
}
