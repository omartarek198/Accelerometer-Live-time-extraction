package com.example.accelerometerextractor;
import android.os.AsyncTask;

import java.net.*;
import java.io.*;
import java.io.DataOutputStream;
import java.sql.Struct;

public class client  {
    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;
    String address;
    int port;

    // constructor to put ip address and port
    public client(String address, int port) {
        // establish a connection

        // string to read message from input
        String line = "";
        this.address = address;
        this.port = port;
        // keep reading until "Over" is input
        try {
            socket = new Socket(address,port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // close the connection


    }

    public void Transmit(String data)
    {

        try {


            System.out.println("Connected");

            // takes input from terminal

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(data);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
