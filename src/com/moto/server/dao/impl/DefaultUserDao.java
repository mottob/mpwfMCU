package com.moto.server.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.moto.server.bean.User;
import com.moto.server.dao.BaseDao;
import com.moto.server.dao.UserDao;
import com.mysql.jdbc.Statement;


public class DefaultUserDao extends BaseDao implements UserDao {

	public int insert(String username, String password, String email,
			String state) {
		String sql = "INSERT INTO user(u_name,u_pwd,u_email,u_state) VALUES(?, ?, ?, ?)"; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
        try {  
        	//important!!5.1.7版本之后的mysql-connector增加了返回GeneratedKeys的条件
            stmt = getJdbcTemplate().getDataSource().getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
            stmt.setString(1, username);  
            stmt.setString(2, password);  
            stmt.setString(3, email);              
            stmt.setString(4, state);              
            int num = stmt.executeUpdate(); 
            int uid = 0;
            if (num == 1) {  
                rs = stmt.getGeneratedKeys();  
                if(rs!=null){  
                    while(rs.next()){  
                    	uid = rs.getInt(1);  
                    }  
                    rs.close();
                    return uid;
                }  
            }  
        } catch (SQLException ex) {  
            ex.printStackTrace();  
        }  
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                }
            }
        }
        return 0;  
	}

	public boolean delete(int id) {
		return true;
	}

	public boolean update(int id, String username, String email, String state) {
		return true;
	}

	public boolean changePassword(int id, String password) {
		return true;
	}
	
	public User select(int id) {
		String sql = "select u_no, u_name, u_email, u_state from user where u_no = ? ";
        final User user = new User();
        final Object[] params = new Object[] { id } ;
		getJdbcTemplate().query(sql, params, new  RowCallbackHandler()  {
             public void  processRow(ResultSet rs) throws SQLException  {
            	 //user.setUserId(rs.getInt("u_no"));
            	 //user.setUsername(rs.getString("u_name"));
            	 //user.setEmail(rs.getString("u_email"));
            	 //user.setState(rs.getString("u_state"));
             }});
		return user;
	}
	
}
