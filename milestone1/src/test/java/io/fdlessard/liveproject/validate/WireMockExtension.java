package io.fdlessard.liveproject.validate;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class WireMockExtension implements BeforeAllCallback, AfterAllCallback {

    public static WireMockServer wireMockServer = new WireMockServer(
            wireMockConfig()
            .bindAddress("127.0.0.1")
            .port(7070)
    );

    @Override
    public void beforeAll(ExtensionContext context) {
        log.info("WireMock server started {}", wireMockServer);
        wireMockServer.start();
        WireMock.configureFor("localhost", 7070);

    }

    @Override
    public void afterAll(ExtensionContext context) {
        log.info("WireMock server stopped");
        wireMockServer.stop();
    }

}
