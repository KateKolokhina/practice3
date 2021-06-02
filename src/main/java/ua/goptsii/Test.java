package ua.goptsii;

import ua.goptsii.process.*;



public class Test {


    public static void main(String[] args) throws InterruptedException {
        ServerSimulator server = new ServerSimulator(150,4,200);
        server.simulate();
    }

}
