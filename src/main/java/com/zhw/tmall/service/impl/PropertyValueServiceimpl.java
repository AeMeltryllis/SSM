package com.zhw.tmall.service.impl;

import com.zhw.tmall.mapper.PropertyValueMapper;
import com.zhw.tmall.pojo.Product;
import com.zhw.tmall.pojo.Property;
import com.zhw.tmall.pojo.PropertyValue;
import com.zhw.tmall.pojo.PropertyValueExample;
import com.zhw.tmall.service.PropertyService;
import com.zhw.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceimpl implements PropertyValueService {
    @Autowired(required = false)
    PropertyValueMapper propertyValueMapper;

    @Autowired
    PropertyService propertyService;

    /**
     * 因为采用了ajax的异步刷新，所以在进入编辑属性{值}页面时必须拥有属性{值}实体，但因为没提供也没必要提供
     * 添加方法，所以在编辑属性{值}页面获取属性分类时就初始化属性{值}并调用insert方法，其值【value】为null
     * @param  product
     */
    @Override
    public void init(Product product) {
/*通过产品获取cid，再通过cid获取属性分类*/
        List<Property> propertyList = propertyService.list(product.getCid());

        for (Property property: propertyList) {
            /*获取ptid和pid*/
            PropertyValue pv = get(property.getId(),product.getId());
            if(null==pv){
                pv = new PropertyValue();
                pv.setPid(product.getId());
                pv.setPtid(property.getId());
                pv.setValue("初始值");
                /*这样数据库里就有属性{值}的记录了*/
                propertyValueMapper.insert(pv);
            }
        }

    }

    @Override
    public void update(PropertyValue pv) {
        propertyValueMapper.updateByPrimaryKeySelective(pv);
    }

    @Override
    /**
     * 根据属性id和产品id获取PropertyValue对象
     * 属性id获取属性名，产品id设定属性值
     **/
    public PropertyValue get(int ptid, int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPtidEqualTo(ptid).andPidEqualTo(pid);
        List<PropertyValue> propertyValueList= propertyValueMapper.selectByExample(example);
        if (propertyValueList.isEmpty())
            return null;
        return propertyValueList.get(0);
    }

    @Override
    public List<PropertyValue> list(int pid) {

        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> result = propertyValueMapper.selectByExample(example);
        for (PropertyValue pv : result) {
            Property property = propertyService.get(pv.getPtid());
            pv.setProperty(property);
        }
        return result;
    }
}
