package cn.gyw.individual.starters.jpa.support;

import java.util.function.Supplier;

public interface Loader<T, ID> {

    UpdateHandler<T> loadById(ID id);

    UpdateHandler<T> load(Supplier<T> t);

}
