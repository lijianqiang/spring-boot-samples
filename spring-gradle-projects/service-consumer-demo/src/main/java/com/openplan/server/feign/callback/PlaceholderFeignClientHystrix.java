package com.openplan.server.feign.callback;

import java.util.List;

import org.springframework.stereotype.Component;

import com.openplan.server.feign.PlaceholderFeignClient;
import com.openplan.server.protocol.result.JsonResponse;
import com.openplan.server.vo.PlaceholderVO;

@Component
public class PlaceholderFeignClientHystrix implements PlaceholderFeignClient {

	@Override
	public JsonResponse<List<PlaceholderVO>> listPlaceholder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonResponse<PlaceholderVO> getPlaceholder(int id) {
		JsonResponse<PlaceholderVO> jsonResponse = new JsonResponse<PlaceholderVO>();
		jsonResponse.setCode(-789);
		jsonResponse.setMessage("PlaceholderFeignClientHystrix:getPlaceholder");
		return jsonResponse;
	}

}
