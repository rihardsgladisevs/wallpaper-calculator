package com.rgladisevs.wallpaper.calculator.room;

import com.rgladisevs.wallpaper.calculator.FileReadException;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class RoomFileLoader {

    private String inputFilePath;
    private String roomDimensionDelimiter;

    public List<Room> load() {
        try (Stream<String> stream = getLinesOfFile()) {
            return stream
                    .map(line -> RoomParserUtils.parseFromString(line, this.roomDimensionDelimiter))
                    .collect(Collectors.toList());
        }
    }

    private Stream<String> getLinesOfFile() {
        try {
            URL url = getClass().getClassLoader().getResource(this.inputFilePath);
            if (url == null) {
                String message = MessageFormat.format("File does not exist by path {0}", this.inputFilePath);
                throw new FileReadException(message);
            }
            URI uri = url.toURI();
            Path path = getPath(uri);
            return Files.lines(path, StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            String message = MessageFormat.format("Can't read file by path {0}", this.inputFilePath);
            throw new FileReadException(message, e);
        }
    }

    /**
     * Kind of hack to handle both filesystems
     * - when executing compiled jar
     * - when executing class directly
     *
     * @param uri - can be both inside of jar or outside
     * @return Path for URI
     * @throws IOException
     */
    private Path getPath(URI uri) throws IOException {
        String[] array = uri.toString().split("!");
        if (array.length == 1) {
            return Path.of(uri);
        } else {
            Map<String, String> env = new HashMap<>();
            env.put("create", "true");
            FileSystem fileSystem = FileSystems.newFileSystem(URI.create(array[0]), env);
            return fileSystem.getPath(array[1]);
        }
    }
}
