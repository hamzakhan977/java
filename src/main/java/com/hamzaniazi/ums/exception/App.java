package com.hamzaniazi.ums.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class App extends Throwable {

    private final String message;

}
