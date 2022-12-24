package example.controllers;

import example.repositories.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod}
import org.springframework.web.servlet.ModelAndView

@Controller
class TodoController @Autowired()(todoRepository : TodoRepository) {
    //@RequestMapping(value = "/index")
    @RequestMapping(path = Array("/index"), method = Array(RequestMethod.GET))
    def index: ModelAndView = {
        val mav: ModelAndView = new ModelAndView("index")
        //val dataList: List = todoRepository.findAll()

        //val dataList : List[Todo] = todoRepository.findAll()
        val dataList  = todoRepository.findAll()
        println(dataList)
        println("test")
        mav.addObject("todos", dataList)
        mav
    }
}
