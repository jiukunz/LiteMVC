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
    private HashMap<String , Object> models = new HashMap<String, Object>();

    public ModelParser(Class<?> clazz, String packageName) {

       this.packageName = packageName;
       setModelTypes(clazz);

    }

    public  HashMap<String, Object> parse(HashMap<String, String[]> params) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Set<String> keys = params.keySet();

        for(String key: keys) {
            Stack items = new Stack<Object>();
            HashMap<Object, String> attrValueAndName = new HashMap<Object, String>();
            Iterable<String> objectNames = Splitter.on('.').split(key);

            for(String itemName : objectNames) {
                Object itemInstance = isInModelTypes(itemName) ? findOrCreateInstance(itemName) : params.get(key)[0];

                attrValueAndName.put(itemInstance, itemName);
                items.push(itemInstance);

            }

            models.putAll(itemsAppliedAttributes(items, attrValueAndName));

        }

        return models;
    }

    private String getClassName(String itemName) {
        return packageName + StringUtils.capitalize(itemName);
    }

    private Object findOrCreateInstance(String itemName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Object itemInstance;
        if (models.containsKey(itemName)) {
            itemInstance = models.get(itemName);
        } else {
            itemInstance = Class.forName(getClassName(itemName)).newInstance();
        }
        return itemInstance;
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

    private  boolean isInModelTypes(String itemName) {
        ArrayList<String> modelTypeString = new ArrayList<String>();

        for (Class clazz : modelTypes) {
            modelTypeString.add(clazz.toString().split(" ")[1]);
        }
        return modelTypeString.contains(getClassName(itemName));
    }

    private  HashMap<String, Object> itemsAppliedAttributes(Stack items, HashMap<Object, String> valueAndName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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
