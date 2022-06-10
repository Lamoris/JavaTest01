package com.bbs.vo;

import java.util.*;

public class BBSVO {
	private int num;
	private String title;
	private String contents;
	private String author;
	private java.sql.Date wdate;

	public BBSVO() {
	}

	public BBSVO(int num) {
		this.num = num;
	}

	public BBSVO(int num, String title, String author, Date wdate, String contents) {
		this.num = num;
		this.title = title;
		this.author = author;
		this.wdate = (java.sql.Date) wdate;
		this.contents = contents;
	}
	public BBSVO(String[] token) {
		this(Integer.parseInt(token[0]),
				token[1],
				token[2],
				java.sql.Date.valueOf(token[3]),
				token[4]);
	}

	@Override
	public boolean equals(Object obj) {
		BBSVO other = (BBSVO) obj;
		return this.num == other.num;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.num);
	}

	@Override
	public String toString() {
		return String.format("%d|%s|%s|%s|%s", num, title, author, wdate, contents);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public java.sql.Date getWdate() {
		return wdate;
	}

	public void setWdate(java.sql.Date wdate) {
		this.wdate = wdate;
	}
}
