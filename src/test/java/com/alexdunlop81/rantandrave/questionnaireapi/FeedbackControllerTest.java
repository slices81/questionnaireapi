/**
 * 
 */
package com.alexdunlop81.rantandrave.questionnaireapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
/**
 * @author Alex
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class FeedbackControllerTest {
	private static final Logger logger = LogManager.getLogger("FeedbackTest");
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

   

    
    @Autowired
    private WebApplicationContext webApplicationContext;

   
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        
      
    }
    
    @Test
    public void submitHappyPath() throws Exception {
        mockMvc.perform(get( "/feedback?name=fred&score=1&comment=ok"
                ))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
              .andExpect(jsonPath("$.name", Matchers.is("fred")))
               .andExpect(jsonPath("$.score", Matchers.is(1)))
               .andExpect(jsonPath("$.comment", Matchers.is("ok")));
    }
    
    /**
     * Name only test should fail
     * @throws Exception
     */
    @Test
    public void submitNameOnly() throws Exception {
        mockMvc.perform(get( "/feedback?name=freds"
                ))
                .andExpect(status().isBadRequest());
                   
    }
    /**
     * Name only score should fail
     * @throws Exception
     */
    @Test
    public void submitScoreOnly() throws Exception {
        mockMvc.perform(get( "/feedback?score=7"
                ))
                .andExpect(status().isBadRequest());
                
    }
    /**
     * Name only score should fail
     * @throws Exception
     */
    @Test
    public void submitCommentOnly() throws Exception {
        mockMvc.perform(get( "/feedback?comment=hello"
                ))
                .andExpect(status().isBadRequest());
      
    }
    
    @Test
    public void submitMissingScore() throws Exception {
        mockMvc.perform(get( "/feedback?comment=xx&name=Freds"
                ))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void submitMissingComment() throws Exception {
        mockMvc.perform(get( "/feedback?score=8&name=Freds"
                ))
                .andExpect(status().isBadRequest());
                
    }
    
    @Test
    public void submitMissingName() throws Exception {
        mockMvc.perform(get( "/feedback?score=8&comment=Freds"
                ))
                .andExpect(status().isBadRequest());
        logger.info(model().toString());       
    }
    
    
    @Test
    public void scoreNonInt() throws Exception {
        mockMvc.perform(get( "/feedback?score=8.5&comment=Freds&name=Alex"
                ))
                .andExpect(status().isBadRequest());
        logger.info(model().toString());           
    }
    
    @Test
    public void scoreZero() throws Exception {
        mockMvc.perform(get( "/feedback?score=0&comment=Freds&name=Alex"
                ))
                .andExpect(status().isBadRequest());
        logger.info(model().toString());            
    }
    
    @Test
    public void scoreBigger() throws Exception {
        mockMvc.perform(get( "/feedback?score=11&comment=Freds&name=Alex"
                ))
                .andExpect(status().isBadRequest());
        logger.info(model().toString());           
    }
    
    @Test
    public void nameEmpty() throws Exception {
        mockMvc.perform(get( "/feedback?score=5&comment=Freds&name="
                ))
                .andExpect(status().isBadRequest());
        logger.info(model().toString());            
    }
    
    @Test
    public void commentEmpty() throws Exception {
        mockMvc.perform(get( "/feedback?score=5&comment=&name=Alex"
                ))
                .andExpect(status().isBadRequest());
        logger.info(model().toString());             
                
    }
    
    @Test
    public void scoreEmpty() throws Exception {
        mockMvc.perform(get( "/feedback?score=&comment=xxx&name=Alex"
                ))
                .andExpect(status().isBadRequest());
        logger.info(model().toString());             
              
    }
}
