package com.stackoverflow.albanoj2.xmltojson;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Main {

	private static int SPACES_PER_INDENT = 4;

	public static void main(String[] args) throws Exception {

		try {
			// Citation: https://stackoverflow.com/questions/1823264/quickest-way-to-convert-xml-to-json-in-java
			URI file = Main.class.getClassLoader().getResource("docs.xml").toURI();
			String xmlContents = readFromInputStream(file);
			JSONObject jsonContents = XML.toJSONObject(xmlContents);
			String jsonString = jsonContents.toString(SPACES_PER_INDENT);
			System.out.println(jsonString);
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private static String readFromInputStream(URI uri) throws IOException {
		// Citation: http://www.baeldung.com/reading-file-in-java
		Path path = Paths.get(uri);
		StringBuilder data = new StringBuilder();
		
		Stream<String> lines = Files.lines(path);
		lines.forEach(line -> data.append(line).append("\n"));
		lines.close();
		
		return data.toString();
	}
}
