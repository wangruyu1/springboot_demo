package com.jvm.note.chapter2;

/**
 * 栈溢出
 * 
 * @author 王如雨
 * @createtime 2018年12月21日 下午2:45:46
 */
public class SOFDemo {

	//vm args:-Xss2k
	public static void main(String[] args) {
		SOFObj sObj = new SOFObj();
		try {
			sObj.inStack();
		} catch (Throwable e) {
			System.out.println("stack length:" + sObj.length);
			e.printStackTrace();
		}
	}

	static class SOFObj {
		int length = 0;

		public void inStack() {
			this.length += 1;
			inStack();
		}
	}
}
