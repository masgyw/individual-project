package cn.gyw.individual.starters.security.base.extension;

import cn.gyw.individual.starters.security.base.BaseJwtUser;

public interface UserContextAware {

    /**
     * 处理token
     *
     * @param token
     * @return
     */
    BaseJwtUser processToken(String token);

}
