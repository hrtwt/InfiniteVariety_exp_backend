# InfiniteVariety_exp_backend

## 事前準備
1. `git clone git@github.com:hrtwt/InfiniteVariety_exp_backend.git`
2. `db/ijadataset.db`にデータベースファイルを配置

## 起動方法

### Docker
1. `docker build -t infin .`
2. `docker run --rm -it -p 8080:8080 infin`

### Gradle
1. `./gradew bootRun`

### fatJar
1. `./gradlew bootJar`
2. `java -jar ./build/libs/InfiniteVarietyExp-*.jar`
