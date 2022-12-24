package example.controllers;

import example.domain.Todo
import example.repositories.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam}
import org.springframework.web.servlet.ModelAndView

@Controller
class TodoController @Autowired()(todoRepository : TodoRepository) {
    @RequestMapping(path = Array("/index", "/"), method = Array(RequestMethod.GET))
    def index: ModelAndView = {
        val mav: ModelAndView = new ModelAndView("index")
        val dataList  = todoRepository.findAll()
        mav.addObject("todos", dataList)
        mav
    }

    @RequestMapping(value = Array("/add"), method = Array(RequestMethod.POST), consumes = Array(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    def add(@RequestParam("text") text: String): String = {
        val todo = new Todo(0L, text)
        todoRepository.save(todo)
        "redirect:/index"
    }

    @RequestMapping(value = Array("/delete"), method = Array(RequestMethod.POST), consumes = Array(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
    def delete(@RequestParam("id") id: Long): String = {
        todoRepository.deleteById(id)
        "redirect:/index"
    }
}
