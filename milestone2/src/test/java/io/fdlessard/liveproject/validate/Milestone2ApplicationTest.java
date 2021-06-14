package io.fdlessard.liveproject.validate;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.fdlessard.liveproject.validate.enums.HealthMetricType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;

@SpringBootTest
@AutoConfigureWebTestClient
@ExtendWith({SpringExtension.class, WireMockExtension.class})
class Milestone2ApplicationTest {

    @Autowired
    private WebTestClient client;

    @Test
    public void redirectToAdvicePostTest() {
        HealthAdvice advice = new HealthAdvice("bill", "advice");

        stubFor(WireMock.post(urlMatching("/advice"))
                .willReturn(aResponse()
                        .withStatus(OK.value())));

        client.mutateWith(mockJwt())
                .mutateWith(csrf())
                .post()
                .uri("/advice")
                .bodyValue(List.of(advice))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void redirectToAdvicePostNoAuthTest() {

        HealthAdvice advice = new HealthAdvice("bill", "advice");

        client.post()
                .uri("/advice")
                .bodyValue(List.of(advice))
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    public void redirectToMetricPostTest() {

        HealthMetric metric = new HealthMetric(0, 0.0, HealthMetricType.HEART_RATE, null);

        stubFor(WireMock.post(urlMatching("/metric"))
                .willReturn(aResponse()
                        .withStatus(OK.value())));

        client.mutateWith(mockJwt())
                .mutateWith(csrf())
                .post()
                .uri("/metric")
                .bodyValue(List.of(metric))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void redirectToMetricPostNoAuthTest() {

        HealthMetric metric = new HealthMetric(0, 0.0, HealthMetricType.HEART_RATE, null);

        client.post()
                .uri("/metric")
                .bodyValue(List.of(metric))
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    public void redirectToProfilePostTest() {

        HealthProfile profile = new HealthProfile(0, "bill", null);

        stubFor(WireMock.post(urlMatching("/profile"))
                .willReturn(aResponse()
                        .withStatus(OK.value())));

        client.mutateWith(mockJwt())
                .mutateWith(csrf())
                .post()
                .uri("/metric")
                .bodyValue(List.of(profile))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void redirectToProfilePostNoAuthTest() {

        HealthProfile profile = new HealthProfile(0, "bill", null);

        client.post()
                .uri("/profile")
                .bodyValue(List.of(profile))
                .exchange()
                .expectStatus().isForbidden();
    }


}