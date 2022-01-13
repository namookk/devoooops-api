package info.devoooops.common.error.exception;

import info.devoooops.common.error.ErrorConst;

import java.util.Optional;

public class DevRuntimeException extends RuntimeException{

    private int code;


    public DevRuntimeException() {

    }

    public DevRuntimeException(ErrorConst errorConst, Throwable cause) {
        super(errorConst.getMsg(), cause);
        this.code = errorConst.getCode();
    }

    public DevRuntimeException(ErrorConst errorConst) {
        super(errorConst.getMsg());
        this.code = errorConst.getCode();
    }

    public DevRuntimeException(ErrorConst errorConst, String customMsg) {
        super(customMsg);
        this.code = errorConst.getCode();
    }

    public DevRuntimeException(String prefix, ErrorConst errorConst, String suffix) {
        super(Optional.ofNullable(prefix).orElse("") + errorConst.getMsg() + Optional.ofNullable(suffix).orElse(""));
        this.code = errorConst.getCode();
    }

    public DevRuntimeException(String message) {
        super(message);
    }

    public DevRuntimeException(Throwable cause) {
        super(cause);
    }

    public DevRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DevRuntimeException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
