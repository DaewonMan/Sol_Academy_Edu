package com.dwm.daisomanage.sell;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.dwm.daisomanage.first.Supplies;
import com.dwm.daisomanage.main.DBManager;

public class SellDAO {

	public static void sell(SellItem item) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql1 = null, sql2 = null;

		try {
			conn = DBManager.connect();

			sql1 = "update FEB13_DWM_SellItem " + "set s_sold = s_sold + ? " + "where s_name = ?";
			pstmt = conn.prepareStatement(sql1);

			pstmt.setInt(1, item.getSold());
			pstmt.setString(2, item.getName());

			if (pstmt.executeUpdate() == 1) {
				pstmt.close();

				// supplies에서 판매된 양만큼 줄이는 sql만든다.
				sql2 = "update FEB13_DWM_SUPPLIES " + "set s_amount = s_amount - ? " + "where s_name = ?";
				pstmt = conn.prepareStatement(sql2);

				pstmt.setInt(1, item.getSold());
				pstmt.setString(2, item.getName());

				if (pstmt.executeUpdate() == 1) {
					SellController.goToSellResult("성공");
				} else {
					SellController.goToSellResult("실패");
				}
			} else {

				SellController.goToSellResult("실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
			SellController.goToSellResult(null);
		} finally {
			DBManager.destroy(conn, pstmt, null);
		}

	}

	public static void info() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			conn = DBManager.connect();

			sql = "select * from FEB13_DWM_SellItem";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			ArrayList<SellItem> items = new ArrayList<>();
			SellItem item = null;

			while (rs.next()) {
				item = new SellItem(rs.getInt("s_no"), rs.getString("s_name"), rs.getInt("s_sold"), rs.getInt("s_sum"));
				items.add(item);
			}

			if (items.size() == 0) {
				SellController.goToInfoResult("실패", null);
			} else {
				SellController.goToInfoResult("성공", items);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SellController.goToInfoResult(null, null);
		} finally {
			DBManager.destroy(conn, pstmt, rs);
		}
	}

	public static void sales() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int tempSale = 0;

		try {
			conn = DBManager.connect();

			sql = "select * from FEB13_DWM_SellItem";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			HashMap<String, Integer> items = new HashMap<>();

			while (rs.next()) {
				items.put(rs.getString("s_name"), rs.getInt("s_sold"));
			}

			if (items.size() == 0) {
				SellController.goToSalesResult("실패", 0);
			}
			
			/*supplies 테이블의 상품명, 판매가격 얻기*/
			pstmt.close();
			rs.close();
			sql = "select * from FEB13_DWM_SUPPLIES";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			ArrayList<Supplies> supplies = new ArrayList<>();
			Supplies supTemp = null;
			while (rs.next()) {
				supTemp = new Supplies(0, rs.getString("s_name"), rs.getInt("s_price"), 0, 0);
				supplies.add(supTemp);
			}

			if (supplies.size() == 0) {
				SellController.goToSalesResult("실패", 0);
			}
			
			int temp = 0;
			String key = null;
			
			// 총 매출 구하기
			Iterator<String> keys = items.keySet().iterator();
			
			while(keys.hasNext()) {
				key = keys.next();
				temp = items.get(key);
				for (Supplies s : supplies) {
					if(key.equals(s.getName())) {
						tempSale += temp * s.getPrice();
					}
				}
			}
			
			SellController.goToSalesResult("성공", tempSale);
			
		} catch (Exception e) {
			e.printStackTrace();
			SellController.goToInfoResult(null, null);
		} finally {
			DBManager.destroy(conn, pstmt, rs);
		}
	}

}
