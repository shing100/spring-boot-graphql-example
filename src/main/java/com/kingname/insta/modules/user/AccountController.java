package com.kingname.insta.modules.user;

import com.kingname.insta.modules.utils.Generator;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@GraphQLApi
@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final Generator generator;
    private final PasswordEncoder passwordEncoder;

    @GraphQLQuery(name = "getAllUser", description = "테스트용")
    public List<Account> getAllUser() {
        return accountRepository.findAll();
    }

    @GraphQLMutation(name = "createAccount")
    public Account createAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @GraphQLMutation(name = "requestSecret")
    public Boolean requestSecret(String email) {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            return false;
        }
        account.setLoginSecret(generator.secretGenerator());
        Account saveAccount = accountRepository.save(account);
        return accountService.sendSecretMail(saveAccount.getEmail(), saveAccount.getLoginSecret());
    }

    @GraphQLMutation(name = "confirmSecret")
    public Account confirmSecret(String secret, String email) {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new IllegalArgumentException("유효하지 않은 이메일입니다.");
        }
        
        if (!secret.equals(account.getLoginSecret())) {
            throw new IllegalArgumentException("시크릿코드가 다릅니다.");
        }
        // token 발급
        return null;
    }
}
