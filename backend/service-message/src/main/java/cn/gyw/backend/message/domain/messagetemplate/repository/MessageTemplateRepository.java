// ---Auto Generated ---
package cn.gyw.backend.message.domain.messagetemplate.repository;

import cn.gyw.backend.message.domain.messagetemplate.MessageTemplate;
import cn.gyw.individual.starters.jpa.support.BaseRepository;
import java.lang.Long;
import java.util.Optional;

public interface MessageTemplateRepository extends BaseRepository<MessageTemplate, Long> {

    /**
     * 根据模板编码查询模板
     * @param templateCode
     * @return
     */
    Optional<MessageTemplate> findByTemplateCode(String templateCode);
}
