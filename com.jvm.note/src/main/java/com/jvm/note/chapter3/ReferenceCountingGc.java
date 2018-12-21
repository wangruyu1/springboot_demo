package com.jvm.note.chapter3;

/**
 * 引用计数gc测试,
 * 
 * @author 王如雨
 * @createtime 2018年12月21日 下午3:56:11
 */
public class ReferenceCountingGc {

	private static final int _1MB = 1024 * 1024;

	private byte[] bytes = new byte[_1MB];

	private ReferenceCountingGc instance;

	// vm args:-XX:+PrintGCDetails
	public static void main(String[] args) {
		ReferenceCountingGc A = new ReferenceCountingGc();
		ReferenceCountingGc B = new ReferenceCountingGc();
		A.instance = B;
		B.instance = A;
		// 显示调用回收A,B,由于使用的gcroot,会被回收,如果使用引用计数,不会回收
		A = null;
		B = null;
		System.gc();
	}
}
