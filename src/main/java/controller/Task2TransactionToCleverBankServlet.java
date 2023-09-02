package controller;

import entity.Bank;
import entity.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BankService;
import service.Task5CheckService;
import service.UserService;

import java.io.IOException;

@WebServlet("/transactiontocleverbank")
public class Task2TransactionToCleverBankServlet extends HttpServlet {
    private UserService userService;
    private Task5CheckService task5CheckService;
    private BankService bankService;


    @Override
    public void init(ServletConfig config) {
        userService = new UserService();
        task5CheckService = new Task5CheckService();
        bankService = new BankService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("transactiontocleverbank.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cleverBankId = 1;
        int idClientFirst = Integer.parseInt(req.getParameter("idClientFirst"));
        int idClientSecond = Integer.parseInt(req.getParameter("idClientSecond"));
        double value = Double.parseDouble(req.getParameter("value"));
        User userFirst = userService.findUserById(idClientFirst);
        User userSecond = userService.findUserById(idClientSecond);
        if (userFirst != null && userSecond != null) {
            if (cleverBankId == userFirst.getBankId() && userFirst.getBankId() == userSecond.getBankId()) {
                double initialSumFromClient = userFirst.getSum();
                double initialSumToClient = userSecond.getSum();
                int userFirstAccount = userFirst.getAccount();
                int userSecondAccount = userSecond.getAccount();
                String error = userService.transactionToCleverBankClient(initialSumFromClient, initialSumToClient, value, idClientFirst, idClientSecond);
                if (error != null) {
                    req.setAttribute("error", error);
                    req.getRequestDispatcher("transactiontocleverbank.jsp").forward(req, resp);
                } else {
                    Bank bankFirst = bankService.findBankById(userFirst.getBankId());
                    Bank bankSecond = bankService.findBankById(userSecond.getBankId());
                    if (bankFirst != null && bankSecond != null) {
                        String bankNameFirst = bankFirst.getBankName();
                        String bankNameSecond = bankFirst.getBankName();
                        task5CheckService.createCheck(value, userFirstAccount, userSecondAccount, bankNameFirst, bankNameSecond);
                    } else {
                        req.setAttribute("error", "This bank with such clientId does not exists, choose other clientId");
                        req.getRequestDispatcher("transactiontocleverbank.jsp").forward(req, resp);
                    }
                }
            } else {
                req.setAttribute("error", "This client is served in other bank. Choose clientId one more time");
            }

        } else {
            req.setAttribute("error", "This client does not exists. Choose clients Id one more time");
            req.getRequestDispatcher("transactiontocleverbank.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("readuser.jsp").forward(req, resp);
    }
}
