package com.lexiang.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.lexiang.vo.User;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemcatsGetRequest;
import com.taobao.api.request.UserSellerGetRequest;
import com.taobao.api.response.ItemcatsGetResponse;
import com.taobao.api.response.UserSellerGetResponse;

public class UserServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String sessionkey = (String) req.getSession()
				.getAttribute("sessionKey");
		List<User> list = this.getUser(sessionkey);

		req.getSession().setAttribute("userList", list);
		req.getRequestDispatcher("/taobaoApi/userShow.jsp").forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public List<User> getUser(String sessionkey) {
		List<User> list = null;
		TaobaoClient client = new DefaultTaobaoClient(DemoConstant.GET__URL,
				DemoConstant.APP_KEY, DemoConstant.APP_SERCET);// 实例化TopClient类
		UserSellerGetRequest req = new UserSellerGetRequest();// 实例化具体API对应的Request类
		req.setFields("nick,user_id,type");
		UserSellerGetResponse response;
		try {
			response = client.execute(req, sessionkey); // 执行API请求并打印结果
			System.out.println("use Info:" + response.getBody());
			if (response.getBody().lastIndexOf("{") != -1) {
				JSONArray jsonUserArray = JSONArray.fromObject("["
						+ response.getBody().substring(
								response.getBody().lastIndexOf("{"),
								response.getBody().indexOf("}") + 1) + "]");
				System.out.println(jsonUserArray.toString());
				list = jsonUserArray.toList(jsonUserArray, User.class);
				for (User cast : list) {
					System.out.println(cast.getNick());
				}
			}
		} catch (ApiException e) {
		}
		return list;
	}

}
