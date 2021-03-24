
        import org.json.simple.JSONObject;

        import java.io.FileWriter;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.util.List;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;
        import java.util.stream.Collectors;
        import java.util.stream.Stream;

public class Count {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("C:\\Users\\40759\\Desktop\\ces\\bigtop");
        List<Path> paths = findByFileExtension(path, ".java");

        paths.forEach(x -> System.out.println(x));
        System.out.println( "Count :"
                +paths.size());

        FileWriter file = new FileWriter("C:\\Users\\40759\\Desktop\\ces\\output.json",true);
        //file.write("[");
        for(Path b : paths){
            Integer i = 0;
            JSONObject jsonfile = new JSONObject();


            List<String> allLines = Files.readAllLines(Paths.get(String.valueOf(b)));
            for (String line : allLines) {
                Pattern pattern = Pattern.compile("^public class");
                Matcher matcher= pattern.matcher(line);
                while(matcher.find()){
                    String match=matcher.group();
                    i=i+1;
                }
            }
            System.out.println(b);
            System.out.println(i);
            jsonfile.put("name","public class");
            jsonfile.put("category","number of classes");
            jsonfile.put("file",b);
            jsonfile.put("value",i);

            file.write(jsonfile.toJSONString());

            //file.write(",");




        }

        //file.write("]");


    }




    public static List<Path> findByFileExtension(Path path, String fileExtension)
            throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }
        List<Path> result;
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(Files::isRegularFile)   // is a file
                    .filter(p -> p.getFileName().toString().endsWith(fileExtension))
                    .collect(Collectors.toList());

        }


        return result;

    }


}


