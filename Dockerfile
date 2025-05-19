# Java 17 JDK 이미지 사용
FROM openjdk:17-jdk-slim

# 작업 디렉토리 지정
WORKDIR /app

# JAR 파일을 이미지에 복사
COPY build/libs/skala-0.0.1-SNAPSHOT.jar app.jar

# 외부에서 접근할 포트
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
