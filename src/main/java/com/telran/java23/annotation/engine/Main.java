package com.telran.java23.annotation.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.java23.annotation.engine.annotation.ConsoleInput;
import com.telran.java23.annotation.engine.annotation.RequestBody;
import com.telran.java23.annotation.engine.annotation.Service;
import com.telran.java23.annotation.engine.annotation.StartMethod;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Scanner;
import java.util.Set;

public class Main {

    //class
    //interface
    //enum
    //annotation (@interface)

    //@Retention | RetentionPolicy:
    // - CLASS ()
    // - SOURCE (lombok annotations, @Override)
    // - RUNTIME (spring @Service, @Controller; Jpa @Entity, @Table)

    //@Target
    @SneakyThrows
    public static void main(String[] args) {
        //ComponentScan("com.telran")
        Reflections reflections = new Reflections("com.telran");
        Set<Class<?>> services = reflections.getTypesAnnotatedWith(Service.class);

        for (Class<?> clazz : services) {

            Object serviceInstance = clazz.newInstance();
            //((AdminService) serviceInstance).run();

//            AdminService adminService = new AdminService();


            Method[] methods = clazz.getDeclaredMethods();

            //String string = (String) (Object) new Integer(15);


            for (Method method : methods) {
                if (!method.isAnnotationPresent(StartMethod.class)) {
                    continue;
                }

                Parameter [] parameters = method.getParameters();


                String input = null;
                Object requestBodyInput = null;
                for (Parameter parameter : parameters) {
                    if (parameter.isAnnotationPresent(ConsoleInput.class) && parameter.getType().equals(String.class)) {

                        ConsoleInput consoleInput = parameter.getAnnotation(ConsoleInput.class);
                        System.out.println(consoleInput.displayMessage());
                        Scanner scanner = new Scanner(System.in);
                        input = scanner.nextLine();
                    }

                    if (parameter.isAnnotationPresent(RequestBody.class)) {

                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Enter JSON:");
                        String jsonInput = scanner.nextLine();
                        ObjectMapper om = new ObjectMapper();
                        requestBodyInput = om.readValue(jsonInput, parameter.getType());

                    }
                }
                if (input == null && requestBodyInput == null) {
                    Object [] params = new Object[parameters.length];
                    method.invoke(serviceInstance, params);
                } else if (input != null){
                    method.invoke(serviceInstance, input);
                } else if (requestBodyInput != null) {
                    method.invoke(serviceInstance, requestBodyInput);
                }

                //Spring beans
                // - where all magic occurs (@Value, @RequestBody ..)
                // - spring does multithreading by its own for YOUR bean
                // - spring beans have scope: singleton, prototype
                // - spring uses its OWN beans (@Configuration) for configuring:
                //          - other beans
                //          - other resources, like database connection object (take 30 secs to create)

            }

        }
    }
}