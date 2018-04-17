// Copyright (c) 1998-2018 Core Solutions Limited. All rights reserved.
// ============================================================================
// CURRENT VERSION CNT.5.0.1
// ============================================================================
// CHANGE LOG
// CNT.5.0.1 : 2018-03-28, derrick.liang, creation
// ============================================================================
package com.star.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author derrick.liang
 */
@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

}
