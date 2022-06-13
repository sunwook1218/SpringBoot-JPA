package com.example.demo.common.util;

public class TypeCastUtils {

    public static <T> T cast(Object origin, Class<T> castType) throws TypeCastException {
        if (origin == null) {
            throw ExceptionUtils.callElementLoggingException(() -> new TypeCastException("origin Object is null"));
        }

        if (castType.isInstance(origin)) {
            return (T) origin;
        } else {
            throw ExceptionUtils.callElementLoggingException(() -> new TypeCastException("origin Object is not " + castType.getName() + " instance"));
        }
    }

    public static class TypeCastException extends Exception {

        public TypeCastException() {
            super();
        }

        public TypeCastException(String message) {
            super(message);
        }

        public TypeCastException(String message, Throwable cause) {
            super(message, cause);
        }

        public TypeCastException(Throwable cause) {
            super(cause);
        }

        protected TypeCastException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

}