package controller;

import entity.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

import java.io.IOException;

@WebServlet("/changeaccountsum")
public class Task1ChangeAccountSumServlet extends HttpServlet {
    private UserService userService;


    @Override
    public void init(ServletConfig config) {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("changeaccountsum.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("userId"));
        double value = Double.parseDouble(req.getParameter("value"));
        String operation = req.getParameter("mathOperation");
        User user = userService.findUserById(id);
        double initialValue = 0;
        if (user != null) {
            initialValue = user.getSum();
            String error = userService.changeAccountSum(initialValue, value, operation, id);
            if (error != null) {
                req.setAttribute("error", error);
                req.getRequestDispatcher("changeaccountsum.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("readuser.jsp").forward(req, resp);
            }
        } else{
            req.setAttribute("error", "Choose clientId one more time");
        }
        req.getRequestDispatcher("changeaccountsum.jsp").forward(req, resp);
    }
}
