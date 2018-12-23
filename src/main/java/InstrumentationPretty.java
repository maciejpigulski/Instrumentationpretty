import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * This Java source file was auto generated by running 'gradle buildInit --type java-library'
 * by 'james' at '12/16/18 7:41 PM' with Gradle 2.11
 *
 * @author james, @date 12/16/18 7:41 PM
 */
public class InstrumentationPretty {

    private String filePath;

    public InstrumentationPretty(String filePath){
        this.filePath = filePath;
    }

    public void processInsturmentationOutput() throws IOException{
        //create test listener and parser
        XmlTestRunListener testListener = new XmlTestRunListener();
        InstrumentationResultParser parser = new InstrumentationResultParser("DeviceFarm Test", testListener);
        
        File reportDir = new File(System.getProperty("user.dir") + "/reports");
        reportDir.mkdir();
        testListener.setReportDir(reportDir);
        
        //read in instrumentation output file
        File file = new File(this.filePath); 
  
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        
        List<String> lines = new ArrayList<String>(); 
        String line;
        while ((line = br.readLine()) != null){
           lines.add(line);
        } 
        System.out.println("printing lines: ");
        System.out.println(Arrays.toString(lines.toArray(new String[0])));
        parser.processNewLines(lines.toArray(new String[0]));

        System.out.println("calling done method to write files");
        parser.done();
        
        // testListener.createOutputResultStream(reportDir);
        // testListener.testRunEnded(elapsedTime, runMetrics);
    }

    public static void main(String args[]){
        System.out.println(Arrays.toString(args));
        try {
            new InstrumentationPretty(args[0]).processInsturmentationOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
