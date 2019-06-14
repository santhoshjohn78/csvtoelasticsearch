package ehss.elasticsearch.index.util;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CSVIndexer {

    public static final String DEFAULTINDEXNAME = "csv2esindex";
    public static final String DEFAULTINDEXTYPE = "csv2esindextype";

    @Autowired
    Indexer indexer;

    public void indexFile(String fileName) throws Exception {
        CSVReader reader = null;
        File file = new File(fileName);
        if (file.exists()==false){
            file = new File(getClass().getClassLoader().getResource(fileName).getFile());
        }
        reader = new CSVReader(new FileReader(file));
        String absFileName = file.getName();
        String[] indexattributes = absFileName.split("_");
        String indexName;
        String indexType;
        String indexId;

        // filenames need to be of the format indexname_type_id

        if (indexattributes != null && indexattributes.length == 3) {
            indexName = indexattributes[0];
            indexType = indexattributes[1];
            indexId = indexattributes[2].replace(".csv", "");
        } else if (indexattributes != null && indexattributes.length == 2) {
            indexName = indexattributes[0];
            indexType = indexattributes[0];
            indexId = indexattributes[1];
        } else if (indexattributes != null && indexattributes.length == 1) {
            indexName = indexattributes[0];
            indexType = indexattributes[0];
            indexId = "";
        } else {
            indexName = DEFAULTINDEXNAME;
            indexType = DEFAULTINDEXTYPE;
            indexId = "";
        }

        String[] line;

        String[] colNames = null;
        while ((line = reader.readNext()) != null) {
            Map<String, Object> jsonMap = new HashMap<>();
            if (colNames == null) {
                colNames = line;
                continue;
            }
            // skip the first line as it is considered columns headers.
            for (int colidx = 0; colidx < line.length; colidx++) {
                if (colidx < colNames.length) {
                    jsonMap.put(colNames[colidx], line[colidx]);
                }
                // the lines which have more columns than the number of col headers are skipped.

            }
            if (jsonMap.isEmpty()==false) {
                indexId = "" + Calendar.getInstance().getTimeInMillis();
                indexer.index(indexName, indexType, indexId, jsonMap);
            }
        }

    }
}
