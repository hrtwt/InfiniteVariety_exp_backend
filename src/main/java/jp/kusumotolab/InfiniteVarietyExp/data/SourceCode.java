package jp.kusumotolab.InfiniteVarietyExp.data;

/** db内のソースコード組を表すレコード */
public record SourceCode(
    int id,
    String code1,
    int code1Id,
    String code2,
    int code2Id) {
}
