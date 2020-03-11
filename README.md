# springboot-amqp
> 本项目使用springboot框架，集成amqp

# 目录
* [1 创建项目](#01)
* [2 启动服务](#02)
* [3 编写代码](#03)
* [4 测试](#04)

## <div id="01"></div>
## 1 创建项目
> 参照或复制springboot-maven项目

## <div id="02"></div>
## 2 启动服务
    2.1、docker pull rabbitmq:management
    2.2、docker run -d -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=secret rabbitmq:management
    2.3、http://localhost:15672/
    
## <div id="03"></div>
## 3 编写代码    
    3.1、pom.xml文件引入依赖
    3.2、application.properties添加配置项
    3.3、配置队列、交换机名称静态类
    3.4、配置队列、交换机bean，使其可在服务器自动生成
    3.5、配置生产端确认回调、退回回调
    3.6、编写生产消息测试类
    3.7、编写消费消息测试类

## <div id="04"></div>
## 4 测试  
    访问http://localhost:8080/swagger-ui.html生产不同模式消息，观察不同消费者消费情况