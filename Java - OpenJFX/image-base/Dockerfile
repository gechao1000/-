# ubuntu 16.04
# openjdk 17.0.1
# maven 3.8.4
FROM ubuntu:16.04
MAINTAINER gg

# change mirror
COPY sources.ubuntu16.list /etc/apt/sources.list
RUN apt-get update \
    && apt-get install -y binutils fakeroot \
    && apt-get clean


# install maven
ADD apache-maven-3.8.4-bin.tar.gz /opt
RUN ln -s /opt/apache-maven-3.8.4/bin/mvn /usr/local/bin


# install openjdk
ADD openjdk-17.0.1_linux-x64_bin.tar.gz /usr/jvm
ENV JAVA_HOME /usr/jvm/jdk-17.0.1
ENV PATH $JAVA_HOME/bin:$PATH


CMD ["bash"]