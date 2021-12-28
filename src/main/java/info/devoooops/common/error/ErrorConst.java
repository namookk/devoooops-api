package info.devoooops.common.error;

import lombok.Getter;

import javax.management.loading.MLetContent;

@Getter
public enum ErrorConst {

    // -1000 ~ 인증관련
    REQUIRED_AUTH(-1000, "인증이 필요합니다."),

    MISSING_REQUIRED_PARAMETER(-2000, "필수 파라미터가 누락 되었습니다."),
    INVALID_PARAMETER_ERROR(-2100, "Invalid Parameter Error"),

    UNKNOWN_ERROR(-9999, "알 수 없는 에러가 발생했습니다.");

    private int code;

    private String msg;

    ErrorConst(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(int code) {
        for(ErrorConst e : ErrorConst.values()) {
            if(e.code == code) {
                return e.msg;
            }
        }
        return null;
    }
}
