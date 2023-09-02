package controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

import java.io.IOException;

@WebServlet("/deleteuser")
public class Task13DeleteUserServlet extends HttpServlet {

        private UserService userService;

        @Override
        public void init(ServletConfig config) throws ServletException {
            userService = new UserService();
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("deleteuser.jsp").forward(req, resp);
        }

        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int userId = Integer.parseInt(req.getParameter("userId"));
            String error = userService.deleteUser(userId);
            if (error != null) {
                req.setAttribute("error", error);//???
                req.getRequestDispatcher("deleteuser.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("readuser.jsp").forward(req, resp);
            }
        }
}
