import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*; 
import java.util.*;

import javax.swing.event.*;

// For tags
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.text.Style;

// BONUS: headers

public class WebPanel extends JPanel { // Has everything to do with visualizing the web page

	private JTextPane tp; // A JEditorPane can render HTML itself, but is not used in this assignment.
	private JScrollPane sp;
	
	private ArrayList<String> tagList;
	private ArrayList<String> commentTagList;
	
	public WebPanel() {
		setLayout(new BorderLayout());
		tp = new JTextPane();
		tp.setEditable(false);
		sp = new JScrollPane(tp);
		add(sp);
		tagList = new ArrayList<>();
		commentTagList = new ArrayList<>();
	}
	
	public void clearTa() {
		tp.setText(null);
		tagList.clear();
		commentTagList.clear();
	}
	
	public void createWebPage(String webPageText) {
		setNewTitle(webPageText);
		webPageText = getBodyText(webPageText);
		webPageText = removeScriptContent(webPageText); // The syntax of javascript will affect tag collection
		collectTags(webPageText);
		
		tp.setText(webPageText);
		formatTags();
	}
	
	public void setNewTitle(String webPageText) {
		String webPageTitle;
		if (webPageText.contains("<title>")) {
			webPageTitle = webPageText.substring(webPageText.indexOf("<title>") + 7,
				webPageText.indexOf("</title>"));
			Browser.bf.setTitle(webPageTitle);
		}
		else {
			return;
		}
	}
	
	public String getBodyText(String webPageText) {
		if (webPageText.contains("<body>")) {
			webPageText = webPageText.substring(webPageText.indexOf("<body>") + 6, 
				webPageText.indexOf("</body>"));
				
			return webPageText;
		}
		else {
			return webPageText;
		}
	}
	
	public String removeScriptContent(String webPageText) {
		String scriptContent;
		while (webPageText.contains("<script>") && webPageText.contains("</script>")) {
			try {
				scriptContent = webPageText.substring(webPageText.indexOf("<script>"), webPageText.indexOf("</script>") + 9);
				webPageText = webPageText.replace(scriptContent, "");
			}
			catch(StringIndexOutOfBoundsException ex) {
				ex.printStackTrace();
			}
		}
		return webPageText;
	}
	
	public void collectTags(String webPageText) {
	
		boolean addToTag = false;
		boolean addToCommentTag = false;
		String tag = "<";
		
		for (int i = 0; i < webPageText.length(); i++) {
	
			if (addToCommentTag == true) {  // first if statement handles comment tags, accounting for tags within. 
				tag += webPageText.charAt(i);
				if (tag.contains("-->")) {
					commentTagList.add(tag);
					addToCommentTag = false;
					tag = "<";
				}
			}
			else if (webPageText.charAt(i) == '<' 
				&& (webPageText.charAt(i + 1) == '/' || Character.isLetter(webPageText.charAt(i + 1)))) {
					
				addToTag = true;
			}
			else if (webPageText.charAt(i) == '<' && webPageText.charAt(i + 1) == '!') { // comment tag detected and handled appropriately in top loop.
				addToCommentTag = true;
			}
			else if (webPageText.charAt(i) == '>' && addToTag == true) {
				tag += ">";
				tagList.add(tag);
	
				tag = "<";
				addToTag = false;
			}
			else if (addToTag == true) {
				tag += webPageText.charAt(i);
			}
		}
	}
	
	public void formatTags() {
		try {
			StyledDocument doc = tp.getStyledDocument();
			
			for (int i = 0; i < commentTagList.size(); i++) {
				int index = tp.getText().indexOf(commentTagList.get(i));
				doc.remove(index, commentTagList.get(i).length());
			}
			
			for (int i = 0; i < tagList.size(); i++) {
				SimpleAttributeSet aSet = new SimpleAttributeSet();
				String tag = tagList.get(i);
				String tpText = tp.getText();
				int index = tp.getText().indexOf(tag);
				
				if (tag.contains("<h") && Character.isDigit(tag.charAt(2))) {
					int endIndex = tpText.indexOf("</h");
					int headerLength = endIndex - index;
					
					StyleConstants.setFontSize(aSet, getFontSize(tag));
					doc.setCharacterAttributes(index, headerLength, aSet, false); // boolean indicates whether past attributes should be cleared.
				}
				/*else if (tag.contains("<img")) {
					int srcStart = tag.indexOf("src=\"") + 5;
					int srcEnd = tag.indexOf("\"", srcStart);
					String imgFile = tag.substring(srcStart, srcEnd);
					try {
						URL imgURL = new URL("http", BModel.host, imgFile);
						Toolkit kit = Toolkit.getDefaultToolkit();
						ImageIcon image = new ImageIcon(kit.getImage(imgURL));
						
						//Style style = doc.addStyle(imgFile, null); // adds new style in logical style hierarchy.
						//StyleConstants.setIcon(style, image); // each style needs unique name
						
						//doc.insertString(index, "", style);
						//doc.setCharacterAttributes(index, 0, aSet, false);
					}
					catch(MalformedURLException ex) {
						ex.printStackTrace();
					}
				}
				else if (tag.contains("<a href=\"http://")) { 
					String link = tag.substring(tag.indexOf("http"), tag.indexOf("\"", 15));
					String linkName = tpText.substring(tpText.indexOf(tag) + tag.length(), tpText.indexOf("</a"));
					URL hyperLink = new URL("http", link, linkName);
					aSet.addAttribute(HTML.Attribute.HREF, hyperLink.toString());
					
					doc.setCharacterAttributes(index, linkName.length(), aSet, false);
				}
				*/
				doc.remove(index, tagList.get(i).length());
			}
		}
		catch(BadLocationException ex) {
			ex.printStackTrace();
		}
	}
	
	public int getFontSize(String tagListUnit) {
		int size = Integer.parseInt(tagListUnit.substring(2, 3));
		switch (size) {
			case 1: return 24;
			case 2: return 22;
			case 3: return 18;
			case 4: return 16;
			case 5: return 12;
			case 6: return 10;
			default: return 12;
		}
	}
}