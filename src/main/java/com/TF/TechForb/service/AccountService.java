package com.TF.TechForb.service;

import com.TF.TechForb.model.Account.Account;
import com.TF.TechForb.model.Account.AccountDTO;
import com.TF.TechForb.model.Account.AccountDetailDTO;

public interface AccountService {
    AccountDetailDTO getByUser(Integer documentNumber);
    AccountDTO createAccount(Account account);


}
