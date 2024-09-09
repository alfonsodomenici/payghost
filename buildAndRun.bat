
mvn clean package
docker build -t payghost .
docker rm -f   payghost
docker run -d --name payghost -e DB_USER='payghost' -e DB_PASSWORD='payghost' -e DB_JDBC_URL='jdbc:mysql://192.168.51.171:3306/payghost' -p8080:8080 payghost