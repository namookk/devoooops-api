package info.devoooops.common.error.exception;

import info.devoooops.common.error.ErrorConst;

public class DevBadRequestException extends DevException{

    public DevBadRequestException(ErrorConst errorConst, Throwable cause) {
        super(errorConst, cause);
    }

    public DevBadRequestException(ErrorConst errorConst) {
        super(errorConst);
    }

    public DevBadRequestException(ErrorConst errorConst, String customMsg) {
        super(errorConst,customMsg);
    }
}
