package com.moto.server.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.moto.server.bean.Diary;
import com.moto.server.dao.BaseDao;
import com.moto.server.dao.DiaryDao;
import com.mysql.jdbc.Statement;


public class DefaultDiaryDao extends BaseDao implements DiaryDao {

	public boolean delete(int id) {
		return true;
	}
	
	public Diary select(int id) {
		String sql = "select * from diary where r_id = ? ";
        final Diary diary = new Diary();
        final Object[] params = new Object[] { id } ;
		getJdbcTemplate().query(sql, params, new  RowCallbackHandler()  {
             public void  processRow(ResultSet rs) throws SQLException  {
            	 diary.setId(rs.getInt("r_id"));
            	 diary.setTitle(rs.getString("r_title"));
            	 diary.setContent(rs.getString("r_content"));
            	 diary.setDate(rs.getTimestamp("r_date"));
            	 diary.setUserId(rs.getInt("u_no"));
             }});
		return diary;
	}

	public int insert(String title, String content, int userId) {
		String sql = "INSERT INTO diary(r_title,r_content,u_no) VALUES(?, ?, ?)";  
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
        try {  
        	//important!!5.1.7版本之后的mysql-connector增加了返回GeneratedKeys的条件
            stmt = getJdbcTemplate().getDataSource().getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
            stmt.setString(1, title);  
            stmt.setString(2, content);  
            stmt.setInt(3, userId);              
            int num = stmt.executeUpdate(); 
            int rid = 0;
            if (num == 1) {  
                rs = stmt.getGeneratedKeys();  
                if(rs!=null){  
                    while(rs.next()){  
                    	rid = rs.getInt(1);  
                    }
                    rs.close();
                    return rid;
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

	public boolean update(int id, String title, String content) {
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Diary> selectByUser(int userId) {
		 String sql = "select * from diary where u_no = ?" ;
		 final Object[] params = new Object[] { userId } ;
         return (List<Diary>)getJdbcTemplate().query(sql, params, new  RowMapperResultSetExtractor(
                 new  DiaryRowMapper()));
	}
	 @SuppressWarnings("rawtypes")
	private class DiaryRowMapper implements RowMapper  {
         public Object mapRow(ResultSet rs,  int  index) throws SQLException  {
        	 final Diary diary = new Diary();
        	 diary.setId(rs.getInt("r_id"));
        	 diary.setTitle(rs.getString("r_title"));
        	 diary.setContent(rs.getString("r_content"));
        	 diary.setDate(rs.getTimestamp("r_date"));
        	 diary.setUserId(rs.getInt("u_no"));
             return  diary;
        } 
    } 
	
}
