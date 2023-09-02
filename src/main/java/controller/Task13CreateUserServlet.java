package controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

import java.io.IOException;

@WebServlet("/createuser")
public class Task13CreateUserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("createuser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = req.getParameter("clientName");
        String account = req.getParameter("account");
        String sum = req.getParameter("sum");
        String bankId = req.getParameter("bankId");
        String error = userService.createUser(clientName, account, sum, bankId);
        if (error != null) {
            req.setAttribute("error", error);
            req.getRequestDispatcher("createuser.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("readuser.jsp").forward(req, resp);
        }
    }
}
