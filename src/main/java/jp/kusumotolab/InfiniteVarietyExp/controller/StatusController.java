package jp.kusumotolab.InfiniteVarietyExp.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jp.kusumotolab.InfiniteVarietyExp.repository.PairIdRepository;
import jp.kusumotolab.InfiniteVarietyExp.repository.ResultsRepository;

@RestController
public class StatusController {
  @Autowired private ResultsRepository resultsRepository;

  @GetMapping("/status")
  public void getStatus() {
    // returns 200 OK
  }

  @GetMapping(
      value = "/status",
      params = {"user"})
  public Map<String, String> getStatus(@RequestParam(name = "user") String user) {
    final Map<String, String> ret = new HashMap<>();
    ret.put("username", user);
    ret.put("toAnswer", String.valueOf(PairIdRepository.getInstance().size()));
    ret.put("answered", String.valueOf(resultsRepository.findLastUpdateAllByUser(user).size()));

    return ret;
  }
}
