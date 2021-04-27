package com.itcast.controller;

import com.itcast.domain.Account;
import com.itcast.domain.User;
import com.itcast.service.AccountService;
import com.itcast.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @RequestMapping(name = "转发到账户的页面", path = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", request.getParameter("username"));
        map.put("password", request.getParameter("password"));
        User user = userService.login(map);
        if (StringUtils.isEmpty(user)) {
            logger.info("用户名或密码错误");
            return "/index";
        } else {
            List<Account> allAccounts = accountService.findAllAccounts();
            request.setAttribute("accounts", allAccounts);
            return "/pages/listAccounts";
        }

    }
}
