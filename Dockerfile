# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-slim

# Install necessary packages
RUN apt-get update && apt-get install -y wget unzip curl gnupg

# Install Chrome
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update && apt-get install -y google-chrome-stable

# Install ChromeDriver
RUN CHROME_DRIVER_VERSION=$(curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE) \
    && wget -N http://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip -P ~/ \
    && unzip ~/chromedriver_linux64.zip -d /usr/local/bin/ \
    && rm ~/chromedriver_linux64.zip

# Install Gradle
RUN wget -q https://services.gradle.org/distributions/gradle-7.4-bin.zip \
    && unzip gradle-7.4-bin.zip \
    && mv gradle-7.4 /opt/gradle \
    && ln -s /opt/gradle/bin/gradle /usr/bin/gradle

# Set up working directory
WORKDIR /app

# Copy the project files into the container
COPY . .

# Make gradlew executable
RUN chmod +x ./gradlew

# Build the project (without running tests)
RUN ./gradlew build -x test

# Run the tests
CMD ["./gradlew", "test", "--info", "--console=plain"]