package application;

import algorithms.TabularAlgorithm;
import datatypes.Tabular;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("/analysetabular")
    public Tabular[] analyseTabular(
            @RequestParam("input") String input,
            @RequestParam("separator") String separator,
            @RequestParam("qis") String QIs
    ) throws Exception {
        List<String> listQIs = Arrays.asList(QIs.split(separator));

        return TabularAlgorithm.analyse(input, separator, listQIs);
    }

    @PostMapping("/testresponse")
    public String testResponse() throws Exception {
        return new String(Files.readAllBytes(Paths.get("./sampledata/test-response.txt")));
    }

    @PostMapping("/anonymise")
    public String anonymise(
            @RequestParam("input") String input,
            @RequestParam("output") String output,
            @RequestParam("separator") String separator,
            @RequestParam("qis") String QIs,
            @RequestParam("k") String k
    ) throws Exception {
        List<String> listQIs = Arrays.asList(QIs.split(separator));

        Collections.shuffle(listQIs);

        Map<Integer, String> anonymisedDataset = algorithms.Anonymise.anonymise(input, separator, listQIs, Integer.parseInt(k));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8));

        bw.write(QIs + "\n");

        for (String row : anonymisedDataset.values()) {
            bw.write(row + "\n");
        }

        bw.close();

        return "K-Anonymisation Completed";
    }
}
