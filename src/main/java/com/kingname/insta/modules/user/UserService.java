package com.kingname.insta.modules.user;

import com.kingname.insta.infra.mail.EmailMessage;
import com.kingname.insta.infra.mail.HtmlEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final HtmlEmailService htmlEmailService;

    public Boolean sendSecretMail(String address, String secret) {
        try {
            EmailMessage emailMessage = EmailMessage.builder()
                        .to(address)
                        .subject("Login Secret for insta")
                        .message(secret)
                        .build();
            htmlEmailService.sendEmail(emailMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
