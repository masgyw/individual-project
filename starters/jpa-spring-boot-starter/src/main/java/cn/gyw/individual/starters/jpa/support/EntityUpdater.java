package cn.gyw.individual.starters.jpa.support;

import cn.gyw.individual.commons.enums.CodeEnum;
import cn.gyw.individual.commons.exceptions.BusinessException;
import cn.gyw.individual.commons.validator.UpdateGroup;
import com.google.common.base.Preconditions;
import io.vavr.control.Try;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;

@Slf4j
public class EntityUpdater<T, ID> extends BaseEntityOperation implements Loader<T, ID>, UpdateHandler<T>, Executor<T> {

    private final CrudRepository<T, ID> repository;
    private T entity;
    private Consumer<T> successHook = t -> log.info("update success");
    private Consumer<? super Throwable> errorHook = e -> e.printStackTrace();

    public EntityUpdater(CrudRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> execute() {
        doValidate(this.entity, UpdateGroup.class);
        T save = Try.of(() -> repository.save(entity))
                .onSuccess(successHook)
                .onFailure(errorHook).getOrNull();
        return Optional.ofNullable(save);
    }

    @Override
    public UpdateHandler<T> loadById(ID id) {
        Preconditions.checkArgument(Objects.nonNull(id), "id is null");
        Optional<T> loadEntity = repository.findById(id);
        this.entity = loadEntity.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError));
        return this;
    }

    @Override
    public UpdateHandler<T> load(Supplier<T> t) {
        this.entity = t.get();
        return this;
    }

    @Override
    public Executor<T> update(Consumer<T> consumer) {
        Preconditions.checkArgument(Objects.nonNull(entity), "entity is null");
        consumer.accept(this.entity);
        return this;
    }

    @Override
    public Executor<T> successHook(Consumer<T> consumer) {
        this.successHook = consumer;
        return this;
    }

    @Override
    public Executor<T> errorHook(Consumer<? super Throwable> consumer) {
        this.errorHook = consumer;
        return this;
    }

}