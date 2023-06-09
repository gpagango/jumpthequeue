package com.devonfw.application.jtqj.general.service.impl.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.devonfw.application.jtqj.general.common.api.to.UserProfileTo;
import com.devonfw.application.jtqj.general.service.api.rest.SecurityRestService;
import com.devonfw.application.jtqj.general.service.base.test.RestServiceTest;
import com.devonfw.module.service.common.api.client.config.ServiceClientConfigBuilder;

/**
 * This class tests the login functionality of {@link SecurityRestServiceImpl}.
 */
@ExtendWith(SpringExtension.class)
public class SecurityRestServiceImplTest extends RestServiceTest {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(SecurityRestServiceImplTest.class);

  /**
   * Test the login functionality as it will be used from a JavaScript client.
   */
  @Test
  @Disabled // Security via Login is currently not implemented, so ignore this test
  public void testLogin() {

    String login = "waiter";
    String password = "waiter";

    ResponseEntity<String> postResponse = login(login, password);
    LOG.debug("Body: " + postResponse.getBody());
    assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(postResponse.getHeaders().containsKey(HttpHeaders.SET_COOKIE)).isTrue();
  }

  /**
   * Test of {@code SecurityRestService.getCsrfToken()}.
   */
  @Test
  public void testGetCsrfToken() {

    String login = "waiter";
    String password = "waiter";
    SecurityRestService securityService = getServiceClientFactory().create(SecurityRestService.class,
        new ServiceClientConfigBuilder().host("localhost").authBasic().userLogin(login).userPassword(password)
            .buildMap());
    CsrfToken csrfToken = securityService.getCsrfToken(null, null);
    assertThat(csrfToken.getHeaderName()).isEqualTo("X-CSRF-TOKEN");
    assertThat(csrfToken.getParameterName()).isEqualTo("_csrf");
    assertThat(csrfToken.getToken()).isNotNull();
    LOG.debug("Csrf Token: {}", csrfToken.getToken());
  }

  /**
   * Test of {@link SecurityRestService#getCurrentUser()}.
   */
  @Test
  public void testGetCurrentUser() {

    String login = "waiter";
    String password = "waiter";
    SecurityRestService securityService = getServiceClientFactory().create(SecurityRestService.class,
        new ServiceClientConfigBuilder().host("localhost").authBasic().userLogin(login).userPassword(password)
            .buildMap());
    UserProfileTo userProfile = securityService.getCurrentUser();
    assertThat(userProfile.getLogin()).isEqualTo(login);
  }

  /**
   * Performs the login as required by a JavaScript client.
   *
   * @param userName the username of the user
   * @param tmpPassword the password of the user
   * @return @ {@link ResponseEntity} containing containing a cookie in its header.
   */
  private ResponseEntity<String> login(String userName, String tmpPassword) {

    String tmpUrl = "http://localhost:" + String.valueOf(this.port) + "/services/rest/login";

    HttpEntity<String> postRequest = new HttpEntity<>(
        "{\"j_username\": \"" + userName + "\", \"j_password\": \"" + tmpPassword + "\"}", new HttpHeaders());

    ResponseEntity<String> postResponse = new RestTemplate().exchange(tmpUrl, HttpMethod.POST, postRequest,
        String.class);
    return postResponse;
  }
}