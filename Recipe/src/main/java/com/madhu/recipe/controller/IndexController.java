/**
 * 
 */
package com.madhu.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Madhu
 *
 */
@Controller
public class IndexController {

	@RequestMapping({"","/","/index", "/index.html"})
	public String getIndexPage() {
		return "index";
	}
}
