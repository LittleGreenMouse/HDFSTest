import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class Test {

    public static void main(String[] args) {
        // HDFS server's address
        String uri = "hdfs://localhost:9000/";

        // Default configuration
        Configuration config = new Configuration();


        try {
            // Get filesystem
            FileSystem fs = FileSystem.get(URI.create(uri), config);

            // List information of path(hadoop fs -ls path)
            Path path = new Path("/");
            FileStatus[] statuses = fs.listStatus(path);
            for(FileStatus status : statuses) {
                System.out.println(status);
            }

            // Create file and get output stream
            FSDataOutputStream os = fs.create(new Path("/test/test.log"));
            // Write file
            os.write("Hello World!".getBytes());
            os.flush();
            os.close();

            // Open file and get input stream
            FSDataInputStream is = fs.open(new Path("/test/test.log"));
            byte[] buffer = new byte[1024];
            int len = is.read(0, buffer, 0, 1024);
            System.out.println(new String(buffer, 0, len));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
