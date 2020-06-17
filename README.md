# RSC快递收派件系统
该项目是一个基于Spring Cloud Alibaba微服务的快递收派件系统，该系统主要分为用户端和快递员端。
用户可以使用该系统进行下单并跟踪订单情况。快递员可以使用快递员端进行订单状态操作和接单，接单包括订单自动分配和从订单池抢单。

## 项目架构图

![image-20200617150035063.png](https://i.loli.net/2020/06/17/SkiL7ACWxyasNTQ.png)

## 项目所用到的技术

项目采用前后端分离架构，目前这里只包含后端部分

### 开发框架

- Spring Cloud Alibaba

- Spring Boot
- MyBatis
- MyBatis Plus

### 中间库

- Redis
- RocketMQ

### 微服务组件

- Nocos
- Dubbo
- Seata

### 项目模块说明

|     模块名称      | 模块类型  |                             说明                             |
| :---------------: | :-------: | :----------------------------------------------------------: |
|      sca-rsc      |    Pom    |        所有模块的父模块，统一管理所有子模块的依赖版本        |
|    sca-common     |    Jar    |    公共组件，包括所有Dubbo要对外暴露的服务接口声明和DTO类    |
| sca-customer-info | Dubbo服务 |                   提供用户个人信息相关服务                   |
| sca-postman-info  | Dubbo服务 | 提供用户快递员信息相关服务，包括上下班打卡，接单模式，工作任务管理等 |
|     sca-order     | Dubbo服务 |                       提供订单相关服务                       |
| sca-distribution  | Dubbo服务 | 订单调度中心，负责订单取件快递员以及派件快递员的分配规则实现 |

### 代码实现相关说明

#### 配置共享

项目中所用到的MySQL，Redis，RocketMQ等连接信息以及部分公共配置都托管在Nacos配置中心，当服务启动时会自从配置中心加载所需配置

相关代码：

bootstrap.yaml

```
spring:
  cloud:
    nacos:
      config:
        server-addr: nacos地址
        namespace: nacos命名空间
        file-extension: yaml
        extension-configs:		# 加载所需配置
          - data-id: config-dubbo.yaml
            refresh: true
          - data-id: config-mysql.yaml
            refresh: true
          - data-id: config-nacos-discovery.yaml
            refresh: true
          - data-id: config-redis.yaml
            refresh: true
          - data-id: config-seata.yaml
            refresh: true
          - data-id: config-rocketmq.yaml
            refresh: true
```

Nacos上对应配置：

![image-20200617154413084.png](https://i.loli.net/2020/06/17/aK4Oy7chSYeldvR.png)

#### 订单流程

![image-qnmfuiznt.png](https://i.loli.net/2020/06/17/Dnjy4PusN5KTecV.png)

- 寄件
  - 顾客下单将订单信息持久化入数据库，并将订单号作为消息Payload发送往消息队列的customer-new-order topic。
  - 调度中心作为customer-new-order消息消费者，向该topic拉取消息消费，根据订单id查找出订单完整信息（如订单寄件地址），并通过Redis上保存的快递员负责区域以及工作量大小，选出合适的快递员，并将订单指派给该快递员负责，若无法选出合适快递员，将订单加入订单池。消息消费完成。
- 派件
  - 快递员员将订单发出之后，会将订单id作为消息Payload发送往消息队列的customer-old-order topic。
  - 调度中心作为customer-old-order消息消费者，向该topic拉取消息消费，根据订单id查找出订单完整信息（如订单派件地址），并通过Redis上保存的快递员负责区域以及工作量大小，选出合适的快递员，并将订单指派给该快递员负责。至此，消息消费完成。