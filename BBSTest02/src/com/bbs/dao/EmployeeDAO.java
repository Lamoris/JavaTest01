package com.bbs.dao;

import java.sql.*;
import java.util.*;

import com.bbs.vo.Employee;

public class EmployeeDAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Connection getConn() {
		try {
			// 코드의 투명성을 위해서 지역변수로 선언해서 직관적인 코드를 만들수 있다.\
			// ojdbc8.jar에서 OracleDriver를 불러오는것
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB Conn error");
		}
		return null;
	}
	public boolean added(Employee emp) {
		conn = getConn();
		String sql = "INSERT INTO employee VALUES(?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setString(3, emp.getJob());
			pstmt.setInt(4, emp.getMgr());
			pstmt.setInt(5, emp.getSal());
			pstmt.setDate(6, emp.getHiredate());
			pstmt.setFloat(7, emp.getComm());
			pstmt.setInt(8, emp.getDeptno());
			int n = pstmt.executeUpdate();
			return n > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Employee> getList() {
		conn = getConn();
		try {
			// stmt = conn.createStatement();
			String sql = "SELECT * FROM employee";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<Employee> list = new ArrayList<>();
			while (rs.next()) {
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				int deptno = rs.getInt("DEPTNO");
				int sal = rs.getInt("SAL");
				java.sql.Date hiredate = rs.getDate("HIREDATE");

				Employee emp = new Employee();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setDeptno(deptno);
				emp.setSal(sal);
				emp.setHiredate(hiredate);
				list.add(emp);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}

	public Employee getEmp(int empno) {
		conn = getConn();
		try {
			// stmt = conn.createStatement();
			String sql = "SELECT * FROM employee WHERE empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int eno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				String job = rs.getString("JOB");
				int mgr = rs.getInt("MGR");
				int sal = rs.getInt("SAL");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				float comm = rs.getInt("COMM");
				int deptno = rs.getInt("DEPTNO");

				Employee emp = new Employee();
				emp.setEmpno(eno);
				emp.setEname(ename);
				emp.setJob(job);
				emp.setMgr(mgr);
				emp.setSal(sal);
				emp.setHiredate(hiredate);
				emp.setComm(comm);
				emp.setDeptno(deptno);
				return emp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}

	public Employee searchByName(String targetEname) {
		conn = getConn();
		try {
			String sql = "SELECT * FROM employee WHERE ename=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, targetEname);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int eno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				String job = rs.getString("JOB");
				int mgr = rs.getInt("MGR");
				int sal = rs.getInt("SAL");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				float comm = rs.getInt("COMM");
				int deptno = rs.getInt("DEPTNO");

				Employee emp = new Employee();
				emp.setEmpno(eno);
				emp.setEname(ename);
				emp.setJob(job);
				emp.setMgr(mgr);
				emp.setSal(sal);
				emp.setHiredate(hiredate);
				emp.setComm(comm);
				emp.setDeptno(deptno);
				return emp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}

	public List<Employee> getDept(int tatgetDeptno) {
		conn = getConn();
		try {
			// stmt = conn.createStatement();
			String sql = "SELECT * FROM employee WHERE deptno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tatgetDeptno);
			rs = pstmt.executeQuery();

			List<Employee> list = new ArrayList<>();
			while (rs.next()) {
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				String job = rs.getString("JOB");
				int mgr = rs.getInt("MGR");
				int sal = rs.getInt("SAL");
				java.sql.Date hiredate = rs.getDate("HIREDATE");
				float comm = rs.getInt("COMM");
				int deptno = rs.getInt("DEPTNO");

				Employee emp = new Employee();
				emp.setEmpno(empno);
				emp.setEname(ename);
				emp.setJob(job);
				emp.setMgr(mgr);
				emp.setSal(sal);
				emp.setHiredate(hiredate);
				emp.setComm(comm);
				emp.setDeptno(deptno);
				list.add(emp);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}

	public List<String> getItemList(String category) {
		conn = getConn();
		String sql = "SELECT " + category + " FROM employee";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<String> list = new ArrayList<>();
			while (rs.next()) {
				list.add(rs.getString(category));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean edit(Employee emp) {
		conn = getConn();

		try {
			/*
			 * stmt = conn.createStatement(); String sql =
			 * String.format("UPDATE employee SET deptno=%d, sal=%d WHERE empno=%d",
			 * emp.getDeptno(), emp.getSal(), emp.getEmpno()); int n =
			 * stmt.executeUpdate(sql); System.out.println("DAO edit() : "+(n>0)); return
			 * n>0;
			 */
			String sql = "UPDATE employee SET deptno=?, sal=? WHERE empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getDeptno());
			pstmt.setInt(2, emp.getSal());
			pstmt.setInt(3, emp.getEmpno());
			int n = pstmt.executeUpdate();
			return n > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}
	public boolean delete(int empno) {
		conn = getConn();
		try {
			String sql = "Delete FROM employee WHERE empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			int n = pstmt.executeUpdate();
			return n>0;
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}
	/*
	 * public String getImage(int empno) { conn = getConn(); try { // stmt =
	 * conn.createStatement(); String sql =
	 * "SELECT picture FROM employee WHERE empno=?"; pstmt =
	 * conn.prepareStatement(sql); pstmt.setInt(1, empno); rs =
	 * pstmt.executeQuery(); int num = 0; String getImage = null; while (rs.next())
	 * { rs.getString(num); return getImage; } } catch (SQLException e) {
	 * e.printStackTrace(); } finally { closeAll(); }
	 * 
	 * }
	 */
	private void closeAll() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
