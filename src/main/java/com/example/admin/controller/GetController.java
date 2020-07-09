package com.example.admin.controller;

import com.example.admin.model.SearchParam;
import com.example.admin.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetController {

    @RequestMapping(path = "/getMethod", method = RequestMethod.GET)
    public String getRequqest(){
        return "Hi getMethod";
    }

    @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        String password="bbbb";
        System.out.println("id: "+id);
        System.out.println("password: "+pwd);
        return id+pwd;
    }
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount() );
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader(){
        //{"resultCode: "OK", "description": "OK"}
        return Header.builder().resultCode("OK").description("OK").build();

    }

}
