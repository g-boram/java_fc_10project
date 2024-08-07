package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    // 1.가장 기본적인 GET방식
    @GetMapping(path = "/hello")  // http://localhost:9090/api/get/hello
    public String hello(){
        return "get Hello";
    }
    // 2.옛날 get 사용방식
    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public String hi(){
        return "hi";
    }
    // 3.입력값 받기
    @GetMapping("/path-variable/{name}") //http://localhost:9090/api/get/path-variable/{spring-boot}
    public String pathVariable(@PathVariable(name="name") String pathName){
        // 매개변수와 입력변수명을 다르게 사용해야 할때 @PathVariable의 속성으로 변수를 지정해줄수 있다.(name="name")

        System.out.println("PathVariable : " + pathName);
        return pathName;
    }
    // 4.쿼리값이 어떤게 들어올지 모를때 Map으로 받기
    // http://localhost:9090/api/get/query-param?user=steve&email=steve@gmail.com&age=30
    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {

        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+" = "+entry.getValue()+"\n");
        });
        return sb.toString();
    }
    // 5. 쿼리를 명확하게 명시했을 경우
    @GetMapping("query-param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ){
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name+" "+email+" "+age;
    }
    // 6. 객체를 만들어 바로 매핑 되게하는 방법 (가장 추천!)
    @GetMapping("query-param03")
    public String queryParam03(UserRequest userRequest){

        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }
}
