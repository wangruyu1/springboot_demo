package com.util.stream;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 输入流处理异常模板
 * 
 * @author 王如雨
 * @createtime 2018年12月18日 下午1:39:26
 */
public class InputStreamProcessingTemplate {
	/**
	 * 缓冲流处理文件,默认编码UTF-8
	 * 
	 * @param fileName
	 *            文件名
	 * @param processor
	 *            处理类
	 * @throws IOException
	 *             异常
	 */
	public static void process(String fileName, InputStreamProcessor processor) throws IOException {
		process(fileName, null, processor);
	}

	/**
	 * 缓冲流处理文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param fileEncoding
	 *            文件编码
	 * @param processor
	 *            处理类
	 * @throws IOException
	 *             异常
	 */
	public static void process(String fileName, String fileEncoding, InputStreamProcessor processor)
			throws IOException {
		IOException processException = null;
		BufferedReader input = null;
		try {
			Charset charset = Charset.forName("UTF-8");
			if (fileEncoding != null) {
				charset = Charset.forName(fileEncoding);
			}
			input = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), charset));
			processor.process(input);
		} catch (IOException e) {
			processException = e;
		} finally {
			if (input != null) {
				try {
					input.close();
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

	static interface InputStreamProcessor {
		default public void process(BufferedReader input) throws IOException {
		}
	}

	public static void main(String[] args) throws IOException {
		List<String> strs = new ArrayList<>();
		InputStreamProcessingTemplate.process("file", "GBK", new InputStreamProcessor() {
			@Override
			public void process(BufferedReader is) throws IOException {
				String line = null;
				while ((line = is.readLine()) != null) {
					strs.add(line);
				}
			}
		});
		System.out.println(strs);
	}
}