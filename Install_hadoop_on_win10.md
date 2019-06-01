# Install Hadoop on win10

---

## Install Hadoop
1. Download ```Hadoop-3.0.0.tar.gz``` from [Offical website][1]
2. Download correction code [winutils][2] for Hadoop on Windows
3. Install JDK1.8(use ```java -version``` to show jdk version)
4. Decompression ```Hadoop-3.0.0.tar.gz``` to path/Hadoop, path can be any path on your computer
5. Set a new environment variable ```HADOOP_HOME``` whose value is your Hadoop path
6. Add ```path\Hadoop\bin``` and ```path\Hadoop\sbin``` to environment variable ```Path```
7. Change file ```path\Hadoop\etc\hadoop\core-site.xml```
    ``` xml
    <configuration>
        <property>
            <name>fs.defaultFS</name>
            <value>hdfs://localhost:9000</value>
        </property>
    </configuration>
    ```
8. Change file ```path\Hadoop\etc\hadoop\mapred-site.xml```
    ``` xml
    <configuration>
        <property>
            <name>mapreduce.framework.name</name>
            <value>yarn</value>
        </property>
    </configuration>
    ```
9. Create directory ```data``` in ```path\Hadoop```
10. Create directory ```namenode``` in ```path\Hadoop\data```
11. Create directory ```datanode``` in ```path\Hadoop\data```
12. Change file ```path\Hadoop\etc\hadoop\hdfs-site.xml```
    ``` xml
    <configuration>
        <property>
            <name>dfs.replication</name>
            <value>1</value>
        </property>
        <property>
            <name>dfs.namenode.name.dir</name>
            <value>path to namenode</value>
        </property>
        <property>
            <name>dfs.datanode.data.dir</name>
            <value>path to datanode</value>
        </property>
    </configuration>
    ```
13. Change file ```path\Hadoop\etc\hadoop\yarn-site.xml```
    ``` xml
    <configuration>
        <property>
            <name>yarn.nodemanager.aux-services</name>
            <value>mapreduce_shuffle</value>
        </property>
        <property>
            <name>yarn.nodemanager.auxservices.mapreduce.shuffle.class</name>  
            <value>org.apache.hadoop.mapred.ShuffleHandler</value>
        </property>
    </configuration>
    ```
14. Change file ```path\Hadoop\etc\hadoop\hadoop-env.cmd```
    Find ```set JAVA_HOME=%JAVA_HOME%```, if your ```JAVA_HOME``` contains spaces, use [8.3 filename][3] to replace original value. For example, my ```JAVA_HOME = C:\Program Files\Java\jdk1.8.0_192```, write ```set JAVA_HOME=C:\PROGRA~1\Java\jdk1.8.0_192```
15. Remove ```path\Hadoop\bin```
16. Copy files download in step 2 into ```path\Hadoop\bin```

---

## Test Hadoop
1. Run ```hdfs namenode -format``` to farmat hdfs
2. CD into ```path\Hadoop\sbin```, run ```start-dfs``` to start HDFS. As a sign of success, a ```hdfs namenode``` process and a ```hdfs datanode``` process will pop up
3. Open [http://localhost:9870](http://localhost:9870) to view HDFS's backstage
4. Run commands
   - ```hadoop fs -ls /```: list path "/"
   - ```hadoop fs -mkdir /test```: create directory ```/test```
   - ```hadoop fs -rm /test1.txt```: remove file ```test1.txt```
   - ```hadoop fs -rm -r /test```: remove directory ```/test```
   - ```hadoop fs -put file /test```: upload file to HDFS ```/test```
   - ```hadoop fs -cat /test/test.txt```: view file content
   - ```hadoop fs -cp URI[URI...] <dest>```: copy file
   - ```hadoop fs -get <src> <localdst>```: download file to local
   - ```hadoop fs -mv URI[URI...] <dest>```: move file
   - ```hadoop fs -du URI[URI...]```: show file's size
5. CD into ```path\Hadoop\sbin```, run ```stop-dfs``` to stop HDFS

[1]: http://archive.apache.org/dist/hadoop/core/
[2]: https://github.com/steveloughran/winutils
[3]: https://en.wikipedia.org/wiki/8.3_filename