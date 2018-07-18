package com.dwm.daisomanage.first;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dwm.daisomanage.main.DBManager;

public class SuppliesDAO {

	public static void info(Supplies s) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBManager.connect();
			
			sql = "select * from FEB13_DWM_SUPPLIES";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			ArrayList<Supplies> supplies = new ArrayList<>();
			Supplies tempSupplies = null;
			
			while(rs.next()) {
				tempSupplies = new Supplies(rs.getInt("s_no"), rs.getString("s_name"), rs.getInt("s_price"), rs.getInt("s_amount"), rs.getInt("s_floor"));
				supplies.add(tempSupplies);
			}
			
			if (supplies.size() == 0) {
				SuppliesController.goToInfoResult("실패", null);
			} else {
				SuppliesController.goToInfoResult("성공", supplies);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			SuppliesController.goToInfoResult(null, null);
		} finally {
			DBManager.destroy(conn, pstmt, rs);
		}
		
	}

	public static void del(Supplies s) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBManager.connect();
			
			sql = "delete from FEB13_DWM_SUPPLIES where s_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, s.getNo());
			
			if (pstmt.executeUpdate() == 1) {
				SuppliesController.goToDelResult("성공");
			} else {
				SuppliesController.goToDelResult("실패");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			SuppliesController.goToDelResult(null);
		} finally {
			DBManager.destroy(conn, pstmt, null);
		}	
	}

}
