package com.jvm.note.chapter2;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * 直接分配本机内存OOM,与JVM内存无关
 * 
 * @author 王如雨
 * @createtime 2018年12月21日 下午3:32:00
 */
public class DirectMemoryOOM {

	private static final long _1M = 1024 * 1024;

	// jvm args:-XX:MaxDirectMemorySize=10m -Xmx20m
	public static void main(String[] args) {
		try {
			Field unsafeField = sun.misc.Unsafe.class.getDeclaredFields()[0];
			unsafeField.setAccessible(true);
			sun.misc.Unsafe unsafe = (Unsafe) unsafeField.get(null);
			while(true){
				unsafe.allocateMemory(_1M);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
