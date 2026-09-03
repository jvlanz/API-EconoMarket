package com.economarket.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
   @GetMapping("/admin")
   public String adm() {
	   return "area adminstrativa";
   }
}
