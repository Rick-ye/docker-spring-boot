package proxy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

import sun.misc.ProxyGenerator;

public class TestProxy {

	public static void main(String[] args) {
		
		People people = (People)Proxy.newProxyInstance(People.class.getClassLoader(), new Class[]{People.class}, new ProxyHandler(new Rick()));
		try {
			people.eat();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		createProxyClassFile();
		System.out.println("动态代理名字："+ people.getClass().getName());
	}
	//将运行期的动态代理对象 提出来
	public static void createProxyClassFile() {
		byte[] data =ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{People.class});
		try {
			FileOutputStream out = new FileOutputStream("$Proxy0.class");
			out.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
