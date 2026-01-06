package com.ibm.example;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * EJB Client for HelloWorld remote EJB
 * Demonstrates how to lookup and invoke remote EJB methods
 */
public class HelloWorldClient {
    
    private HelloWorldRemote helloWorldRemote;
    
    /**
     * Initialize the EJB client and lookup the remote EJB
     * @throws NamingException if the EJB lookup fails
     */
    public void initialize() throws NamingException {
        // Set up JNDI properties for Liberty client
        Properties props = new Properties();
        
        // Create initial context
        InitialContext ctx = new InitialContext();
        
        // Lookup the remote EJB
        String jndiName = "java:global/HelloWorldServer/HelloWorldBean!com.ibm.example.HelloWorldRemote";

        try {
            helloWorldRemote = (HelloWorldRemote) ctx.lookup(jndiName);
            System.out.println("Successfully looked up HelloWorld EJB");
        } catch (NamingException e) {
            System.err.println("Failed to lookup HelloWorld EJB: " + e.getMessage());
            throw e;
        } finally {
            ctx.close();
        }
    }
    
    /**
     * Call the sayHello() method on the remote EJB
     * @return the greeting message from the EJB
     */
    public String callSayHello() {
        if (helloWorldRemote == null) {
            throw new IllegalStateException("EJB not initialized. Call initialize() first.");
        }
        
        try {
            return helloWorldRemote.sayHello();
        } catch (Exception e) {
            System.err.println("Error calling sayHello(): " + e.getMessage());
            throw new RuntimeException("Failed to call remote EJB method", e);
        }
    }
    
    /**
     * Call the sayHello(String name) method on the remote EJB
     * @param name the name to include in the greeting
     * @return the personalized greeting message from the EJB
     */
    public String callSayHello(String name) {
        if (helloWorldRemote == null) {
            throw new IllegalStateException("EJB not initialized. Call initialize() first.");
        }
        
        try {
            return helloWorldRemote.sayHello(name);
        } catch (Exception e) {
            System.err.println("Error calling sayHello(String): " + e.getMessage());
            throw new RuntimeException("Failed to call remote EJB method", e);
        }
    }
    
    /**
     * Cleanup resources
     */
    public void cleanup() {
        helloWorldRemote = null;
    }
}