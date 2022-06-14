FROM openjdk:17-slim AS base

#for compose.override
FROM base AS dev

FROM base AS builder

WORKDIR /backend

# download deps
COPY *.gradle gradlew /backend/
COPY gradle/ /backend/gradle/
RUN ./gradlew build -x test --parallel --continue > /dev/null 2>&1 || true

# make fatjar
COPY src/ /backend/src/
RUN ./gradlew bootJar


FROM openjdk:17-slim

WORKDIR /backend

COPY --from=builder /backend/build/libs/InfiniteVarietyExp*-SNAPSHOT.jar /backend/InfiniteVarietyExp-backend.jar
COPY ./db/*.db /backend/db/

CMD ["java", "-jar", "InfiniteVarietyExp-backend.jar"]
