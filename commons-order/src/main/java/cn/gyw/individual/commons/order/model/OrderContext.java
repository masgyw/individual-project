package cn.gyw.individual.commons.order.model;

/**
 * @date 2023/3/30
 */
public interface OrderContext {

    /**
     * 是否继续链
     *
     * @return true/false
     */
    boolean continueChain();
}
