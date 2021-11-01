# json
[![CN](https://img.shields.io/badge/Language-English-blue)](https://github.com/bitjerry/json/README.md)
![APM](https://img.shields.io/apm/l/json)

>1. ***此工具提供为json的序列化与反序列化提供支持***
>
> 2. ***Json.java将json转换为字符串, Nosj.java将字符串转为json***
>
> 3. ***项目不依赖于任何第三方包***
>
> 4. ***项目在jdk14下编译并测试通过***
>
> 5. ***欢迎提供反馈, 纠正bug***

## _功能描述_

### Json

1. ***可以接受任何map类型直接转为json, 接受任何list类型作为json数组***

2. ***接受基本类型, 引用类型变量, 以及null***

3. ***getJson()方法传入只接受map的子类, 保证转换的安全, 不要尝试暴露及使用其它方法, 此方法调用返回的值为StringBuilder, 若有需要请自行使用toString()转换, 若直接使用Json, 得到的是json字符串***

4. ***Json类以LinkedHashMap作为父类, 可以使用LinkedHashMap的所有方法为json增删改查, 如果要考虑线程安全, 可以将其替换为支持线程安全的map***

5. ***字符串将以双引号包裹, 其它类型无标志***

### Nosj

1. ***标准json格式: 以`{}`开头结尾, json对象必须有键值对, 且key必须为string, value以, 结尾. 同级json对象不允许重复的key***

2. ***转换结果的value属性: Integer, Double, Boolean, String, null***

3. ***只有大括号与中括号可以限定区域, 其它符号不生效, 若要在字符串中出现逗号或者冒号, 务必写在双引号内. 规范写法应为字符串添加双引号, 但不要无限嵌套双引号, 可能产生未知结果, 作为字符串的引号务必转义`\\\"`, 而不是`\"` 不要使用未闭合引号***

4. ***parseJson()方法接受一个json格式的字符串, 返回Json. 此方法会做变量类型识别与转换, 请严格遵守变量声明规则, 避免发生不必要的转换问题***

5. ***无需引号自动识别, 可以在key或者value中不使用引号, 但这种情况下不要在key, value中出现json关键字, 如`{}[],": `这些符号, key, value前的及闭合引号后的所有控制字符以及空格都会消失, 如果想保留它们, 请使用引号将它们括起来.***

# _测试用例_
---
**代码**
```
String m = "{\":g[g{}g\":[null, {eee:null,1:[[1],[2],[\"3,ee\"],[{1:2},[\"122}3[32\"]],[6],[[[[\"]\"]]]]],2:3},{1:2,2:3}],\"wee}w\":\"23,223\",wye:{qwqw:weq,qwwq:{wq:\"211e1\",211:wqqzwqq,ewei:iweetcx}}}";
System.out.println(Json.getJson(parseJson(m)).toString());
```
**结果**
>{":g[g{}g": [null, {"eee": null, "1": [[1], [2], ["3,ee"], [{"1": 2}, ["122}3[32"]], [6], [[[["]"]]]]], "2": 3}, {"1": 2, "2": 3}], "wee}w": "23,223", "wye": {"qwqw": "weq", "qwwq": {"wq": "211e1", "211": "wqqzwqq", "ewei": "iweetcx"}}}
---
*2021/11/1 17:49:51*
*Mr.lin*