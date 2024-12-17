package com.servlet;

import java.io.IOException;



import com.conn.DBConnect;
import com.dao.CandidateDAO;
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
@WebServlet("/addVote")
public class addVote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public addVote() {
       
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		String candidate = new String(request.getParameter("Candidates"));
		candidate = candidate.trim();
		
		System.out.println(candidate);
		
		String voter = new String(request.getParameter("voter"));
		
	
	
		
		try{
			CandidateDAO dao = new CandidateDAO(DBConnect.getConn());
			
			UserDAO dao2 = new UserDAO(DBConnect.getConn());
			
			User  user = dao2.getVoterByEmail(voter);
			System.out.println(user);
			
		 	if(user.getStatus().equals("Voted"))
			{
		 		Cookie cndnotadded = new Cookie("cndnotadded","cndnotadded");
				cndnotadded.setMaxAge(10);
				response.addCookie(cndnotadded);
				response.sendRedirect("voterhome.jsp");
				
				
			}
			else
			{
		
			    if(dao.addCandidate(candidate) > 0 && dao2.updateUser(voter) > 0)
			    {
			    	
			    	Cookie cndadded = new Cookie("cndadded","cndadded");
					cndadded.setMaxAge(10);
					response.addCookie(cndadded);
			    	response.sendRedirect("voterhome.jsp");
			    }
			    else {
			    	Cookie cndnotadded = new Cookie("cndnotadded","cndnotadded");
					cndnotadded.setMaxAge(10);
					response.addCookie(cndnotadded);
			    	response.sendRedirect("voterhome.jsp");
			    }
			
			}
			
		}catch (Exception ex){
			System.out.println(ex);
		}

	
			
	
				
			
			
		
		
	}

}