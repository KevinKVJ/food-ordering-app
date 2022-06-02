package app.ordering.food.common;

public enum ResultEnum {

    SUCCESS(1000),
    BAD_REQUEST(1001),
    NOT_FOUND(1002);

    private final Integer code;

    ResultEnum(int code) {
        this.code = code;
    }

    public static String getMsg(ResultEnum code) {
        switch (code) {
            case SUCCESS:
                return "OK";
            case BAD_REQUEST:
                return "BAD REQUEST";
            case NOT_FOUND:
                return "NOT FOUND";
            default:
                return "";
        }
    }

    public int getCode() {
        return code;
    }
}
