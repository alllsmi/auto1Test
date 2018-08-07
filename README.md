# Readme

- Test could be launched on Windows and Linux environments, it depends on parameter isWindows in src/test/resources/testing.properties
- Test could be launched from IDE and CLI. There should be installed Java and Maven. Launch from CLI: navigate to the root of project
and execute "maven clean test".
- Also attached Dockerfile with description of container suitable for test launch. Execute "docker build -t @name@ /path/to/dockerfile/" and
"docker run @name@".
