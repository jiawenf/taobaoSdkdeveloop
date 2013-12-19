package com.lexiang.servlet;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lexiang.vo.Item;
import com.lexiang.vo.Shop;
import com.lexiang.vo.Trade;
import com.lexiang.vo.User;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemGetRequest;
import com.taobao.api.request.ShopGetRequest;
import com.taobao.api.request.ShopcatsListGetRequest;
import com.taobao.api.request.TopatsTradesSoldGetRequest;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.request.UserSellerGetRequest;
import com.taobao.api.response.ItemGetResponse;
import com.taobao.api.response.ShopGetResponse;
import com.taobao.api.response.ShopcatsListGetResponse;
import com.taobao.api.response.TopatsTradesSoldGetResponse;
import com.taobao.api.response.TradesSoldGetResponse;
import com.taobao.api.response.UserSellerGetResponse;

public class APITest {
	protected static String url = "http://gw.api.taobao.com/router/rest";// 沙箱环境调用地址
	// 正式环境需要设置为:http://gw.api.taobao.com/router/rest
	protected static String appkey = "21660292";
	protected static String appSecret = "61f550c6f8bdbd4f6cb1f1337a29173a";
	// protected static String sessionkey =
	// "103769b9b60285a9cc69295bba26161c096ao2K8AVDFyHRBlQGRnZwnmMXGASz0"; // 如
	protected static String sessionkey = "61018245c2d4ae1818798643a96e68561f3562ff3ec2c16195711034"; // 沙箱测试帐号sandbox_c_1授权后得到的sessionkey

	// 获取卖家信息
	public static void testUserGet() {
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret);// 实例化TopClient类
		UserSellerGetRequest req = new UserSellerGetRequest();// 实例化具体API对应的Request类
		req.setFields("nick,user_id,type");
		// req.setFields("user_id,nick,sex,seller_credit,type,has_more_pic,item_img_num,item_img_size,prop_img_num,prop_img_size,auto_repost,promoted_type,status,alipay_bind,consumer_protection,avatar,liangpin,sign_food_seller_promise,has_shop,is_lightning_consignment,has_sub_stock,is_golden_seller,vip_info,magazine_subscribe,vertical_market,online_gaming");
		// // req.setNick("sandbox_c_1");
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
				List<User> list = jsonUserArray.toList(jsonUserArray,
						User.class);
				for (User cast : list) {
					System.out.println(cast.getNick());
				}
			}

		} catch (ApiException e) { // deal error
		}

		/*
		 * TaobaoClient client = new DefaultTaobaoClient(url, appkey,
		 * appSecret);// 实例化TopClient类 UserBuyerGetRequest reqb = new
		 * UserBuyerGetRequest();// 实例化具体API对应的Request类
		 * reqb.setFields("nick,user_id,type"); // req.setNick("sandbox_c_1");
		 * UserBuyerGetResponse response; try { response = client.execute(reqb,
		 * sessionkey); // 执行API请求并打印结果 System.out.println("result:" +
		 * response.getBody()); } catch (ApiException e) { // deal error }
		 */

	}

	// 获取淘宝店铺信息
	public static void getShopInfo(String nick) {
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret);
		ShopGetRequest req = new ShopGetRequest();
		req.setFields("sid,cid,title,nick,desc,bulletin,pic_path,created,modified");
		req.setNick("xbhnjk");
		try {
			ShopGetResponse response = client.execute(req);
			System.out.println("shop Info:" + response.getBody());
			if (response.getBody().lastIndexOf("{") != -1) {
				JSONArray jsonUserArray = JSONArray.fromObject("["
						+ response.getBody().substring(
								response.getBody().lastIndexOf("{"),
								response.getBody().indexOf("}") + 1) + "]");
				System.out.println(jsonUserArray.toString());
				List<Shop> list = jsonUserArray.toList(jsonUserArray,
						Shop.class);
				for (Shop cast : list) {
					System.out.println(cast.getNick());
				}
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取top交易信息
	public static void getSold() {
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret);
		TopatsTradesSoldGetRequest req = new TopatsTradesSoldGetRequest();
		req.setFields("tid,seller_nick,buyer_nick,payment");
		req.setStartTime("20130901");
		req.setEndTime("20131029");
		req.setIsAcookie(false);
		try {
			TopatsTradesSoldGetResponse response = client.execute(req,
					sessionkey);
			System.out.println("time sold :" + response.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 搜索当前会话用户作为卖家已卖出的交易数据
	public static void get() {
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret);
		TradesSoldGetRequest req = new TradesSoldGetRequest();
		req.setFields("seller_nick,buyer_nick,title,type,created,sid,tid,seller_rate,buyer_rate,status,payment,discount_fee,adjust_fee,post_fee,total_fee,pay_time,end_time,modified,consign_time,buyer_obtain_point_fee,point_fee,real_point_fee,received_payment,commission_fee,pic_path,num_iid,num_iid,num,price,cod_fee,cod_status,shipping_type,receiver_name,receiver_state,receiver_city,receiver_district,receiver_address,receiver_zip,receiver_mobile,receiver_phone");
		try {
			TradesSoldGetResponse response = client.execute(req, sessionkey);
			System.out.println("all sold :" + response.getBody());
			if (response.getBody().indexOf("[") != -1) {
				JSONArray jsonUserArray = JSONArray.fromObject(response
						.getBody().substring(response.getBody().indexOf("["),
								response.getBody().lastIndexOf("]") + 1));
				List<Trade> list = jsonUserArray.toList(jsonUserArray,
						Trade.class);
				for (Trade cast : list) {
					System.out.println(cast.getCreated());
				}
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取单个商品的详细信息
	public static void getItem(long num_iid) {
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret);
		ItemGetRequest req = new ItemGetRequest();
		// req.setFields("detail_url,num_iid,title,nick,type,cid,seller_cids,props,input_pids,input_str,desc,pic_url,num,valid_thru,list_time,delist_time,stuff_status,location,price,post_fee,express_fee,ems_fee,has_discount,freight_payer,has_invoice,has_warranty,has_showcase,modified,increment,approve_status,postage_id,product_id,auction_point,property_alias,item_img,prop_img,sku,video,outer_id,is_virtual");
		req.setFields("detail_url,num_iid,title,desc,pic_url,num,delist_time,approve_status");
		req.setNumIid(35674139582L);
		try {
			ItemGetResponse response = client.execute(req, sessionkey);

			System.out.println(response.getBody());
			if (response.getBody().lastIndexOf("{") != -1) {
				JSONObject jsonObject = JSONObject.fromObject( response.getBody().substring(
								response.getBody().lastIndexOf("{"),
								response.getBody().indexOf("}")  +1));
				System.out.println(jsonObject.toString());
				Item item = (Item) JSONObject.toBean(jsonObject, Item.class);
			}
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取淘宝面向买家的浏览导航类目（跟后台卖家商品管理的类目有差异）
	public static void getShopCastList() {
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, appSecret);
		ShopcatsListGetRequest req = new ShopcatsListGetRequest();
		req.setFields("sid,cid,title,nick,desc,bulletin,pic_path,created,modified");
		try {
			ShopcatsListGetResponse response = client.execute(req);
			System.out.println(response.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// APITest.testUserGet();
		// APITest.GetShopCast();
		// APITest.get();
		// APITest.getSold();
		APITest.getItem(35674139582L);
		// APITest.getShopInfo("adf");
		// APITest.getShopCastList();
	}

}
