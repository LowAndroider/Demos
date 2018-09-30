package com.exam.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author LowAndroider
 * @date 2018/9/30
 */
public class MvpCreator {

    private static final String MODULE_NAME = "app";
    private static final String NAME = "Login";
    private static final String BASE_PACKAGE = "com.exam";

    private static final String TEMPLATE_PATH = MODULE_NAME + "/src/main/java/com/exam/util/template";
    private static final String PACKAGE_NAME = BASE_PACKAGE + "." + NAME.toLowerCase();


    private static final String BASE_MVP = "com.util.mvp.*";

    private static final String CLASS_PATH = MODULE_NAME + "/src/main/java/" + PACKAGE_NAME.replace('.', '/');

    public static void main(String[] args) {
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("classPath", PACKAGE_NAME);
            dataMap.put("name", NAME);
            dataMap.put("baseMvpPath", BASE_MVP);
            dataMap.put("basePackage",BASE_PACKAGE);

            // step4 加载模版文件
            Template contract = configuration.getTemplate("MvpContractTemplate.ftl");

            Template activity = configuration.getTemplate("MvpActivityTemplate.ftl");

            Template presenter = configuration.getTemplate("MvpPresenterTemplate.ftl");

            Template layout = configuration.getTemplate("LayoutTemplate.ftl");

            // step5 生成数据
            File contractFile = new File(CLASS_PATH + "/contract" + File.separator + NAME + "Contract.kt");

            File activityFile = new File(CLASS_PATH + File.separator + NAME + "Activity.kt");

            File presenterFile = new File(CLASS_PATH + "/presenter" + File.separator + NAME + "Presenter.kt");

            File layoutFile = new File("src/main/res/layout" + File.separator + "activity_" + NAME.toLowerCase() + ".xml");

            // step6 输出文件
            if(!contractFile.exists()) {
                contractFile.getParentFile().mkdirs();
                contractFile.createNewFile();
            } else {
                System.out.println("文件已存在");
                return;
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(contractFile)));
            contract.process(dataMap, out);
            out.flush();

            if(!activityFile.exists()) {
                activityFile.getParentFile().mkdirs();
                activityFile.createNewFile();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(activityFile)));
            activity.process(dataMap, out);
            out.flush();

            if(!presenterFile.exists()) {
                presenterFile.getParentFile().mkdirs();
                presenterFile.createNewFile();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(presenterFile)));
            presenter.process(dataMap, out);
            out.flush();

            if(!layoutFile.exists()) {
                layoutFile.getParentFile().mkdirs();
                layoutFile.createNewFile();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(layoutFile)));
            layout.process(dataMap, out);
            out.flush();

            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^ 文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
