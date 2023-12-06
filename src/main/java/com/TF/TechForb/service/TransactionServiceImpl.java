package com.TF.TechForb.service;

import com.TF.TechForb.error.TechForbException;
import com.TF.TechForb.model.Account.Account;
import com.TF.TechForb.model.Card.Card;
import com.TF.TechForb.model.Transaction.*;
import com.TF.TechForb.model.User.User;
import com.TF.TechForb.repository.AccountRepository;
import com.TF.TechForb.repository.CardRepository;
import com.TF.TechForb.repository.TransactionRepository;
import com.TF.TechForb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionMapper transactionMapper;

    public List<TransactionDTO> getAllByAccount(Long idAccount) {
        try {
            Optional<Account> accountFounded = accountRepository.findById(idAccount);
            if(accountFounded.isPresent()) {
                List<Transaction> transactionsByAccount = transactionRepository.findByAccount(idAccount);
                List<TransactionDTO> transactionDTOS = transactionsByAccount.stream().map(
                        transaction -> transactionMapper.transactionToTransactionDto(transaction)).collect(Collectors.toList());

                return transactionDTOS;
            } else {
                throw new TechForbException("Account not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

//    public List<TransactionDTO> getReceiverTransactionsByAccount(Long idAccount) {
//        try {
//            Optional<Account> accountFounded = accountRepository.findById(idAccount);
//            if(accountFounded.isPresent()) {
//                List<Transaction> transactions = transactionRepository.getReceiverTransactionsByAccount(idAccount);
//                List<TransactionDTO> transactionDTOS = transactions.stream().map(
//                        transaction -> transactionMapper.transactionToTransactionDto(transaction)).collect(Collectors.toList());
//
//                return transactionDTOS;
//            } else {
//                throw new TechForbException("User not found", null, HttpStatus.NOT_FOUND);
//            }
//        } catch (TechForbException ex) {
//            throw ex;
//        } catch (Exception e) {
//            throw new TechForbException("Error get receiver transactions", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    public List<TransactionDTO> getSenderTransactionsByAccount(Long idAccount) {
//        try {
//            Optional<Account> accountFounded = accountRepository.findById(idAccount);
//            if(accountFounded.isPresent()) {
//                List<Transaction> transactions = transactionRepository.getSenderTransactionsByAccount(idAccount);
//                List<TransactionDTO> transactionDTOS = transactions.stream().map(
//                        transaction -> transactionMapper.transactionToTransactionDto(transaction)).collect(Collectors.toList());
//
//                return transactionDTOS;
//            } else {
//                throw new TechForbException("User not found", null, HttpStatus.NOT_FOUND);
//            }
//        } catch (TechForbException ex) {
//            throw ex;
//        } catch (Exception e) {
//            throw new TechForbException("Error get sender transactions", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    public List<Transaction> getLatestTransactions(Long idAccount) {
        try {
            Optional<Account> account = accountRepository.findById(idAccount);
            if(account.isPresent()) {
                List<Transaction> latestTransactions = transactionRepository.getLatestFiveTransactions(idAccount);

                return latestTransactions;
            } else {
                throw new TechForbException("Account not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error get latest transactions", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<TransactionAccumulatedAmountDTO> getReceiverTransactionsInTheLastWeekByAccount(Long idAccount) {
        try {
            Optional<Account> account = accountRepository.findById(idAccount);
            if(account.isPresent()) {
                List<TransactionAccumulatedAmountDTO> receiverTransactions = transactionRepository.getReceiverTransactionsInTheLastWeekByAccount(idAccount);

                return receiverTransactions;
            } else {
                throw new TechForbException("Account not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error get receiver transactions in the last week", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public List<TransactionAccumulatedAmountDTO> getSenderTransactionsInTheLastWeekByAccount(Long idAccount) {
        try {
            Optional<Account> account = accountRepository.findById(idAccount);
            if(account.isPresent()) {
                List<TransactionAccumulatedAmountDTO> senderTransactions = transactionRepository.getSenderTransactionsInTheLastWeekByAccount(idAccount);

                return senderTransactions;
            } else {
                throw new TechForbException("Account not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error get sender transactions in the last week", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<TransactionResponseChartDTO> getTransactionWeek(Long idAccount) {
        try {
            List<LocalDate> week = new ArrayList<>();
            List<TransactionResponseChartDTO> transactions = new ArrayList<>();
            LocalDate now = LocalDate.now();
            List<TransactionAccumulatedAmountDTO> senderTransactions = this.getSenderTransactionsInTheLastWeekByAccount(idAccount);
            List<TransactionAccumulatedAmountDTO> receiverTransactions = this.getReceiverTransactionsInTheLastWeekByAccount(idAccount);
            for(int i = 6; i >= 1; i--) {
                week.add(now.minusDays(i));
            }
            week.add(now);
            for(LocalDate day : week) {
                TransactionAccumulatedAmountDTO transactionAccumulatedAmountSender = this.searchTransactionAmount(senderTransactions, day);
                TransactionAccumulatedAmountDTO transactionAccumulatedAmountReceiver = this.searchTransactionAmount(receiverTransactions, day);
                Double receiverAmount = 0D;
                Double senderAmount = 0D;
                if(transactionAccumulatedAmountSender != null) {
                    senderAmount = transactionAccumulatedAmountSender.getAccumulatedAmount();
                }
                if(transactionAccumulatedAmountReceiver != null) {
                    receiverAmount = transactionAccumulatedAmountReceiver.getAccumulatedAmount();
                }
                transactions.add(new TransactionResponseChartDTO(day,receiverAmount,senderAmount));
            }
            return transactions;
        } catch (Exception e) {
            throw e;
        }
    }

    private TransactionAccumulatedAmountDTO searchTransactionAmount(List<TransactionAccumulatedAmountDTO> senderTransactions, LocalDate day) {
        for (TransactionAccumulatedAmountDTO transaction : senderTransactions) {
            if(transaction.getDate().equals(day)) {
                return transaction;
            }
        }
        return null;
    }

    public TransactionDTO makeTransaction(TransactionDTO transactionDTO) {
        try {
            Optional<Account> accountSenderFounded = accountRepository.findById(transactionDTO.getIdSenderAccount());
            if(accountSenderFounded.isPresent()) {
                Optional<Account> accountReceiverFounded = accountRepository.findById(transactionDTO.getIdReceiverAccount());
                if(accountReceiverFounded.isPresent()) {
                    Account senderAccount = accountSenderFounded.get();
                    Account receiverAccount = accountReceiverFounded.get();
                    Card senderCard = cardRepository.findByAccount(senderAccount.getIdAccount()).get();
                    if(senderCard.getBalance() >= transactionDTO.getAmount()) {
                        Card receiverCard = cardRepository.findByAccount(receiverAccount.getIdAccount()).get();

                        senderCard.setBalance(senderCard.getBalance() - transactionDTO.getAmount());
                        receiverCard.setBalance(receiverCard.getBalance() + transactionDTO.getAmount());
                        cardRepository.save(senderCard);
                        cardRepository.save(receiverCard);

                        Transaction transactionCompleted = transactionMapper.transactionDtoToTransaction(transactionDTO);
                        transactionRepository.save(transactionCompleted);

                        return transactionDTO;
                    } else {
                        throw new TechForbException("Insufficient Founds", null, HttpStatus.BAD_REQUEST);
                    }
                } else {
                    throw new TechForbException("Receiver Account not found", null, HttpStatus.NOT_FOUND);
                }
            } else {
                throw new TechForbException("Sender Account not found", null, HttpStatus.NOT_FOUND);
            }

        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in Create Transaction", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public TransactionDepositWithdrawalDTO makeDeposit(TransactionDepositWithdrawalDTO transaction) {
        try {
            Optional<Account> accountFounded = accountRepository.findById(transaction.getIdAccount());
            if(accountFounded.isPresent()) {
                Optional<User> userFounded = userRepository.findById(accountFounded.get().getUser().getIdUser());
                if(userFounded.isPresent()) {
                    Optional<Card> cardFounded = cardRepository.findByAccount(accountFounded.get().getIdAccount());
                    if(cardFounded.isPresent()) {
                        Card card = cardFounded.get();
                        card.setBalance(card.getBalance() + transaction.getAmount());
                        cardRepository.save(card);

                        return transaction;
                    } else {
                        throw new TechForbException("Card not found", null, HttpStatus.NOT_FOUND);
                    }
                } else {
                    throw new TechForbException("User not found", null, HttpStatus.NOT_FOUND);
                }

            } else {
                throw new TechForbException("Account not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in Make Deposit", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public TransactionDepositWithdrawalDTO makeWithdrawal(TransactionDepositWithdrawalDTO transaction) {
        try {
            Optional<Account> accountFounded = accountRepository.findById(transaction.getIdAccount());
            if(accountFounded.isPresent()) {
                Optional<User> userFounded = userRepository.findById(accountFounded.get().getUser().getIdUser());
                if(userFounded.isPresent()) {
                    Optional<Card> cardFounded = cardRepository.findByAccount(accountFounded.get().getIdAccount());
                    if(cardFounded.isPresent()) {
                        Card card = cardFounded.get();
                        if(card.getBalance() >= transaction.getAmount()) {
                            card.setBalance(card.getBalance() - transaction.getAmount());
                            cardRepository.save(card);

                            return transaction;
                        } else {
                            throw new TechForbException("Insufficient Founds", null, HttpStatus.BAD_REQUEST);
                        }

                    } else {
                        throw new TechForbException("Card not found", null, HttpStatus.NOT_FOUND);
                    }
                } else {
                    throw new TechForbException("User not found", null, HttpStatus.NOT_FOUND);
                }

            } else {
                throw new TechForbException("Account not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in Make Withdrawal", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Double getIncomeBalanceByAccount(Long idAccount) {
        try {
           Optional<Account> accountFounded = accountRepository.findById(idAccount);
           if(accountFounded.isPresent()) {
                return transactionRepository.getReceiverTransactionsByAccount(idAccount);
           } else {
               throw new TechForbException("Account not found", null, HttpStatus.NOT_FOUND);
           }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in Make Withdrawal", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Double getOutflowBalanceByAccount(Long idAccount) {
        try {
            Optional<Account> accountFounded = accountRepository.findById(idAccount);
            if(accountFounded.isPresent()) {
                return transactionRepository.getSenderTransactionsByAccount(idAccount);
            } else {
                throw new TechForbException("Account not found", null, HttpStatus.NOT_FOUND);
            }
        } catch (TechForbException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechForbException("Error in Make Withdrawal", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
