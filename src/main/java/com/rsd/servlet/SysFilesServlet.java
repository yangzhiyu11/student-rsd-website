package com.rsd.servlet;

import com.rsd.bean.SysFiles;
import com.rsd.service.impl.SysFileServiceImpl;
import com.rsd.service.ISysFilesService;
import com.rsd.util.UploadUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@MultipartConfig
@WebServlet(urlPatterns = {"/sysFiles/list", "/sysFiles/doAdd", "/sysFiles/toUpdatePage", "/sysFiles/doUpdate", "/sysFiles/doDelete","/sysFiles/downLoad"})
public class SysFilesServlet extends HttpServlet {

    private ISysFilesService sysFilesService = new SysFileServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals("/sysFiles/list")) {
            list(request, response);
        } else if (request.getRequestURI().equals("/sysFiles/doAdd")) {
            doAdd(request, response);
        } else if (request.getRequestURI().equals("/sysFiles/toUpdatePage")) {
            toUpdatePage(request, response);
        } else if (request.getRequestURI().equals("/sysFiles/doUpdate")) {
            doUpdate(request, response);
        } else if (request.getRequestURI().equals("/sysFiles/doDelete")) {
            doDel(request, response);
        } else if (request.getRequestURI().equals("/sysFiles/downLoad")) {
            downLoad(request, response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SysFiles> list = sysFilesService.queryAllList();
        request.setAttribute("list",list);
        request.getRequestDispatcher("/sysFiles/list.jsp").forward(request, response);

    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("myFile");

        String name = part.getSubmittedFileName();
        if (!name.equals("")) {
            String path = UploadUtil.baseDir + UploadUtil.getNewFileName(name);
            Long fileSize = part.getSize();

            part.write(this.getServletContext().getRealPath("/") + path);
//            UploadUtil.upload(part.getInputStream(), this.getServletContext().getRealPath("/") + path);

            SysFiles sysFiles = new SysFiles();
            sysFiles.setName(name);
            sysFiles.setPath(path);
            sysFiles.setFileSize(fileSize);
            sysFiles.setCreateTime(new Date());

            sysFilesService.insert(sysFiles);
            response.sendRedirect("/sysFiles/list");
        } else {
            response.sendRedirect("sysFiles/add.jsp");
        }
    }


    private void toUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        SysFiles sysFiles = sysFilesService.getById(Integer.valueOf(id));

        request.setAttribute("sysFiles",sysFiles);
        request.getRequestDispatcher("/sysFiles/update.jsp").forward(request,response);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Part part = request.getPart("myFile");

        String name = part.getSubmittedFileName();
        if (!name.equals("")) {
            SysFiles sysFilesOld = sysFilesService.getById(Integer.valueOf(id));
            String filePath = sysFilesOld.getPath();
            File file = new File(this.getServletContext().getRealPath("/")+filePath);
            if (file.exists()){
                file.delete();
            }

            String path = UploadUtil.baseDir + UploadUtil.getNewFileName(name);
            long fileSize = part.getSize();

            part.write(this.getServletContext().getRealPath("/")+path);
//            UploadUtil.upload(part.getInputStream(),this.getServletContext().getRealPath("/")+path);

            SysFiles sysFiles = new SysFiles();
            sysFiles.setId(Integer.valueOf(id));
            sysFiles.setName(name);
            sysFiles.setPath(path);
            sysFiles.setFileSize(fileSize);
            sysFiles.setCreateTime(new Date());


            sysFilesService.update(sysFiles);

            response.sendRedirect("/sysFiles/list");
        } else {
            response.sendRedirect("/sysFiles/toUpdatePage?id="+id);
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        SysFiles sysFiles = sysFilesService.getById(Integer.valueOf(id));

        String filePath = sysFiles.getPath();
        File file = new File(this.getServletContext().getRealPath("/")+filePath);
        if (file.exists()) {
            file.delete();
        }

        sysFilesService.delete(Integer.valueOf(id));

        response.sendRedirect("/sysFiles/list");
    }

    private void downLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        SysFiles sysFiles = sysFilesService.getById(Integer.valueOf(id));

        File file = new File(this.getServletContext().getRealPath("/")+sysFiles.getPath());
        if (file.exists()){
            String fileName = URLEncoder.encode(sysFiles.getName(),"UTF-8");
            response.reset();
            response.setContentType("application/x-msdownload");
            response.addHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");

            InputStream inStream = new FileInputStream(file);
            byte[] buf = new byte[4096];
            ServletOutputStream servletOS = response.getOutputStream();
            int readLength;
            while (((readLength = inStream.read(buf)) != -1)) {
                servletOS.write(buf,0,readLength);
            }
            inStream.close();
            servletOS.flush();
            servletOS.close();

            response.getWriter().print("下载成功！");
        }
    }

}
