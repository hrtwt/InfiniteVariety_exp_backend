package jp.kusumotolab.InfiniteVarietyExp.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** db内のソースコード組を表すレコード */
public record SourceCode(int id, String code1, int code1Id, String code2, int code2Id) {

  public SourceCode deduplicatedSourceCode() {
    final String deduplicatedCode1 = addIdToMethodName(code1, code1Id);
    final String deduplicatedCode2 = addIdToMethodName(code2, code2Id);

    return new SourceCode(
        this.id, deduplicatedCode1, this.code1Id, deduplicatedCode2, this.code2Id);
  }

  private String addIdToMethodName(final String method, final int id) {
    final String methodName = getMethodName(method);
    return method.replaceFirst(methodName + "\\s*\\(", methodName + "_" + id + "(");
  }

  private String getMethodName(final String code) {
    final Pattern methodDeclaration =
        Pattern.compile(
            "(public|protected|private|static|\\s+)?[\\w<>\\[\\]]+\\s+(?<methodName>\\w+) *\\([^)]*\\) *(\\{?|[^;])\n");
    Matcher matcher = methodDeclaration.matcher(code);
    matcher.find();
    return matcher.group("methodName");
  }
}
