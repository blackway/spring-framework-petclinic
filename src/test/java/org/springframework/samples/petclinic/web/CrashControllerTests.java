package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * Test class for {@link CrashController}
 *
 * @author Colin But
 */
//@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-core-config.xml", "classpath:spring/mvc-test-config.xml"})
@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
class CrashControllerTests {

    @Autowired
    private CrashController crashController;

    @Autowired
    private SimpleMappingExceptionResolver simpleMappingExceptionResolver;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(crashController)
            .setHandlerExceptionResolvers(simpleMappingExceptionResolver)
            .build();
    }

    @Test
    void testTriggerException() throws Exception {
        mockMvc.perform(get("/oups"))
            .andExpect(view().name("exception"))
            .andExpect(model().attributeExists("exception"))
            .andExpect(forwardedUrl("exception"))
            .andExpect(status().isOk());
    }
}
