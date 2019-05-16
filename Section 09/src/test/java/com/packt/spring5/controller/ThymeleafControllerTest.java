package com.packt.spring5.controller;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@SpringJUnitWebConfig
@SpringBootTest
class ThymeleafControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private WebClient webClient;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        webClient = MockMvcWebClientBuilder.webAppContextSetup(wac).build();
    }

    @Test
    void shouldRenderView() throws Exception {
        mockMvc.perform(get("/mvc/view"))
                .andExpect(status().isOk())
                .andExpect(xpath("//strong/text()").string(new StringContains("Thymeleaf")));
    }

    @Test
    void shouldRenderViewUsingWebClient() throws Exception {
        HtmlPage simpleMvcViewPage = webClient.getPage("http://localhost/mvc/view");
        DomText title = (DomText) simpleMvcViewPage.getByXPath("//strong/text()").get(0);
        assertThat(title.getTextContent()).contains("Thymeleaf");
    }
}