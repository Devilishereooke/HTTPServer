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

        String line;
        System.out.println("------- HTTP Request Start -------");

        while((line = clientInput.readLine()) != null){
            if(line.isEmpty()){
                break;                  // end of headers
            }

            System.out.println(line);
        }

        System.out.println("------- HTTP Request End -------");

        clientSocket.close();
    }
}
