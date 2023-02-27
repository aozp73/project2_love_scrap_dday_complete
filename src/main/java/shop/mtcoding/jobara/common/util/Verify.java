package shop.mtcoding.jobara.common.util;

import org.springframework.http.HttpStatus;

import shop.mtcoding.jobara.common.ex.CustomException;

public class Verify {

    public static void validateStiring(String target, String msg) {
        if (target == null || target.isEmpty()) {
            throw new CustomException(msg);
        }
    }

    public static void validateStiring(String target, String msg, HttpStatus status) {
        if (target == null || target.isEmpty()) {
            throw new CustomException(msg, status);
        }
    }

    public static void validateStiring(String target, String msg, HttpStatus status, String location) {
        if (target == null || target.isEmpty()) {
            throw new CustomException(msg, status, location);
        }
    }

    public static void validateObject(Object target, String msg) {
        if (target == null) {
            throw new CustomException(msg);
        }
    }

    public static void validateObject(Object target, String msg, HttpStatus status) {
        if (target == null) {
            throw new CustomException(msg, status);
        }
    }

    public static void validateObject(Object target, String msg, HttpStatus status, String location) {
        if (target == null) {
            throw new CustomException(msg, status, location);
        }
    }
}
