package com.rsd.servlet;

import com.rsd.util.UploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@MultipartConfig
@WebServlet("/servlet/upload")
public class UploadFilesServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part headPic = request.getPart("headPic");
        InputStream in = headPic.getInputStream();

        String fileName = headPic.getSubmittedFileName();
        String newFileName = UploadUtil.getNewFileName(fileName);

        String uploadPath = this.getServletContext().getRealPath("/")+ UploadUtil.baseDir + newFileName;
        boolean b = UploadUtil.upload(in, uploadPath);

        response.getWriter().print(b ? "文件上传成功！":"文件上传失败!");
    }
}
