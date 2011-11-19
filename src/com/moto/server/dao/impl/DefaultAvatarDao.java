package com.moto.server.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.moto.server.bean.Avatar;
import com.moto.server.dao.AvatarDao;
import com.moto.server.dao.BaseDao;
import com.mysql.jdbc.Statement;


public class DefaultAvatarDao extends BaseDao implements AvatarDao {

	public boolean delete(int id) {
		return true;
	}
	
	public Avatar select(int id) {
		String sql = "select * from head where h_id = ? ";
        final Avatar avatar = new Avatar();
        final Object[] params = new Object[] { id };
		getJdbcTemplate().query(sql, params, new  RowCallbackHandler()  {
             public void  processRow(ResultSet rs) throws SQLException  {
            	 avatar.setId(rs.getInt("h_id"));
            	 avatar.setUserId(rs.getInt("u_no"));
            	 avatar.setDescription(rs.getString("h_des"));
            	 avatar.setData(rs.getBytes("h_data"));
             }});
		return avatar;
	}

	public int insert(String description, File file, int userId) {
		String sql = "INSERT INTO head(h_des,h_data,u_no) VALUES(?, ?, ?)"; 
		FileInputStream fis=null;
		ResultSet rs=null;
		PreparedStatement stmt=null;
        try {  
        	fis = new FileInputStream(file);
            //PreparedStatement stmt = getJdbcTemplate().getDataSource().getConnection().prepareStatement(sql);  
        	//important!!5.1.7版本之后的mysql-connector增加了返回GeneratedKeys的条件
        	stmt = getJdbcTemplate().getDataSource().getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, description);  
            stmt.setBinaryStream(2, fis, (int)file.length());  
            stmt.setInt(3, userId);              
            int num = stmt.executeUpdate(); 
            int avid = 0;
            if (num == 1) {  
                rs = stmt.getGeneratedKeys();  
                if(rs!=null){  
                    while(rs.next()){  
                    	avid = rs.getInt(1);  
                    } 
                    rs.close();
                    return avid;
                }  
            }  
        } catch (SQLException ex) {  
            ex.printStackTrace();  
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
        finally {
        	if (fis != null) {
                try {
                	fis.close();
                } catch (IOException ex) {
                }
            }
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

	public boolean update(int id, String description, File file) {
		return true;
	}

	public Avatar selectByUser(int userId) {
		String sql = "select * from head where u_no = ? ";
        final Avatar avatar = new Avatar();
        final Object[] params = new Object[] { userId } ;
		getJdbcTemplate().query(sql, params, new  RowCallbackHandler()  {
             public void  processRow(ResultSet rs) throws SQLException  {
            	 avatar.setId(rs.getInt("h_id"));
            	 avatar.setUserId(rs.getInt("u_no"));
            	 avatar.setDescription(rs.getString("h_des"));
            	 avatar.setData(rs.getBytes("h_data"));
             }});
		return avatar;
	}
	
}
