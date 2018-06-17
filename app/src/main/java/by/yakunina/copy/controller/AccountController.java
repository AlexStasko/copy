package by.yakunina.copy.controller;

import by.yakunina.copy.model.Customer;
import by.yakunina.copy.model.CustomerForm;
import by.yakunina.copy.model.auth.Account;
import by.yakunina.copy.model.support.EntityId;
import by.yakunina.copy.service.AccountService;
import by.yakunina.copy.service.CustomerService;
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
    @Resource
    private CustomerService customerService;

    @GetMapping("/account")
    public String performPage(Model model) {
        model.addAttribute("account", new CustomerForm());
        return "account";
    }

    @PostMapping("/account")
    public String register(@ModelAttribute CustomerForm newAccount) {
        LOGGER.info(newAccount.toString());
        Customer customer = new Customer.CustomerBuilder()
                .withName(newAccount.getFirstName())
                .withLastName(newAccount.getLastName())
                .withEmail(newAccount.getEmail())
                .withAddress(newAccount.getAddress())
                .withPhoneNumber(newAccount.getPhoneNumber())
                .build();
        EntityId id = customerService.createCustomer(customer);
        LOGGER.info("created Customer with id [{}]", id);
        Account account = new Account.AccountBuilder()
                .withUsername(newAccount.getUsername())
                .withPassword(newAccount.getUsername())
                .withUserId(id)
                .build();
        accountService.createAccount(account);
        LOGGER.info("Created account [{}]", account);
        return "redirect:/";
    }
}
