package jp.kusumotolab.InfiniteVarietyExp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultController {

  @GetMapping("/next")
  public int next(@RequestParam(name = "user", required = true) String user) {
    return 7; // todo
  }

  @GetMapping("/result")
  public String getResult(
      @RequestParam(name = "user", required = true) String user,
      @RequestParam(name = "id", required = true) int id) {
    return "o";
  }

  @PostMapping("/result")
  public void PostResult(
      @RequestParam(name = "id", required = true) int id,
      @RequestParam(name = "user", required = true) String user) {}
}
