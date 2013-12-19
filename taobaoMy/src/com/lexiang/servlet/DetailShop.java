package com.lexiang.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.lexiang.vo.Shop;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.SellercatsListGetRequest;
import com.taobao.api.request.ShopGetRequest;
import com.taobao.api.response.SellercatsListGetResponse;
import com.taobao.api.response.ShopGetResponse;

public class DetailShop extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String str = req.getParameter("nick");
		List<Shop> list = null;
		if (str != null && !str.equals("")) {
			list = this.GetShopInfoes(str);
		}
		req.getSession().setAttribute("shopList", list);
		req.getRequestDispatcher("/taobaoApi/shopShow.jsp").forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	// 获取具体卖家店铺分类信息
	public List<Shop> GetShopInfoes(String nick) {
		List<Shop> list = null;
		TaobaoClient client = new DefaultTaobaoClient(DemoConstant.GET__URL,
				DemoConstant.APP_KEY, DemoConstant.APP_SERCET);
		ShopGetRequest req = new ShopGetRequest();
		req.setFields("sid,cid,title,nick,desc,bulletin,pic_path,created,modified");
		req.setNick(nick);
		try {
			ShopGetResponse response = client.execute(req);
			System.out.println("shop Info:" + response.getBody());
			if (response.getBody().lastIndexOf("{") != -1) {
				JSONArray jsonUserArray = JSONArray.fromObject("["
						+ response.getBody().substring(
								response.getBody().lastIndexOf("{"),
								response.getBody().indexOf("}") + 1) + "]");
				System.out.println(jsonUserArray.toString());
				list = jsonUserArray.toList(jsonUserArray, Shop.class);
				for (Shop cast : list) {
					System.out.println(cast.getNick());
				}
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
