package jp.kusumotolab.InfiniteVarietyExp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jp.kusumotolab.InfiniteVarietyExp.entity.ResultEntity;
import jp.kusumotolab.InfiniteVarietyExp.repository.ResultsRepository;

@RestController
public class ResultController {

  @Autowired private ResultsRepository resultsRepository;

  @GetMapping("/next")
  public int next(@RequestParam(name = "user", required = true) String user) {
    return 7; // todo
  }

  @GetMapping("/result")
  public List<ResultEntity> getResult(
      @RequestParam(name = "user", required = true) String user,
      @RequestParam(name = "id", required = true) int pairId) {
    final var result = resultsRepository.findAllByPairIdAndUser(pairId, user);
    if (result.isEmpty()) {
      // todo throw 404
    }
    return result;
  }

  @PostMapping("/result")
  public void PostResult(
      @RequestParam(name = "id") int pairId,
      @RequestParam(name = "user") String user,
      @RequestParam(name = "judge") String judge) {
    final var result = new ResultEntity();
    result.setPairId(pairId);
    result.setUser(user);
    result.setJudge(judge);
    resultsRepository.saveAndFlush(result);
  }
}
