package com.study.ajaxEx.Controller;

import com.study.ajaxEx.dto.AjaxDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AjaxController {
    @GetMapping("/ex01")
    public String ex01() {
        System.out.println("AjaxController.ex01");
        return "index"; //index.html 에 작성된 내용이 콘솔에 출력
        //return 값을 비워 놓으니 오류 발생 index 라고 주니 제대로 실행 됨
        //index 라고 적어 놓아도 spring 에서 뒤에 html 을 붙여 찾아감
        //하지만 index 페이지로 이동하지는 않음
        //ex01 페이지에 ajax 성공 부분에 변수를 추가하니 콘솔에 index 페이지 내용이 찍히는걸 볼 수 있음
    }

    @PostMapping("/ex02")
    public @ResponseBody String ex02() {
        System.out.println("AjaxController.ex02");
        return "안녕하세요"; // 리턴값이 그대로 출력됨 ResponseBody 가 리턴값을 바디에 실어서 보내주기 때문

        //get 과 다르게 ResponseBody 어노테이션이 붙음
    }

    @GetMapping("/ex03")
    public @ResponseBody String ex03(@RequestParam("param1") String param1,
                                     @RequestParam("param2") String param2) {
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
        return "ex03메서드 호출 완료";
    }

    @PostMapping("/ex04")
    public @ResponseBody String ex04(@RequestParam("param1") String param1,
                                     @RequestParam("param2") String param2) {
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
        return "ex04메서드 호출 완료";
    }

    @GetMapping("/ex05")
    public @ResponseBody AjaxDTO ex05(@ModelAttribute AjaxDTO ajaxDTO) {
        System.out.println("ajaxDTO = " + ajaxDTO);
        return ajaxDTO;

    }

    @PostMapping("/ex06")
    public @ResponseBody AjaxDTO ex06(@ModelAttribute AjaxDTO ajaxDTO) {
        System.out.println("ajaxDTO = " + ajaxDTO);
        return ajaxDTO;

    }

    @PostMapping("/ex07") //JSON 방식으로 왔다
    public @ResponseBody AjaxDTO ex07(@RequestBody AjaxDTO ajaxDTO) {
        System.out.println("ajaxDTO = " + ajaxDTO);
        return ajaxDTO;
    }

    private List<AjaxDTO> DTOList() {
        List<AjaxDTO> dtoList = new ArrayList<>();
        dtoList.add(new AjaxDTO("data1","data11"));
        dtoList.add(new AjaxDTO("data2","data22"));
        return dtoList;
    }

    @PostMapping("/ex08") //JSON 방식으로 왔다
    public @ResponseBody List<AjaxDTO> ex08(@RequestBody AjaxDTO ajaxDTO) {
        System.out.println("ajaxDTO = " + ajaxDTO);
        List<AjaxDTO> dtoList = DTOList();
        dtoList.add(ajaxDTO);
        return dtoList;
    }

    @PostMapping("/ex09") //JSON 방식으로 왔다
    public ResponseEntity ex09(@RequestBody AjaxDTO ajaxDTO) {
        //ResponseBody 를 사용할때는 데이터만 리턴을 했지만 ResponseEntity 를 써서 데이터랑 코드를 한번에 담을 수 있다.
        System.out.println("ajaxDTO = " + ajaxDTO);
        return new ResponseEntity<>(ajaxDTO, HttpStatus.OK);
        //HttpStatus 를 다양하게 활용 가능 ex) not found 로 주면 해당 정보가 넘어왔을때 낫 파운드가 콘솔에 찍힘
    }

    @PostMapping("/ex10") //JSON 방식으로 왔다
    public ResponseEntity ex10(@RequestBody AjaxDTO ajaxDTO) {
        System.out.println("ajaxDTO = " + ajaxDTO);
        List<AjaxDTO> dtoList = DTOList();
        dtoList.add(ajaxDTO);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}
