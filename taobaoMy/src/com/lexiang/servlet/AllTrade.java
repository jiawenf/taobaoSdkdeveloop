package com.lexiang.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.lexiang.vo.Trade;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.response.TradesSoldGetResponse;

public class AllTrade extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String sessionkey = (String) req.getSession()
				.getAttribute("sessionKey");
		List<Trade> list = this.getAll(sessionkey);
		req.getSession().setAttribute("tradeList", list);
		req.getRequestDispatcher("/taobaoApi/tradeShow.jsp").forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public List<Trade> getAll(String sessionkey) {
		List<Trade> list = null;
		TaobaoClient client = new DefaultTaobaoClient(DemoConstant.GET__URL,
				DemoConstant.APP_KEY, DemoConstant.APP_SERCET);
		TradesSoldGetRequest req = new TradesSoldGetRequest();
		req.setFields("seller_nick,buyer_nick,title,type,created,sid,tid,seller_rate,buyer_rate,status,payment,discount_fee,adjust_fee,post_fee,total_fee,pay_time,end_time,modified,consign_time,buyer_obtain_point_fee,point_fee,real_point_fee,received_payment,commission_fee,pic_path,num_iid,num_iid,num,price,cod_fee,cod_status,shipping_type,receiver_name,receiver_state,receiver_city,receiver_district,receiver_address,receiver_zip,receiver_mobile,receiver_phone");
		try {
			TradesSoldGetResponse response = client.execute(req, sessionkey);
			System.out.println("all sold :" + response.getBody());
			if (response.getBody().indexOf("[") != -1) {
				JSONArray jsonUserArray = JSONArray.fromObject(response
						.getBody().substring(response.getBody().indexOf("["),
								response.getBody().lastIndexOf("]") + 1));
				list = jsonUserArray.toList(jsonUserArray, Trade.class);
				for (Trade cast : list) {
					System.out.println(cast.getBuyer_nick());
				}
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return list;
	}
}
