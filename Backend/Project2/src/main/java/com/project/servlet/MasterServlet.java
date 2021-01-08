package com.project.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterServlet extends HttpServlet {
	static {
        try {
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Static block has failed me");
        }
    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Master Servlet doGet method");
		RequestHelper.process(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Master Servlet doPost method");
		RequestHelper.process(req, res);
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Master Servlet doPut method");
		RequestHelper.process(req, res);
	}
}
