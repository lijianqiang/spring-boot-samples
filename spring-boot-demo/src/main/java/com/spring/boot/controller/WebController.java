package com.spring.boot.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.gson.Gson;
import com.spring.boot.bean.JsonRequest;
import com.spring.boot.bean.JsonResponse;

@Controller
@RequestMapping("/web")
public class WebController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Gson gson = new Gson();

    @RequestMapping("/hello")
    public String actionIndex(Locale locale, Model model) {
        model.addAttribute("greeting", "Hello!");

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("currentTime", formattedDate);

        return "web/hello";
    }

    @RequestMapping("/demo")
    public String actionDemo(Model model) {
        model.addAttribute("welcome", "hello world, spring boot...");
        return "web/demo";
    }

    @RequestMapping("/scan")
    public String actionScan(Model model) {
        return "web/scan";
    }

    @RequestMapping(value = "/parse", method = RequestMethod.POST, produces = "application/*; charset=UTF-8")
    @ResponseBody
    public String actionParse(HttpServletRequest request, @RequestBody JsonRequest jsonRequest) {
        logger.info("user input: {}", jsonRequest.getContent());
        JsonResponse res = new JsonResponse();
        res.setCode(200);
        res.setMsg("ok");
        res.setData("helloworld:" + jsonRequest.getContent());
        return gson.toJson(res);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        logger.info("user Exception: {}", ex);

        JsonResponse res = new JsonResponse();
        res.setCode(400);
        res.setMsg("ok");
        res.setData(ex.getMessage());
        return gson.toJson(res);
    }
}
