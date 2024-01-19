package bitcamp.myapp.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.net.Socket;

public class DaoProxyGenerator {

  private String host;
  private int port;

  private Gson gson;

  public DaoProxyGenerator(String host, int port) {
    this.host = host;
    this.port = port;
    gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
  }

  public <T> T create(Class<T> clazz, String dataName) {
    return (T) Proxy.newProxyInstance(
      DaoProxyGenerator.class.getClassLoader(),
      new Class<?>[]{clazz},
      (proxy, method, args) -> {
        try (Socket socket = new Socket(host, port);
          DataInputStream in = new DataInputStream(socket.getInputStream());
          DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

          out.writeUTF(dataName);
          out.writeUTF(method.getName());
          if (args == null) {
            out.writeUTF("");
          } else {
            out.writeUTF(gson.toJson(args[0]));
          }

          String statusCode = in.readUTF();     // 서버에서 보낸 건 무조건 읽어야한다(응답해야한다). 규칙임.
          String entity = in.readUTF();

          if (!statusCode.equals("200")) {
            throw new Exception(entity);
          }

          Type returnType = method.getGenericReturnType();

          if (returnType == void.class) {
            return null;
          } else {
            return gson.fromJson(entity, returnType);
          }

////          Class<?> returnType = method.getReturnType();
////
////          if (returnType == List.class) {
////
////            System.out.println(returnType.getGenericSuperclass());
////
////            return new ArrayList();
//////            return (List<Assignment>) gson.fromJson(entity,
//////              TypeToken.getParameterized(
//////                ArrayList.class,
//////                ((ParameterizedType) returnType.getGenericSuperclass()).getActualTypeArguments()));
//
//          } else if (returnType == void.class) {
//            return null;
//
//          } else {
//            return gson.fromJson(entity, returnType);
//          }
////          Class<?>[] interfaces = returnType.getInterfaces();
////          for (Class<?> cls : interfaces) {
////            System.out.println(cls.getName());
////          }
//
////          Object result = gson.fromJson(entity, returnType);
////          System.out.println(result);
////          return result;

        } catch (Exception e) {
          e.printStackTrace();
          throw new DaoException(e);
        }
      }

    );
  }
}