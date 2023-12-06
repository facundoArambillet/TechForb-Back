package com.TF.TechForb.service;

import com.TF.TechForb.error.TechForbException;
import com.TF.TechForb.model.Account.Account;
import com.TF.TechForb.model.User.*;
import com.TF.TechForb.model.UserDocumentType.UserDocumentType;
import com.TF.TechForb.repository.AccountRepository;
import com.TF.TechForb.repository.UserDocumentTypeRepository;
import com.TF.TechForb.repository.UserRepository;
import com.TF.TechForb.security.JwtProvider;
import com.TF.TechForb.security.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDocumentTypeRepository userDniTypeRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserMapper userMapper;

    public UserDTO getById(Long id) {
        try {
            Optional<User> userFounded = userRepository.findById(id);
            if(userFounded.isPresent()) {
                User user = userFounded.get();
                UserDTO userDTO = userMapper.userToUserDto(user);

                return userDTO;
            } else {
                throw new TechForbException("User not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in get by id", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public UserDTO getByDocumentNumber(Integer documentNumber) {
        try {
            Optional<User> userFounded = userRepository.findByDocumentNumber(documentNumber);
            if(userFounded.isPresent()) {
                User user = userFounded.get();
                UserDTO userDTO = userMapper.userToUserDto(user);

                return userDTO;
            } else {
                throw new TechForbException("User not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in get by document number", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public UserDTO getByAccount(Long idAccount) {
        try {
            Optional<Account> accountFounded = accountRepository.findById(idAccount);
            if(accountFounded.isPresent()) {
                UserDTO userDTO = userMapper.userToUserDto(accountFounded.get().getUser());

                return userDTO;
            } else{
                throw new TechForbException("Account not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in get by account", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public UserDTO register(UserCreateDTO userCreateDTO) {
        try {
            Optional<User> userFounded = userRepository.findByDocumentNumber(userCreateDTO.getDocumentNumber());
            if(userFounded.isPresent()) {
                throw new TechForbException("This user already exist ", null, HttpStatus.BAD_REQUEST);
            } else {
                Optional<UserDocumentType> userDniType = userDniTypeRepository.findById(userCreateDTO.getIdUserDocumentType());
                if(userDniType.isPresent()) {
                    userCreateDTO.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
                    User user = userMapper.userCreateDtoToUser(userCreateDTO);
                    UserDTO userDTO = userMapper.userCreateDtoToUserDto(userCreateDTO);
                    Account account = new Account();
                    account.setUser(user);
                    userRepository.save(user);
                    accountService.createAccount(account);

                    return userDTO;
                } else {
                    throw new TechForbException("That document type does not exist ", null, HttpStatus.BAD_REQUEST);
                }
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in Create user", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Token login(UserLoginDTO userLoginDTO) {
        try {
            Optional<User> userFounded = userRepository.findByDocumentNumber(userLoginDTO.getDocumentNumber());
            if(userFounded.isPresent()) {
                User user = userFounded.get();
                if(passwordEncoder.matches(userLoginDTO.getPassword(),user.getPassword())) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userLoginDTO.getDocumentNumber(), userLoginDTO.getPassword());
                    Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
                    UserDetailsImpl userToken = (UserDetailsImpl) authentication.getPrincipal();
                    String token = jwtProvider.createToken(userToken);

                    return new Token(token);
                } else {
                    throw new TechForbException("Password incorrect", null, HttpStatus.BAD_REQUEST);
                }
            } else {
                throw new TechForbException("Dni number not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in Create user", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public UserDTO deleteUser(Long id) {
        try {
            Optional<User> userFounded = userRepository.findById(id);
            if(userFounded.isPresent()) {
                UserDTO userDTO = userMapper.userToUserDto(userFounded.get());
                userRepository.delete(userFounded.get());

                return userDTO;
            } else {
                throw new TechForbException("User not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in Delete user", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
