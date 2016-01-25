package net.bubble.application.app.constatns;

import net.bubble.common.manager.properties.PropertiesManager;


/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2016年1月20日
 */
public class PropertyValues {
	
	/**
	 * API日志源路径(远程)
	 */
	public static final String API_SOURCE_LOG_PATH = (String) PropertiesManager.getContextProperties().get(PropertyKeys.KEY_SOURCE_API_LOGFILE_PATH);
	
	/**
	 * SHARE日志源路径(远程)
	 */
	public static final String SHARE_SOURCE_LOG_PATH = (String) PropertiesManager.getContextProperties().get(PropertyKeys.KEY_SOURCE_SHARE_LOGFILE_PATH);
	
	/**
	 * PORTAL日志源路径(远程)
	 */
	public static final String PORTAL_SOURCE_LOG_PATH = (String) PropertiesManager.getContextProperties().get(PropertyKeys.KEY_SOURCE_PORTAL_LOGFILE_PATH);
	
	/**
	 * 日志文件目的根路径
	 */
	public static final String LOCAL_DESTINATION_ROOT_PATH = (String) PropertiesManager.getContextProperties().get(PropertyKeys.KEY_LOCAL_LOGFILE_ROOT);
	
	/**
	 * HDFS文件根路径
	 */
	public static final String HDFS_DESTINATION_ROOT_PATH = (String) PropertiesManager.getContextProperties().get(PropertyKeys.KEY_HDFS_LOGFILE_ROOT);

	/**
	 * API日志远程用户
	 */
	public static final String SOURCE_LOG_USERNAME = (String) PropertiesManager.getContextProperties().get(PropertyKeys.KEY_SOURCE_API_LOGFILE_USERNAME);;

	/**
	 * API日志远程密码
	 */
	public static final String SOURCE_LOG_PASSWORD = (String) PropertiesManager.getContextProperties().get(PropertyKeys.KEY_SOURCE_API_LOGFILE_PASSWORD);;

}
