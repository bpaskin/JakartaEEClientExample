package com.ibm.example;

import javax.naming.NamingException;

/**
 * Main class for the HelloWorld EJB Client application
 * Demonstrates remote EJB invocation from a Liberty client
 */
public class HelloWorldClientMain {
    
    public static void main(String[] args) {
        System.out.println("Starting HelloWorld EJB Client...");
        
        HelloWorldClient client = new HelloWorldClient();
        
        try {
            // Initialize the EJB client
            System.out.println("Initializing EJB client...");
            client.initialize();
            
            // Test the simple sayHello() method
            System.out.println("\n=== Testing sayHello() ===");
            String result1 = client.callSayHello();
            System.out.println("Result: " + result1);
            
            // Test the personalized sayHello(String name) method
            System.out.println("\n=== Testing sayHello(String name) ===");
            String result2 = client.callSayHello("Liberty Client");
            System.out.println("Result: " + result2);
            
            // Test with empty name
            System.out.println("\n=== Testing sayHello with empty name ===");
            String result3 = client.callSayHello("");
            System.out.println("Result: " + result3);
            
            // Test with null name
            System.out.println("\n=== Testing sayHello with null name ===");
            String result4 = client.callSayHello(null);
            System.out.println("Result: " + result4);
            
            System.out.println("\n=== All tests completed successfully! ===");
            
        } catch (NamingException e) {
            System.err.println("Failed to initialize EJB client: " + e.getMessage());
            System.err.println("Make sure the HelloWorld server is running on localhost:2814");
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error during EJB invocation: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } finally {
            // Cleanup
            client.cleanup();
            System.out.println("EJB client cleanup completed.");
        }
    }
}