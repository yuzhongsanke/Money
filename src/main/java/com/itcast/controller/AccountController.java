package com.itcast.controller;

import com.itcast.domain.Account;
import com.itcast.domain.User;
import com.itcast.service.AccountService;
import com.itcast.service.UserService;
import com.itcast.utils.ExcelUtils;
import com.itcast.utils.ZxExportExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/listAccounts")
    public String listAccounts(HttpServletRequest request) {
        List<Account> allAccounts = accountService.findAllAccounts();
        request.setAttribute("accounts",allAccounts);
        return "/pages/listAccounts";
    }


    @RequestMapping(name = "转发到账户的页面", path = "/addAccountUI")
    public String addAccountUI() {
        return "pages/addAccount";
    }

    @RequestMapping(name = "添加账户信息", path = "/addAccount")
    public String addAccount(Account account) {
        accountService.saveAccount(account);
        return "redirect:/account/listAccounts";
    }

    @RequestMapping(name = "转发到指定账户的页面", path = "/editAccountUI")
    public String editAccountUI(Integer aid, Model model) {
        Account byAid = accountService.findAccountByAid(aid);
        model.addAttribute("account", byAid);
        return "pages/editAccount";
    }

    @RequestMapping(name = "保存修改的账户的页面", path = "/editAccount")
    public String editAccount(Account account) {
        accountService.updateAccount(account);
        return "redirect:/account/listAccounts";
    }

    @RequestMapping(name = "删除账户信息", path = "/delAccounts")
    public String delAccounts(Integer[] aids) {
        accountService.deleteAccounts(aids);
        return "redirect:/account/listAccounts";
    }


    @RequestMapping(name = "下载按钮", path = "/install", method = RequestMethod.GET)
//    @ResponseBody
    public String installAccounts(HttpServletRequest request, HttpServletResponse response) {

        List<Account> list = accountService.findAllAccounts();

        String[] all = {"ID", "姓名", "余额"};

        String realPath = request.getSession().getServletContext().getRealPath(File.separator + "resources" + File.separator + "download" + File.separator);
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = format.format(date);
        s += ".xlsx";

        String path = realPath + File.separator + s;
        logger.info("finalPath is " + path);


        //all 标题， list数据， path下载路径
        try {
            new ZxExportExcel().zxExprotExcelXLSX(all, list, path);
            System.out.println(path);
            request.setAttribute("path", File.separator + "resources" + File.separator + "download" + File.separator + s);
            return "result/success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/result/error";
    }


}
