/**
 * 
 */
package com.xy.yiyao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xy.yiyao.api.model.DemoModel;
import com.xy.yiyao.api.service.DemoService;
import com.xy.yiyao.dal.dao.DemoDao;
import com.xy.yiyao.dal.entity.DemoEntity;
import com.xy.yiyao.mapper.BeanUtil;
import com.xy.yiyao.mapper.DemoMapper;
import com.xy.yiyao.util.id.IDGenerator;

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
	
	@Override
	public String deleteById(String id) {
		demoDao.deleteById(id);
		return id;
	}
	
	@Override
	public List<DemoModel> findAll() {
		return BeanUtil.demoEntity2Model(demoDao.findAll());
	}
}
