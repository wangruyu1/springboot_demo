package com.jvm.note.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量池OOM,jdk8中是Metaspace
 * 
 * @author 王如雨
 * @createtime 2018年12月21日 下午3:02:59
 */
public class RuntimeConstantPoolOOM {

	// vm args:-XX:MetaspaceSize=2m -XX:MaxMetaspaceSize=2m -XX:+PrintGCDetails
	public static void main(String[] args) {
		List<String> strs = new ArrayList<>();
		for (int i = 0;; i++) {
			strs.add(String.valueOf(i).intern());
		}
	}
}
