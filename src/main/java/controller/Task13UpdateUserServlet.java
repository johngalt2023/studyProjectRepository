package controller;

import entity.UserDto;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

import java.io.IOException;

@WebServlet("/updateuser")
public class Task13UpdateUserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("updateuser.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        String clientName = req.getParameter("clientName");
        int account = Integer.parseInt(req.getParameter("account"));
        double sum = Double.parseDouble(req.getParameter("sum"));
        int bankId = Integer.parseInt(req.getParameter("bankId"));
        UserDto userDto = new UserDto(clientName, account, sum, bankId);
        String error = userService.userUpdate(userId, userDto);
        if (error != null) {
            req.setAttribute("error", error);
            req.getRequestDispatcher("updateuser.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("readuser.jsp").forward(req, resp);
        }
    }
}
