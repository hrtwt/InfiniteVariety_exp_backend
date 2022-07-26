package jp.kusumotolab.InfiniteVarietyExp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import jp.kusumotolab.InfiniteVarietyExp.entity.ResultEntity;
import jp.kusumotolab.InfiniteVarietyExp.repository.PairIdRepository;
import jp.kusumotolab.InfiniteVarietyExp.repository.ResultsRepository;

@RestController
public class ResultController {

  @Autowired private ResultsRepository resultsRepository;

  @GetMapping("/next")
  public int next(@RequestParam(name = "user") String user) {
    final var answerdId =
        resultsRepository.findAllByUser(user).stream()
            .mapToInt(ResultEntity::getPairId)
            .max()
            .orElse(0);
    return PairIdRepository.getInstance()
        .next(answerdId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @GetMapping(
      value = "/result",
      params = {"user"})
  public List<ResultEntity> getResult(@RequestParam(name = "user") String user) {
    final var result = resultsRepository.findLastUpdateAllByUser(user);
    if (result.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return result;
  }

  @GetMapping(
      value = "/result",
      params = {"user", "id"})
  public List<ResultEntity> getResult(
      @RequestParam(name = "user") String user,
      @RequestParam(name = "id") int pairId) {
    final var result = resultsRepository.findAllByPairIdAndUser(pairId, user);
    if (result.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return result;
  }

  @PostMapping("/result")
  public void postResult(
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
