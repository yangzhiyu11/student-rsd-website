package com.rsd.servlet;

import com.rsd.bean.SysRole;
import com.rsd.bean.SysUser;
import com.rsd.service.SysRoleService;
import com.rsd.service.SysUserService;
import com.rsd.util.JDBCUtil;
import com.rsd.util.Md5Util;
import com.rsd.util.UploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@MultipartConfig
@WebServlet(urlPatterns = {"/sysUser/list", "/sysUser/toAddPage", "/sysUser/doAdd", "/sysUser/toUpdatePage", "/sysUser/doUpdate", "/sysUser/doDelete"})
public class SysUserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals("/sysUser/list")) {
            list(request, response);
        } else if (request.getRequestURI().equals("/sysUser/toAddPage")) {
            toAddPage(request, response);
        } else if (request.getRequestURI().equals("/sysUser/doAdd")) {
            doAdd(request, response);
        } else if (request.getRequestURI().equals("/sysUser/toUpdatePage")) {
            toUpdatePage(request, response);
        } else if (request.getRequestURI().equals("/sysUser/doUpdate")) {
            doUpdate(request, response);
        } else if (request.getRequestURI().equals("/sysUser/doDelete")) {
            doDel(request, response);
        }

    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchLoginName = request.getParameter("searchLoginName");
        String searchRealName = request.getParameter("searchRealName");
        String searchTel = request.getParameter("searchTel");
        String searchRoleId = request.getParameter("searchRoleId");
        String searchSex = request.getParameter("searchSex");
        String searchStartTime = request.getParameter("searchStartTime");
        String searchEndTime = request.getParameter("searchEndTime");

        SysUser searchSysUser = new SysUser();
        searchSysUser.setLoginName(searchLoginName);
        searchSysUser.setRealName(searchRealName);
        searchSysUser.setTel(searchTel);
        searchSysUser.setRoleId(searchRoleId != null && !searchRoleId.equals("")?Integer.valueOf(searchRoleId) : null);
        searchSysUser.setSex(searchSex);
        searchSysUser.setSearchStartTime(searchStartTime);
        searchSysUser.setSearchEndTime(searchEndTime);


        SysRoleService sysRoleService = new SysRoleService();
        List<SysRole> sysRoleList = sysRoleService.queryAllList();

        SysUserService sysUserService = new SysUserService();
        List<SysUser> sysUserList = sysUserService.queryListBySearch(searchSysUser);
//        if (searchRealName == null) {
//            sysUserList = sysUserService.queryAllList();
//        } else {
//            sysUserList = sysUserService.queryListBySearch(searchLoginName,searchRealName);
//        }
        request.setAttribute("sysUserList", sysUserList);
        request.setAttribute("sysRoleList",sysRoleList);
        request.setAttribute("searchSysUser",searchSysUser);
        request.getRequestDispatcher("/sysUser/list.jsp").forward(request, response);
    }

    private void toAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SysRoleService sysRoleService = new SysRoleService();
        List<SysRole> sysRoleList = sysRoleService.queryAllList();

        request.setAttribute("sysRoleList", sysRoleList);
        request.getRequestDispatcher("/sysUser/add.jsp").forward(request, response);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String loginName = request.getParameter("loginName");
        String realName = request.getParameter("realName");
        Part headPicPath = request.getPart("headPicPath");
        System.out.println("--------"+headPicPath);
        String roleId = request.getParameter("roleId");
        String sex = request.getParameter("sex");
        String tel = request.getParameter("tel");
        String password = "123456";
        String encrypt = Md5Util.encrypt(password);

        String basePath = null;
        String fileName = headPicPath.getSubmittedFileName();
        if (!fileName.equals("")) {
            String newFileName = UploadUtil.getNewFileName(fileName);//得到新名称
            basePath = UploadUtil.baseDir + newFileName;
            String newFilePath = this.getServletContext().getRealPath("/") +basePath;//得到新路径
            UploadUtil.upload(headPicPath.getInputStream(),newFilePath);//输入源，输出的位置
        }

        SysUser sysUser = new SysUser();
        sysUser.setLoginName(loginName);
        sysUser.setPassword(encrypt);
        sysUser.setRealName(realName);
        sysUser.setHeadPicPath(basePath);
        sysUser.setRoleId(Integer.valueOf(roleId));
        sysUser.setSex(sex);
        sysUser.setTel(tel);
        sysUser.setCreateTime(new Date());

        SysUserService sysUserService = new SysUserService();
        sysUserService.insert(sysUser);

        response.sendRedirect("/sysUser/list");
    }

    private void toUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        SysUserService sysUserService = new SysUserService();
        SysUser sysUser = sysUserService.getById(Integer.valueOf(id));

        request.setAttribute("sysUser", sysUser);

        SysRoleService sysRoleService = new SysRoleService();
        List<SysRole> sysRoleList = sysRoleService.queryAllList();

        request.setAttribute("sysRoleList", sysRoleList);

        request.getRequestDispatcher("/sysUser/update.jsp").forward(request, response);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        String realName = request.getParameter("realName");
        String roleId = request.getParameter("roleId");
        String sex = request.getParameter("sex");
        String tel = request.getParameter("tel");

        String basePath = null;
        Part headPicPath = request.getPart("headPicPath");
        String fileName = headPicPath.getSubmittedFileName();
        if (!fileName.equals("")) {
            String newFileName = UploadUtil.getNewFileName(fileName);//得到新名称
            basePath = UploadUtil.baseDir + newFileName;
            String newFilePath = this.getServletContext().getRealPath("/") +basePath;//得到新路径
            UploadUtil.upload(headPicPath.getInputStream(),newFilePath);//输入源，输出的位置
        }

        SysUser sysUser = new SysUser();
        sysUser.setId(Integer.valueOf(id));
        sysUser.setLoginName(loginName);
        sysUser.setPassword(password);
        sysUser.setRealName(realName);
        sysUser.setHeadPicPath(basePath);
        sysUser.setRoleId(Integer.valueOf(roleId));
        sysUser.setSex(sex);
        sysUser.setTel(tel);

        SysUserService sysUserService = new SysUserService();
        sysUserService.update(sysUser);
        response.sendRedirect("/sysUser/list");
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        SysUserService sysUserService = new SysUserService();
        sysUserService.delete(Integer.valueOf(id));

        response.sendRedirect("/sysUser/list");
    }
}
