# berryshot #
Spring boot service for uploading raspstill pictures to dropbox.

Generate your dropbox access token via https://www.dropbox.com/developers/

Update dropbox properties
```bash
dropbox.auth.code={yourAuthCode}
dropbox.identifier=berryshot
```

Create package with maven
```bash
mvn clean compile package
```

Run spring boot app
```bash
java -jar target/berryshot-0.0.1-SNAPSHOT.jar
```

Testing
```
curl http://localhost:8080/v1/auth/info
```
