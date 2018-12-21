package com.jvm.note.chapter3;

/**
 * finalize方法可以逃脱被gc的命运,但是finalize只能被仅仅执行一次
 * 创建一个FinalizeEscapeGc对象,不被引用,然后执行gc,在finalize中再次将该对象被gcroot引用.最后再次去掉该对象的引用,执行gc
 * 
 * @author 王如雨
 * @createtime 2018年12月21日 下午4:17:22
 */
public class FinalizeEscapeGc {

	private static FinalizeEscapeGc SAVE_HOOK = null;

	@Override
	protected void finalize() throws Throwable {
		System.out.println("carry out finalize method.");
		SAVE_HOOK = this;
	}

	public static boolean isAlive() {
		return SAVE_HOOK != null;
	}

	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new FinalizeEscapeGc();
		// 第一次gc
		executeGc();
		// 第二次gc
		executeGc();
	}

	private static void executeGc() throws InterruptedException {
		SAVE_HOOK = null;
		System.gc();
		// 让gc线程执行操作
		Thread.sleep(1000);
		if (isAlive()) {
			System.out.println("I'm alive.");
		} else {
			System.out.println("I'm dead.");
		}
	}

}
