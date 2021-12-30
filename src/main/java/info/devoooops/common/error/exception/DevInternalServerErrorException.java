package info.devoooops.common.error.exception;

import info.devoooops.common.error.ErrorConst;

public class DevInternalServerErrorException extends DevException{
    public DevInternalServerErrorException(ErrorConst errorConst, Throwable cause) {
        super(errorConst, cause);
    }
    public DevInternalServerErrorException(ErrorConst errorConst) {
        super(errorConst);
    }
}
