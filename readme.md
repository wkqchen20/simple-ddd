基于 CQE 模式简单 ddd demo

目录结构
```
.
├── application         // 应用服务
│   ├── assembler       // 入参转换为领域的 assembler
│   │   └── user        // user assembler
│   └── dto             // application service response dto
│       └── user
├── domain              // 领域层
│   ├── base            // 公共
│   │   ├── enums       // 枚举相关
│   │   └── event       // 事件接口定义
│   └── user            // 
│       └── event       // user 领域下的事件
├── infrastructure      // 基础层
│   ├── config          // 注入相关配置
│   ├── dao             // dao
│   │   └── user
│   │       ├── mapper
│   │       └── po
│   └── event           // event impl
└── interfaces          // 接口层
    ├── commands        // 新增修改等命令放在这个地方
    │   └── user
    ├── dto             // response 相关
    └── queries         // 查询等放这里
        └── user
```