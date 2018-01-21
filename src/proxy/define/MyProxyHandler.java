package proxy.define;

import java.lang.reflect.Method;

public class MyProxyHandler implements MyInvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object obj) throws Exception {
		
		return null;
	}
	
	private void after() {
		System.out.println("自定义：吃完收拾碗筷");
	}

	private void before() {
		System.out.println("自定义：烧菜做饭");
	}
}
