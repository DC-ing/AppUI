# Appium-MacOS 环境搭建

brew 是 MacOS 的一个强大的应用安装管理工具，很多软件和工具只需要通过一个命令即可完成安装，无需配置环境变量，非常方便。在 mac 的终端软件上输入以下命令即可完成安装 brew：

```
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

本篇文章基于 MacOS 10.12.6 系统环境搭建，使用的 shell 脚本为 bash，安装路径最好不要有带有中文字符，以免出现一些奇怪的错误。

## 1. 安装 Java-Mac OS X 最新版本
先安装 Apple 提供的 Java 6 runtime 版本，才能驱动 Java 7，Java 8 <br>
Apple 官方的 Java 最新版本可到这里下载：https://support.apple.com/kb/DL1572 <br>
Java 8 最新版本可到这里下载：http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

以上2步均成功安装后，在配置文件添加 `JAVA_HOME`，在终端使用 `vim ~/.bash_profile`，即可打开该用户当前的配置文件。
填写

```
export JAVA_HOME=$(/usr/libexec/java_home)
export PATH=$PATH:$JAVA_HOME/bin
```

即可完成配置，可不用受到以后升级 Java 版本，而重新配置 `JAVA_HOME` 的困扰。


## 2. 安装 android-sdk
以下2种方式选择任意一种即可完成安装 android-sdk

### 2.1 使用 brew 安装
使用命令`brew cask install android-sdk`安装，直接安装到`/usr/local/Cellar/android-sdk`,且环境配置也自动安装好。

### 2.2 使用 google 官方提供的压缩包
android-sdk 的压缩包可以到这里下载 [android-sdk_r24.4.macosx.zip](https://dl.google.com/android/android-sdk_r24.4.1-macosx.zip) <br>
下载完后，解压到指定的目录，将该环境变量加入到 `~/.bash_profile`中即可。

```
export ANDROID_HOME=/your/android-sdk/path
export PATH=${PATH}:${ANDROID_HOME}/tools
export PATH=${PATH}:${ANDROID_HOME}/platform-tools
export PATH=${PATH}:${ANDROID_HOME}/build-tools/25.0.2
```


## 3. 安装 Node.JS
终端，执行命令`brew install node`安装即可，如需安装指定版本，将 node 替换成 node@6 即可
检查 node 是否安装完成，指定命令，`node -v`,输出结果有版本信息，则 node 安装正常


## 4. 安装 iOS 测试环境
为了可以正常操作设备，需安装`libimobiledevice`库<br>
`brew install libimobiledevice --HEAD  # 最新的更新版本`<br>
`brew install ideviceinstaller         # 仅支持 iOS 9，如需支持 iOS 10，使用第一个命令`

由于Appium 测试 ios，使用的是 facebook 的 WebDriverAgent 框架，需安装以下依赖库<br>
`brew install carthage`

由于`ideviceinstaller`未支持 iOS 10，需安装 ios-deploy<br>
`npm install -g ios-deploy`

测试真机时，需要从 xcode 获取更新的日志信息，需安装<br>
`gem install xcpretty`


## 5. 安装 appium-doctor
终端，执行命令`npm install -g appium-doctor`安装即可，终端上，输入`appium-doctor`检查环境配置情况，如全部打勾，即为通过。


## 6. 安装 Appium
安装使用`npm install -g appium --no-shrinkwrap`，安装最新版本的 Appium，各模块也是最新的，ios 测试使用 usb 协议，可正常运行。
安装完成后，执行命令`appium -v`，输出版本信息，即安装成功。


## 7. 安装 IDE 开发环境
可使用 Eclipse 和 IDEA，看个人喜好<br>
  Eclipse需另外安装 Android 插件（ADT23.0.7）,点击这里下载：https://dl.google.com/android/ADT-23.0.7.zip <br>
  IDEA在安装过程中，勾选 Android 插件，即可完成安装，无须另外再安装。<br>


## 8. Maven
maven 可使用 IDE 自带的，也可以使用自己下载的。

### 8.1 IDE 自带的 maven
啥都不用做，安装 IDE 即完成 maven 的安装

### 8.2 自己安装的 maven
到官网下载最新版的 Maven 压缩包，将其解压到指定的文件夹，在环境变量文件 `~/.bash_profile` 加入 Maven 的环境变量即可。

```
export MAVEN_HOME=/your/apache-maven/path
export PATH=${PATH}:${MAVEN_HOME}/bin
```



