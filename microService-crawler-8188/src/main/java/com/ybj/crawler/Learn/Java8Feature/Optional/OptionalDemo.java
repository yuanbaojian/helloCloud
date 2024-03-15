package com.ybj.crawler.Learn.Java8Feature.Optional;

import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  为了避免nullPointException的错误
 *  可选对象
 *  使用时， 使用Optional的静态方法最好， 不用声明一个对象，没有必要
 * @Date $ $
 * @Param $
 * @return $
 **/
public class OptionalDemo {






    @Test
    public void testOf(){
        Optional<String> stringOptional=Optional.ofNullable("222");

        // 2.lamdba语法  能够实现自动对象转换？？？？
        stringOptional.ifPresent(item->System.out.println("lamdba表达式中的长度 = " + item.length()));
        System.out.println("stringOptional.isPresent() = " + stringOptional.isPresent());

        // 3.如果不存在， 用自定义值代替
        String value=stringOptional.orElse("eee");
        System.out.println("value = " + value);

        // 4.使用map
        stringOptional=stringOptional.map(s -> s+="hello");

        System.out.println("stringOptional = " + stringOptional.get());
    }

    @Test
    public void testNullPointException(){
        Person person=new Person();
        person.setName("张三");
        // person.setAddress(new Address("中国"));
        Address address = Optional.ofNullable(person.getAddress()).orElse(new Address("中国"));
        Optional.ofNullable(person.getAddress()).map(Address::getLocation).ifPresent(location -> System.out.println("location = " + location));
        System.out.println("address = " + address);
//        Address address=new Address();
//        address.setLocation("EARTH");
//        person.setAddress(address);
//         OptionalDemo.printPersonAddressV1(person);
    }

    @Test
    public void testMap(){
        String orderData = "{}";
        Map map = Optional.ofNullable(orderData)
                .map(e -> JSONUtil.toBean(e, Map.class))
                .map(e -> {
                    e.put("comfirm", "comfirm");
                    return e;
                }).get();
        System.out.println("map = " + map);

        Object o = Optional.ofNullable(orderData)
                .map(e -> JSONUtil.toBean(e, Map.class))
                .map(e -> e.put("comfirm", "comfirm")).get();

        System.out.println("o = " + o);
    }

    @Test
    public void testMapBetweenStream(){
        String orderData = "{}";
        Object o = Optional.ofNullable(orderData)
                .map(e -> JSONUtil.toBean(e, Map.class))
                .map(e -> {
                    e.put("comfirm", "comfirm");
                    return e;
                }).get();
        System.out.println("o = " + o);

        Object error1 = Optional.ofNullable(orderData)
                .map(e -> JSONUtil.toBean(e, Map.class))
                .map(e -> {
                    Object put = e.put("comfirm", "comfirm");
                    return put;
                }).get();

        // 省略的return，表示直接放回当前行的返回值
        Object error2 = Optional.ofNullable(orderData)
                .map(e -> JSONUtil.toBean(e, Map.class))
                .map(e -> e.put("comfirm", "comfirm"))
                .get();

    }


    // 1. 没有空指针处理
    public static void printPersonAddressV1(Person person){
        String location=person.getAddress().getLocation();
        System.out.println("地址： " + location.toLowerCase());
    }


    //2.  进行显式null判断
    public static void printPersonAddressV2(Person person){
        Address address=person.getAddress();
        if(address!=null){
            String name=address.getLocation();
            if(name!=null){
                System.out.println("name = " + name.toLowerCase());
            }
        }
    }

    //3.  使用  isPresent()  [隐式判断null]
    public static void printPersonAddressV3(Person person){
        Optional<Address> addressOptional= Optional.ofNullable(person.getAddress());
        if(addressOptional.isPresent()){
            Optional<String> nameOptional= Optional.ofNullable(addressOptional.get().getLocation());
            if(nameOptional.isPresent()){
                System.out.println("name = " + nameOptional.get().toLowerCase());
            }
        }
    }


    // 4.使用optional自带的静态方法
    public static void printPersonAddressV4(Person person){
        Optional<Address> addressOptional= Optional.ofNullable(person.getAddress());
        Optional<String> location=addressOptional.map(address -> address.getLocation());
        location.ifPresent(name -> System.out.println("name = " + name.toLowerCase()));
    }



}



@Getter
@Setter
class Person{
    String name;
    Address address;
}


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Address{
    String location;
}
