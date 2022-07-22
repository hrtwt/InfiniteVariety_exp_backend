package jp.kusumotolab.InfiniteVarietyExp.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PairIdRepository {

  private final TreeSet<Integer> pairIds;

  private static class Holder {
    private static final PairIdRepository pairIdRepository = new PairIdRepository();

    private Holder() {}
  }

  private PairIdRepository() {
    final Path csvPath = Paths.get("db/pairId.csv");
    pairIds = makePairIdsFromCsv(csvPath);
  }

  private TreeSet<Integer> makePairIdsFromCsv(final Path csvPath) {
    try {
      return Files.readAllLines(csvPath).stream()
          .map(Integer::parseInt)
          .collect(Collectors.toCollection(TreeSet::new));
    } catch (IOException e) {
      e.printStackTrace();
      return new TreeSet<>();
    }
  }

  public static PairIdRepository getInstance() {
    return Holder.pairIdRepository;
  }

  public Optional<Integer> next(final int nowId) {
    return Optional.ofNullable(pairIds.higher(nowId));
  }

  public Optional<Integer> indexOf(final int pairId) {
    if (!pairIds.contains(pairId)) {
      return Optional.empty();
    }
    final int index = pairIds.headSet(pairId).size();
    return Optional.of(index);
  }

  public int size() {
    return pairIds.size();
  }
}
