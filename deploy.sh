mvn clean package -DskipTests
scp -P 8022 target/lib/chat_netty-1.0-SNAPSHOT.jar zhangll@test.zll0428.fun:/home/zhangll/app/