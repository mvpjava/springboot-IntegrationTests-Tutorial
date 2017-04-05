/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvpjava.mongo;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    private final MongoService mongoService;

    public LogController(MongoService mongoService) {
        this.mongoService = mongoService;
    }

    @RequestMapping(value = "/log", method = RequestMethod.POST)
    @ResponseBody
    public LogRecord log(@RequestBody LogRecord logRecord) {
        LogRecord log = mongoService.log(logRecord); 
        return log;
    }
}
