package datastructure.src.reflaction;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestReflection {
    public static void main(String[] args) {
        TestD testD = new TestD("TESTD");
        TestC testC = new TestC("TESTC", Arrays.asList(testD));
        TestB testB = new TestB("TESTB", Arrays.asList(testC));
        TestA testA = new TestA("TESTA", testB);

        String fieldPath = "testB.testCList.testDList.testD";
        Object value = getValueFromPath(testA, fieldPath);

        System.out.println(value);
        System.out.println(isNestedCollection(value));
    }


    private static boolean isNestedCollection(Object object){
        if (object instanceof List<?>) {
            List<?> list = (List<?>) object;
            for (Object element : list) {
                if (element instanceof List<?>) {
                    return true;
                }
            }
        }
        return false;
    }
    static Object getValueFromPath(TestA testA, String fieldPath) {
        return getValueFromPath(testA, fieldPath.split("\\."), 0);
    }

    private static Object getValueFromPath(Object objectDetails, String[] fieldNames, int index) {
        System.out.println("fetching value for "+fieldNames[index]);
        if (objectDetails == null) {
            return null;
        }
        //0 == 1-1 -- 3 == 4-3

        if (objectDetails instanceof List) {
            List<Object> list = (List<Object>) objectDetails;
            List<Object> newList = new ArrayList<>();
            for (Object o : list) {
                Object object = getValueFromPath(o, fieldNames, index);
                if (object != null) {
                    newList.add(object);
                }
            }
            objectDetails = newList;
        } else if (index == fieldNames.length - 1) {
            return getDeclaredValueFromPath(objectDetails, fieldNames[index]);
        } else {
            return getValueFromPath(getDeclaredValueFromPath(objectDetails, fieldNames[index]), fieldNames, index + 1);
        }
        return objectDetails;
    }

    private static Object getDeclaredValueFromPath(Object objectDetails, String fieldName) {
        if (objectDetails == null || fieldName == null || fieldName.equals("")) {
            return null;
        }
        try {
            Field field = objectDetails.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(objectDetails);
        } catch (IllegalAccessException | NoSuchFieldException ex) {
            throw new RuntimeException(ex);
        }
    }
}
