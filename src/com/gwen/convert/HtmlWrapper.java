package com.gwen.convert;

import java.util.HashMap;
import java.util.Map;

public class HtmlWrapper {
	
	private static final String HTML_HEAD_PORTION = "			<!DOCTYPE html>" + //
"	<html lang=\"en\">" + //
"	  <head>" + //
"	        <meta charset=\"utf-8\" />" + //
"	        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />" + //
"	        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />" + //
"	        <title>Gwen-Web DSL</title>" + //
"	        <link href=\"resources/bootstrap-3.3.6/css/bootstrap.min.css\" rel=\"stylesheet\" />" + //
"	        <script src=\"resources/jquery-2.1.4.min.js\"></script>" + //
"	        <script src=\"resources/bootstrap-3.3.6/js/bootstrap.min.js\"></script>" + //
"	        <style>" + //
"	          body {" + //
"	                padding: 40px;" + //
"	          }" + //
"	          .panel-heading {" + //
"	        padding: 5px 15px;" + //
"	          }" + //
"	          .panel-title {" + //
"	                font-family: Menlo,Monaco,Consolas,\"Courier New\",monospace;" + //
"	                font-size: 13px;" + //
"	          }" + //
"	          a:visited {" + //
"	            text-decoration: none;" + //
"	          }" + //
"	        </style>" + //
"	  </head>" + //
"	  <body>" + //
"	        <a href=\"https://github.com/gwen-interpreter/gwen/wiki/The-Gwen-Logo\"><img src=\"https://github.com/gwen-interpreter/gwen/wiki/img/gwen-attractor.png\"></a>" + //
"	        <h2>Gwen-Web DSL</h2>" + //
"	        <hr>" + //
"	        All feature files must conform to the <a href=\"https://github.com/cucumber/cucumber/wiki/Feature-Introduction\">Gherkin standard for feature files</a>." + //
"	        The following steps are supported <em>(click on a step for details)</em>:<br><br>" + //
"	        <code>Given</code>, <code>When</code>, <code>Then</code>, <code>And</code>, or <code>But</code>..<br><br>" + //
"	        <div class=\"panel-group\" id=\"accordion\" role=\"tablist\" aria-multiselectable=\"true\">" + //
"" + //
"	<!--- ---->";

	private static final String HTML_TAIL_PORTION = "<!---- ----> " + //
        "</div>" + //
        "<hr>" + //
"" + //
"       <div class=\"well well-sm\">" + //
"    Gwen-web is open sourced at <a href=\"https://github.com/gwen-interpreter/gwen-web\">https://github.com/gwen-interpreter/gwen-web</a> | <a href=\"https://github.com/gwen-interpreter/gwen-web/blob/master/README.md#license\">License</a>" + //
"    </div>" + //
"  </body>" + //
"</html>";

	final String HTML_PANEL_TEMPLATE = "                <div class=\"panel panel-default\">\n" + //
			"<div class=\"panel-heading\" role=\"tab\" id=\"dsl-%d\">\n" + //
            "                    <h4 class=\"panel-title\">\n" + //
            "                            <a role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse-%d\" aria-expanded=\"true\" aria-controls=\"collapseOne\">\n" + //
            "                           %s\n" + //
            "                            </a>\n" + //
            "                    </h4>\n" + //
            "            </div>\n" + //
            "            <div id=\"collapse-%d\" class=\"panel-collapse collapse\" role=\"tabpanel\" aria-labelledby=\"dsl-%d\">\n" + //
            "                    <div class=\"panel-body\">\n" + //
            "                            %s\n" + //
            //"                            <ul>\n" + //
            "                                    %s\n" + //
            //"                            </ul>\n" + //
            "                    </div>\n" + //
            "            </div>\n" + //
            "    </div>\n"; //1,2,3,4 = %d for index
		//1 %s = step, 2%s = description, 3%s = parameters
	
	String htmlPanelTemplate_backup = "                <div class=\"panel panel-default\">" + //
			"<div class=\"panel-heading\" role=\"tab\" id=\"dsl-%d\">" + //
            "                    <h4 class=\"panel-title\">" + //
            "                            <a role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse-%d\" aria-expanded=\"true\" aria-controls=\"collapseOne\">" + //
            "                           <code>&lt;dropdown&gt;</code> &lt;text|value&gt; &lt;should|should not&gt; &lt;be|contain|start with|end with|match regex|match xpath&gt; &quot;<code>&lt;expression&gt;</code>&quot;" + //
            "                            </a>" + //
            "                    </h4>" + //
            "            </div>" + //
            "            <div id=\"collapse-%d\" class=\"panel-collapse collapse\" role=\"tabpanel\" aria-labelledby=\"dsl-%d\">" + //
            "                    <div class=\"panel-body\">" + //
            "                            Checks that a dropdown selection matches or does not match a given expression" + //
            "                            <ul>" + //
            "                                    <li><code>&lt;dropdown&gt;</code> = the dropdown element to check" + //
            "                                    <li><code>&lt;expression&gt;</code> = the expression to match against</li>" + //
            "                            </ul>" + //
            "                    </div>" + //
            "            </div>" + //
            "    </div>";
	
	public String formatHead() {
		return HTML_HEAD_PORTION;
	}
	
	public String formatBottom() {
		return HTML_TAIL_PORTION;
	}

	public String formatPanel(MarkdownItem md, int ii) {
		
		HashMap<String, String> params = md.getParametersAsKeyValue(); 
		
		
		String step = formatQuote(formatCode(md.getStep()));
		
		String description = formatQuote(formatCode(md.getDescription()));
		StringBuffer sb = new StringBuffer();
		if (params.size() > 0) {
			sb.append("<ul>\n");
			for (Map.Entry<String, String> entry : params.entrySet()) {
			    String key = entry.getKey();
			    
			    String formattedKey = formatQuote(formatCode(key)).trim();
			    step = step.replace(formattedKey, "<code>"+formattedKey+"</code>");
			    
			    String value = entry.getValue();
			    sb.append("<li><code>" + formatQuote(formatCode(key)) + "</code> = " + formatQuote(formatCode(value)) + "</li>\n");
			}
			sb.append("</ul>\n");
		}
		
		return String.format(HTML_PANEL_TEMPLATE, ii, ii, step, ii, ii, description , sb.toString());
	}

	private String formatQuote(String formatQuote) {
		return formatQuote.replaceAll("`", "&quot;").replaceAll("\"&quot;","&quot;").replaceAll("&quot;\"","&quot;");
	}

	private String formatCode(String formatCode) {
		return formatCode.replaceAll("<", "&lt;").replaceAll(">","&gt;");
	}

}
