package cn.gyw.backend.house.shedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @date 2023/3/7
 */
@Slf4j
@EnableScheduling
@Configuration
public class HouseScheduleConfig {

    private HouseInfoCsvReader houseInfoCsvReader;

    /**
     * 读取本地CSV数据并入库
     */
    @Scheduled(cron = "0 0 8,10,12 * * ?")
    public void readCsvAndSave() {
        log.info("Start read yesterday csv file and save to db...");
        boolean result = houseInfoCsvReader.readAndSaveDbByYesterday();
        log.info("End of save to db, result :{}", result);
    }

    @Autowired
    public void setHouseInfoCsvReader(HouseInfoCsvReader houseInfoCsvReader) {
        this.houseInfoCsvReader = houseInfoCsvReader;
    }
}
