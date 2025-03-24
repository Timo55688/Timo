package com.store.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.List;

public class StoreJDBCDAO implements StoreDAOInterface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/allieatfinal_db01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO store (name, managerName, email, password, "
			+ "phoneNum, guiNum, businessRegNum, " + "regTime, points, accStat, opStat, "
			+ "opTime, pickTime, lastOrder, closeTime, " + "address, county, district, postalCode, "
			+ "starNum, visitorsNum,reviewed) "
			+ "VALUES (? ,? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE store set name=?,managerName=?,email=?,"
			+ "password=?,phoneNum=?,guiNum=?,businessRegNum=?," + "opStat=?,"
			+ "opTime=?,pickTime=?,lastOrder=?,closeTime=?,address=?,"
			+ "county=?,district=?,postalCode=?" + " where storeId = ?";

	private static final String DELETE = "DELETE FROM store where storeId= ?";

	private static final String GET_ONE_STMT = "SELECT name,managerName,email,password,phoneNum,guiNum,"
			+ "businessRegNum,regTime,points,accStat,opStat,opTime,pickTime,lastOrder,closeTime,"
			+ "address,county,district,postalCode,starNum,visitorsNum,storeId,reviewed FROM store where storeId = ?";

	private static final String GET_ALL_STMT = "SELECT name,managerName,email,password,phoneNum,guiNum,"
			+ "businessRegNum,regTime,points,accStat,opStat,opTime,pickTime,lastOrder,closeTime,"
			+ "address,county,district,postalCode,starNum,visitorsNum,reviewed,storeId FROM store order by storeId";

	public void insert(StoreVO storeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, storeVO.getName());
			pstmt.setString(2, storeVO.getManagerName());
			pstmt.setString(3, storeVO.getEmail());
			pstmt.setString(4, storeVO.getPassword());
			pstmt.setString(5, storeVO.getPhoneNum());
			pstmt.setString(6, storeVO.getGuiNum());
			pstmt.setString(7, storeVO.getBusinessRegNum());
			pstmt.setTimestamp(8, storeVO.getRegTime());
			pstmt.setInt(9, storeVO.getPoints());
			pstmt.setInt(10, storeVO.getAccStat());
			pstmt.setInt(11, storeVO.getOpStat());
			pstmt.setString(12, storeVO.getOpTime());
			pstmt.setString(13, storeVO.getPickTime());
			pstmt.setString(14, storeVO.getLastOrder());
			pstmt.setString(15, storeVO.getCloseTime());
			pstmt.setString(16, storeVO.getAddress());
			pstmt.setString(17, storeVO.getCounty());
			pstmt.setString(18, storeVO.getDistrict());
			pstmt.setInt(19, storeVO.getPostalCode());
			pstmt.setInt(20, storeVO.getStarNum());
			pstmt.setInt(21, storeVO.getVisitorsNum());
			pstmt.setInt(22, storeVO.getReviewed());
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

	public void update(StoreVO storeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, storeVO.getName());
			pstmt.setString(2, storeVO.getManagerName());
			pstmt.setString(3, storeVO.getEmail());
			pstmt.setString(4, storeVO.getPassword());
			pstmt.setString(5, storeVO.getPhoneNum());
			pstmt.setString(6, storeVO.getGuiNum());
			pstmt.setString(7, storeVO.getBusinessRegNum());
//			pstmt.setTimestamp(8, storeVO.getRegTime());
//			pstmt.setInt(8, storeVO.getPoints());
//			pstmt.setInt(9, storeVO.getAccStat());
			pstmt.setInt(8, storeVO.getOpStat());
			pstmt.setString(9, storeVO.getOpTime());
			pstmt.setString(10, storeVO.getPickTime());
			pstmt.setString(11, storeVO.getLastOrder());
			pstmt.setString(12, storeVO.getCloseTime());
			pstmt.setString(13, storeVO.getAddress());
			pstmt.setString(14, storeVO.getCounty());
			pstmt.setString(15, storeVO.getDistrict());
			pstmt.setInt(16, storeVO.getPostalCode());
//			pstmt.setInt(19, storeVO.getStarNum());
//			pstmt.setInt(20, storeVO.getVisitorsNum());
//			pstmt.setInt(20, storeVO.getReviewed());
			pstmt.setInt(17, storeVO.getStoreId());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public void delete(Integer storeId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, storeId);

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

	public StoreVO findByPrimaryKey(Integer storeId) {

		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, storeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeVO = new StoreVO();
				storeVO.setName(rs.getString("name"));
				storeVO.setManagerName(rs.getString("managerName"));
				storeVO.setEmail(rs.getString("email"));
				storeVO.setPassword(rs.getString("password"));
				storeVO.setPhoneNum(rs.getString("phoneNum"));
				storeVO.setGuiNum(rs.getString("guiNum"));
				storeVO.setBusinessRegNum(rs.getString("businessRegNum"));
				storeVO.setRegTime(rs.getTimestamp("regTime"));
				storeVO.setPoints(rs.getInt("points"));
				storeVO.setAccStat(rs.getInt("accStat"));
				storeVO.setOpStat(rs.getInt("opStat"));
				storeVO.setOpTime(rs.getString("opTime"));
				storeVO.setPickTime(rs.getString("pickTime"));
				storeVO.setLastOrder(rs.getString("lastOrder"));
				storeVO.setCloseTime(rs.getString("closeTime"));
				storeVO.setAddress(rs.getString("address"));
				storeVO.setCounty(rs.getString("county"));
				storeVO.setDistrict(rs.getString("district"));
				storeVO.setPostalCode(rs.getInt("postalCode"));
				storeVO.setStarNum(rs.getInt("starNum"));
				storeVO.setVisitorsNum(rs.getInt("visitorsNum"));
				storeVO.setReviewed(rs.getInt("reviewed"));
				storeVO.setStoreId(rs.getInt("storeId"));
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

		return storeVO;

	}

	public List<StoreVO> getAll() {
		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO stVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				stVO = new StoreVO();
				stVO.setStoreId(rs.getInt("storeId"));
				stVO.setName(rs.getString("name"));
				stVO.setManagerName(rs.getString("managerName"));
				stVO.setEmail(rs.getString("email"));
				stVO.setPassword(rs.getString("password"));
				stVO.setPhoneNum(rs.getString("phoneNum"));
				stVO.setGuiNum(rs.getString("guiNum"));
				stVO.setBusinessRegNum(rs.getString("businessRegNum"));
				stVO.setRegTime(rs.getTimestamp("regTime"));
				stVO.setPoints(rs.getInt("points"));
				stVO.setAccStat(rs.getInt("accStat"));
				stVO.setOpStat(rs.getInt("opStat"));
				stVO.setOpTime(rs.getString("opTime"));
				stVO.setPickTime(rs.getString("pickTime"));
				stVO.setLastOrder(rs.getString("lastOrder"));
				stVO.setCloseTime(rs.getString("closeTime"));
				stVO.setAddress(rs.getString("address"));
				stVO.setCounty(rs.getString("county"));
				stVO.setDistrict(rs.getString("district"));
				stVO.setPostalCode(rs.getInt("postalCode"));
				stVO.setStarNum(rs.getInt("starNum"));
				stVO.setReviewed(rs.getInt("reviewed"));
				stVO.setVisitorsNum(rs.getInt("visitorsNum"));
				stVO.setStoreId(rs.getInt("StoreId"));
				list.add(stVO);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void main(String[] args) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		StoreJDBCDAO dao = new StoreJDBCDAO();
		StoreVO storeVO = new StoreVO();

		// Insert
//		storeVO.setName("陶陶亭");
//		storeVO.setManagerName("麥當雄");
//		storeVO.setEmail("abcsfrr@jfss.com");
//		storeVO.setPassword("123456");
//		storeVO.setPhoneNum("0988888888");
//		storeVO.setGuiNum("0800");
//		storeVO.setBusinessRegNum("12345678");
//		storeVO.setReviewed(1);
//		storeVO.setRegTime(timestamp);
//		storeVO.setPoints(100);
//		storeVO.setAccStat(1);
//		storeVO.setOpStat(1);
//		storeVO.setOpTime("00:00");
//		storeVO.setPickTime("00:00");
//		storeVO.setLastOrder("00:00");
//		storeVO.setCloseTime("00:00");
//		storeVO.setAddress("台北市某某路");
//		storeVO.setCounty("台北市");
//		storeVO.setDistrict("大安區");
//		storeVO.setPostalCode(106);
//		storeVO.setStarNum(5);
//		storeVO.setVisitorsNum(500);
//		dao.insert(storeVO);
//		System.out.println("上傳成功");

//		// update
		storeVO.setName("好吃喔");
		storeVO.setManagerName("王小明");
		storeVO.setEmail("abc@god.com");
		storeVO.setPassword("123456");
		storeVO.setPhoneNum("0988888888");
		storeVO.setGuiNum("0800");
		storeVO.setBusinessRegNum("12345678");
//		storeVO.setReviewed(1);
//		storeVO.setRegTime(timestamp);
//		storeVO.setPoints(100);
//		storeVO.setAccStat(1);
		storeVO.setOpStat(1);
		storeVO.setOpTime("10:00");
		storeVO.setPickTime("12:00");
		storeVO.setLastOrder("19:00");
		storeVO.setCloseTime("20:00");
		storeVO.setAddress("台北市某某路");
		storeVO.setCounty("台北市");
		storeVO.setDistrict("大安區");
		storeVO.setPostalCode(106);
//		storeVO.setStarNum(5);
//	stVO1.setVisitorsNum(500);
		storeVO.setStoreId(7);
		dao.update(storeVO);
		System.out.println("更新成功");

		// Delete
		dao.delete(11);
		System.out.println("刪除成功");

		// find by key
//		StoreVO stVO2 = dao.findByPrimaryKey(5);
//		System.out.print(stVO2.getStoreId() + ", ");
//		System.out.print(stVO2.getName() + ", ");
//		System.out.print(stVO2.getManagerName() + ", ");
//		System.out.print(stVO2.getEmail() + ", ");
//		System.out.print(stVO2.getPassword() + ", ");
//		System.out.print(stVO2.getPhoneNum() + ", ");
//		System.out.print(stVO2.getGuiNum() + ", ");
//		System.out.print(stVO2.getBusinessRegNum() + ", ");
//		System.out.print(stVO2.getRegTime() + ", ");
//		System.out.print(stVO2.getPoints() + ", ");
//		System.out.print(stVO2.getAccStat() + ", ");
//		System.out.print(stVO2.getOpStat() + ", ");
//		System.out.print(stVO2.getOpTime() + ", ");
//		System.out.print(stVO2.getPickTime() + ", ");
//		System.out.print(stVO2.getLastOrder() + ", ");
//		System.out.print(stVO2.getCloseTime() + ", ");
//		System.out.print(stVO2.getAddress() + ", ");
//		System.out.print(stVO2.getCounty() + ", ");
//		System.out.print(stVO2.getDistrict() + ", ");
//		System.out.print(stVO2.getPostalCode() + ", ");
//		System.out.print(stVO2.getStarNum() + ", ");
//		System.out.print(stVO2.getVisitorsNum() + ", ");
//		System.out.print(stVO2.getReviewed());

//		 getAll
//		List<StoreVO> list = dao.getAll();
//		for (StoreVO temp : list) {
//			System.out.print(temp.getStoreId() + ", ");
//			System.out.print(temp.getName() + ", ");
//			System.out.print(temp.getManagerName() + ", ");
//			System.out.print(temp.getEmail() + ", ");
//			System.out.print(temp.getPassword() + ", ");
//			System.out.print(temp.getPhoneNum() + ", ");
//			System.out.print(temp.getGuiNum() + ", ");
//			System.out.print(temp.getBusinessRegNum() + ", ");
//			System.out.print(temp.getRegTime() + ", ");
//			System.out.print(temp.getPoints() + ", ");
//			System.out.print(temp.getAccStat() + ", ");
//			System.out.print(temp.getOpStat() + ", ");
//			System.out.print(temp.getOpTime() + ", ");
//			System.out.print(temp.getPickTime() + ", ");
//			System.out.print(temp.getLastOrder() + ", ");
//			System.out.print(temp.getCloseTime() + ", ");
//			System.out.print(temp.getAddress() + ", ");
//			System.out.print(temp.getCounty() + ", ");
//			System.out.print(temp.getDistrict() + ", ");
//			System.out.print(temp.getPostalCode() + ", ");
//			System.out.print(temp.getStarNum() + ", ");
//			System.out.print(temp.getVisitorsNum() + ", ");
//			System.out.print(temp.getReviewed());
//			System.out.println();
//		}

	}
}
