package com.framework.report;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.framework.utils.FileUtils;
import com.framework.utils.EnvironmentVersions;

/**
 * 测试报告初始化类
 *
 * @version 1.0
 *
 */

public class ExtentManager {
    
    private static ExtentReports extent;

    /**
     * 测试报告初始化，默认使用项目的 report 文件夹
     *
     * @return 测试报告
     */
    public static ExtentReports getInstance() {
    	if (extent == null)
    		createInstance("report/ibeiliao-client-report.html");
        return extent;
    }

    /**
     * 测试报告初始化，需要指定测试报告生成路径
     *
     * @param fileName 测试报告生成路径
     * @return 测试报告
     */
    public synchronized static ExtentReports createInstance(String fileName) {
    	FileUtils.createFile(fileName);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        //设置页面名称
        htmlReporter.config().setDocumentTitle("贝聊客户端测试报告");
        //设置测试报告名称
        htmlReporter.config().setReportName("贝聊客户端测试报告");
        //设置编码，utf-8支持中文
        htmlReporter.config().setEncoding("UTF-8");
        //设置图表的位置
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        //设置主题，如正常或黑暗
        htmlReporter.config().setTheme(Theme.STANDARD);
        //是否默认显示图表
        htmlReporter.config().setChartVisibilityOnOpen(true);
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);
        //获取系统信息
        Properties props = System.getProperties();
        extent.setSystemInfo("操作系统名称", props.getProperty("os.name"));
        extent.setSystemInfo("操作系统版本", props.getProperty("os.version"));
        extent.setSystemInfo("Java 版本", props.getProperty("java.version"));
        extent.setSystemInfo("用户名称", props.getProperty("user.name"));
        InetAddress s = null;
        try {
        	s = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		EnvironmentVersions version = new EnvironmentVersions();
        extent.setSystemInfo("主机名称", s.getHostName());
        extent.setSystemInfo("IP 地址", s.getHostAddress());
        extent.setSystemInfo("Node.js 版本", version.getNodeVersion());
        extent.setSystemInfo("Selenium Server 版本", version.getVersionForPOM("org.seleniumhq.selenium"));
        extent.setSystemInfo("Appium Server 版本", version.getAppiumVersion());
        extent.setSystemInfo("Appium Java-client 版本", version.getVersionForPOM("io.appium"));
        return extent;
	}
}