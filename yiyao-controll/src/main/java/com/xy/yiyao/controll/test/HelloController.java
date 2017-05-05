/**
 * 
 */
package com.xy.yiyao.controll.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xy.yiyao.api.model.DemoModel;
import com.xy.yiyao.api.service.DemoService;

/**
 * @author Administrator
 *
 */
@Controller
public class HelloController {
	
	@Resource
	private DemoService demoService;
	
	@RequestMapping("/hello")
    public @ResponseBody String test() {
		DemoModel demoModel = new DemoModel();
		demoService.addUser(demoModel );
        return "hello, world! This com from spring!";
    }
}
