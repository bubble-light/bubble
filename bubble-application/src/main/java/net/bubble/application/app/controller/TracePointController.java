package net.bubble.application.app.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 埋点数据
 * 
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2016年1月22日
 */
@RestController
@RequestMapping({ "/tracepoint" })
public class TracePointController {

	private static final Logger logger = LoggerFactory.getLogger(TracePointController.class);

	@RequestMapping(value = { "/traces" }, method = { RequestMethod.POST })
	public void accessTrace(HttpEntity<String> httpEntity) {
		try {
			String content = httpEntity.getBody();
			String decodeContent = URLDecoder.decode(content, "UTF-8");
			logger.info("Receive a message: [{}]", content);
			logger.info("decode: [{}]", decodeContent);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e.getCause());
		}
	}
}
