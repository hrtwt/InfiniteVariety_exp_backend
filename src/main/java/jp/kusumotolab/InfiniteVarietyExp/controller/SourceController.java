package jp.kusumotolab.InfiniteVarietyExp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jp.kusumotolab.InfiniteVarietyExp.data.SourceCode;
import jp.kusumotolab.InfiniteVarietyExp.service.SqliteService;

@RestController
@RequestMapping("source")
public class SourceController {

  @Autowired SqliteService sqlite;

  @GetMapping
  public SourceCode getSource(@RequestParam(name = "id", required = true) int id) {
    return sqlite.findSourceCodeById(id);
  }
}
