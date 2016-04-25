package playplane.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
@EnableWebMvc 
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatMessageHandler(),"/websocket/chatMessageServer").addInterceptors(new HandshakeInterceptor());
        registry.addHandler(chatMessageHandler(), "/sockjs/chatMessageServer").addInterceptors(new HandshakeInterceptor()).withSockJS();
    }
 
    @Bean
    public TextWebSocketHandler chatMessageHandler(){
        return new WebSocketHandler();
    }

}
