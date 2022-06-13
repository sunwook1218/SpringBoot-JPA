package com.example.demo.common.util;

import java.util.function.Supplier;

public class ExceptionUtils {

    public static <E extends Exception> E callElementLoggingException(Supplier<E> exception) {
        E customException = exception.get();
        StackTraceElement callElement = customException.getStackTrace()[2];

//        Logger.getLogger(callElement.getClass()).error("ERROR occurred at [" + callElement.getClassName() + "." + callElement.getMethodName() + "]");
        return customException;
    }

}