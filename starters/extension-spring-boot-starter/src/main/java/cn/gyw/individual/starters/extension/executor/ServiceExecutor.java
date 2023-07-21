package cn.gyw.individual.starters.extension.executor;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @date 2023/7/21
 */
public interface ServiceExecutor {

    /**
     * 执行无返回值
     * @param clazz
     * @param bizScene
     * @param consumer
     * @param <S>
     */
    <S> void execute(Class<S> clazz, BizScene bizScene, Consumer<S> consumer);

    /**
     * 执行有返回值
     * @param clazz
     * @param bizScene
     * @param function
     * @param <S>
     * @param <R>
     * @return
     */
    <S, R> R execute(Class<S> clazz, BizScene bizScene, Function<S, R> function);

}
