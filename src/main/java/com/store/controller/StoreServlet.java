package com.store.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.cj.x.protobuf.Mysqlx.Error;
import com.store.model.StoreService;
import com.store.model.StoreVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/store/store.do")
public class StoreServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOneForDisplay".equals(action)) {

			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);

			String str = req.getParameter("storeId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsg.add("輸入餐廳編號");
			}
			if (!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/store/selectStore.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer storeId = null;
			try {
				storeId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsg.add("wrong format");
			}

			if (!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/store/selectStore.jsp");
				failureView.forward(req, res);
				return;
			}

			StoreService storeSvc = new StoreService();
			StoreVO storeVO = storeSvc.getOneStore(storeId);
			if (storeVO == null) {
				errorMsg.add("查無資料");
			}

			if (!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/store/selectStore.jsp");
				failureView.forward(req, res);
				return;
			}

			req.setAttribute("storeVO", storeVO);
			String url = "/store/listOneStore.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOneForUpdate".equals(action)) {

			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);

			Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());
			StoreService storeSvc = new StoreService();
			StoreVO storeVO = storeSvc.getOneStore(storeId);

			req.setAttribute("storeVO", storeVO);
			String url = "/store/updateStoreInput.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("update".equals(action)) {

			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);

			Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());
			
			String name = req.getParameter("name");
			String nameReg = "^(?! )[ \u4e00-\u9fa5a-zA-Z0-9_]+(?<! )$";
			if (name == null || name.trim().length() == 0) {
				errorMsg.add("plz input name");
			} else if (!name.trim().matches(nameReg)) {
				errorMsg.add("wrong format");
			}

			String managerName = req.getParameter("managerName");
			String managerNameReg = "^(?! )[ \u4e00-\u9fa5a-zA-Z0-9_]+(?<! )$";
			if (managerName == null || managerName.trim().length() == 0) {
				errorMsg.add("plz input managerName");
			} else if (!managerName.trim().matches(managerNameReg)) {
				errorMsg.add("wrong format");
			}

			String email = req.getParameter("email");
			String emailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
			if (email == null || email.trim().length() == 0) {
				errorMsg.add("plz input email");
			} else if (!email.trim().matches(emailReg)) {
				errorMsg.add("wrong format");
			}

			String password = req.getParameter("password");
			String passwordReg = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
			if (password == null || password.trim().length() == 0) {
				errorMsg.add("plz input email");
			} else if (!password.trim().matches(passwordReg)) {
				errorMsg.add("至少包含英文和數字且6碼以上");
			}

			String phoneNum = req.getParameter("phoneNum");
			String phoneNumReg = "^09\\d{8}$";
			if (phoneNum == null || phoneNum.trim().length() == 0) {
				errorMsg.add("plz input phonenumber");
			} else if (!phoneNum.trim().matches(phoneNumReg)) {
				errorMsg.add("wrong format");
			}

			String guiNum = req.getParameter("guiNum");
			String guiNumReg = "^\\d{8}$";
			if (guiNum == null || guiNum.trim().length() == 0) {
				errorMsg.add("plz input reg number");
			} else if (!guiNum.trim().matches(guiNumReg)) {
				errorMsg.add("統一編號只有八碼");
			}

			String businessRegNum = req.getParameter("businessRegNum");
			String businessRegNumReg = "^\\d{8}$";
			if (businessRegNum == null || businessRegNum.trim().length() == 0) {
				errorMsg.add("plz input reg number");
			} else if (!businessRegNum.trim().matches(businessRegNumReg)) {
				errorMsg.add("營業登記只有八碼");
			}

//			Integer points = null;
//			try {
//				points = Integer.valueOf(req.getParameter("points").trim());
//			} catch (NumberFormatException e) {
//				points = 0;
//				errorMsg.add("num plz");
//			}

//			Integer accStat = null;
//			try {
//				accStat = Integer.valueOf(req.getParameter("accStat").trim());
//				if(accStat < 0 || accStat > 1) {
//					errorMsg.add("0-1 plz");
//				}
//			} catch (NumberFormatException e) {
//				accStat = 0;
//				errorMsg.add("num plz");
//			}

			Integer opStat = null;
			try {
				opStat = Integer.valueOf(req.getParameter("opStat").trim());
				if(opStat < 0 || opStat > 1) {
					errorMsg.add("0-1 plz");
				}
			} catch (NumberFormatException e) {
				opStat = 0;
				errorMsg.add("num plz");
			}

			String opTime = req.getParameter("opTime");
			String opTimeReg = "^([01]\\d|2[0-3]):[0-5]\\d$";
			if (opTime == null || opTime.trim().length() == 0) {
				errorMsg.add("input time");
			} else if (!opTime.trim().matches(opTimeReg)) {
				errorMsg.add("wrong format");
			}

			String pickTime = req.getParameter("pickTime");
			String pickTimeReg = "^([01]\\d|2[0-3]):[0-5]\\d$";
			if (pickTime == null || pickTime.trim().length() == 0) {
				errorMsg.add("input time");
			} else if (!pickTime.trim().matches(pickTimeReg)) {
				errorMsg.add("wrong format");
			}

			String lastOrder = req.getParameter("lastOrder");
			String lastOrderReg = "^([01]\\d|2[0-3]):[0-5]\\d$";
			if (lastOrder == null || lastOrder.trim().length() == 0) {
				errorMsg.add("input time");
			} else if (!lastOrder.trim().matches(lastOrderReg)) {
				errorMsg.add("wrong format");
			}

			String closeTime = req.getParameter("closeTime");
			String closeTimeReg = "^([01]\\d|2[0-3]):[0-5]\\d$";
			if (closeTime == null || closeTime.trim().length() == 0) {
				errorMsg.add("input time");
			} else if (!closeTime.trim().matches(closeTimeReg)) {
				errorMsg.add("wrong format");
			}

			String address = req.getParameter("address");
			String addressReg = "^[\\u4e00-\\u9fa5a-zA-Z0-9\\-()（）．.、\\s]{6,100}$";
			if (address == null || address.trim().length() == 0) {
				errorMsg.add("plz input");
			} else if (!address.trim().matches(addressReg)) {
				errorMsg.add("wrong format");
			}

			String county = req.getParameter("county");
			String countyReg = "^[\\u4e00-\\u9fa5]{2,4}[市縣]$";
			if (county == null || county.trim().length() == 0) {
				errorMsg.add("plz input");
			} else if (!county.trim().matches(countyReg)) {
				errorMsg.add("wrong format");
			}

			String district = req.getParameter("district");
			String districtReg = "^[\\u4e00-\\u9fa5]{2,5}[區鎮市鄉]$";
			if (district == null || district.trim().length() == 0) {
				errorMsg.add("plz input");
			} else if (!district.trim().matches(districtReg)) {
				errorMsg.add("wrong format");
			}

			Integer postalCode = null;
			try {
				postalCode = Integer.valueOf(req.getParameter("postalCode").trim());
			} catch (NumberFormatException e) {
				postalCode = 0;
				errorMsg.add("num plz");
			}
			
//			Integer starNum = null;
//			try {
//				starNum = Integer.valueOf(req.getParameter("starNum").trim());
//				if(starNum < 0 || starNum > 3) {
//					errorMsg.add("0-3 plz");
//				}
//			}catch(NumberFormatException e) {
//				starNum = 0;
//				errorMsg.add("num plz");
//			} 
			
//			Integer visitorsNum = null;
//			try {
//				visitorsNum = Integer.valueOf(req.getParameter("visitorsNum").trim());
//			}catch(NumberFormatException e) {
//				visitorsNum = 0;
//				errorMsg.add("不需要輸入數字");
//			}
			
//			Integer reviewed = null;
//			try {
//				reviewed = Integer.valueOf(req.getParameter("reviewed").trim());
//			}catch(NumberFormatException e) {
//				reviewed = 0;
//				errorMsg.add("num plz");
//			}
			
			StoreVO storeVO = new StoreVO();
			storeVO.setStoreId(storeId);
			storeVO.setName(name);
			storeVO.setManagerName(managerName);
			storeVO.setEmail(email);
			storeVO.setPassword(password);
			storeVO.setPhoneNum(phoneNum);
			storeVO.setGuiNum(guiNum);
			storeVO.setBusinessRegNum(businessRegNum);
//			storeVO.setPoints(points);
//			storeVO.setAccStat(accStat);
			storeVO.setOpStat(opStat);
			storeVO.setOpTime(opTime);
			storeVO.setPickTime(pickTime);
			storeVO.setLastOrder(lastOrder);
			storeVO.setCloseTime(closeTime);
			storeVO.setAddress(address);
			storeVO.setCounty(county);
			storeVO.setDistrict(district);
			storeVO.setPostalCode(postalCode);
//			storeVO.setStarNum(starNum);
//			storeVO.setVisitorsNum(visitorsNum);
//			storeVO.setReviewed(reviewed);
			
			if(!errorMsg.isEmpty()) {
				req.setAttribute("storeVO", storeVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/store/updateStoreInput.jsp");
				failureView.forward(req, res);
				return;
			}
			
			StoreService storeSvc = new StoreService();
			storeVO = storeSvc.updateStore(storeId, name, managerName, email, password, phoneNum
					, guiNum, businessRegNum, opStat, opTime
					, pickTime, lastOrder, closeTime, address, county, district, postalCode);
			
			req.setAttribute("storeVO", storeVO);
			String url = "/store/listOneStore.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("insert".equals(action)) {

		    List<String> errorMsg = new LinkedList<>();
		    req.setAttribute("errorMsg", errorMsg);

		    // 1. 取得所有欄位
		    String name = req.getParameter("name");
		    if (name == null || name.trim().isEmpty()) {
		        errorMsg.add("請輸入店名");
		    }

		    String managerName = req.getParameter("managerName");
		    if (managerName == null || managerName.trim().isEmpty()) {
		        errorMsg.add("請輸入負責人姓名");
		    }

		    String email = req.getParameter("email");
		    String emailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		    if (email == null || email.trim().isEmpty()) {
		        errorMsg.add("請輸入電子信箱");
		    } else if (!email.matches(emailReg)) {
		        errorMsg.add("電子信箱格式錯誤");
		    }

		    String password = req.getParameter("password");
		    String passwordReg = "^(?=.*[A-Za-z])(?=.*\\d).{6,}$";
		    if (password == null || password.trim().isEmpty()) {
		        errorMsg.add("請輸入密碼");
		    } else if (!password.matches(passwordReg)) {
		        errorMsg.add("密碼至少包含英文與數字，且長度需大於6碼");
		    }

		    String phoneNum = req.getParameter("phoneNum");
		    String phoneReg = "^09\\d{8}$";
		    if (phoneNum == null || phoneNum.trim().isEmpty()) {
		        errorMsg.add("請輸入電話");
		    } else if (!phoneNum.matches(phoneReg)) {
		        errorMsg.add("電話格式錯誤");
		    }

		    String guiNum = req.getParameter("guiNum");
		    String guiReg = "^\\d{8}$";
		    if (guiNum == null || guiNum.trim().isEmpty()) {
		        errorMsg.add("請輸入統編");
		    } else if (!guiNum.matches(guiReg)) {
		        errorMsg.add("統一編號格式錯誤（需8碼）");
		    }

		    String businessRegNum = req.getParameter("businessRegNum");
		    if (businessRegNum == null || businessRegNum.trim().isEmpty()) {
		        errorMsg.add("請輸入營業登記編號");
		    } else if (!businessRegNum.matches(guiReg)) {
		        errorMsg.add("營業登記編號格式錯誤（需8碼）");
		    }

		    java.sql.Timestamp regTime = new java.sql.Timestamp(System.currentTimeMillis());

		    Integer reviewed = 0;
		    Integer points = 0;

		    Integer accStat = null;
		    try {
		        accStat = Integer.valueOf(req.getParameter("accStat").trim());
		        if (accStat < 0 || accStat > 1) errorMsg.add("帳號狀態請輸入 0 或 1");
		    } catch (Exception e) {
		        errorMsg.add("帳號狀態請填整數");
		    }

		    Integer opStat = null;
		    try {
		        opStat = Integer.valueOf(req.getParameter("opStat").trim());
		        if (opStat < 0 || opStat > 1) errorMsg.add("營運狀態請輸入 0 或 1");
		    } catch (Exception e) {
		        errorMsg.add("營運狀態請填整數");
		    }

		    String timeReg = "^([01]\\d|2[0-3]):[0-5]\\d$";

		    String opTime = req.getParameter("opTime");
		    if (opTime == null || !opTime.matches(timeReg)) errorMsg.add("請輸入正確的開店時間");

		    String pickTime = req.getParameter("pickTime");
		    if (pickTime == null || !pickTime.matches(timeReg)) errorMsg.add("請輸入正確的取餐時間");

		    String lastOrder = req.getParameter("lastOrder");
		    if (lastOrder == null || !lastOrder.matches(timeReg)) errorMsg.add("請輸入正確的最晚下單時間");

		    String closeTime = req.getParameter("closeTime");
		    if (closeTime == null || !closeTime.matches(timeReg)) errorMsg.add("請輸入正確的關店時間");

		    String address = req.getParameter("address");
		    String addressReg = "^[\\u4e00-\\u9fa5a-zA-Z0-9\\-()（）．.、\\s]{6,100}$";
		    if (address == null || address.trim().isEmpty()) {
		        errorMsg.add("請輸入地址");
		    } else if (!address.matches(addressReg)) {
		        errorMsg.add("地址格式錯誤");
		    }

		    String county = req.getParameter("county");
		    String countyReg = "^[\\u4e00-\\u9fa5]{2,4}[市縣]$";
		    if (county == null || !county.matches(countyReg)) errorMsg.add("請輸入正確的縣市");

		    String district = req.getParameter("district");
		    String districtReg = "^[\\u4e00-\\u9fa5]{2,5}[區鎮市鄉]$";
		    if (district == null || !district.matches(districtReg)) errorMsg.add("請輸入正確的鄉鎮市區");

		    Integer postalCode = null;
		    try {
		        postalCode = Integer.valueOf(req.getParameter("postalCode"));
		    } catch (Exception e) {
		        errorMsg.add("請輸入有效的郵遞區號");
		    }

		    Integer starNum = 0;
		    Integer visitorsNum = 0;

		    StoreVO storeVO = new StoreVO();
		    storeVO.setName(name);
		    storeVO.setManagerName(managerName);
		    storeVO.setEmail(email);
		    storeVO.setPassword(password);
		    storeVO.setPhoneNum(phoneNum);
		    storeVO.setGuiNum(guiNum);
		    storeVO.setBusinessRegNum(businessRegNum);
		    storeVO.setRegTime(regTime);
		    storeVO.setReviewed(reviewed);
		    storeVO.setPoints(points);
		    storeVO.setAccStat(accStat);
		    storeVO.setOpStat(opStat);
		    storeVO.setOpTime(opTime);
		    storeVO.setPickTime(pickTime);
		    storeVO.setLastOrder(lastOrder);
		    storeVO.setCloseTime(closeTime);
		    storeVO.setAddress(address);
		    storeVO.setCounty(county);
		    storeVO.setDistrict(district);
		    storeVO.setPostalCode(postalCode);
		    storeVO.setStarNum(starNum);
		    storeVO.setVisitorsNum(visitorsNum);

		    if (!errorMsg.isEmpty()) {
		        req.setAttribute("storeVO", storeVO);
		        req.getRequestDispatcher("/store/addStore.jsp").forward(req, res);
		        return;
		    }

		    StoreService storeSvc = new StoreService();
		    storeSvc.addStore(storeVO);

		    req.setAttribute("storeVO", storeVO);
		    req.getRequestDispatcher("/store/listOneStore.jsp").forward(req, res);
		}
		
		if ("delete".equals(action)) {
		    List<String> errorMsg = new LinkedList<>();
		    req.setAttribute("errorMsg", errorMsg);

		    Integer storeId = null;
		    try {
		        storeId = Integer.valueOf(req.getParameter("storeId"));
		    } catch (Exception e) {
		        errorMsg.add("無效的店家編號");
		    }

		    if (!errorMsg.isEmpty()) {
		        req.getRequestDispatcher("/store/listAllStore.jsp").forward(req, res);
		        return;
		    }

		    StoreService storeSvc = new StoreService();
		    storeSvc.deleteStore(storeId);

		    req.getRequestDispatcher("/store/listAllStore.jsp").forward(req, res);
		}

		if ("getOneForDisplay".equals(action)) {
		    List<String> errorMsg = new LinkedList<>();
		    req.setAttribute("errorMsg", errorMsg);

		    Integer storeId = null;
		    try {
		        storeId = Integer.valueOf(req.getParameter("storeId"));
		    } catch (Exception e) {
		        errorMsg.add("請輸入正確的店家編號");
		    }

		    if (!errorMsg.isEmpty()) {
		        req.getRequestDispatcher("/store/selectStore.jsp").forward(req, res);
		        return;
		    }

		    StoreService storeSvc = new StoreService();
		    StoreVO storeVO = storeSvc.getOneStore(storeId);
		    if (storeVO == null) {
		        errorMsg.add("查無資料");
		        req.getRequestDispatcher("/store/selectStore.jsp").forward(req, res);
		        return;
		    }

		    req.setAttribute("storeVO", storeVO);
		    req.getRequestDispatcher("/store/listOneStore.jsp").forward(req, res);
		}
		
		if ("getOneForUpdate".equals(action)) {
		    List<String> errorMsg = new LinkedList<>();
		    req.setAttribute("errorMsg", errorMsg);

		    Integer storeId = null;
		    try {
		        storeId = Integer.valueOf(req.getParameter("storeId"));
		    } catch (Exception e) {
		        errorMsg.add("請輸入正確的店家編號");
		    }

		    if (!errorMsg.isEmpty()) {
		        req.getRequestDispatcher("/store/listAllStore.jsp").forward(req, res);
		        return;
		    }

		    StoreService storeSvc = new StoreService();
		    StoreVO storeVO = storeSvc.getOneStore(storeId);
		    if (storeVO == null) {
		        errorMsg.add("查無資料");
		        req.getRequestDispatcher("/store/listAllStore.jsp").forward(req, res);
		        return;
		    }

		    req.setAttribute("storeVO", storeVO);
		    req.getRequestDispatcher("/store/updateStoreInput.jsp").forward(req, res);
		}

		
	}

}
