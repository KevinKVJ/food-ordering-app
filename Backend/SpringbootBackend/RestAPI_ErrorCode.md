## Rest API Error Code

#### Principle

##### Controller / Microservice (001-999)

| Module | Code  |
| --- | --- |
|  Merchant | 001 |
|  Product | 002 |
|  Category | 005 |

##### Error Type

| Letter | 含义  |
| --- | --- |
| P | 参数错误 |
| B | 业务错误 |
| F | 文件IO错误 |
| O | 其他错误 |
| M | 中间件调用出错 |

##### Detailed Error Code (001-009)

##### Special Error Codes

| Error Code | 含义 |
| --- | --- |
| 0000000 | 成功返回结果 |
| 0000001 | request body 不符合json格式 |
| 0000002 | 没有提供合法token |
