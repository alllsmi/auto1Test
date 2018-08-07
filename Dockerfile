# Pull base image.
FROM ubuntu

# Install Java.
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer && \
    apt-get clean

RUN apt-get install maven -y
RUN apt-get install git -y
RUN git clone https://github.com/alllsmi/auto1Test
RUN apt-get install libnss3 libgconf-2-4 -y
RUN chmod +x /auto1Test/src/test/resources/chromedriver

RUN apt-get update && apt-get install -yq \
    chromium-chromedriver \
    chromium-browser \
    xvfb \
    xsel \
    unzip \
    xclip

# chromeDriver v2.40
RUN wget -q "https://chromedriver.storage.googleapis.com/2.40/chromedriver_linux64.zip" -O /tmp/chromedriver.zip \
    && unzip /tmp/chromedriver.zip -d /usr/bin/ \
    && rm /tmp/chromedriver.zip

# create symlink to chromedriver (to the PATH)
RUN ln -s /usr/bin/chromium-browser \
    && chmod 777 /usr/bin/chromium-browser


# Define working directory.
WORKDIR /data

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME       /usr/lib/jvm/java-8-oracle
ENV LANG            en_US.UTF-8

ENTRYPOINT cd /auto1Test; git checkout .; git pull; chmod +x /auto1Test/src/test/resources/chromedriver; \
sed -i -e 's/isWindows=true/isWindows=false/g' /auto1Test/src/test/resources/testing.properties; mvn clean test