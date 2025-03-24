package com.foodType.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodTypeJDBCDAO implements FoodTypeDAOinterface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/allieatfinal_db01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO foodType (storeId, type) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE foodType set type=? where foodTypeId = ?";
	private static final String DELETE = "DELETE FROM foodType where foodTypeId= ?";
	private static final String GET_ONE_STMT = "SELECT foodTypeId,storeId,type FROM foodType where foodTypeId = ?";
	private static final String GET_ALL_STMT = "SELECT foodTypeId,storeId,type FROM foodType order by foodTypeId";
	
	
	
	public void insert(FoodTypeVO foodType) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, foodType.getStoreId());
			pstmt.setString(2, foodType.getType());
			pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public void update(FoodTypeVO foodType) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
//			pstmt.setInt(1, foodType.getStoreId());
			pstmt.setString(1, foodType.getType());
			pstmt.setInt(2, foodType.getFoodTypeId());
			pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public void delete(Integer foodTypeId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, foodTypeId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	public FoodTypeVO findByPrimaryKey(Integer foodTypeId) {
		FoodTypeVO foodTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, foodTypeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				foodTypeVO = new FoodTypeVO();
				foodTypeVO.setFoodTypeId(rs.getInt("foodTypeId"));
				foodTypeVO.setStoreId(rs.getInt("storeId"));
				foodTypeVO.setType(rs.getString("type"));
				
			}
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return foodTypeVO;
		
	}

	public List<FoodTypeVO> getAll() {
		List<FoodTypeVO> list = new ArrayList<FoodTypeVO>();
		FoodTypeVO foodTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				foodTypeVO = new FoodTypeVO();
				foodTypeVO.setFoodTypeId(rs.getInt("foodTypeId"));
				foodTypeVO.setStoreId(rs.getInt("storeId"));
				foodTypeVO.setType(rs.getString("type"));
				list.add(foodTypeVO);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	public static void main(String[] args) {

		FoodTypeJDBCDAO dao = new FoodTypeJDBCDAO();
		FoodTypeVO foodTypeVO = new FoodTypeVO();

		// insert
		foodTypeVO.setStoreId(1);
		foodTypeVO.setType("葷食");
		dao.insert(foodTypeVO);
		System.out.print("上傳成功");

		// update
		FoodTypeVO ftVO3 = new FoodTypeVO();
//		ftVO3.setStoreId(6);
		ftVO3.setType("大肉");
		ftVO3.setFoodTypeId(2);
		dao.update(ftVO3);
		System.out.print("更新成功");
		

		// Delete
		dao.delete(2);
		System.out.print("刪除成功");
		

		// find by key
		FoodTypeVO ftVO2 = dao.findByPrimaryKey(11);
		System.out.print(ftVO2.getFoodTypeId()+ ", ");
		System.out.print(ftVO2.getStoreId()+ ", ");
		System.out.print(ftVO2.getType()+ ", ");
		
		

		// getAll
		List<FoodTypeVO> list = dao.getAll();
		for (FoodTypeVO temp : list) {
			System.out.print(temp.getFoodTypeId() + ", ");
			System.out.print(temp.getStoreId() + ", ");
			System.out.print(temp.getType() + ", ");
			System.out.println();
		}

	}


}
