package com.food.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.food.model.FoodService;
import com.food.model.FoodVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50 // 50MB
)

@WebServlet("/food/food.do")
public class FoodServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOneForDisplay".equals(action)) {

			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);
			
			String str = req.getParameter("foodId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsg.add("請輸入餐點編號");
			}
			if (!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/food/selectPage.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer foodId = null;
			try {
				foodId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsg.add("餐點編號格式不正確");
			}

			if (!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/food/selectPage.jsp");
				failureView.forward(req, res);
				return;
			}

			FoodService foodSvc = new FoodService();
			FoodVO foodVO = foodSvc.getOneFood(foodId);
			if (foodVO == null) {
				errorMsg.add("查無資料");
			}

			if (!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/food/selectPage.jsp");
				failureView.forward(req, res);
				return;
			}

			req.setAttribute("foodVO", foodVO);
			String url = "/food/listOneFood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOneForUpdate".equals(action)) {

			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);

			Integer foodId = Integer.valueOf(req.getParameter("foodId").trim());
			FoodService foodSvc = new FoodService();
			FoodVO foodVO = foodSvc.getOneFood(foodId);

			req.setAttribute("foodVO", foodVO);
			String url = "/food/updateFoodInput.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {

			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);

			Integer foodId = Integer.valueOf(req.getParameter("foodId").trim());
			String name = req.getParameter("name");
			String nameReg = "^(?! )[ \u4e00-\u9fa5a-zA-Z0-9_]+(?<! )$";
			if (name == null || name.trim().length() == 0) {
				errorMsg.add("餐點名稱:請勿空白");
			} else if (!name.trim().matches(nameReg)) {
				errorMsg.add("餐點名稱:只能是中英文字母和數字");
			}

			java.sql.Timestamp createdTime = null;
			try {
				if (createdTime == null) {
				    FoodService foodSvc = new FoodService();
				    FoodVO existingTime = foodSvc.getOneFood(foodId); // 查詢資料庫
				    if (existingTime != null && existingTime.getPhoto() != null) {
				        createdTime = existingTime.getCreatedTime(); // 使用資料庫的圖片
				    }
				}
			} catch (IllegalArgumentException e) {
				createdTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsg.add("請輸入日期");
			}
			System.out.println(createdTime);

			Integer status = null;
			try {
				status = Integer.valueOf(req.getParameter("status").trim());
			} catch (NumberFormatException e) {
				status = 0;
				errorMsg.add("狀態請填數字");
			}

			Integer amount = null;
			try {
				amount = Integer.valueOf(req.getParameter("amount").trim());
			} catch (NumberFormatException e) {
				amount = 0;
				errorMsg.add("數量請填數字");
			}

			String photo = null;

			try {
				 
				Part filePart = req.getPart("photo"); // name="photo" 對應 JSP 的 <input type="file">
				String fileName = filePart.getSubmittedFileName(); 
				
				if (fileName != null && !fileName.trim().isEmpty()) {
					
					String uploadPath = getServletContext().getRealPath("/") + "images_uploaded/";
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists()) {
						uploadDir.mkdirs(); 
					}

					String newFilePath = uploadPath + fileName;
					filePart.write(newFilePath); 

					photo = "images_uploaded/" + fileName; 
				} else {
					if (photo == null) {
					    FoodService foodSvc = new FoodService();
					    FoodVO existingFood = foodSvc.getOneFood(foodId); 
					    if (existingFood != null && existingFood.getPhoto() != null) {
					        photo = existingFood.getPhoto();
					    }
					}
				}
			} catch (Exception e) {
				errorMsg.add("圖片上傳失敗：" + e.getMessage());
			}
			
			System.out.println(photo);

			Integer cost = null;
			try {
				cost = Integer.valueOf(req.getParameter("cost").trim());
			} catch (NumberFormatException e) {
				cost = 0;
				errorMsg.add("消耗點數請填數字");
			}

			Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());

			FoodVO foodVO = new FoodVO();
			foodVO.setFoodId(foodId);
			foodVO.setStoreId(storeId);
			foodVO.setName(name);
			foodVO.setCreatedTime(createdTime);
			foodVO.setStatus(status);
			foodVO.setAmount(amount);
			foodVO.setPhoto(photo);
			foodVO.setCost(cost);

			if (!errorMsg.isEmpty()) {
				req.setAttribute("foodVO", foodVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/food/updateFoodInput.jsp");
				failureView.forward(req, res);
				return;
			}

			FoodService foodSvc = new FoodService();
			foodVO = foodSvc.updateFood(foodId, storeId, name, createdTime, status, amount, photo, cost);

			req.setAttribute("foodVO", foodVO);
			String url = "/food/listOneFood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {

			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);

			String name = req.getParameter("name");
			String nameReg = "^(?! )[ \u4e00-\u9fa5a-zA-Z0-9_]+(?<! )$";
			if (name == null || name.trim().length() == 0) {
				errorMsg.add("餐點名稱:請勿空白");
			} else if (!name.trim().matches(nameReg)) {
				errorMsg.add("餐點名稱只能是中、英文字母、數字");
			}

			Integer status = null;
			try {
				status = Integer.valueOf(req.getParameter("status").trim());
			} catch (NumberFormatException e) {
				status = 0;
				errorMsg.add("狀態請填數字");
			}

			Integer amount = null;
			try {
				amount = Integer.valueOf(req.getParameter("amount").trim());
			} catch (NumberFormatException e) {
				amount = 0;
				errorMsg.add("數量請填數字");
			}

			
			String photo = null; 
			try {
				Part filePart = req.getPart("photo"); 
				String fileName = filePart.getSubmittedFileName(); 

				if (fileName != null && !fileName.trim().isEmpty()) {
					// 確保上傳目錄存在
					String uploadPath = getServletContext().getRealPath("/") + "images_uploaded/";
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists()) {
						uploadDir.mkdirs(); 
					}

					
					String newFilePath = uploadPath + fileName;
					filePart.write(newFilePath);

					
					photo = "images_uploaded/" + fileName;
					
				} else {
			        errorMsg.add("請上傳圖片");
			    }
			} catch (Exception e) {
				errorMsg.add("圖片上傳失敗：" + e.getMessage());
			}

			Integer cost = null;
			try {
				cost = Integer.valueOf(req.getParameter("cost").trim());
			} catch (NumberFormatException e) {
				cost = 0;
				errorMsg.add("消耗點數請填數字");
			}

			Integer storeId = 1;
			try {
				storeId = Integer.valueOf(req.getParameter("storeId").trim());
			} catch (NumberFormatException e) {
				errorMsg.add("請選擇店家");
			}

			FoodVO foodVO = new FoodVO();
			foodVO.setStoreId(storeId);
			foodVO.setName(name);
			foodVO.setStatus(status);
			foodVO.setAmount(amount);
			foodVO.setPhoto(photo);
			foodVO.setCost(cost);

			if (!errorMsg.isEmpty()) {
				req.setAttribute("foodVO", foodVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/food/addFood.jsp");
				failureView.forward(req, res);
				return;
			}

			FoodService foodSvc = new FoodService();
			foodVO = foodSvc.addFood(storeId, name, status, amount, photo, cost);

			String url = "/food/listAllFood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("delete".equals(action)) {
			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);
			Integer foodId = Integer.valueOf(req.getParameter("foodId"));

			FoodService foodSvc = new FoodService();
			foodSvc.deleteFood(foodId);

			String url = "/food/listAllFood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

}
