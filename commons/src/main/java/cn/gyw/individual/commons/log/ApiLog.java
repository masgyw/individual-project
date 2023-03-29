package cn.gyw.individual.commons.log;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 请求API日志对象
 */
@Data
public class ApiLog implements Serializable {

    private static final long serialVersionUID = -2819065952237845813L;
    /**
     * 日志id
     */
    private String sequenceNum;
    /**
     * 当前操作人id
     */
    private String loginAccount;
    /**
     * 服务api
     */
    private String host;
    /**
     * 服务端口
     */
    private String port;
    /**
     * 操作请求的链接
     */
    private String actionUrl;
    /**
     * 执行的模块
     */
    private String module;
    /**
     * 执行的方法
     */
    private String method;
    /**
     * 描述
     */
    private String desc;
    /**
     * 请求查询参数
     */
    private String queryParam;
    /**
     * 请求数据
     */
    private String reqData;
    /**
     * 响应码
     */
    private String respCode;
    /**
     * 响应数据
     */
    private String respData;
    /**
     * 执行的时间
     */
    private Date gmtCreate;
}
