package run.mone.mcp.moon.config;

import com.google.common.collect.Maps;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.DubboVersion;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description DubboConfiguration
 *
 * @author lizhao
 * @date 2021/6/2 11:59
 */
@Configuration
public class DubboConfiguration {
    
    @Value("${dubbo.protocol.port}")
    private int port;
    
    @Value("${server.port}")
    private String httpGateWayPort;
    
    @Value("${dubbo.registry.address}")
    private String regAddress;
    
    @Value("${app.name}")
    private String appName;
    
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(appName);
        applicationConfig.setParameters(Maps.newHashMap());
        applicationConfig.getParameters().put("http_gateway_port", httpGateWayPort);
        applicationConfig.getParameters().put("dubbo_version", new DubboVersion().toString());
        applicationConfig.setQosEnable(false);
        return applicationConfig;
    }
    
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(regAddress);
        return registryConfig;
    }
    
    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(port);
        protocolConfig.setTransporter("netty4");
        protocolConfig.setThreadpool("fixed");
        protocolConfig.setThreads(800);
        return protocolConfig;
    }
    
}
