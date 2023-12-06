package com.TF.TechForb.controller;

import com.TF.TechForb.model.Account.Account;
import com.TF.TechForb.model.Account.AccountDTO;
import com.TF.TechForb.model.Account.AccountDetailDTO;
import com.TF.TechForb.service.AccountServiceImpl;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("/byUser/{documentNumber}")
    public AccountDetailDTO getByUser(@PathVariable("documentNumber") Integer documentNumber) {
        return accountService.getByUser(documentNumber);
    }
    @PostMapping
    public AccountDTO createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }
}
