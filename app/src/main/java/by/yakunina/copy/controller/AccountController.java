package by.yakunina.copy.controller;

import by.yakunina.copy.model.auth.Account;
import by.yakunina.copy.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class AccountController {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Resource
    private AccountService accountService;

    @GetMapping("/account")
    public String performPage(Model model) {
        model.addAttribute("account", new Account());
        return "account";
    }

    @PostMapping("/account")
    public String register(@ModelAttribute Account newAccount) {
        LOGGER.info(newAccount.toString());
        accountService.createAccount(newAccount);
        return "redirect:/";
    }
}
