package org.shancm.hellocase.service;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Shancm
 * @date 2020/6/22
 */
@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {

    private int serverPort;

    public int getPort(){
        return serverPort;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        serverPort = webServerInitializedEvent.getWebServer().getPort();
    }
}
