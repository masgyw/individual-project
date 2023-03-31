package cn.gyw.individual.commons.order.pipeline;

/**
 * @date 2023/3/31
 */
public interface Handler<I, O> {

    O process(I input);
}
