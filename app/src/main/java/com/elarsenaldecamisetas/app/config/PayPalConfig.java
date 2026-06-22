package com.elarsenaldecamisetas.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;


@Configuration
public class PayPalConfig {
    @Value("${paypal.client-id}")
    private String clientID;

    @Value("${paypal.client-secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    @Bean
    public PayPalHttpClient payPalHttpClient(){
        var environment = "sandbox".equalsIgnoreCase(mode)
            ? new PayPalEnvironment.Sandbox(clientID, clientSecret)
            : new PayPalEnvironment.Live(clientID, clientSecret);
        return new PayPalHttpClient(environment);
    }
}
