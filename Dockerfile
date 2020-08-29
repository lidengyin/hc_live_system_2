FROM java:8
MAINTAINER lidengyin
ARG JAR_FILE
ADD ${JAR_FILE} live-system-1.jar
ADD ./simsun.ttc /usr/share/fonts
ADD ./haichuang.png /usr/local
EXPOSE 8252
ENTRYPOINT ["java","-jar","live-system-1.jar"]