package com.seproject.healthqa.web.controller.admin;

import com.seproject.healthqa.web.bean.AllTopics;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {

    private static Logger log = Logger.getLogger("InfoLogging");

    @GetMapping(value = "/all")
//  @PreAuthorize("hasRole('USER')")
    public boolean getTopicsRp() {
        
        return true;
    }
  
// สร้างมอคไว้เฉย ๆ     
//    @GetMapping(value = "/delete")
////  @PreAuthorize("hasRole('USER')")
//    public boolean deleteDoctor() {
//        
//        return true;
//    }
//    
//    @GetMapping(value = "/rp")
////  @PreAuthorize("hasRole('USER')")
//    public boolean confirmDelete() {
//        
//        return true;
//    }
    
    
    
    
}