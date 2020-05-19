package com.blingwei.musicService.utils;

import com.blingwei.musicService.result.Result;

public class ResultFactory {
    public static Result buildSuccessResult(String message, Object data) {
        return new Result(200, message, data);
    }

    public static Result buildFailResult(String message) {
        return new Result(400, message, null);
    }
}
