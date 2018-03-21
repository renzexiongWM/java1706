package com.qianfeng;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2018/3/21.
 */
public class ActivitiTest {

      @Test
      public  void testCase1() {

//              //加载配置文件
//              ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
//              //创建流程引擎
//              ProcessEngine processEngine = configuration.buildProcessEngine();
          ProcessEngineConfiguration process = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
          ProcessEngine processEngine = process.buildProcessEngine();

      }
    /**
     * 发布流程（将绘制的BPMN图部署到数据库中）
     */
    @Test
    public void testCase2(){
        //获取默认的流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取流程部署服务
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //流程部署
        Deployment deployment = repositoryService.createDeployment()
                .name("hell")
                //指定流程图
                .addClasspathResource("hello.bpmn")
                //部署
                .deploy();
        String key = deployment.getKey();
        String name = deployment.getName();
        System.out.println(key);
        System.out.println(name);

    }

    /**
     * 启动流程
     */
    @Test
  public  void  test3(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//        RuntimeSerice是用来的启动流程
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
//         启动流程，参数是bpmn文件的ID
        runtimeService.startProcessInstanceByKey("myProcess_1");


    }
    /*
      查询用户名下的任务

     */
    @Test
     public  void test4(){
        ProcessEngine defaultE = ProcessEngines.getDefaultProcessEngine();
//         TaskService  是用来操作任务的服务
        TaskService taskService = defaultE.getTaskService();
//        查询任务的工具类
        TaskQuery taskQuery = taskService.createTaskQuery();
//        指定要查询哪一个代理人的任务 参数：代理人的名称
        taskQuery.taskAssignee("张三");
        List<Task> list = taskQuery.list();
        for (Task task:list
             ) {
            System.out.println("ID:"+task.getId());
            System.out.println("任务名称:"+task.getName());
            System.out.println("分类:"+task.getCategory());
            System.out.println("受理人:"+task.getAssignee());
        }

    }
    @Test
    public  void  test5(){
        ProcessEngines.getDefaultProcessEngine()
                .getTaskService()
                .complete("5005");
    }
}
