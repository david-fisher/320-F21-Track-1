FROM nvidia/opengl:1.2-glvnd-devel-ubuntu18.04

RUN apt-get update -y
RUN apt-get install -y openjfx=8u161-b12-1ubuntu2 libopenjfx-jni=8u161-b12-1ubuntu2 libopenjfx-java=8u161-b12-1ubuntu2
RUN apt-get install -y openjdk-8-jdk

WORKDIR /app
COPY src ./src
WORKDIR src
RUN javac Main.java
ENV LIBGL_ALWAYS_INDIRECT=1
CMD ["java", "Main"]