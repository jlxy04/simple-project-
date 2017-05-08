/**
 * 
 */
package com.xy.yiyao.controll;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xy.yiyao.api.model.DemoModel;
import com.xy.yiyao.api.service.DemoService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/user")
public class HelloController {
	
	@Resource
	private DemoService demoService;
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	@ResponseBody
    public String addDemoUser(DemoModel demoModel) {
		DemoModel resModel = demoService.addUser(demoModel);
        return "create success! you id is " + resModel.getId();
    }
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String deleteUser(@PathVariable("id")String id) {
		demoService.deleteById(id);
		return "delete id " + id + " success";
	}
	
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	@ResponseBody
	public List<DemoModel> findAll() {
		return demoService.findAll();
	}
}
