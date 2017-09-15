package com.demo.protobuf.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.demo.protobuf.model.PersonProtobuf;
import com.demo.protobuf.model.PersonProtobuf.Person;
import com.demo.protobuf.model.PersonProtobufExt;
import com.google.gson.Gson;
import com.google.protobuf.ExtensionRegistry;

public class PersonProtoHelper {

    private static Gson gson = new Gson();

    /**
     * 生成要给原始对象
     * 
     * @return
     */
    public static Person toObject() {
        PersonProtobuf.Person.Builder personBuilder = PersonProtobuf.Person.newBuilder();
        personBuilder.setId(1);
        personBuilder.setName("hello");
        personBuilder.setEmail("aa@email.com");

        PersonProtobuf.Person person = personBuilder.build();

        return person;
    }

    /**
     * 生成要给扩展了字段的对象
     * 
     * @return
     */
    public static Person toObjectExt() {
        PersonProtobuf.Person.Builder personBuilder = PersonProtobuf.Person.newBuilder();
        personBuilder.setId(1);
        personBuilder.setName("hello");
        personBuilder.setEmail("aa@email.com");

        // 拓展的消息字段需要使用setExtension()方法，第一个参数就是proto文件中的新添加的字段（类名.字段），第二个是其value，value是新对象设置的数值
        // 将新消息生成的对象绑定到老消息中
        PersonProtobufExt.PersonExt.Builder personExtBuilder = PersonProtobufExt.PersonExt.newBuilder();
        personExtBuilder.setCity("fuzhou");
        personBuilder.setExtension(PersonProtobufExt.PersonExt.persionExt, personExtBuilder.build());

        PersonProtobuf.Person person = personBuilder.build();
        return person;
    }

    public static byte[] toByteArray(Person person) {
        // 将数据写到输出流，如网络输出流，这里就用ByteArrayOutputStream来代替
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            person.writeTo(output);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return output.toByteArray();
    }

    public static Person parse(byte[] byteArray) {
        // 接收到流并读取，如网络输入流，这里用ByteArrayInputStream来代替
        ByteArrayInputStream input = new ByteArrayInputStream(byteArray);
        // 反序列化
        try {
            Person personParse = Person.parseFrom(input);
            return personParse;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Person parseExt(byte[] byteArray) {
        // 解析及反序列化
        // 由于proto文件中有拓展属性，需要使用ExtensionRegistry实例registry，调用registerAllExtensions()方法使ExtendSimpleProtoBuf类中的拓展数据关联到registry
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        PersonProtobufExt.registerAllExtensions(registry);

        // 接收到流并读取，如网络输入流，这里用ByteArrayInputStream来代替
        ByteArrayInputStream input = new ByteArrayInputStream(byteArray);
        // 反序列化
        try {
            // parseFrom()从一个特定的字节数组解析成消息,解析消息时，除了input流中，拓展的数据位于registry对象中
            Person personParse = Person.parseFrom(input, registry);
            return personParse;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        //Person person = toObject();
        Person person = toObjectExt();

        System.out.println(gson.toJson(person));

        // 将数据写到输出流，如网络输出流，这里就用ByteArrayOutputStream来代替

        // -------------- 分割线：上面是发送方，将数据序列化后发送 ---------------

        byte[] byteArray = toByteArray(person);

        // -------------- 分割线：下面是接收方，将数据接收后反序列化 ---------------
        Person personParse = parse(byteArray);
        System.out.println(gson.toJson(personParse));
        System.out.println(personParse.getName());
        System.out.println(personParse.getEmail());
        
        
        Person personParse2 = parseExt(byteArray);
        System.out.println(gson.toJson(personParse2));
        System.out.println(personParse2.getName());
        System.out.println(personParse2.getEmail());
        //拓展消息中的内容需要通过getExtension()方法获取，参数就是proto文件中拓展字段（类名.字段）。  
        PersonProtobufExt.PersonExt personExt = personParse2.getExtension(PersonProtobufExt.PersonExt.persionExt);
        System.out.println("city:" + personExt.getCity());
    }

}
