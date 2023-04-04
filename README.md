# static-server

静态资源服务

## Java版本

```shell
#进入目录
cd ./java

#打包
mvn clean package

#启动服务
java -jar ./target/static-server.jar

#查看参数
java -jar ./target/static-server.jar -h

#在8080端口启动，使用UTF-8作为响应编码，匹配任意的/开头的地址，静态资源目录为static
java -jar ./target/static-server.jar -p 8080 -e UTF-8 -m / -d static
```

参数
```shell
usage:
 -d,--dir <arg>        Static resources directory
 -e,--encoding <arg>   Response content encoding
 -h,--help             Help
 -m,--match <arg>      URI paths that begin with this path will match
 -p,--port <arg>       Server port
```

### 

## Go版本
