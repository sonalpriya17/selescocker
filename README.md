# Selenium Docker Test Runner

Key Features:
- Seamless ChromeDriver Management: Automatically fetches the correct ChromeDriver version using WebDriverManager, eliminating compatibility issues.
- Leverages Selenium 4: Takes advantage of Selenium 4's enhanced ChromeDriver handling for a smoother testing experience.
- Chrome for Testing: Utilizes Google's dedicated testing environment for optimal compatibility and stability.
- Minimizes Manual Intervention: Reduces the need for manual ChromeDriver updates and version checks, saving time and effort.

Automated web testing using Selenium WebDriver in Docker. Uses TestNG, Gradle, and Chrome. Features dockerized environment, automated ChromeDriver management, headless testing, real-time logging, and Gradle integration. Currently verifies page titles, with room for expansion. Ideal for CI/CD pipelines and consistent cross-platform testing.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/selenium-docker-test-runner.git
    cd selenium-docker-test-runner
    ```
2. Install Docker from [here](https://docs.docker.com/get-docker/).
3. Build the Docker image:
    ```sh
    docker build -t selenium-test .
    ```

## Usage

Run tests and view results:

1. Run the Docker container:
    ```sh
    docker run -it --rm selenium-test
    ```

2. The test results will be displayed in the console output.

3. To run tests with custom parameters:
    ```sh
    docker run -it --rm -e BASE_URL=https://example.com selenium-test
    ```

4. To save test results to a local directory:
    ```sh
    docker run -it --rm -v $(pwd)/test-results:/app/test-results selenium-test
    ```

## Contributing

1. Fork the project
2. Create your feature branch:
    ```sh
    git checkout -b feature/AmazingFeature
    ```
3. Commit your changes:
    ```sh
    git commit -m 'Add some AmazingFeature'
    ```
4. Push to the branch:
    ```sh
    git push origin feature/AmazingFeature
    ```
5. Open a pull request

## License

Distributed under the MIT License. See `LICENSE` file for more information.
