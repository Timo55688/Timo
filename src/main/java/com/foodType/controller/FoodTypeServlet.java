package com.foodType.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.foodType.model.FoodTypeService;
import com.foodType.model.FoodTypeVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/foodType/foodType.do")
public class FoodTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOneForUpdate".equals(action)) {
			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);
			
			Integer foodTypeId = Integer.valueOf(req.getParameter("foodTypeId").trim());
			
			FoodTypeService foodTypeSvc = new FoodTypeService();
			FoodTypeVO foodTypeVO = foodTypeSvc.getOneFoodType(foodTypeId);
			
			req.setAttribute("foodTypeVO", foodTypeVO);
			RequestDispatcher successView = req.getRequestDispatcher("/foodType/updateFoodTypeInput.jsp");
			successView.forward(req, res);
		}
		
		if ("getOneForDisplay".equals(action)) {

			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);

			String str = req.getParameter("foodTypeId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsg.add("plz input id");
			}
			if (!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/foodType/selectFoodType.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer foodTypeId = null;
			try {
				foodTypeId = Integer.valueOf(str);
			} catch (NumberFormatException e) {
				errorMsg.add("num plz");
			}

			FoodTypeService foodTypeSvc = new FoodTypeService();
			FoodTypeVO foodTypeVO = foodTypeSvc.getOneFoodType(foodTypeId);
			if (foodTypeVO == null) {
				errorMsg.add("查無資料");
			}

			if (!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/foodType/selectFoodType.jsp");
				failureView.forward(req, res);
				return;
			}

			req.setAttribute("foodTypeVO", foodTypeVO);
			String url = "/foodType/listOneFoodType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {

			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);

			Integer foodTypeId = Integer.valueOf(req.getParameter("foodTypeId").trim());
			Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());


			String type = req.getParameter("type");
			String typeReg = "^(全素|純素|蛋素|奶素|蛋奶素|奶蛋素|五辛素|葷|素|葷食|素食)$";
			if (type == null || type.trim().length() == 0) {
				errorMsg.add("plz input");
			} else if (!type.trim().matches(typeReg)) {
				errorMsg.add("請輸入餐飲是以下哪種：全素|純素|蛋素|奶素|蛋奶素|奶蛋素|五辛素|葷|葷食");
			}

			FoodTypeVO foodTypeVO = new FoodTypeVO();
			foodTypeVO.setType(type);
			foodTypeVO.setFoodTypeId(foodTypeId); // ✅補這行
			foodTypeVO.setStoreId(storeId);       // ✅補這行

			if (!errorMsg.isEmpty()) {
				req.setAttribute("foodTypeVO", foodTypeVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/foodType/updateFoodTypeInput.jsp");
				failureView.forward(req, res);
				return;
			}

			FoodTypeService foodTypeSvc = new FoodTypeService();

			foodTypeVO = foodTypeSvc.updateFoodType(foodTypeId, type);

			req.setAttribute("foodTypeVO", foodTypeVO);
			String url = "/foodType/listOneFoodType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {

			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);

			String type = req.getParameter("type");
			String typeReg = "^(全素|純素|蛋素|奶素|蛋奶素|奶蛋素|五辛素|葷|素|葷食|素食)$";
			if (type == null || type.trim().length() == 0) {
				errorMsg.add("plz input");
			} else if (!type.trim().matches(typeReg)) {
				errorMsg.add("請輸入餐飲是以下哪種：全素|純素|蛋素|奶素|蛋奶素|奶蛋素|五辛素|葷|葷食");
			}

			Integer storeId = null;
			try {
				storeId = Integer.valueOf(req.getParameter("storeId").trim());
			} catch (NumberFormatException e) {
				errorMsg.add("請選擇點家");
			}

			FoodTypeVO foodTypeVO = new FoodTypeVO();
			foodTypeVO.setType(type);
			foodTypeVO.setStoreId(storeId);

			if (!errorMsg.isEmpty()) {
				req.setAttribute("foodTypeVO", foodTypeVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/foodType/addFoodType.jsp");
				failureView.forward(req, res);
				return;
			}

			FoodTypeService foodTypeSvc = new FoodTypeService();
			foodTypeVO = foodTypeSvc.addFoodType(storeId, type);

			RequestDispatcher successView = req.getRequestDispatcher("/foodType/listAllFoodType.jsp");
			successView.forward(req, res);
		}
	}
}
