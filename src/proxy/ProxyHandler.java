package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {

	People people = null;
	
	public ProxyHandler(People people) {
		this.people = people;
	}
	
	/**
	 * 看的到的具体方法
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		method.invoke(people, null);
		after();
		return null;
	}

	private void after() {
		System.out.println("吃完收拾碗筷");
	}

	private void before() {
		System.out.println("烧菜做饭");
	}

}
