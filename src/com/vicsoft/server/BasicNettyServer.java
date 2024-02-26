package com.vicsoft.server;

import com.vicsoft.server.server.BasicServer;

public class BasicNettyServer {

    public static void main(String[] args) {
        BasicServer.getInstance().openChannel();
    }

}
