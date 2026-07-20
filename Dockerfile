# syntax=docker/dockerfile:1

# ---- 빌드 스테이지 ----
# JAR은 아키텍처 독립이므로 항상 빌더 네이티브 아키텍처($BUILDPLATFORM)에서 1회만 빌드한다.
# (멀티아치 빌드 시 QEMU 에뮬레이션으로 Gradle을 돌리는 비용을 피함)
FROM --platform=$BUILDPLATFORM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /workspace

COPY gradlew settings.gradle.kts build.gradle.kts ./
COPY gradle ./gradle
COPY src ./src

RUN --mount=type=cache,target=/root/.gradle \
    chmod +x gradlew && ./gradlew --no-daemon clean bootJar -x test

# ---- 런타임 스테이지 ----
# 타깃 아키텍처별 JRE 위에 JAR만 얹는다(코드 실행 없음 → 에뮬레이션 비용 최소).
FROM eclipse-temurin:17-jre-jammy AS runtime
WORKDIR /app

# 비루트 사용자로 실행
RUN groupadd -r app && useradd -r -g app -u 1001 app
COPY --from=build /workspace/build/libs/*-SNAPSHOT.jar app.jar
USER app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
