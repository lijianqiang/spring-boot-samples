package com.openplan.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openplan.server.model.PlaceItem;

@Controller
@RequestMapping("/show")
public class ShowController {

	private static final Logger LOG = LoggerFactory.getLogger(ShowController.class);


	@RequestMapping
	public String actionIndex(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");

		LOG.info("id:{}", id);

		model.addAttribute("greeting", "Hello!");

		PlaceItem item = buildItem(id);
		model.addAttribute("item", item);

		return "show/index";
	}

	private PlaceItem buildItem(String id) {
		PlaceItem item = new PlaceItem();
		item.setLocation("未知地点");
		item.setName("未知");
		item.setIntro("-");
		if (id == null || id.length() < 1) {
			return item;
		}

		if ("a1".equals(id)) {
			item.setName("融侨中心");
			item.setLocation("融侨中心位于福州市台江区，江滨大道北侧，江岸名都东侧");
			item.setIntro("融侨滨江广场将配套白金五星级酒店、甲A级别的写字楼，大型的百货、超市、影院及零售业店铺等顶级商业设施，引领福州城市综合体的新风向。");
			return item;
		}

		if ("b1".equals(id)) {
			item.setName("宝龙城市广场");
			item.setLocation("台江区的工业路与西二环路的交汇处");
			item.setIntro("福州宝龙广场占地107亩，总建筑面积达22万㎡，其中商业面积约16万㎡，是福州第一个与国际接轨的时尚消费体验中心项目。");
			return item;
		}

		if ("c1".equals(id)) {
			item.setName("仓山万达广场");
			item.setLocation("浦上大道以东侧靠近尤溪洲大桥");
			item.setIntro("集甲级5A写字楼、大型高档购物中心、娱乐中心、商务酒店、SOHO办公、时尚步行街、酒店式公寓七大顶级业态为一体的超大型城市综合体。");
			return item;
		}

		return item;
	}
}
