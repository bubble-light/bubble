package net.bubble.application.app.jobtask;

import java.io.File;

import net.bubble.application.app.constatns.PropertyValues;
import net.bubble.application.framework.exception.HDFSException;
import net.bubble.application.framework.service.HdaoopHDFSTemplate;
import net.bubble.common.utils.DateUtil;
import net.bubble.common.utils.FileUtil;
import net.bubble.common.utils.HttpClientUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 拉取nginx日志定时任务
 * 
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2016年1月19日
 */
@Component
public class NginxLogPullerTask extends AbstarctTask implements InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(NginxLogPullerTask.class);

	@Autowired
	private HdaoopHDFSTemplate hadoopHDFSTemplate;

	private static final String apiLogFolder = "apilogs", shareLogFolder = "sharelogs", portalLogFolder = "portallogs";

	/**
	 * 拉取API日志
	 */
	@Scheduled(cron = "00 10 00 * * ?")
	public void pullApiLogJob() {
		try {
			logger.info("pullApiLogJob() start running.");
			String fileName = DateUtil.getDate(-1) + "-access.log";// 日志文件名
			String apiLogDst = PropertyValues.LOCAL_DESTINATION_ROOT_PATH + File.separator + apiLogFolder;
			String sourceUrl = PropertyValues.API_SOURCE_LOG_PATH + "/" + fileName;// 远程日志文件
			HttpClientUtil.downLoadFile(sourceUrl, PropertyValues.SOURCE_LOG_USERNAME, PropertyValues.SOURCE_LOG_PASSWORD,
					apiLogDst);// 下载日志到本地
			String localFile = apiLogDst + File.separator + fileName;
			String hdfsPath = PropertyValues.HDFS_DESTINATION_ROOT_PATH + "/" + apiLogFolder + "/" + fileName;
			hadoopHDFSTemplate.copyFromLocal(false, true, localFile, hdfsPath);// 上传日志到HDFS
			FileUtil.renameFile(localFile, localFile + "_backup");// 备份本地日志
			// TODO 触发mapreduce任务
			logger.info("pullApiLogJob() running finished.");
		} catch (HDFSException e) {
			logger.error(e.getMessage(), e.getCause());
		}
	}

	@Scheduled(cron = "00 05 00 * * ?")
	public void pullShareLogJob() {
		try {
			logger.info("pullShareLogJob() start running.");
			String fileName = DateUtil.getDate(-1) + "-access.log";
			String shareLogDst = PropertyValues.LOCAL_DESTINATION_ROOT_PATH + File.separator + shareLogFolder;
			String sourceUrl = PropertyValues.SHARE_SOURCE_LOG_PATH + "/" + fileName;// 远程日志文件
			HttpClientUtil.downLoadFile(sourceUrl, PropertyValues.SOURCE_LOG_USERNAME, PropertyValues.SOURCE_LOG_PASSWORD,
					shareLogDst);
			String localFile = shareLogDst + File.separator + fileName;
			String hdfsPath = PropertyValues.HDFS_DESTINATION_ROOT_PATH + "/" + shareLogFolder + "/" + fileName;
			hadoopHDFSTemplate.copyFromLocal(false, true, localFile, hdfsPath);
			FileUtil.renameFile(localFile, localFile + "_backup");
			// TODO 触发mapreduce任务
			logger.info("pullShareLogJob() running finished.");
		} catch (HDFSException e) {
			logger.error(e.getMessage(), e.getCause());
		}
	}
	
	

	@Override
	public void afterPropertiesSet() throws Exception {
		initLocalDestination();
	}

	private void initLocalDestination() {
		File rootPath = new File(PropertyValues.LOCAL_DESTINATION_ROOT_PATH);
		if (!rootPath.exists())
			rootPath.mkdir();
	}
}
