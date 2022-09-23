package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class search
 */
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pid =Integer.parseInt(request.getParameter("pid"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_details","root","root");
			Statement stmt=con.createStatement();
			
			PrintWriter out= response.getWriter();
			out.println("<html><body>");
			
			boolean srch=false;
			
			ResultSet rs =(ResultSet) stmt.executeQuery("select * from Product");
			while(rs.next())
			{
				if(rs.getInt(1)== (pid))
				{
					out.println("<h1>Product Details</h1>");
					out.println("<h2>"+rs.getInt(1)+"\t:\t"+rs.getString(2)+"\t:\t"+rs.getInt(3)+" rs"+"\t:\t"+rs.getString(4)+"</h2></body></html>");
					
					srch=true;
				}
			
			}
			if(srch) {
				out.println("");
			}
			else {
				RequestDispatcher rd=request.getRequestDispatcher("/search.html");
				rd.include(request, response);
				out.println("<h3 align='center'><font color='red'>Enterd Product Id Doesn't Exist </font></h3></body></html>");
			}
			
			
		}
		catch (Exception e) {
			System.out.println(e);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
