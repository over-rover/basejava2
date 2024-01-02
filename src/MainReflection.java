import webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("uuid_for_reflection");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "reflection_mofified_uuid");
        System.out.println(r);

        //System.out.println(Arrays.toString(r.getClass().getDeclaredMethods()));

        for (Method method : r.getClass().getDeclaredMethods()) {
            System.out.println(method.getName());
        }

        Method method = r.getClass().getDeclaredMethods()[1];
        System.out.println("\n" + method.invoke(r));

        method = r.getClass().getDeclaredMethod("toString");
        System.out.println("\n" + method.invoke(r));


    }
}
