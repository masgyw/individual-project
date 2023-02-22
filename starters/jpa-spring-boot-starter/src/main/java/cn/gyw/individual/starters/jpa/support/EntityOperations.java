package cn.gyw.individual.starters.jpa.support;

import org.springframework.data.repository.CrudRepository;

@SuppressWarnings("unchecked")
public abstract class EntityOperations {

  public static <T, ID> EntityUpdater<T, ID> doUpdate(CrudRepository<T, ID> repository) {
    return new EntityUpdater<>(repository);
  }

  public static <T, ID> EntityCreator<T, ID> doCreate(CrudRepository<T, ID> repository) {
    return new EntityCreator(repository);
  }


}
