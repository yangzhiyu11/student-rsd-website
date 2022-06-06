package com.rsd.servlet;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rsd.bean.NewsInfo;
import com.rsd.service.INewsInfoService;
import com.rsd.service.impl.NewsInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@MultipartConfig
@WebServlet(urlPatterns = {"/newsInfo/list","/newsInfo/doAdd","/newInfo/toUpdatePage","/newsInfo/doUpdate","/newsInfo/doDelete"})
public class NewsInfoServlet extends HttpServlet {

    private INewsInfoService newsInfoService = null;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equals("/newsInfo/list")) {
            list(request,response);
        }
        else if (request.getRequestURI().equals("/newsInfo/doAdd")) {
            doAdd(request,response);
        }
        else if (request.getRequestURI().equals("/newsInfo/toUpdatePage")) {
            toUpdatePage(request,response);
        }
        else if (request.getRequestURI().equals("/newsInfo/doUpdate")) {
            doUpdate(request,response);
        }
        else if (request.getRequestURI().equals("/newsInfo/doDelete")) {
            doDel(request,response);
        }

    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        newsInfoService = new NewsInfoServiceImpl();
        List<NewsInfo> list = newsInfoService.queryList();

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(list);
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String typeId = request.getParameter("typeId");
        String shortDesc = request.getParameter("shortDesc");
        String content = request.getParameter("content");
        String showTime = request.getParameter("showTime");
        String pubUserId = request.getParameter("pubUserId");

        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setTitle(title);
        newsInfo.setTypeId(Integer.valueOf(typeId));
        newsInfo.setShortDesc(shortDesc);
        newsInfo.setContent(content);
        newsInfo.setShowTime(showTime);
        newsInfo.setPubUserId(Integer.valueOf(pubUserId));
        newsInfo.setCreateTime(new Date());

        newsInfoService.insert(newsInfo);
        PrintWriter out = response.getWriter();
        out.print("true");

    }

    private void toUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        NewsInfo newsInfo = newsInfoService.getById(Integer.valueOf(id));

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(newsInfo);
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String typeId = request.getParameter("typeId");
        String shortDesc = request.getParameter("shortDesc");
        String content = request.getParameter("content");
        String showTime = request.getParameter("showTime");
        String pubUserId = request.getParameter("pubUserId");

        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setId(Integer.valueOf(id));
        newsInfo.setTitle(title);
        newsInfo.setTypeId(Integer.valueOf(typeId));
        newsInfo.setShortDesc(shortDesc);
        newsInfo.setContent(content);
        newsInfo.setShowTime(showTime);
        newsInfo.setPubUserId(Integer.valueOf(pubUserId));
        newsInfo.setUpdateTime(new Date());
        newsInfoService.update(newsInfo);

        PrintWriter out = response.getWriter();
        out.print("true");
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        newsInfoService.deleteById(Integer.valueOf(id));

        PrintWriter out = response.getWriter();
        out.print("true");
    }
}
