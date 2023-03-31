package cn.gyw.individual.commons.order.matcher;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 匹配器
 * <p>
 * 参考：byte-buddy 类 net.bytebuddy.matcher.ElementMatcher
 *
 * @date 2023/3/31
 */
public interface ElementMatcher<T> {

    /**
     * @param target 待匹配的实例
     * @return true-匹配；其他-false
     */
    boolean matches(T target);

    interface Junction<S> extends ElementMatcher<S> {

        <U extends S> Junction<U> and(ElementMatcher<? super U> other);

        <U extends S> Junction<U> or(ElementMatcher<? super U> other);

        abstract class AbstractBase<V> implements Junction<V> {

            @SuppressWarnings("unchecked")
            @Override
            public <U extends V> Junction<U> and(ElementMatcher<? super U> other) {
                return new Conjunction<U>(this, other);
            }

            @SuppressWarnings("unchecked")
            @Override
            public <U extends V> Junction<U> or(ElementMatcher<? super U> other) {
                return new Disjunction<U>(this, other);
            }
        }

        /**
         * 全都匹配
         *
         * @param <W>
         */
        class Conjunction<W> extends AbstractBase<W> {

            private final List<ElementMatcher<? super W>> matchers;

            public Conjunction(ElementMatcher<? super W>... matcher) {
                this(Arrays.asList(matcher));
            }

            @SuppressWarnings("unchecked")
            public Conjunction(List<ElementMatcher<? super W>> matchers) {
                this.matchers = new ArrayList<>(matchers.size());
                for (ElementMatcher<? super W> matcher : matchers) {
                    if (matcher instanceof Conjunction<?>) {
                        this.matchers.addAll(((Conjunction<? super W>) matcher).matchers);
                    } else {
                        this.matchers.add(matcher);
                    }
                }
            }

            /**
             * @param target 待匹配的实例
             * @return true-匹配；其他-false
             */
            @Override
            public boolean matches(@Nullable W target) {
                for (ElementMatcher<? super W> matcher : matchers) {
                    if (!matcher.matches(target)) {
                        return false;
                    }
                }
                return true;
            }

            @Override
            public String toString() {
                StringBuilder builder = new StringBuilder("(");
                boolean first = true;
                for (ElementMatcher<? super W> matcher : matchers) {
                    if (first) {
                        first = false;
                    } else {
                        builder.append(" and ");
                    }
                    builder.append(matcher);
                }
                return builder.append(")").toString();
            }
        }

        /**
         * 匹配一个即可
         *
         * @param <W>
         */
        class Disjunction<W> extends AbstractBase<W> {

            private final List<ElementMatcher<? super W>> matchers;

            public Disjunction(ElementMatcher<? super W>... matcher) {
                this(Arrays.asList(matcher));
            }

            @SuppressWarnings("unchecked")
            public Disjunction(List<ElementMatcher<? super W>> matchers) {
                this.matchers = new ArrayList<>(matchers.size());
                for (ElementMatcher<? super W> matcher : matchers) {
                    if (matcher instanceof Disjunction<?>) {
                        this.matchers.addAll(((Disjunction<? super W>) matcher).matchers);
                    } else {
                        this.matchers.add(matcher);
                    }
                }
            }

            /**
             * @param target 待匹配的实例
             * @return true-匹配；其他-false
             */
            @Override
            public boolean matches(@Nullable W target) {
                for (ElementMatcher<? super W> matcher : matchers) {
                    if (matcher.matches(target)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String toString() {
                StringBuilder builder = new StringBuilder("(");
                boolean first = true;
                for (ElementMatcher<? super W> matcher : matchers) {
                    if (first) {
                        first = false;
                    } else {
                        builder.append(" or ");
                    }
                    builder.append(matcher);
                }
                return builder.append(")").toString();
            }
        }

        /**
         * 不可为null值的匹配器
         *
         * @param <W> 带匹配的对象
         */
        abstract class ForNonNullValues<W> extends AbstractBase<W> {
            @Override
            public boolean matches(@Nullable W target) {
                return target != null && doMatch(target);
            }

            protected abstract boolean doMatch(W target);
        }
    }
}
