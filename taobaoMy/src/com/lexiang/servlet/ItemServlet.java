package com.lexiang.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lexiang.vo.Item;
import com.lexiang.vo.Trade;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemGetRequest;
import com.taobao.api.response.ItemGetResponse;

public class ItemServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String sessionkey = (String) req.getSession()
				.getAttribute("sessionKey");
		long num_iid = Long.parseLong(req.getParameter("num_iid"));

		Item item = null;
		if (num_iid >= 0) {
			item = this.getItem(sessionkey, num_iid);
			req.getSession().setAttribute("item", item);
			req.getRequestDispatcher("/taobaoApi/itemShow.jsp").forward(req,
					resp);
		}
	}

	// 获取单个商品的详细信息
	public Item getItem(String sessionkey, long num_iid) {
		Item item = null;
		TaobaoClient client = new DefaultTaobaoClient(DemoConstant.GET__URL,
				DemoConstant.APP_KEY, DemoConstant.APP_SERCET);
		ItemGetRequest req = new ItemGetRequest();
		req.setFields("detail_url,num_iid,title,desc,pic_url,num,delist_time,approve_status");
		req.setNumIid(num_iid);
		try {
			ItemGetResponse response = client.execute(req, sessionkey);
			if (response.getBody().lastIndexOf("{") != -1) {
				JSONObject jsonObject = JSONObject.fromObject(response
						.getBody().substring(
								response.getBody().lastIndexOf("{"),
								response.getBody().indexOf("}") + 1));
				System.out.println(jsonObject.toString());
				item = (Item) JSONObject.toBean(jsonObject, Item.class);
			}
			System.out.println(response.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}
}
