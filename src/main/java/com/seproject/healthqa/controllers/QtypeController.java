package com.seproject.healthqa.controllers;

import com.seproject.healthqa.entitys.Qtype;
import com.seproject.healthqa.repositorys.QtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chaloemphisit
 */
@RestController
@RequestMapping("/")
public class QtypeController {

    @Autowired
    QtypeRepository qtypeRepository;

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Qtype> getQtype() {
        return qtypeRepository.findAll();
    }
}
