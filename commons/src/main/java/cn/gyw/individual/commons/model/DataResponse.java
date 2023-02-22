package cn.gyw.individual.commons.model;

import cn.gyw.individual.commons.enums.BaseEnum;
import cn.gyw.individual.commons.enums.CodeEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Objects;

/**
 * 响应对象
 *
 * @param <E> 数据
 */
@Data
public final class DataResponse<E> {

    @Setter(AccessLevel.PRIVATE)
    private Integer code;
    @Setter(AccessLevel.PRIVATE)
    private String msg;
    @Setter(AccessLevel.PRIVATE)
    private E result;

    private DataResponse() {
    }

    public static <E> DataResponse<E> success(E e) {
        DataResponse<E> object = new DataResponse<>();
        object.setCode(CodeEnum.Success.getCode());
        object.setMsg(CodeEnum.Success.getName());
        object.setResult(e);
        return object;
    }

    public static <E> DataResponse<E> success(E t, String msg) {
        DataResponse<E> obj = success(t);
        obj.setMsg(msg);
        return obj;
    }

    public static DataResponse fail(BaseEnum codeEnum) {
        DataResponse object = new DataResponse();
        object.setMsg(codeEnum.getName());
        object.setCode(codeEnum.getCode());
        return object;
    }

    public static DataResponse fail(String msg) {
        DataResponse object = new DataResponse();
        object.setMsg(msg);
        object.setCode(CodeEnum.Fail.getCode());
        return object;
    }

    public static <E> DataResponse<E> fail(E e, String msg) {
        DataResponse<E> object = new DataResponse<>();
        object.setCode(CodeEnum.Fail.getCode());
        object.setMsg(msg);
        object.setResult(e);
        return object;
    }

    public static <E> DataResponse<E> res(BaseEnum codeEnum, E e) {
        DataResponse<E> object = new DataResponse<>();
        object.setMsg(codeEnum.getName());
        object.setCode(codeEnum.getCode());
        object.setResult(e);
        return object;
    }

    public boolean isSuccess() {
        return Objects.equals(CodeEnum.Success.getCode(), this.getCode());
    }
}
