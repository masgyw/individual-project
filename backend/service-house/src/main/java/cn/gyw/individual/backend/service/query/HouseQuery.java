// ---Auto Generated ---
package cn.gyw.individual.backend.service.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Schema
@Data
public class HouseQuery extends BaseHouseQuery {

    /**
     * 爬取日期-开始
     */
    private LocalDate startCrawlDate;

    /**
     * 爬取日期-结束
     */
    private LocalDate endCrawlDate;
}
