package controller;

import entity.Bank;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BankService;


import java.io.IOException;

@WebServlet("/readbank")
public class Task13ReadBankServlet extends HttpServlet {
    private BankService bankService;

    @Override
    public void init(ServletConfig config) {
        bankService = new BankService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("readbank.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bankId = Integer.parseInt(req.getParameter("bankId"));
        Bank bank = bankService.findBankById(bankId);
        if (bank != null) {
            req.getSession().setAttribute("currentBank", bank);
        } else{
            req.setAttribute("error", "Choose bankId one more time");
        }
        req.getRequestDispatcher("readbank.jsp").forward(req, resp);
    }
}
