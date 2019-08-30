/**
 * 
 */
package com.test.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author govindarajuv
 *
 */
@RestController
@RequestMapping
@Slf4j
public class AllUrlMapping {

	/**
	 * 
	 */
	public AllUrlMapping() {
		log.info("AllUrlMappingAllUrlMappingAllUrlMappingAllUrlMappingAllUrlMappingAllUrlMapping");
	}
	
	@GetMapping("*")
	public String notFound() {
		return "notFound";
	}
}
