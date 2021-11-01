# json

[![CN](https://img.shields.io/badge/Language-Chinese-red)](https://github.com/bitjerry/json/blob/main/README.zh-CN.md)
[![APM](https://img.shields.io/badge/license-MIT-2345.svg)](https://github.com/bitjerry/json)

>1. ***this tool supports the serialization and deserialization of json***
>
>2. ***json.java converts json to string, and nosj.java converts string to json***
>
>3. ***the project does not depend on any third-party package***
>
>4. ***the project was compiled and tested under jdk14***
>
>5. ***welcome to provide feedback and correct the bug***

## Function description

### Json

1. ***any map type can be directly converted to json, and any list type can be accepted as json array***

2. ***accepts basic types, reference type variables, and null***

3. ***getJson() method only accepts the subclass of map. Do not try to expose or use other methods. The returned value of this method call is StringBuilder. If necessary, please use toString() conversion. If you use json directly, you will get json string***

4. ***json class takes LinkedHashMap as its parent class. You can use all methods of LinkedHashMap to add, delete, modify or query json. If you  consider thread safety, you can replace it with a map that supports thread safety suck as "extends ConcurrentHashMap"***

5. ***string will be wrapped in double quotation marks, and other types will not be marked***

### Nosj

1. ***standard json format: start and end with `{}`. JSON objects must have key value pairs, and values must be string and end with`,`. Duplicate keys are not allowed for json objects at the same level***

2. ***value attribute of conversion result: Integer, Double, Boolean, String, null***

3. ***only braces and brackets can limit the area, and other symbols do not take effect. If commas or colons appear in the string, they must be written in double quotation marks. The standard writing method should add double quotation marks to the string, but do not nest double quotation marks infinitely, which may produce unknown results. As quotation marks of the string, be sure to escape `\\\"`, not `\"`. Do not use open quotation marks***

4. ***parseJson() method accepts a string in json format and returns json. This method can identify and convert variable types. Please strictly abide by variable declaration rules to avoid unnecessary conversion problems***

5. ***If string not be wrapped in double quotation marks, the method can also automatically identify them, but in this case, json keywords should not appear in key and value, such as `{} [], "`: these symbols, all control characters before key and value and spaces after closed quotation marks will disappear. If you want to keep them, please use quotation marks to enclose them***

## Test case
**code**
```
String m = "{\":g[g{}g\":[null, {eee:null,1:[[1],[2],[\"3,ee\"],[{1:2},[\"122}3[32\"]],[6],[[[[\"]\"]]]]],2:3},{1:2,2:3}],\"wee}w\":\"23,223\",wye:{qwqw:weq,qwwq:{wq:\"211e1\",211:wqqzwqq,ewei:iweetcx}}}";
System.out.println(Json.getJson(parseJson(m)).toString());
```
**result**
>{":g[g{}g": [null, {"eee": null, "1": [[1], [2], ["3,ee"], [{"1": 2}, ["122}3[32"]], [6], [[[["]"]]]]], "2": 3}, {"1": 2, "2": 3}], "wee}w": "23,223", "wye": {"qwqw": "weq", "qwwq": {"wq": "211e1", "211": "wqqzwqq", "ewei": "iweetcx"}}}

## License
MIT © [bitjerry](https://github.com/bitjerry/json/blob/main/LICENSE)
----------
*2021/11/1 17:49:51*
*Mr.lin*
