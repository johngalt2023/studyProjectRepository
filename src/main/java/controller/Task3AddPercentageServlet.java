package controller;

import entity.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.Task3CheckPercentageService;
import service.ThreadClass;
import service.TransactionNextBank;
import service.UserService;

import java.io.IOException;

@WebServlet("/checkandaddpercentage")
public class Task3AddPercentageServlet extends HttpServlet {

        private UserService userService;

        @Override
        public void init(ServletConfig config) {
            userService = new UserService();
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("checkandaddpercentage.jsp").forward(req, resp);
        }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        User user = userService.findUserById(userId);
        if (user != null) {

            Task3CheckPercentageService task3CheckPercentageService = new Task3CheckPercentageService(userId, user.getSum());
            task3CheckPercentageService.start();
        } else {
            req.setAttribute("error", "User does not exist, choose new one");
            req.getRequestDispatcher("checkandaddpercentage.jsp").forward(req, resp);
        }
    }
}
