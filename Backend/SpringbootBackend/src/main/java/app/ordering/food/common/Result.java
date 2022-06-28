package app.ordering.food.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private String code;
    private String  msg;
    private T       data;

    public static <T> Result<T> success(T data) {
        return new Result<>("0000000", "OK", data);
    }

    public static <T> Result<T> success(T data, String msg) {
        return new Result<>("0000000", msg, data);
    }

    public static <T> Result<T> success() {
        return new Result<>("0000000", "OK", null);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>("0000000", msg, null);
    }

    public static <T> Result<T> error(String code, String msg) {
        return new Result<>(code, msg, null);
    }
}
