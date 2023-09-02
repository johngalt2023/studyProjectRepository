package controller;

import entity.Bank;
import entity.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.*;

import java.io.IOException;

@WebServlet("/transactionnextbank")
public class Task2TransactionNextBankServlet extends HttpServlet {
    private UserService userService;
    private TransactionNextBank transactionNextBank;
    private Task5CheckService task5CheckService;
    private BankService bankService;


    @Override
    public void init(ServletConfig config) {
        userService = new UserService();
        transactionNextBank = new TransactionNextBank();
        bankService = new BankService();
        task5CheckService = new Task5CheckService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("transactionnextbank.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cleverBankId = 1;
        int toBankId = Integer.parseInt(req.getParameter("toBankId"));
        int idClientFirst = Integer.parseInt(req.getParameter("idClientFirst"));
        int idClientSecond = Integer.parseInt(req.getParameter("idClientSecond"));
        double value = Double.parseDouble(req.getParameter("value"));
        User userFirst = userService.findUserById(idClientFirst);
        User userSecond = userService.findUserById(idClientSecond);
        if (userFirst != null && userSecond != null) {
            if (cleverBankId < toBankId && cleverBankId == userFirst.getBankId() && toBankId == userSecond.getBankId()) {
                double initialSumFromClientClever = userFirst.getSum();
                double initialSumToClientNextBank = userSecond.getSum();
                int userFirstAccount = userFirst.getAccount();
                int userSecondAccount = userSecond.getAccount();
                Bank bankFirst = bankService.findBankById(userFirst.getBankId());
                Bank bankSecond = bankService.findBankById(userSecond.getBankId());
                if (initialSumFromClientClever >= value) {
                    if (bankFirst != null && bankSecond != null) {
                        ThreadClass threadClass = new ThreadClass(initialSumFromClientClever, initialSumToClientNextBank, value, idClientFirst, idClientSecond, transactionNextBank);
                        threadClass.start();
                        String bankNameFirst = bankFirst.getBankName();
                        String bankNameSecond = bankFirst.getBankName();
                        task5CheckService.createCheck(value, userFirstAccount, userSecondAccount, bankNameFirst, bankNameSecond);
                    } else {
                        req.setAttribute("error", "This bank does not exists, choose bank Id one more time");
                        req.getRequestDispatcher("transactionnextbank.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("error", "Value is greater than first account has, paste new one");
                    req.getRequestDispatcher("transactionnextbank.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("error", "This user has other bank Id, paste new one");
                req.getRequestDispatcher("transactionnextbank.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", "Choose clients Id one more time");
            req.getRequestDispatcher("transactionnextbank.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("readuser.jsp").forward(req, resp);
    }


}


