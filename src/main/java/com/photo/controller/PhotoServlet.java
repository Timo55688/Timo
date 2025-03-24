package com.photo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.food.model.FoodService;
import com.food.model.FoodVO;
import com.photo.model.PhotoService;
import com.photo.model.PhotoVO;

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
@WebServlet("/photo/photo.do")
public class PhotoServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOneForDisplay".equals(action)) {
			
			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);
			
			String str = req.getParameter("photoId");
			if(str == null || str.trim().length() == 0) {
				errorMsg.add("plz input num");
			}
			if(!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/photo/selectPhoto.jsp");
				failureView.forward(req, res);
				return;
			}
			
			Integer photoId = null;
			try {
				photoId = Integer.valueOf(str);
			}catch(Exception e ) {
				errorMsg.add("wrong format");
			}
			
			if(!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/photo/selectPhoto.jsp");
				failureView.forward(req, res);
				return;
			}
			
			PhotoService photoSvc = new PhotoService();
			PhotoVO photoVO = photoSvc.getOnePhoto(photoId);
			if(photoVO == null) {
				errorMsg.add("查無資料");
			}
			
			if(!errorMsg.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/photo/selectPhoto.jsp");
				failureView.forward(req, res);
				return;
			}
			
			req.setAttribute("photoVO", photoVO);
			RequestDispatcher successView = req.getRequestDispatcher("/photo/listOnePhoto.jsp");
			successView.forward(req, res);
		}
		
		if("getOneForUpdate".equals(action)) {
			
			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);
			
			Integer photoId = Integer.valueOf(req.getParameter("photoId").trim());
			PhotoService photoSvc = new PhotoService();
			PhotoVO photoVO = photoSvc.getOnePhoto(photoId);
			
			req.setAttribute("photoVO", photoVO);
			RequestDispatcher successView = req.getRequestDispatcher("/photo/updatePhotoInput.jsp");
			successView.forward(req, res);
		}
		
		if ("update".equals(action)) {
			
			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);
			
			Integer photoId = Integer.valueOf(req.getParameter("photoId").trim());
			
			java.sql.Timestamp updateTime = null;
			try {
				if (updateTime == null) {
					PhotoService photoSvc = new PhotoService();
					PhotoVO existingTime = photoSvc.getOnePhoto(photoId); // 查詢資料庫
					if (existingTime != null) {
						updateTime = existingTime.getUpdateTime(); 
					}
				}
			} catch (IllegalArgumentException e) {
				updateTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsg.add("請輸入日期");
			}
			
			byte[] photoSrc = null;

			try {
			    Part filePart = req.getPart("photoSrc"); // name="photoSrc" 對應 JSP 的 <input type="file">

			    if (filePart != null && filePart.getSize() > 0) {
			        try (InputStream inputStream = filePart.getInputStream()) {
			            photoSrc = inputStream.readAllBytes();
			        }
			    } else {
			        // 若沒上傳新圖片，從資料庫撈舊圖保持不變（更新時用）
			        if (photoSrc == null && req.getParameter("photoId") != null) {
			            PhotoService photoSvc = new PhotoService();
			            PhotoVO existingPhoto = photoSvc.getOnePhoto(photoId);
			            if (existingPhoto != null) {
			                photoSrc = existingPhoto.getPhotoSrc(); // 要確保 VO 有 byte[] 欄位
			            }
			        }
			    }
			} catch (Exception e) {
			    errorMsg.add("圖片上傳失敗：" + e.getMessage());
			}

			Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());
			
			PhotoVO photoVO = new PhotoVO();
			photoVO.setPhotoId(photoId);
			photoVO.setStoreId(storeId);
			photoVO.setPhotoSrc(photoSrc);
			
			if(!errorMsg.isEmpty()) {
				req.setAttribute("photoVO", photoVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/photo/updatePhotoInput.jsp");
				failureView.forward(req, res);
				return;
			}
			
			PhotoService photoSvc = new PhotoService();
			photoVO = photoSvc.updatePhoto(photoId, storeId, photoSrc, updateTime);
			
			req.setAttribute("photoVO", photoVO);
			String url = "/photo/listOnePhoto.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);			
		}
		
		if ("insert".equals(action)) {
			
			List<String> errorMsg = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsg);
			
			byte[] photoSrc = null;

			try {
			    Part filePart = req.getPart("photoSrc");

			    if (filePart != null && filePart.getSize() > 0) {
			        try (InputStream inputStream = filePart.getInputStream()) {
			            photoSrc = inputStream.readAllBytes(); // 將圖片讀成 byte[]
			        }
			    } else {
			        errorMsg.add("請上傳圖片");
			    }

			} catch (Exception e) {
			    errorMsg.add("圖片上傳失敗：" + e.getMessage());
			}

			
			Integer storeId = Integer.valueOf(req.getParameter("storeId").trim());
			
			PhotoVO photoVO = new PhotoVO();
			photoVO.setStoreId(storeId);
			photoVO.setPhotoSrc(photoSrc);

			if(!errorMsg.isEmpty()) {
				req.setAttribute("photoVO", photoVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/photo/addPhoto.jsp");
				failureView.forward(req, res);
				return;
			}
			
			PhotoService photoSvc = new PhotoService();
			photoVO = photoSvc.addPhoto(storeId, photoSrc);
			
			req.setAttribute("photoVO", photoVO);
			String url = "/photo/listAllPhoto.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);			
		}
		
		if ("showPhoto".equals(action)) {
		    try {
		        Integer photoId = Integer.valueOf(req.getParameter("photoId"));
		        PhotoService photoSvc = new PhotoService();
		        PhotoVO photoVO = photoSvc.getOnePhoto(photoId);
		        byte[] photoSrc = photoVO.getPhotoSrc();

		        if (photoSrc != null && photoSrc.length > 0) {
		            res.setContentType("image/png"); // 或 "image/png" 根據你的圖片格式
		            res.setContentLength(photoSrc.length);
		            res.getOutputStream().write(photoSrc);
		        } else {
		            res.sendError(HttpServletResponse.SC_NOT_FOUND);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		    }
		    return; // 很重要！避免繼續執行下面邏輯
		}
	}
}
