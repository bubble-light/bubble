package net.bubble.application.app.jobtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 拉取nginx日志定时任务
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2016年1月19日
 */
@Component
public class NginxLogPullerTask extends AbstarctTask{
	
	private static final Logger logger = LoggerFactory.getLogger(NginxLogPullerTask.class);
	
	/**
	 * 拉取API日志
	 */
	@Scheduled(cron="0/1 * * * *")//TODO crontab
	public void pullApiLogJob(){
		logger.info("{}","pullApiLogJob()");
	}
}
