# HDFSTest
HDFS IO oprations.

---

## Install Hadoop
Please refer to [Install Hadoop on win10][1]

---

## HDFSTest
HDFS basic oprations such as list path information, create file, write file, read file and etc.

### How to run?
- Place HDFSTest on client
- Change uri to hadoop server
  ``` java
  // HDFS server's address
  String uri = "hdfs://localhost:9000/";
  ```
- Compile it
  ``` shell
  javac Test.java
  ```
- Run it
  ``` shell
  java Test
  ```

[1]: Install_hadoop_on_win10.md