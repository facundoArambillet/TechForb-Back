package com.TF.TechForb.model.Account;

import com.TF.TechForb.model.User.User;
import com.TF.TechForb.model.User.UserDTO;
import com.TF.TechForb.model.User.UserMapper;
import com.TF.TechForb.repository.AccountRepository;
import com.TF.TechForb.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@NoArgsConstructor
public class AccountMapper {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    public AccountDTO accountToAccountDto(Account account) {
        Long idUser = account.getUser().getIdUser();
        AccountDTO accountDTO = new AccountDTO(idUser);
        return accountDTO;
    }
    public AccountDetailDTO accountToAccountDetailDto(Account account) {
        Long idUser = account.getUser().getIdUser();
        AccountDetailDTO accountDetailDTO = new AccountDetailDTO(account.getIdAccount(),idUser);
        return accountDetailDTO;
    }
    public Account accountDtoToAccount(AccountDTO accountDTO) {
        Optional<User> user = userRepository.findById(accountDTO.getIdUser());
        Optional<Account> account = accountRepository.findByUser(user.get().getIdUser());
        return account.get();
    }
}
