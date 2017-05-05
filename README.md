# Component_developmen_Application
android组件开发


<b><h1>一.项目module功能介绍：</h1></b>
  
  1.common_lib   
          
      各功能业务要使用到的base类（如Activity,Fragment,BaseAdapter等）；
  
  2.business_intent 
  
      各业务activity跳转路由；
  
  3.common_business  
        
      各业务公用的模块，比如业务A和业务B（以下简称A，B）A和B都要使用的model,资源，fragment，activity等的都放这个里面；
  
  4.business_dev_test  
  
      单独调试各业务功能，启动宿主app;
  
  5.business_dev  
        
      各业务module单独放到该文件下开发（以防业务太多影响根目录结构）；
  
            A.business_demo1  业务事例一；
     
            B.business_demo2  业务事例二；
      
  6.main_app  
  
       最后各业务合并打包主app；
  
<b><h1>二.本项目希望以后实现组件开发，各组件可以单独打包成Apk和main_app宿主一起发布,做到线上热修复；</h1></b>

    （希望有志之士一起开发，项目将一直保持开源）


  
  
