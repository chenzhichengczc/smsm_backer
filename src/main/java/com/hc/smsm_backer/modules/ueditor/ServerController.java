package com.hc.smsm_backer.modules.ueditor;

import com.baidu.ueditor.ActionEnter;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ServerController {
    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath ="/static/lib/ueditor/1.4.3/jsp";
        String rootPath1 =request.getSession().getServletContext()
                .getRealPath("/");
        ApplicationHome h = new ApplicationHome(getClass());
        String dirPath = h.getSource().getPath() + "/static/lib/ueditor/1.4.3/jsp";
        System.out.println("dirPath = " + dirPath);
        System.out.println("rootPath = " + rootPath);
        System.out.println("rootPath1 = " + rootPath1);
        String readfile = readfile("/static/lib/ueditor/1.4.3/jsp/config.json");
        System.out.println("readfile = " + readfile);
        try {
            response.setCharacterEncoding("UTF-8");

            PrintWriter writer = response.getWriter();
            writer.write(new ActionEnter( request, rootPath ).exec());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //file 相对路径
    public  String readfile(String file){
        InputStream stream = this.getClass().getResourceAsStream(file);
        StringBuffer sb = new StringBuffer() ;
        BufferedReader br = null ;
        try {
            br = new BufferedReader(new InputStreamReader(stream,"UTF-8")) ;
            String s=null ;
            while((s=br.readLine()) !=null){
                sb.append(s) ;
            }
            br.close();
        } catch (FileNotFoundException e) {
            
        } catch (IOException e) {
           
        }finally {
            if(br !=null){
                try {
                    br.close();
                } catch (IOException e) {
                   
                }
            }
        }
        return sb.toString();
    }
}
