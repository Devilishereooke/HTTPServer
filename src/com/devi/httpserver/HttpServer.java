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

        try(ServerSocket serverSocket = new ServerSocket(port)){
            while(true){
                Socket clientSocket = serverSocket.accept();

                System.out.println("Client connected!!" + clientSocket);

                clientSocket.close();
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
