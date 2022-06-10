package com.bbs.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.dao.EmployeeDAO;
import com.bbs.vo.Employee;

public class JdbcEmpService {
	HttpServletRequest request;
	HttpServletResponse response;

	public JdbcEmpService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String process() {
		String cmd = request.getParameter("cmd");
		if (cmd == null || cmd.equals("index")) {
			return "/jdbcEmp/index.jsp";
		} 
		
		else if (cmd.equals("list")) {
			EmployeeDAO dao = new EmployeeDAO();
			List<Employee> list = dao.getList();
			request.setAttribute("list", list);
			return "/jdbcEmp/jdbcEmpList.jsp";
		} 
		
		else if (cmd.equals("detail")) {
			int empno = Integer.parseInt(request.getParameter("empno"));
			EmployeeDAO dao = new EmployeeDAO();
			Employee emp = dao.getEmp(empno);
			request.setAttribute("emp", emp);
			return "/jdbcEmp/empDetail.jsp";
		} 
		
		else if (cmd.equals("empList_dept")) {
			int empno = Integer.parseInt(request.getParameter("deptno"));
			EmployeeDAO dao = new EmployeeDAO();
			List<Employee> list = dao.getDept(empno);
			request.setAttribute("list", list);
			return "/jdbcEmp/empList_dept.jsp";
		} 
		
		else if(cmd.equals("searchEmp")){
			EmployeeDAO dao = new EmployeeDAO();
			String category = request.getParameter("search");
			String key = request.getParameter("searchBar");
			Employee emp = null;
			switch(category) {
				case "empno":emp = dao.getEmp(Integer.parseInt(key));break;
				case "ename":emp = dao.searchByName(key);break;
			}
			/*
			if (search == 0) {
				int empno = Integer.parseInt(searchBar);
				Employee emp = dao.getEmp(empno);
				request.setAttribute("emp", emp);
			}
			else if(search == 1) {
				String ename = searchBar;
				Employee emp = dao.searchByName(ename);
				request.setAttribute("emp", emp);
			}
			*/
			request.setAttribute("emp", emp);
			return "/jdbcEmp/empDetail.jsp";
//			int empno = Integer.parseInt(request.getParameter("empno"));
//			String ename = request.getParameter("ename");
//			EmployeeDAO dao = new EmployeeDAO();
//			if(empno)
//			Employee emp = dao.serchEmp(num, data);
//			request.setAttribute("emp", emp);
		} 
		
		else if (cmd.equals("edit")) {
			int empno = Integer.parseInt(request.getParameter("empno"));
			EmployeeDAO dao = new EmployeeDAO();
			Employee emp = dao.getEmp(empno);
			request.setAttribute("emp", emp);
			return "/jdbcEmp/empEdit.jsp";
		} 
		
		else if (cmd.equals("edited")) {
			int empno = Integer.parseInt(request.getParameter("empno"));
			int sal = Integer.parseInt(request.getParameter("sal"));
			int deptno = Integer.parseInt(request.getParameter("deptno"));

			Employee emp = new Employee();
			emp.setEmpno(empno);
			emp.setSal(sal);
			emp.setDeptno(deptno);

			System.out.println(emp.toString());
			EmployeeDAO dao = new EmployeeDAO();
			boolean edited = dao.edit(emp);
			System.out.println("Service cmd=edited: " + edited);
			try {
				PrintWriter out = response.getWriter();
				out.println(String.format("{\"%s\":%b}", "edited", edited));
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else if(cmd.equals("getItemList")) {
			String category = request.getParameter("search");
			EmployeeDAO dao = new EmployeeDAO();
			List<String> list = dao.getItemList(category);
			
			String items="[";
			for(int i=0; i<list.size(); i++) {
				items += String.format("\"%s\"",list.get(i));
				if(i<list.size()-1) {
					items += ",";
				}
			}
			items += "]";
			
			try {
				PrintWriter out = response.getWriter();
				out.println(items);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
