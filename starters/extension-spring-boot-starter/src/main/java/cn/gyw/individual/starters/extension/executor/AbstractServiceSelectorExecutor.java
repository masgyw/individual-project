package cn.gyw.individual.starters.extension.executor;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @date 2023/7/21
 */
public abstract class AbstractServiceSelectorExecutor implements ServiceExecutor {

    @Override
    public <S> void execute(Class<S> clazz, BizScene bizScene, Consumer<S> consumer) {
        S service = selectService(bizScene, clazz);
        consumer.accept(service);
    }

    @Override
    public <S, R> R execute(Class<S> clazz, BizScene bizScene, Function<S, R> function) {
        S service = selectService(bizScene, clazz);
        return function.apply(service);
    }

    /**
     * 根据bizId 查询Service
     *
     * @param bizScene
     * @param clazz
     * @param <S>
     * @return
     */
    protected abstract <S> S selectService(BizScene bizScene, Class<S> clazz);
}
