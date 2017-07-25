package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

public class UserDAO  {
	
	
	//JDBC���ú���
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	// SQL��ɾ�
	private final String USER_GET = "select * from users where id=? and password=?";
	
	//ȸ������̸� set�ƴѰ� 
	/* (non-Javadoc)
	 * @see com.springbook.biz.user.impl.UserService#getUser(com.springbook.biz.user.UserVO)
	 */
	public UserVO getUser(UserVO vo){
		UserVO user = null;
		
			System.out.println("===> ȸ�����");
				try {
					conn = JDBCUtil.getConnection();
					stmt = conn.prepareStatement(USER_GET);
					stmt.setString(1, vo.getId());
					stmt.setString(2, vo.getPassword());
					rs = stmt.executeQuery();
					
					if(rs.next()){
						user = new UserVO();
						
						user.setId(rs.getString("ID"));
						user.setPassword(rs.getString("PASSWORD"));
						user.setName(rs.getString("Name"));
						user.setRole(rs.getString("Role"));
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					JDBCUtil.close(stmt, conn);
				}
				return user;
				
	}
}
