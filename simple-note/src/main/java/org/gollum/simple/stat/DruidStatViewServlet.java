package org.gollum.simple.stat;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author wurenhai
 * @date 2018/1/25
 */
@WebServlet(urlPatterns = "/druid/*",
        initParams={
                @WebInitParam(name="allow",value=""),
                @WebInitParam(name="deny",value=""),
                @WebInitParam(name="loginUsername",value="admin"),
                @WebInitParam(name="loginPassword",value="123"),
                @WebInitParam(name="resetEnable",value="false")
        })
public class DruidStatViewServlet extends StatViewServlet {
    private static final long serialVersionUID = -8985256801035052767L;
}
