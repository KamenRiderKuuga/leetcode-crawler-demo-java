# LeetCode-Crawler-JavaDemo🐞
 LeetCode中文站爬虫的Java实现，持续更新+整理代码



## 目前完成功能：

1. 登录并获取Cookie
2. 获取所有题目列表
3. 获取指定题目内容
4. 提交题解至LeetCode，并返回`submissionId`
5. 使用`submissionid`查询题解



## 使用示例：

> LeetCodeHelper是一个帮助类，帮助使用者用于和LeetCode交互，具体的使用方法可以看test中的使用方法



### 实例化帮助类LeetCodeHelper⚙

> 三个参数分别用来用于发起http请求，配置CookieSpec，保存Cookie，没有直接内嵌在帮助类里是为了更方便让使用者使用自己的httpClient实例

```java
// 用准备好的参数实例化LeetCodeHelper
LeetCodeHelper leetCodeHelper = new LeetCodeHelper(httpClient, requestConfig, httpCookieStore);
```



### 获取所有题目列表📚(无需登录)

> 这里获取到的是一个Json字符串，可以自行处理

```java
// 获取所有题目列表
String questionsListJsonString = leetCodeHelper.getQuestionsList();
```



### 获取指定题目详情🔍(无需登录)

> 这里获取到的是一个Json字符串，可以自行处理

```java
String questionSlug = "two-sum";
// 获取题目详情
String questionContentJsonString = leetCodeHelper.getQuestionDetailBySlug(questionSlug);
```



### 登录到LeetCode✔

> 这里的账号密码直接写在`Constants`类里面了，可以自己调整账号密码配置方式，调用这个函数之后，cookieStore会自动保存Cookie信息

```java
leetCodeHelper.loginToLeetCode();
```



### 提交题解并返回submissionId😊(需要登录)

> 登录过程在这个函数内部也会去调用，所以要使用这个函数不需要自行调用`loginToLeetCode()`，但是要记得这里需要配置了可用的账号密码才能进行。返回的submissionId是LeetCode的提交流水号，后续用这个流水号就可以查到提交结果了

```java
// 构建题解
AnswerParam answerParam = new AnswerParam();
answerParam.setAnswer("print(\"hello, world!\")");
answerParam.setLanguage(LanguageEnums.PYTHON3.getCode());
leetCodeHelper.loginToLeetCode();
// 提交题解并返回提交流水号
String submissionId = leetCodeHelper.submitAnswerToLeetCode(answerParam, questionSlug, questionId, true);
```



### 使用submissionId获取判题结果🆗(需要登录)

> 登录过程在这个函数内部也会去调用，所以要使用这个函数不需要自行调用`loginToLeetCode()`，但是要记得这里需要配置了可用的账号密码才能进行。返回的判题结果是一个Json字符串，可以自行处理

```java
// 使用流水获取判题结果
String judgementResultJsonString = "";
judgementResultJsonString = leetCodeHelper.queryJudgementResult(submissionId, questionSlug).getBody();
```



## To-Do List

1. 获取题目模板