package com.chan.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

@Service("dBUtil")
public class DBUtil {
	@Resource(name="selfDatasource")
	private DataSource dataSource_self;
	
	public void DML(List<String> list) throws SQLException{
		Connection conn = null;
		try {
			conn = dataSource_self.getConnection();
			DML(list,conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	public void DML(List<String> list,Connection conn) throws SQLException{
		Statement stmt = null;
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			for(String sql:list){
				stmt.execute(sql);
			}
			conn.commit();
			//还原connection的自动提交属性
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
			throw e;
		} finally {
			if(stmt != null){
				try {
					stmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
