package com.bbs.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.dao.BBSDAO;
import com.bbs.vo.BBSVO;

public class BBSService {

	private HttpServletRequest request;
	private HttpServletResponse response;

	public BBSService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String process() {
		String cmd = request.getParameter("cmd");
		if(cmd == null || cmd.equals("index")) {
			return "/bbs/index.jsp";
		}
		else if(cmd.equals("add_form")) {
			return "/bbs/add_form.jsp";
		}
		else if(cmd.equals("add")) {
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			String uid = (String)request.getSession().getAttribute("uid");
			java.sql.Date now = new java.sql.Date(new java.util.Date().getTime());
			BBSDAO dao = new BBSDAO();
			int num = dao.getLastNum() + 1;
			
			BBSVO bbs = new BBSVO();
			bbs.setNum(num);
			bbs.setTitle(title);
			bbs.setContents(contents);
			bbs.setAuthor(uid);
			bbs.setWdate(now);
			boolean added = dao.add(bbs);
			try {
				PrintWriter out = response.getWriter();
				out.println(String.format("{\"%s\":%b}", "added", added));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(cmd.equals("list")) {
			BBSDAO dao = new BBSDAO();
			List<BBSVO> list = dao.getList();
			request.setAttribute("list", list);
			return "/bbs/list.jsp";
		}
		else if(cmd.equals("detail")) {
			int num = Integer.parseInt(request.getParameter("num"));
			BBSDAO dao = new BBSDAO();
			BBSVO bbs = dao.getBBS(num);
			request.setAttribute("bbs", bbs);
			return "/bbs/detail.jsp";
		}
		else if(cmd.equals("edit")) {
			int num = Integer.parseInt(request.getParameter("num"));
			BBSDAO dao = new BBSDAO();
			BBSVO bbs = dao.getBBS(num);
			request.setAttribute("bbs", bbs);
			return "/bbs/edit_form.jsp";
		}
		else if(cmd.equals("edited")) {
			int num = Integer.parseInt(request.getParameter("num"));
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
	//		String uid = (String)request.getSession().getAttribute("uid");
			
			BBSVO bbs = new BBSVO();
			bbs.setNum(num);
			bbs.setTitle(title);
			bbs.setContents(contents);
			
			BBSDAO dao = new BBSDAO();
			boolean edited = dao.edit(bbs);
			try {
				PrintWriter out = response.getWriter();
				out.println(String.format("{\"%s\":%b}", "edited", edited));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(cmd.equals("delete")) {
			int num = Integer.parseInt(request.getParameter("num"));
			BBSDAO dao = new BBSDAO();
			boolean deleted = dao.delete(num);
			try {
				PrintWriter out = response.getWriter();
				out.println(String.format("{\"%s\":%b}", "deleted", deleted));
				return "bbs?cmd=list";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
