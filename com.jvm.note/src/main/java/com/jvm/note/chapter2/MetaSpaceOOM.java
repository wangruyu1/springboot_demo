package com.jvm.note.chapter2;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Metaspace OOM,使用尽量多的代理类
 * 
 * @author 王如雨
 * @createtime 2018年12月21日 下午3:18:57
 */
public class MetaSpaceOOM {
	
	
	// vm args:-XX:MetaspaceSize=5m -XX:MaxMetaspaceSize=5m -XX:+PrintGCDetails
	public static void main(String[] args) {
		while (true) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObj.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {

				@Override
				public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
					return arg3.invokeSuper(arg0, arg2);
				}
			});
			enhancer.create();
		}
	}

	static class OOMObj {

	}
}
