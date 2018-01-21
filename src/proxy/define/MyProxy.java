package proxy.define;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * 替换jdk给我们的代理对象
 * @author Administrator
 *
 */
public class MyProxy {
	
	protected MyInvocationHandler h;
	//定义一个回车键
	static String rt = "\r\t";
	private MyProxy() {
		
	}
	
	protected MyProxy(MyInvocationHandler h) {
		this.h = h;
	}
	
	//创建一个内存当中的$Proxy0实例
	public static Object createProxyInstance(
			ClassLoader loader, Class interf, MyInvocationHandler h) throws IOException{
		if(h != null){
			throw new NullPointerException();
		}
		//实际运行这个
		System.out.println("==========自定义：类构造器一个代理类的Java对象===========");
		Method[] methods = interf.getMethods();
		String proxyClassString = "package proxy.define;" + rt
				+"import java.lang.reflect.Method;" + rt
				+"public class $Proxy0 implements "+interf.getName()+"{" + rt
				+"MyInvocationHandler h;"+rt
				+"public $Proxy0(MyInvocationHandler h){"+rt
				+"this.h = h"+rt+"}"+getMethodString(methods, interf)+rt
				+"}";
		//将够造的自定义代理类转换成文件
		String fileName="E:/tts9/workspace/proxy/src/proxy/define/$Proxy.java";
		File file = new File(fileName);
		FileWriter fw = new FileWriter(file);
		fw.write(proxyClassString);
		fw.flush();
		fw.close();
		//编译这个文件
		JavaCompiler compile = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compile.getStandardFileManager(null, null, null);
		Iterable units = fileManager.getJavaFileObjects(file);
		//编译这个任务
		CompilationTask comTask = compile.getTask(null, fileManager, null, null, null, units);
		comTask.call();
		fileManager.close();
		//编译后就是class文件，接下来就把这个class文件到  内存
		//ClassLoader loader = ClassLoader("E:/tts9/workspace/proxy/src/proxy/define/$Proxy.java");
		return null;
		
	}

	private static String getMethodString(Method[] methods, Class interf) {
		String proxyMe = "";
		for(Method method : methods){
			proxyMe += "public void " + method.getName()+"() throws Throwable {"+rt
					+ "Method md="+interf.getName()+".class.getMethod(\""
					+method.getName()+"\",new Class[]{});"+rt
					+"this.h.invoke(this,md,null);"+rt+"}"+rt;
		}
		System.out.println("proxyMe:"+proxyMe);
		return proxyMe;
	}
	
}
