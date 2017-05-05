/**
 * 
 */
package com.xy.yiyao.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.xy.yiyao.api.model.DemoModel;
import com.xy.yiyao.dal.entity.DemoEntity;

/**
 * @author Administrator
 *
 */
public class BeanUtil {
	
	public static DemoModel demoEntity2Model(DemoEntity demoEntity) {
		return DemoMapper.INSTANCE.demoEntity2Model(demoEntity);
	}
	
	public static DemoEntity demoModel2Entity(DemoModel demoModel) {
		return DemoMapper.INSTANCE.demoModel2Entity(demoModel);
	}
	
	public static List<DemoModel> demoEntity2Model(List<DemoEntity> demoEntities) {
		List<DemoModel> demoModels = null;
		if(CollectionUtils.isNotEmpty(demoEntities)) {
			demoModels = new ArrayList<DemoModel>();
			for (DemoEntity demoEntity : demoEntities) {
				demoModels.add(demoEntity2Model(demoEntity));
			}
		}
		return demoModels;
	}
	
	public static List<DemoEntity> demoModel2Entity(List<DemoModel> demoModels) {
		List<DemoEntity> demoEntities = null;
		if(CollectionUtils.isNotEmpty(demoModels)) {
			demoEntities = new ArrayList<DemoEntity>();
			for (DemoModel demoModel : demoModels) {
				demoEntities.add(demoModel2Entity(demoModel));
			}
		}
		return demoEntities;
	}
	
}
