# rock-paper-scissors

Java 17 + Spring Boot 3.5 기반 가위바위보 REST API.

## 요구 환경

- JDK 17 이상 (시스템 `JAVA_HOME` = Corretto 17)
- Gradle Wrapper 포함 (별도 설치 불필요)

## 실행 / 빌드 / 테스트

```powershell
.\gradlew.bat bootRun   # 서버 실행 → http://localhost:8080
.\gradlew.bat test      # 테스트
.\gradlew.bat build     # 빌드 (실행 가능 jar → build/libs/)
```

## API

```
GET /api/play?h={hand}
```

- `h`: **다국어 허용**
  - 영어: `rock` / `paper` / `scissors` (`r` / `p` / `s`)
  - 한국어: `바위` / `보` / `가위`, `묵` / `빠` / `찌`
  - 일본어: `グー` / `パー` / `チョキ`
  - 중국어: `石头` / `布` / `剪刀`
- 응답: `{"player":"ROCK","computer":"SCISSORS","outcome":"WIN"}`
- 알 수 없거나 누락된 입력 → `400 Bad Request`

예:

```bash
curl "http://localhost:8080/api/play?h=rock"
```

## 구조 (계층형 Spring MVC / REST)

```
org.yscha88
├── RockPaperScissorsApplication   # 부트 진입점 (@SpringBootApplication)
├── web
│   ├── GameController             # GET /api/play
│   └── ApiExceptionHandler        # 400 처리 (ProblemDetail)
└── game                           # 도메인 로직 (프레임워크 비의존)
    ├── Hand (ROCK=0/PAPER=1/SCISSORS=2)
    ├── Outcome (WIN/LOSE/DRAW)
    ├── GameResult (record)
    ├── HandParser                 # 다국어 → Hand
    └── RockPaperScissors          # randomHand(): private + SecureRandom (편향 0)
```

## VSCode

`Extension Pack for Java` + `Gradle for Java` 확장 권장 (`.vscode/extensions.json`).
`build.gradle.kts` 변경 후에는 **Command Palette → "Java: Clean Java Language Server Workspace"** 로 Gradle 재임포트하면 IntelliSense가 Spring 의존성을 인식한다.
