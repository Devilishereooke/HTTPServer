package com.devi.httpserver;

import java.io.*;
import java.net.*;

public class HttpServer {
    private int port;

    public HttpServer(int port){
        this.port = port;
    }

    public void start(){
        System.out.println("Starting a socket on port: " + port);

        // 1. Start the socket on port
        try(ServerSocket serverSocket = new ServerSocket(port)){
            while(true){
                // 2. Listen for the client request
                Socket clientSocket = serverSocket.accept();

                System.out.println("Client connected!!" + clientSocket);

                // 3. Handle HTTP request
                handleClient(clientSocket);

                clientSocket.close();
            }
        }
        catch(IOException e){   
            System.out.println(e);
        }
    }

    private void handleClient(Socket clientSocket) throws IOException{
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        OutputStream outputStream = clientSocket.getOutputStream();

        // 1. Read and print the client request
        String line;
        System.out.println("------- HTTP Request Start -------");

        while((line = clientInput.readLine()) != null){
            if(line.isEmpty()){
                break;                  // end of headers
            }

            System.out.println(line);
        }

        System.out.println("------- HTTP Request End -------");


        // 2. Build response body
        String responseBody = "Hello from Java HTTP server...";

        // 3. Build HTTP response
        String response = 
                    "HTTP/1.1 200 OK\n" + 
                    "Content-Type: text/plain\n" +
                    "Content-Length: " + responseBody.length() + "\n" +
                    "\n" + 
                    responseBody;

        // 4. Send response
        outputStream.write(response.getBytes());
        outputStream.flush();  // cleaning the outputstream

        clientSocket.close();  // closing the socket
    }
}
