package com.dolnikova;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("name", "Alex");
        map.put("surName", "Smit");
        map.put("age", "25");
        map.put("group", "java");


        mapToClass(map, User.class);

    }

    private static void mapToClass(Map<String, String> map, Class<?> myClass) {

        if (myClass == User.class) {
            User user = new User();

            for (Map.Entry<String, String> entry : map.entrySet()) {
                String name = entry.getKey();

                StringBuilder nameMethod = new StringBuilder();
                nameMethod.append("set");

                char first = name.charAt(0);
                nameMethod.append(Character.toUpperCase(first));

                for (int j = 1; j < name.length(); j++) {
                    nameMethod.append(name.charAt(j));
                }

                try {
                    Method method = user.getClass().getDeclaredMethod(nameMethod.toString(), String.class);
                    method.setAccessible(true);
                    method.invoke(user, map.get(name));
                    method.setAccessible(false);
                } catch (Exception e) {
                    continue;
                }

            }

            System.out.println(user);

        }


    }


}
