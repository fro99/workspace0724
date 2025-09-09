package com.kh.jdbc.service;

import java.sql.Connection;
import java.util.List;

public class MemberService {
	
	public List<Member> selectAllMemverList(){
		Connection conn = JDBCTemplate.getConnection();
	}

}
