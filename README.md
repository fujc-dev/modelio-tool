#### 项目描述

```text
我们每次在使用Modelio绘制数据库表关系模型时，因为Modelio不支持数据库的导入，工作量大而且也没有技术含量，而且还费时费力。
通过反向分析*.XMI，了解其结构，我们可以查询数据库的表结构，生成Modelio的模型文件，然后导入Modelio中，将创建模型的冗余工作减少，提高效率。
```

#### 使用方法
````text
1. 运行java -jar lyrcsoft-modelio-backend-0.0.1-SNAPSHOT.jar
2. 选择【文件】-> 【数据源管理】 -> 【新增数据源】
3.返回到首页，刷新数据源列表，选择数据源，点击【生成Modelio】，会生成*.XMI文件
4.打开Modelio，新建Project，在新建Project下新建Package，选中Package，点击【Import/Export】，选择*.XMI文件导入模型
5.完毕。
````