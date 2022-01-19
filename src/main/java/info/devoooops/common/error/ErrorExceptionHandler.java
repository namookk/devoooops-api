package info.devoooops.common.error;

import info.devoooops.common.error.exception.*;
import info.devoooops.util.ApiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

import static info.devoooops.util.ApiUtils.error;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorExceptionHandler {

    private ResponseEntity<ApiUtils.ApiResult<?>> newResponse(ErrorResponse errorResponse, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(error(errorResponse), headers, status);
    }

    private ResponseEntity<ApiUtils.ApiResult<?>> newResponse(String message, HttpStatus status, String details) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(error(status, message, details), headers, status);
    }

    /**
     * 400 에러
     * @param e
     */
    @ExceptionHandler(value = {DevBadRequestException.class})
    private ResponseEntity<?> handleBadRequestError(DevBadRequestException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(e.getCode())
                .msg(e.getMessage())
                .build();
        return newResponse(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * 400 에러 (request body error)
     * @param e
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    private ResponseEntity<?> handleBadRequestError(HttpMessageNotReadableException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(ErrorConst.MISSING_REQUIRED_PARAMETER.getCode())
                .msg(ErrorConst.MISSING_REQUIRED_PARAMETER.getMsg())
                .build();
        return newResponse(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * 400 에러 (request parameters binding error)
     * @param e
     */
    @ExceptionHandler(value = {ServletRequestBindingException.class})
    private ResponseEntity<?> handleBadRequestError(ServletRequestBindingException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(ErrorConst.MISSING_REQUIRED_PARAMETER.getCode())
                .msg(ErrorConst.MISSING_REQUIRED_PARAMETER.getMsg())
                .build();
        return newResponse(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * 400 요청에러(@Valid 에러)
     * @param e
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    private ResponseEntity<?> handleBadRequestValidError(MethodArgumentNotValidException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(ErrorConst.INVALID_PARAMETER_ERROR.getCode())
                .msg(e.getBindingResult().getFieldError().getDefaultMessage())
                .detail(Arrays.deepToString((new Exception()).getStackTrace()))
                .build();
        return newResponse(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * 400 요청에러(@Validated 에러)
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    private ResponseEntity<?> handleBadRequestValidatedError(ConstraintViolationException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(ErrorConst.INVALID_PARAMETER_ERROR.getCode())
                .msg(e.getMessage())
                .detail(Arrays.deepToString((new Exception()).getStackTrace()))
                .build();
        return newResponse(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * 401 인증에러
     * @param e
     */
    @ExceptionHandler(value = {DevUnauthorizedException.class})
    private ResponseEntity<?> handleUnauthorized(DevUnauthorizedException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(e.getCode())
                .msg(e.getMessage())
                .detail(Arrays.deepToString((new Exception()).getStackTrace()))
                .build();
        return newResponse(error, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 400 로그인에러
     * @param e
     */
    @ExceptionHandler(value = {BadCredentialsException.class})
    private ResponseEntity<?> handleBadCredentialsError(BadCredentialsException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(ErrorConst.BAD_CREDENTIAL.getCode())
                .msg(ErrorConst.BAD_CREDENTIAL.getMsg())
                .detail(e.getMessage())
                .build();
        return newResponse(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * 403 권한 에러
     * @param e
     */
    @ExceptionHandler(value = {DevForbiddenException.class})
    private ResponseEntity<?> handleForbiddenError(DevForbiddenException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(e.getCode())
                .msg(e.getMessage())
                .detail(Arrays.deepToString((new Exception()).getStackTrace()))
                .build();
        return newResponse(error, HttpStatus.FORBIDDEN);
    }

    /**
     * 404 에러
     * @param e
     */
    @ExceptionHandler(value = {DevNotFoundException.class})
    private ResponseEntity<?> handleNotFoundError( DevNotFoundException e){
        ErrorResponse error = ErrorResponse
                .builder()
                .code(e.getCode())
                .msg(e.getMessage())
                .detail(Arrays.deepToString((new Exception().getStackTrace())))
                .build();

        return newResponse(error, HttpStatus.NOT_FOUND);
    }

    /**
     * 500 서버 에러
     * @param e
     */
    @ExceptionHandler(value = {DevInternalServerErrorException.class})
    private ResponseEntity<?> handleInternalServerError(DevException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(e.getCode())
                .msg(e.getMessage())
                .detail(Arrays.deepToString(new Exception().getStackTrace()))
                .build();
        return newResponse(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * 500 서버 에러
     * @param e
     */
    @ExceptionHandler(value = {DevException.class})
    private ResponseEntity<?> handleDevExceptionError(DevException e) {
        ErrorResponse error = ErrorResponse
                .builder()
                .code(e.getCode())
                .msg(e.getMessage())
                .build();
        return newResponse(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 500 서버 에러
     * @param e
     */
    @ExceptionHandler(value = {Exception.class})
    private ResponseEntity<?> handleInternalServerError(Exception e) {
        e.printStackTrace();

        ErrorResponse error = ErrorResponse
                .builder()
                .code(ErrorConst.UNKNOWN_ERROR.getCode())
                .msg(ErrorConst.UNKNOWN_ERROR.getMsg())
                .detail(e.getMessage())
                .build();
        return newResponse(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
