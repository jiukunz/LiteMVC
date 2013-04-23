package com.thoughtworks;

import com.google.common.base.Splitter;
import com.sun.xml.internal.ws.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

public class ModelParser {

    private ArrayList<Class> modelTypes = new ArrayList<Class>();
    private String packageName;

    public ModelParser(Class<?> clazz, String packageName) {

       this.packageName = packageName;
       setModelTypes(clazz);

    }

    public  HashMap<String, Object> parse(HashMap<String, String[]> params) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        HashMap<String , Object> models = new HashMap<String, Object>();
        Set<String> keys = params.keySet();

        for(String key: keys) {
            Stack items = new Stack<Object>();
            HashMap<Object, String> attrValueAndName = new HashMap<Object, String>();

            for(String item : Splitter.on('.').split(key)) {
                String itemCapitalize = StringUtils.capitalize(item);
                String itemClassName = packageName + itemCapitalize;

                if (isInModelTypes(itemClassName)) {

                    Object itemInstance;
                    if (models.containsKey(item)) {
                        itemInstance = models.get(item);
                    } else {
                        itemInstance = Class.forName(itemClassName).newInstance();
                    }

                    attrValueAndName.put(itemInstance, item);
                    items.push(itemInstance);

                } else {

                    attrValueAndName.put(params.get(key)[0], item);
                    items.push(params.get(key)[0]);

                }

            }

            models.putAll(applyAttributes(items, attrValueAndName));

        }

        return models;
    }

    private void setModelTypes(Class clazz){

        if(!clazz.toString().contains(packageName)) {
            return;
        }

        modelTypes.add(clazz);
        Field[] fields =  clazz.getDeclaredFields();

        for(Field field : fields) {
            modelTypes.add(field.getType());
            setModelTypes(field.getType());
        }

    }

    private  boolean isInModelTypes(String itemClassName) {
        ArrayList<String> modelTypeString = new ArrayList<String>();

        for (Class clazz : modelTypes) {
            modelTypeString.add(clazz.toString().split(" ")[1]);
        }
        return modelTypeString.contains(itemClassName);
    }

    private  HashMap<String, Object> applyAttributes(Stack items, HashMap<Object, String> valueAndName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HashMap<String, Object> model = new HashMap<String, Object>();

        Object temp = items.pop();
        while(items.size() > 0) {
            Object item = items.pop();
            Class<?> itemClass = item.getClass();
            itemClass.getMethod(getMethodName(valueAndName.get(temp)),temp.getClass()).invoke(item,temp);

            model.put(valueAndName.get(item), item);
            temp = item;
        }

        return model;
    }

    private  String getMethodName(String attrName) {
        return "set" + StringUtils.capitalize(attrName);
    }
}
