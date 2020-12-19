package com.revature.goshopping.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.goshopping.model.Auth;
import com.revature.goshopping.utility.JwtUtility;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demos")
public class DemoController {
  @GetMapping("/login")
  public String loginDemo() {
    Auth auth = new Auth(10, true);
    String jwt = null;

    try {
      jwt = JwtUtility.create(auth);
    } catch (JsonProcessingException e) { }

    return jwt;
  }

  @GetMapping("/parse_jwt")
  public String gettingTheAuth(@RequestHeader Map<String, String> headers) {
    System.out.println(headers);
    // assuming the headers.get("Authorization"] is set to
    // "Bearer <JWT>", where the jwt is valid, the following getAuth method
    // should return the auth object it contains. null if its not or doesnt
    // exist.
    Auth auth = JwtUtility.getAuth(headers);
    return "i parsed from the jwt in the headers an auth object = " + auth;
  }


	@ResponseBody
  @GetMapping
  public List<Demo> getDemos() {
    return Arrays.asList(new Demo("this is a demo!"));
  }

  @ResponseBody
  @GetMapping(value = "/{id}")
  public Demo getDemo(
      @PathVariable int id,
      @RequestParam(required = false) String qp
  ) {
	  return new Demo("you sent an id of " + id + " with query param qp=" + qp);
  }

  @PostMapping
  public void createDemo(@RequestBody Demo demo) {
    System.out.println("creating demo with content=" + demo.content);
  }

  private static class Demo {
    private String content;

    private Demo() {

    }

    public Demo(String content) {
      this.content = content;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }
  }

  /*

  	@RequestMapping(value = "/planet", method = RequestMethod.GET)
//	@ResponseBody
	public List<Planet> getPlanets() {

//		return ResponseEntity.status(HttpStatus.OK).body(planetService.getPlanets());
//		resp.setStatus(400);
		return planetService.getPlanets();
	}

	@RequestMapping(value = "/planet/{id}", method = RequestMethod.GET)
//	@ResponseBody
	public Planet getPlanet(@PathVariable("id") int id) {
		return planetService.getPlanetById(id);
	}

//	@GetMapping(value = "/planet")
//	public Planet getPlanet(@RequestParam int planetId) {
//		return planetService.getPlanetById(planetId);
//	}

	@PostMapping(value = "/planet")
	public Planet addPlanet(@RequestBody PlanetDTO planet) {
		return planetService.addPlanet(planet);
	}
   */
}
