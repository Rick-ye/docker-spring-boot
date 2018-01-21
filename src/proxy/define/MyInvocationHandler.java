package proxy.define;

import java.lang.reflect.Method;

/**
 * 自定义动态处理接口
 * @author Administrator
 *
 */
public interface MyInvocationHandler {
	public Object invoke(Object proxy, Method method, Object obj) throws Exception;
}
