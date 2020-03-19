package com.company;

import java.io.*;
import java.net.*;

public class Client
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(null);
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[4*1024];
        while (true) {
            String sentence = inFromUser.readLine();
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 7877);
            clientSocket.send(sendPacket);
            DatagramPacket hui = new DatagramPacket(receiveData,receiveData.length);
            clientSocket.receive(hui);
            System.out.println(new String(receiveData));






        }
    }
}