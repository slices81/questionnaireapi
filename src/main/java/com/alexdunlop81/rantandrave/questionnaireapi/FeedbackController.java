/**
 * 
 */
package com.alexdunlop81.rantandrave.questionnaireapi;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Alex
 *
 */
@RestController
public class FeedbackController extends WebMvcConfigurerAdapter{
	private static final Logger logger = LogManager.getLogger("FeedbackController");
	@RequestMapping(value = "/feedback", method=RequestMethod.GET)
    @ResponseBody
	public Feedback feedback(@Valid  @ModelAttribute() Feedback feedback) {
        logger.info("Feedback webmetod called");
		return new Feedback(feedback);
    }
}
