## 叨叨IM解决方案

## 升级注意
v0.8.0 版本，对代码结构及部分实现机制进行了大量调整，变动如下：

1. 将```chat``` application module 拆分为两部分：```uikit``` library module 和 ```chat``` application module。```uikit```可以library的方式导入项目，里面包含了大量可重用的UI。
2. 移除```LayoutRes```、```SendLayoutRes```、```ReceiveLayoutRes```等注解，并更新```MessageViewHolder```等的实现机制

## 特别注意
1. ```com.android.tools.build:gradle:3.5.0``` 可能存在bug，会导致音视频crash，请勿使用此版本

叨叨IM是一套跨平台、核心功能开源的即时通讯解决方案，主要包含以下内容。

| 仓库                                                         | 说明                                                    | 备注 |
| ------------------------------------------------------------ | ------------------------------------------------------- | ---- |
| [android-chat](https://github.com/wildfirechat/android-chat) | 叨叨IM Android SDK源码和App源码                       |可以很方便地进行二次开发，或集成到现有应用当中      |
| [ios-chat](https://github.com/wildfirechat/ios-chat)         | 叨叨IM iOS SDK源码和App源码                            |可以很方便地进行二次开发，或集成到现有应用当中      |
| [pc-chat](https://github.com/wildfirechat/pc-chat)           | 基于[Electron](https://electronjs.org/)开发的PC平台应用 |      |
| [web-chat](https://github.com/wildfirechat/web-chat)          | Web平台的Demo, [体验地址](http://web.wildfirechat.cn)   |      |
| [wx-chat](https://github.com/wildfirechat/wx-chat)           | 微信小程序平台的Demo   |      |
| [server](https://github.com/wildfirechat/server)             | IM server                                               |      |
| [app server](https://github.com/wildfirechat/app_server)     | 应用服务端                                          |      |
| [robot_server](https://github.com/wildfirechat/robot_server) | 机器人服务端                                        |      |
| [push_server](https://github.com/wildfirechat/push_server)   | 推送服务器                                              |      |
| [docs](https://github.com/wildfirechat/docs)                 | 叨叨IM相关文档，包含设计、概念、开发、使用说明          |      | |


## 说明

本工程为叨叨IM Android App，开发过程中，充分考虑了二次开发和集成需求，可作为SDK集成到其他应用中，或者直接进行二次开发，详情可以阅读[docs](http://docs.wildfirechat.cn).


开发一套IM系统真的很艰辛，请路过的朋友们给点个star，支持我们坚持下去🙏🙏🙏🙏🙏

## 开发调试说明

我们采用最新稳定版Android Studio及对应的gradle进行开发，对于旧版本的IDE，我们没有测试，编译之类问题，需自行解决。

## 二次开发说明

叨叨IM采用bugly作为日志手机工具，大家二次开发时，务必将```MyApp.java```中的 ```bugly id``` 替换为你们自己的，否则错误日志都跑我们这儿来了，你们收集不到错误日志，我们也会受到干扰。

另外，如果可以请告知我们，我们会在案例参考把项目加上。

## 混淆说明
1. 确保所依赖的```lifecycle```版本在2.2.0或以上。
2. 参考```chat/proguard-rules.pro```进行配置。

## Android Support 说明

叨叨IM Android 客户端，基于```AndroidX```包开发，如果老项目采用的是```Android Support```包，可尝试采用[jetifier](https://developer.android.google.cn/studio/command-line/jetifier?hl=zh_cn)
转成```Android Support```软件包。

### 联系我们

> 商务合作请优先采用邮箱和我们联系。技术问题请到[叨叨IM论坛](http://bbs.wildfirechat.cn/)发帖交流。

1. heavyrain.lee  邮箱: heavyrain.lee@wildfirechat.cn  微信：wildfirechat
2. imndx  邮箱: imndx@wildfirechat.cn  微信：wfchat

### 问题交流

1. 如果大家发现bug，请在GitHub提issue
2. 其他问题，请到[叨叨IM论坛](http://bbs.wildfirechat.cn/)进行交流学习
3. 微信公众号

<img src="http://static.wildfirechat.cn/wx_wfc_qrcode.jpg" width = 50% height = 50% />

> 强烈建议关注我们的公众号。我们有新版本发布或者有重大更新会通过公众号通知大家，另外我们也会不定期的发布一些关于叨叨IM的技术介绍。

## 体验Demo
我们提供了体验demo，请使用微信扫码下载安装体验

![叨叨IM](http://static.wildfirechat.cn/download_qrcode.png)

## 应用截图
![ios-demo1](http://static.wildfirechat.cn/android-deomo1.gif)

![ios-demo2](http://static.wildfirechat.cn/android-deomo1.gif)

<img src="http://static.wildfirechat.cn/android-view1.png" width = 50% height = 50% />

<img src="http://static.wildfirechat.cn/android-view2.png" width = 50% height = 50% />

<img src="http://static.wildfirechat.cn/android-view3.png" width = 50% height = 50% />

<img src="http://static.wildfirechat.cn/android-view4.png" width = 50% height = 50% />

<img src="http://static.wildfirechat.cn/android-view5.png" width = 50% height = 50% />

<img src="http://static.wildfirechat.cn/android-view6.png" width = 50% height = 50% />

<img src="http://static.wildfirechat.cn/android-view7.png" width = 50% height = 50% />

<img src="http://static.wildfirechat.cn/android-view8.png" width = 50% height = 50% />

<img src="http://static.wildfirechat.cn/android-view9.png" width = 50% height = 50% />

<img src="http://static.wildfirechat.cn/android-view10.png" width = 50% height = 50% />

<img src="http://static.wildfirechat.cn/android-view11.png" width = 50% height = 50% />


## 集成
1. client部分，自行下载代码，并将client module引入你们自己的项目。
2. uikit部分，自行下载代码，并将uikit module引入你们自己的项目。
3. push部分，自行下载代码，将push module引入你们自己的项目。

## 贡献
欢迎提交pull request，一起打造一个更好的开源IM。

## 鸣谢
1. [LQRWeChat](https://github.com/GitLqr/LQRWeChat) 本项目中图片选择器、表情基于此开发
2. [butterKnife](https://github.com/JakeWharton/butterknife)
3. OKHttp等一些其他优秀的开源项目
4. 本工程使用的Icon全部来源于[icons8](https://icons8.com)，对他们表示感谢。
5. Gif动态图来源于网络，对网友的制作表示感谢。

如果有什么地方侵犯了您的权益，请联系我们删除🙏🙏🙏

## 案例参考

todo

## License

1. Under the MIT license. See the [LICENSE](https://github.com/wildfirechat/mars/blob/firechat/LICENSE) file for details.
2. Under the 996ICU License. See the [LICENSE](https://github.com/996icu/996.ICU/blob/master/LICENSE) file for details.
