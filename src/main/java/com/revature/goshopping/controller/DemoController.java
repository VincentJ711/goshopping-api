package com.revature.goshopping.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/demos")
public class DemoController {
  /*
    TODO how to log every request?
    with servlets we could have all our servlets extend our own custom servlet
    which could override the service method and log each request there.

    TODO make sure cors works
   */
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
