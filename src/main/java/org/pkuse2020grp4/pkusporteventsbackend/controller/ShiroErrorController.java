package org.pkuse2020grp4.pkusporteventsbackend.controller;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ShiroErrorController extends BasicErrorController {
    public ShiroErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Exception exception = (Exception)request.getAttribute("javax.servlet.error.exception");
        HttpStatus status = getStatus(request);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("msg",exception.getMessage());
        map.put("code",1);
        map.put("data",null);
        return new ResponseEntity<Map<String, Object>>(map, status);
    }
}
