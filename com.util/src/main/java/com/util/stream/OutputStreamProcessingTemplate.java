package com.util.stream;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * 输出流处理异常模板
 * 
 * @author 王如雨
 * @createtime 2018年12月18日 下午1:39:26
 */
public class OutputStreamProcessingTemplate {
	/**
	 * 缓冲流写文件,默认编码utf-8
	 * 
	 * @param fileName
	 *            文件名
	 * @param append
	 *            true=追加,false=覆盖
	 * @param processor
	 *            处理类
	 * @throws IOException
	 *             异常
	 */
	public static void process(String fileName, boolean append, OutputStreamProcessor processor) throws IOException {
		process(fileName, null, append, processor);
	}

	/**
	 * 缓冲流写文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param fileEncoding
	 *            文件编码
	 * @param append
	 *            true=追加,false=覆盖
	 * @param processor
	 *            处理类
	 * @throws IOException
	 *             异常
	 */
	public static void process(String fileName, String fileEncoding, boolean append, OutputStreamProcessor processor)
			throws IOException {
		IOException processException = null;
		BufferedWriter writer = null;
		try {
			Charset charset = Charset.forName("UTF-8");
			if (fileEncoding != null) {
				charset = Charset.forName(fileEncoding);
			}
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, append), charset));
			processor.process(writer);
		} catch (IOException e) {
			processException = e;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					if (processException != null) {
						throw new IOException("process " + fileName + " occurs exception.", processException);
					} else {
						throw new IOException("close stream for " + fileName + " occurs exception.", e);
					}
				}
			}
			if (processException != null) {
				throw new IOException("process " + fileName + " occurs exception.", processException);
			}
		}

	}

	static interface OutputStreamProcessor {
		default public void process(BufferedWriter writer) throws IOException {
		}
	}

	public static void main(String[] args) throws IOException {
		OutputStreamProcessingTemplate.process("file", "UTF-8", true, new OutputStreamProcessor() {
			@Override
			public void process(BufferedWriter writer) throws IOException {
				writer.write("asdasd\n");
				writer.write("asdasd\n");
				writer.write("asdasd阿打算\n");
			}
		});
	}
}