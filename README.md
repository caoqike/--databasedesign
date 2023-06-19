# -这是数据库课设

## 更新说明

-- 2022/12/5

暂时使用文件夹src

package UseDruidConnection：通过Druid连接池连接数据库

package Entity：存放实体类


## GitHub保存项目

idea中，VCS -- GET FROM VERSION CONTROL 获取项目

同步分支：导入至idea中的可能只有主分支main，Git -- Fitch 更新分支，打开左下角的Git，此时Remote分支应该已经更新成功，右键New Branch From “...” 将远程分支同步至本地。

若出现连接超时错误，在File -- Setting -- GitHub 中设置超时连接时间

该项目的编译器是JDK1.8.0。在编译前须在Project Structure -- Project 中更改SDK，将language level设置为8，否则很可能报错找不到主类。

在编译前，须在Project Structure -- Module 中将src设置为资源文件
