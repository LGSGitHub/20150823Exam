package com.hand.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageDaoImpl {

	public ResultSet getLanguage(Connection conn) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("select name from language");
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public ResultSet getLanguageId(Connection conn,String name)
	{
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement("select language_id from language where name=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next())
			{
				return rs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("tesssssssssssss");
		return null;
	}
}
