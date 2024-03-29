package jp.kusumotolab.InfiniteVarietyExp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jp.kusumotolab.InfiniteVarietyExp.data.SourceCode;
import jp.kusumotolab.InfiniteVarietyExp.repository.DatasetRepository;

@RestController
@RequestMapping("source")
public class SourceController {

  @Autowired DatasetRepository datasetRepository;

  @GetMapping
  public SourceCode getSource(
      @RequestParam(name = "id") int id,
      @RequestParam(name = "deduplicate", required = false, defaultValue = "true")
          boolean needDeduplicate) {
    final SourceCode sourceCode = datasetRepository.findSourceCodeByPairId(id);

    if (needDeduplicate) {
      return sourceCode.deduplicatedSourceCode();
    }

    return sourceCode;
  }
}
