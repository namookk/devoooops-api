package info.devoooops.common.error;

import lombok.Getter;

import javax.management.loading.MLetContent;

@Getter
public enum ErrorConst {

    /**
     * -1001 ~ -1999
     * 400 Bad Request
     */
    DUPLICATE_ID(-1000, "중복된 아이디입니다."),

    MISSING_REQUIRED_PARAMETER(-1001, "필수 파라미터가 누락 되었습니다."),

    BAD_CREDENTIAL(-1002, "아이디 및 비밀번호가 일치하지 않습니다."),

    INVALID_PARAMETER_ERROR(-1100, "Invalid Parameter Error"),

    /**
     * -2001 ~ -2999
     * 401 Unauthorized
     */

    REQUIRED_AUTH(-2000, "인증이 필요합니다."),

    /**
     * -3001 ~ -3999
     * 403 Forbidden
     */

    /**
     * -4001 ~ -4999
     * 404 Not found
     */

    NOT_FOUND_DATA(-4000, "데이터가 없습니다."),

    /**
     * -5001 ~ 5999
     * other 4xx errors
     */

    /**
     * -6001 ~ 6999
     */

    /**
     * -7001 ~ 7999
     */

    /**
     * -8001 ~ 8999
     */

    /**
     * -9001 ~ 9999
     * 500 INTERNAL SERVER ERROR
     * 5xx Unexpected Error
     */

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
