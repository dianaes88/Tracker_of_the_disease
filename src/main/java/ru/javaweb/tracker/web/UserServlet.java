package ru.javaweb.tracker.web;

import org.slf4j.Logger;
import ru.javaweb.tracker.util.UsersUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* log.debug("redirect to users");

//        request.getRequestDispatcher("/users.jsp").forward(request, response);
        response.sendRedirect("users.jsp");*/
//        String action = request.getParameter("action");
//        System.out.println(action);
//        if(action.equals("all")) {

            log.info("getAll");
            request.setAttribute("users", UsersUtil.getUsersByName(UsersUtil.USERS));
            request.getRequestDispatcher("/users.jsp").forward(request, response);
        }
  // }
}
