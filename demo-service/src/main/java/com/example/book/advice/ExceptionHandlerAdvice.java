//package com.example.book.advice;
//
//import org.springframework.ui.Model;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// * 控制器建言
// * zhangyd
// * 2020/4/2 14:45
// */
//@ControllerAdvice
//public class ExceptionHandlerAdvice {
//
//    /**
//     * 全局处理异常
//     * @param exception
//     * @param webRequest
//     * @return
//     */
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView exception(Exception exception, WebRequest webRequest){
//        ModelAndView modelAndView = new ModelAndView("error.jsp");
//        modelAndView.addObject("errorMessage",exception.getMessage());
//        return modelAndView;
//    }
//
//    /**
//     * 将键值对添加到全局
//     * 所有@requestMapping注解地方都可以获得到
//     * @param model
//     */
//    @ModelAttribute
//    public void addAttributes(Model model){
//        model.addAttribute("msg","额外信息");
//    }
//
//    /**
//     * 设置 webDataBinder
//     * @param webDataBinder 用来自动绑定前台请求参数到model中
//     */
//    @InitBinder
//    public void initBinder(WebDataBinder webDataBinder){
//        webDataBinder.setDisallowedFields("id");
//    }
//}
