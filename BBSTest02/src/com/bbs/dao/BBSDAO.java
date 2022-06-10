package com.bbs.dao;

import java.io.*;
import java.util.*;

import com.bbs.vo.BBSVO;

public class BBSDAO {
	private static final String fpath = "C:/test/bbs.txt";

	public int getLastNum() {

		try {
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			String line = null;
			int num = 0;
			while ((line = br.readLine()) != null) {
				num = Integer.parseInt(line.split("\\|")[0]);
			}
			br.close();
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<BBSVO> getList() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			String line = null;
			List<BBSVO> list = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				String[] token = line.split("\\|");
				BBSVO bbs = new BBSVO();
				bbs.setNum(Integer.parseInt(token[0]));
				bbs.setTitle(token[1]);
				bbs.setAuthor(token[2]);
				bbs.setWdate(java.sql.Date.valueOf(token[3]));
				list.add(bbs);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BBSVO getBBS(int num) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			String line = null;
			int _num = 0;
			while ((line = br.readLine()) != null) {
				String[] token = line.split("\\|");
				_num = Integer.parseInt(token[0]);
				if (num == _num) {
					BBSVO bbs = new BBSVO();
					bbs.setNum(Integer.parseInt(token[0]));
					bbs.setTitle(token[1]);
					bbs.setAuthor(token[2]);
					bbs.setWdate(java.sql.Date.valueOf(token[3]));
					bbs.setContents(token[4]);
					br.close();
					return bbs;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean add(BBSVO bbs) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fpath, true));
			pw.println(bbs);
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean edit(BBSVO targetbbs) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			String line = null;
			List<BBSVO>	list = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				String token[] = line.split("\\|");
				BBSVO bbs = new BBSVO(token);
				if(bbs.equals(targetbbs)) {
					bbs.setTitle(targetbbs.getTitle());
					bbs.setContents(targetbbs.getContents());
				}
				list.add(bbs);
			}
			br.close();
			return overwrite(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean delete(int targetNum) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fpath));
			String line = null;
			List<BBSVO>	list = new ArrayList<>();
			while((line = br.readLine()) != null) {
				String token[] = line.split("\\|");
				BBSVO bbs = new BBSVO(token);
				if(bbs.getNum()==targetNum) {
					continue;	
				}else
					list.add(bbs);
			}
			br.close();
			return overwrite(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean overwrite(List<BBSVO> list) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fpath));
			for (int i = 0; i < list.size(); i++) {
				pw.println(list.get(i));
			}
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
