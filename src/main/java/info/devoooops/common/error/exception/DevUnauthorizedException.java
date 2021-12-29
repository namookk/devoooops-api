package info.devoooops.common.error.exception;

import info.devoooops.common.error.ErrorConst;

public class DevUnauthorizedException extends DevException{
    public DevUnauthorizedException(ErrorConst errorConst) {
        super(errorConst);
    }
}
