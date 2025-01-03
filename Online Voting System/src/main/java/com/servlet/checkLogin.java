package com.servlet;

import java.io.IOException;



import com.conn.DBConnect;
import com.dao.UserDAO;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




@MultipartConfig
@WebServlet("/checkLogin")
public class checkLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public checkLogin() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String email = new String(request.getParameter("email"));
		 email = email.trim();
	
		
		
		String password = new String(request.getParameter("password"));
		password = password.trim();
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		
		
		
		try{
			UserDAO dao = new UserDAO(DBConnect.getConn());
			
			
			if (dao.checkadmin(user)==true)
			{
				Cookie admin = new Cookie("admin",email);
				admin.setMaxAge(9999);
				response.addCookie(admin);
				response.sendRedirect("adminhome.jsp");
			}
			else if (dao.checkVoter(user)==true)
			{
				Cookie voter = new Cookie("voter",email);
				voter.setMaxAge(9999);
				response.addCookie(voter);
				response.sendRedirect("voterhome.jsp");
			}
				else
				{
					Cookie admfail = new Cookie("loginfailed","loginfailed");
					admfail.setMaxAge(10);
					response.addCookie(admfail);
					response.sendRedirect("login.jsp");
				}
			
			}catch(Exception ex){
			   System.out.println(ex.getMessage());
			}
			
	
				
			
			
		
		
	}

}