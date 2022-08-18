package jp.kusumotolab.InfiniteVarietyExp.controller;

import java.util.List;
import java.util.Optional;
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
    final var answeredId =
        resultsRepository.findAllByUser(user).stream()
            .mapToInt(ResultEntity::getPairId)
            .max()
            .orElse(0);
    return PairIdRepository.getInstance()
        .next(answeredId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @GetMapping(value = "/result")
  public List<ResultEntity> getResult(
      @RequestParam(name = "user") String user,
      @RequestParam(name = "id") Optional<Integer> pairId) {
    final var result =
        pairId
            .map(id -> resultsRepository.findAllByPairIdAndUser(id, user))
            .orElseGet(() -> resultsRepository.findAllByUser(user));

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
