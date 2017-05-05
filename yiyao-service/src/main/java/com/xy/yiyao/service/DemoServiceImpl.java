/**
 * 
 */
package com.xy.yiyao.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xy.yiyao.api.model.DemoModel;
import com.xy.yiyao.api.service.DemoService;
import com.xy.yiyao.dal.dao.DemoDao;
import com.xy.yiyao.dal.entity.DemoEntity;
import com.xy.yiyao.mapper.DemoMapper;
import com.xy.yiyao.util.IDGenerator;

/**
 * @author Administrator
 *
 */
@Component("yiyao.demoService")
public class DemoServiceImpl implements DemoService{

	@Resource
	private DemoDao demoDao;
	
	@Override
	public DemoModel addUser(DemoModel demoModel) {
		if(demoModel != null) {
			demoModel.setId(IDGenerator.generatorID());
			DemoEntity demoEntity = DemoMapper.INSTANCE.demoModel2Entity(demoModel);
			demoDao.addDemo(demoEntity);
		}
		return demoModel;
	}
}
