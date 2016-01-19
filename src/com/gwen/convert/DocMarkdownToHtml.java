package com.gwen.convert;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

public class DocMarkdownToHtml {

	private String markDownFilename;
	private OutputStream outputStream;

	public static void main(String[] args) throws IOException {

		DocMarkdownToHtml docMarkdownToHtml = new DocMarkdownToHtml();
		docMarkdownToHtml.setMarkdownFilename("./dsl.md");
		docMarkdownToHtml.setOutfile(System.out);

		docMarkdownToHtml.processHtml();
	}

	private void setMarkdownFilename(String markDownFilename) {
		this.markDownFilename = markDownFilename;
	}

	private void setOutfile(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	private void processHtml() throws IOException {
		List<String> list = Files.readAllLines(
				new File(this.markDownFilename).toPath(),
				Charset.defaultCharset());

		List<MarkdownItem> markdownItems = convertLinesToMarkdown(list);

		HtmlWrapper htmlWrapper = new HtmlWrapper();
		
		outputStream.write((htmlWrapper.formatHead() + "\n").getBytes());
		for (int ii=0;ii<markdownItems.size(); ii++) {
			MarkdownItem md = markdownItems.get(ii);
			outputStream.write(((htmlWrapper.formatPanel(md, ii)) + "\n").getBytes());
		}
		outputStream.write((htmlWrapper.formatBottom() + "\n").getBytes());
	}

	private List<MarkdownItem> convertLinesToMarkdown(List<String> lineItems) {

		boolean started = false;

		List markdownListItems = new LinkedList<MarkdownItem>();
		for (String item : lineItems) {

			item = item.trim().substring(1);
			item = item.substring(0, item.length() - 1).trim();

			String[] tokens = item.split(" \\| ");

			if (!started && tokens[0].contains("----")) {
				started = true;
				continue;
			}
			if (started) {
				MarkdownItem markdownItem = new MarkdownItem();
				markdownItem.setStep(tokens[0]);
				markdownItem.setDescription(tokens[1]);
				if ((tokens[1].endsWith(" |")) && (tokens.length == 2)) {
					markdownItem.setParameters("-");
				} else {
					markdownItem.setParameters(tokens[2]);
				}
				markdownListItems.add(markdownItem);
			}
		}

		return markdownListItems;
	}

}
