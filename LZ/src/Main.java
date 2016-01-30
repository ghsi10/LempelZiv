import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main{
	public static void main(String[] args) throws IOException {
		String str = readFile("text.txt", Charset.defaultCharset());
		LempelZiv lz = new LempelZiv();
		lz.encode(str);
		System.out.println("Compress: "+((1-((lz.getPairs().length*2.0)/str.length()))*100)+"%");
	}
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}