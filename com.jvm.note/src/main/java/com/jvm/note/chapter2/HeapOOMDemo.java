package com.jvm.note.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存溢出
 * 
 * @author 王如雨
 * @createtime 2018年12月21日 下午2:45:37
 */
public class HeapOOMDemo {
	public static void main(String[] args) {
		// vm args:
		// -Xms10m -Xmx10m -verbose:gc -XX:+PrintGCDetails
		// -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=
		List<OOMObj> strs = new ArrayList<>();
		while (true) {
			strs.add(new OOMObj());
		}
	}

	static class OOMObj {

	}
}
