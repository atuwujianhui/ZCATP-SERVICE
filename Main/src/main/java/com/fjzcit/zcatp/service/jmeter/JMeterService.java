package com.fjzcit.zcatp.service.jmeter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.fjzcit.zcatp.common.constant.JMeterConstant;
import org.apache.jmeter.assertions.ResponseAssertion;
import org.apache.jmeter.assertions.gui.AssertionGui;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.protocol.java.control.gui.JavaTestSamplerGui;
import org.apache.jmeter.protocol.java.sampler.JavaSampler;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.samplers.SampleSaveConfiguration;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jmeter.visualizers.ViewResultsFullVisualizer;
import org.apache.jorphan.collections.HashTree;

public class JMeterService {
    public static HashTree testPlanTree;
    // public static List<Object> listLeaf = new LinkedList<Object>();
    public static HashTree javaSamplerHashTree = new HashTree();
    /*****

    *****添加没有assertion的java Sampler
    public static void addJavaSampler(String nameOfJavaSampler) {
        JavaSampler javaSampler = new JavaSampler();
        javaSampler.setName(nameOfJavaSampler);
        javaSampler.setClassname("com.mycompany.MySampler");  //这里的Java Sampler要另外写代码实现
        Arguments arguments = new Arguments();
        arguments.addArgument("something","somevalue");
        arguments.addArgument("otherthing", "othervalue"));
        javaSampler.setArguments(arguments);
        javaSampler.setProperty(TestElement.TEST_CLASS, JavaSampler.class.getName());
        javaSampler.setProperty(TestElement.GUI_CLASS, JavaTestSamplerGui.class.getName());
        listLeaf.add(javaSampler);
    }
     ****/
    public void addJavaSamplerWithAssertion(String nameOfJavaSampler, String responseField,
                                            String responsePatternType, String testStr) {
        ResponseAssertion responseAssertion = new ResponseAssertion();
        responseAssertion.setProperty(TestElement.TEST_CLASS, ResponseAssertion.class.getName());
        responseAssertion.setProperty(TestElement.GUI_CLASS, AssertionGui.class.getName());
        responseAssertion.setName("Response Assertion");
        responseAssertion.setEnabled(true);
        switch (responseField) {
            case "TEXT":
                responseAssertion.setTestFieldResponseData();
                break;
            case "DOCUMENT":
                responseAssertion.setTestFieldResponseDataAsDocument();
                break;
            case "URL":
                responseAssertion.setTestFieldURL();
                break;
            case "RESPONSE_CODE":
                responseAssertion.setTestFieldResponseCode();
                break;
            case "RESPONSE_MESSAGE":
                responseAssertion.setTestFieldResponseMessage();
                break;
            case "RESPONSE_HEADERS":
                responseAssertion.setTestFieldResponseHeaders();
                break;
        }
        responseAssertion.setAssumeSuccess(true);
        switch (responsePatternType) {
            case "CONTAINS":
                responseAssertion.setToContainsType();
                break;
            case "MATCHES":
                responseAssertion.setToMatchType();
                break;
            case "EQUALS":
                responseAssertion.setToEqualsType();
                break;
            case "SUBSTRING":
                responseAssertion.setToSubstringType();
                break;
        }
        // responseAssertion.setToNotType();
        responseAssertion.addTestString(testStr);

        JavaSampler javaSampler = new JavaSampler();
        javaSampler.setName(nameOfJavaSampler);
        javaSampler.setClassname("com.mycompany.MySampler");
        Arguments arguments = new Arguments();
        arguments.addArgument("something","somevalue");
        arguments.addArgument("otherthing", "othervalue");
        javaSampler.setArguments(arguments);

        javaSampler.setProperty(TestElement.TEST_CLASS, JavaSampler.class.getName());
        javaSampler.setProperty(TestElement.GUI_CLASS, JavaTestSamplerGui.class.getName());

        javaSamplerHashTree.add(javaSampler).add(responseAssertion);
    }

    /**
     * 生成JMX脚本
     */
    public static void genericScript() {
        // 文件分隔符
        String slash = System.getProperty("file.separator");
        // 根据“/resources/jmeter/bin/”中的JMeter配置信息初始化JMeter脚本生成环境
        File jmeterHome = new File(JMeterService.class.getClassLoader().getResource("").getPath() + slash + "jmeter");
        System.out.println(JMeterService.class.getClassLoader().getResource("jmeter/bin/jmeter.properties").getPath());
        System.out.println(jmeterHome.getPath());
        // 获取JMeter属性信息
        File jmeterProperties = new File(JMeterService.class.getClassLoader().getResource("jmeter/bin/jmeter.properties").getPath());
        JMeterUtils.loadJMeterProperties(jmeterProperties.getPath());
        JMeterUtils.setJMeterHome(jmeterHome.getPath());
        JMeterUtils.initLocale();

        // 定义测试脚本结构对象
        HashTree testPlanTree = new HashTree();

        /**
         * 线程组循环次数控制
         */
        TestElement loopController = new LoopController();
        ((LoopController) loopController).setLoops(3);
        ((LoopController) loopController).setFirst(true);
        loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
        loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
        ((LoopController) loopController).initialize();

        /**
         * 创建执行计划
         */
        TestPlan testPlan = new TestPlan("执行计划 - Java创建JMeter脚本");
        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
        testPlan.setUserDefinedVariables((Arguments) new ArgumentsPanel().createTestElement());

        /**
         * 创建线程组
         */
        ThreadGroup threadGroup = new ThreadGroup();
        // 名称
        threadGroup.setName("线程组");
        // 线程数
        threadGroup.setNumThreads(10);
        // 发起时间周期
        threadGroup.setRampUp(1);
        // 设置循环次数
        threadGroup.setSamplerController(((LoopController) loopController));
        threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
        threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());

        /**
         * 创建查看结果树
         */
        SampleSaveConfiguration ssc = new SampleSaveConfiguration();
        ResultCollector resultCollector = new ResultCollector();
        resultCollector.setName("查看结果树");
        resultCollector.setErrorLogging(false);
        resultCollector.setSaveConfig(ssc);
        resultCollector.setProperty(TestElement.TEST_CLASS, ResultCollector.class.getName());
        resultCollector.setProperty(TestElement.GUI_CLASS, ViewResultsFullVisualizer.class.getName());

        /**
         * 创建执行计划数
         */
        // 添加执行计划、线程组
        HashTree threadGroupHashTree = testPlanTree.add(testPlan).add(threadGroup);
        // 添加查看结果树
        threadGroupHashTree.add(resultCollector);
        threadGroupHashTree.add(javaSamplerHashTree);

        // 生成jmx脚本
        try {
            SaveService.saveTree(testPlanTree,
                    new FileOutputStream(JMeterConstant.JMX_FOLDER + slash + JMeterConstant.JMETER_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("finish generating jmx file:" + JMeterConstant.JMX_FOLDER + slash + JMeterConstant.JMETER_FILE);
    }

    public static void main(String[] args) {
//        File path = null;
//        try {
//            path = new File(ResourceUtils.getURL("classpath:").getPath());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        if(!path.exists()) path = new File("");
//        System.out.println("path:"+path.getAbsolutePath());
//        System.out.println(System.getProperty("user.dir"));
        JMeterService.genericScript();
    }
}