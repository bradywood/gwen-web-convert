package com.gwen.convert;

public class HtmlWrapper {
	
	String htmlPanelTemplate = "                <div class=\"panel panel-default\">\n" + //
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
            "                            <ul>\n" + //
            "                                    <li>%s</li>\n" + //
            "                            </ul>\n" + //
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

	public String formatPanel(MarkdownItem md, int ii) {
		// TODO Auto-generated method stub
		return String.format(htmlPanelTemplate, ii, ii,formatQuote(formatCode(md.getStep())), ii, ii,  formatQuote(formatCode(md.getDescription())), formatQuote(formatCode(md.getParameters())));
	}

	private String formatQuote(String formatQuote) {
		return formatQuote.replaceAll("`", "&quot;");
	}

	private String formatCode(String formatCode) {
		return formatCode.replaceAll("<", "&lt;").replaceAll(">","&gt;");
	}

}
