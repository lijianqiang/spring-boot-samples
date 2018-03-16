package com.openplan.server.api;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.openplan.server.protocol.result.JsonResponse;
import com.openplan.server.vo.PlaceholderVO;

public interface PlaceholderHttpApi {
	
	  @RequestMapping(value = "/placeholders", method = RequestMethod.GET)
	  JsonResponse<List<PlaceholderVO>> listPlaceholder();

	  @RequestMapping(value = "/placeholders/{id}", method = RequestMethod.GET)
	  JsonResponse<PlaceholderVO> getPlaceholder(@PathVariable("id") int id);

}
