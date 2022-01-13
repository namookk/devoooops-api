package info.devoooops.common.error.exception;

import info.devoooops.common.error.ErrorConst;
import lombok.Getter;

import java.util.Optional;

@Getter
public class DevException extends Exception{

    private int code;


    public DevException() {

    }

    public DevException(ErrorConst errorConst, Throwable cause) {
        super(errorConst.getMsg(), cause);
        this.code = errorConst.getCode();
    }

    public DevException(ErrorConst errorConst) {
        super(errorConst.getMsg());
        this.code = errorConst.getCode();
    }

    public DevException(ErrorConst errorConst, String customMsg) {
        super(customMsg);
        this.code = errorConst.getCode();
    }

    public DevException(String prefix, ErrorConst errorConst, String suffix) {
        super(Optional.ofNullable(prefix).orElse("") + errorConst.getMsg() + Optional.ofNullable(suffix).orElse(""));
        this.code = errorConst.getCode();
    }

    public DevException(String message) {
        super(message);
    }

    public DevException(Throwable cause) {
        super(cause);
    }

    public DevException(String message, Throwable cause) {
        super(message, cause);
    }

    public DevException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
