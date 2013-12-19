package com.lexiang.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String ses = (String) session.getAttribute("sessionKey");
		System.out.println("||" + ses + "||");
		if (ses == null || ses.equals("")) {

			String str = req.getParameter("sessionKey");
			if (str != null && !str.equals("")) {
				System.out.println("||" + str + "|");
				session.setAttribute("sessionKey", str);
				req.getRequestDispatcher("/taobaoApi/castSelect.jsp").forward(
						req, resp);
			} else {
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}
		} else {
			req.getRequestDispatcher("/taobaoApi/castSelect.jsp").forward(req,
					resp);
		}

	}

}
