package com.thoughtworks;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.thoughtworks.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

public class ModelParser {

    private ArrayList<Class> modelTypes = new ArrayList<Class>();
    private String packageName;
    private HashMap<String , Object> models = new HashMap<String, Object>();
    private Class clazz;

    public ModelParser(Class<?> clazz, String packageName) {

        this.packageName = packageName;
        setModelTypes(clazz);
        this.clazz = clazz;

    }

    public Object parse(Map<String, String[]> params) throws Exception {

        Set<String> keys = params.keySet();
        String modelName = "";

        String firstKey = Iterables.get(keys, 0);
        Iterable<String> names = Splitter.on('.').split(firstKey);
        modelName = Iterables.get(names, 0);
        models.put(modelName, clazz.newInstance());

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

        return models.get(modelName);
    }

    private String getClassName(String itemName) {
        return packageName + com.sun.xml.internal.ws.util.StringUtils.capitalize(itemName);
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

    private  HashMap<String, Object> itemsAppliedAttributes(Stack items, HashMap<Object, String> valueAndName) throws Exception {
        HashMap<String, Object> model = new HashMap<String, Object>();

        Object temp = items.pop();
        while(items.size() > 0) {
            Object item = items.pop();
            Class<?> itemClass = item.getClass();
            if(temp.getClass().equals(String.class)) {
                Class requiredClass = item.getClass().getDeclaredField(valueAndName.get(temp)).getType();
                temp = StringUtils.toPrimitive(requiredClass, (String) temp);
                itemClass.getMethod(getMethodName(valueAndName.get(temp.toString())),temp.getClass()).invoke(item,temp);
            } else {
                itemClass.getMethod(getMethodName(valueAndName.get(temp)),temp.getClass()).invoke(item,temp);
            }

            model.put(valueAndName.get(item), item);
            temp = item;
        }

        return model;
    }

    private  String getMethodName(String attrName) {
        return "set" + com.sun.xml.internal.ws.util.StringUtils.capitalize(attrName);
    }
}
