# AppUI 自动化框架入门使用03 - 代码测试用例的编写

## 3 代码测试用例的编写
### 3.1 构建 Java-maven 工程
#### 3.1.1 导入提供的 jar 包。
一般情况下，会提供一些稳定版本的 jar 包，包含了框架本身依赖的 jar 包
    
```
AppUI-x.x.x-jar-with-dependencies.jar
    
```
    
#### 3.1.2 编译框架的源码
这种方式，需要下载框架的源码，使用 `maven install` 命令将源码编译并通过。这时候，框架的依赖 jar 包和 框架的 jar 包也自动放到本机的 maven 仓库中。在工程的 `pom.xml`文件，加上依赖即可。

```
<dependency>
   <groupId>com.DC</groupId>
   <artifactId>AppUI</artifactId>
   <version>${version.you.require}</version>
</dependency>
```
    

### 3.2 新建一个关键字页面类，定义测试的 sheet 名（在编写 Excel 测试用例文档-测试场景的【测试的 sheet】值）
关键字类，需要继承框架中的 KeyWord 类（Android 继承 AndroidKeyWord，iOS 继承 IOSKeyWord）

    ```
    import com.appium.keyword.AndroidKeyWord;
    import com.appium.locator.AppLocator;
    import io.appium.java_client.MobileElement;
    
    public class LoginKeyWord extends AndroidKeyWord {
    
        public LoginKeyWord(AppLocator<MobileElement> locator) {
            this.locator = locator;
            caseSheet = "sheetName";
        }
    }
    ```

3. 编写测试类，测试刚添加的 LoginKeyWord。编写完成后，右键运行即可。
    
    ```
    import com.appium.keyword.Engine_Excel;
    import com.appium.locator.AndroidLocator;
    import com.appium.locator.InitLocator;
    import com.framework.utils.ExcelUtils;
    
    import io.appium.java_client.MobileElement;
    
    import org.testng.annotations.Test;
    import org.testng.annotations.AfterTest;
    import org.testng.annotations.BeforeTest;
    
    import java.io.IOException;
    
    public class LoginKeyWordTest {
    
    
        protected static AndroidLocator<MobileElement> locator;
        protected ExcelUtils excelUtils;
        protected static Engine_Excel engine;
    
        @BeforeTest
        public void beforeTest() throws IOException {
            String excelPath = "/your/excel/path";
            excelUtils = new ExcelUtils(excelPath);
            engine = new Engine_Excel(excelPath);
            locator = new GetLocator().getAndroidLocator(excelUtils.getAndroidDeviceTestConfig(deviceNum));
        }
    
        @AfterTest
        public void afterTest() {
            locator.quit();
        }
    
        @Test
        public void test_login() {
            engine.runTest(new LoginKeyWord(locator));
        }
    }
    
    ```

