package com.TF.TechForb.controller;

import com.TF.TechForb.model.User.UserCreateDTO;
import com.TF.TechForb.model.User.UserDTO;
import com.TF.TechForb.model.User.UserLoginDTO;
import com.TF.TechForb.security.Token;
import com.TF.TechForb.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{idUser}")
    public UserDTO getById(@PathVariable("idUser") Long idUser) {
        return userService.getById(idUser);
    }
    @GetMapping("/document-number/{documentNumber}")
    public UserDTO getByDocumentNumber(@PathVariable("documentNumber") Integer documentNumber) {
        return userService.getByDocumentNumber(documentNumber);
    }
    @GetMapping("/by-account/{idAccount}")
    public UserDTO getByAccount(@PathVariable("idAccount") Long idAccount) {
        return userService.getByAccount(idAccount);
    }
    @PostMapping("/register")
    public UserDTO register(@RequestBody UserCreateDTO userCreateDTO) {
        return userService.register(userCreateDTO);
    }
    @PostMapping("/login")
    public Token login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }
    @DeleteMapping("/{idUser}")
    public UserDTO deleteUser(@PathVariable("idUser") Long idUser) {
        return userService.deleteUser(idUser);
    }
}
