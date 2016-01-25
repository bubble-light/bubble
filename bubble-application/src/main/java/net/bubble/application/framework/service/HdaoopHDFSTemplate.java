package net.bubble.application.framework.service;

import java.io.IOException;

import net.bubble.application.framework.exception.HDFSException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HdaoopHDFSTemplate {

	private static final Logger logger = LoggerFactory.getLogger(HdaoopHDFSTemplate.class);

	@Autowired
	Configuration configuration;

	private volatile FileSystem fs;

	public void mkdir(String dir) throws HDFSException {
		try {
			Path path = new Path(dir);
			fs = FileSystem.newInstance(configuration);
			fs.mkdirs(path);
		} catch (IOException e) {
			close();
			logger.error(e.getMessage(), e.getCause());
			throw new HDFSException("创建HDFS文件失败!", e);
		} finally {
			close();
		}
	}

	/**
	 * 删除HDFS文件
	 * 
	 * @param hdfsPath
	 *            hdfs路径
	 * @param recursive
	 *            递归删除
	 * @throws HDFSException
	 */
	public void rm(String hdfsPath, boolean recursive) throws HDFSException {
		try {
			Path path = new Path(hdfsPath);
			fs = FileSystem.newInstance(configuration);
			fs.delete(path, recursive);
		} catch (IOException e) {
			close();
			logger.error(e.getMessage(), e.getCause());
			throw new HDFSException("删除HDFS文件失败!", e);
		} finally {
			close();
		}
	}

	/**
	 * 上传文件到hdfs
	 * 
	 * @param delSrc
	 *            删除原文件
	 * @param overwrite
	 *            是否覆盖
	 * @param src
	 *            源
	 * @param dst
	 *            目的地
	 * @throws HDFSException
	 */
	public void copyFromLocal(boolean delSrc, boolean overwrite, String src, String dst) throws HDFSException {
		try {
			Path srcPath = new Path(src);
			Path dstPath = new Path(dst);
			fs = FileSystem.newInstance(configuration);
			fs.copyFromLocalFile(delSrc, overwrite, srcPath, dstPath);
		} catch (IOException e) {
			close();
			logger.error(e.getMessage(), e.getCause());
			throw new HDFSException("上传本地文件失败!", e);
		} finally {
			close();
		}
	}

	/**
	 * HDFS文件复制到本地
	 * 
	 * @param delSrc
	 *            是否删除源文件
	 * @param src
	 *            源
	 * @param dst
	 *            目的
	 * @throws HDFSException
	 */
	public void copyFromLocal(boolean delSrc, String src, String dst) throws HDFSException {
		try {
			Path srcPath = new Path(src);
			Path dstPath = new Path(dst);
			fs = FileSystem.newInstance(configuration);
			fs.copyToLocalFile(delSrc, srcPath, dstPath);
		} catch (IOException e) {
			close();
			logger.error(e.getMessage(), e.getCause());
			throw new HDFSException("上传本地文件失败!", e);
		} finally {
			close();
		}
	}

	private void close() {
		try {
			if (fs != null)
				fs.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e.getCause());
		}
	}

}
