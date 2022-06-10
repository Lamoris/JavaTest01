package com.bbs.vo;

import java.sql.Date;
import java.util.Objects;

public class Employee {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private int sal;
	private java.sql.Date hiredate;
	private float comm;
	private int deptno;
	
	//Date type에 toString이 Override되어있음
	
	public Employee() {}
	
	public Employee(int empno, String ename, int deptno, int sal, Date hiredate) {
		this.empno = empno;
		this.ename = ename;
		this.deptno = deptno;
		this.sal = sal;
		this.hiredate = hiredate;
	}
	
	public Employee(int empno, String ename, String job, int mgr, int sal, Date hiredate, int comm, int deptno) {
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.sal = sal;
		this.hiredate = hiredate;
		this.comm = comm;
		this.deptno = deptno;
	}

	@Override
	public boolean equals(Object obj) {
		Employee emp = (Employee)obj;
		return this.empno == emp.empno;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.empno);
	}

	@Override
	public String toString() {
		return String.format("%d\t%s\t%d\t%d\t%s", empno, ename, deptno, sal, hiredate);
	}
	

	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public java.sql.Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(java.sql.Date hiredate) {
		this.hiredate = hiredate;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public float getComm() {
		return comm;
	}
	public void setComm(float comm) {
		this.comm = comm;
	}
}
