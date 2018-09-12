package info.xpanda.labs;

import java.io.*;

public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        return defineClass("info.xpanda.labs.Hello", b, 0, b.length);
    }

    //用于加载类文件
    private byte[] loadClassData(String name) {

        name = "E:\\work\\workspaces\\labs\\labs-main\\target\\classes\\info\\xpanda\\labs\\" + name + ".class";
        System.out.println("find class:" + name);
        //使用输入流读取类文件
        InputStream in = null;
        //使用byteArrayOutputStream保存类文件。然后转化为byte数组
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(new File(name));
            out = new ByteArrayOutputStream();
            int i = 0;
            while ( (i = in.read()) != -1){
                out.write(i);
            }

        }catch (Exception e){}
        finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return out.toByteArray();

    }
}
