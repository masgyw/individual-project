package cn.gyw.individual.starters.jpa.support;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @date 2023/3/10
 */
public interface BatchExecutor<T> {

    Optional<Iterable<T>> batchExecute();

    Executor<Iterable<T>> batchSuccessHook(Consumer<Iterable<T>> consumer);

    Executor<Iterable<T>> batchErrorHook(Consumer<? super Throwable> consumer);

}
