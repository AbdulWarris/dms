/**
 *  OpenKM, Open Document Management System (http://www.openkm.com)
 *  Copyright (c) 2006-2011  Paco Avila & Josep Llort
 *
 *  No bytes were intentionally harmed during the development of this application.
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.openkm.frontend.client.widget.searchresult;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.openkm.frontend.client.Main;
import com.openkm.frontend.client.bean.GWTDocument;
import com.openkm.frontend.client.bean.GWTFolder;
import com.openkm.frontend.client.bean.GWTMail;
import com.openkm.frontend.client.bean.GWTPermission;
import com.openkm.frontend.client.bean.GWTQueryResult;
import com.openkm.frontend.client.util.CommonUI;
import com.openkm.frontend.client.util.OKMBundleResources;
import com.openkm.frontend.client.util.Util;
import com.openkm.frontend.client.widget.WidgetUtil;
import com.openkm.frontend.client.widget.dashboard.keymap.TagCloud;

/**
 * SearchFullResult
 * 
 * @author jllort
 *
 */
public class SearchFullResult extends Composite {
	
	private ScrollPanel scrollPanel;
	private FlexTable table;
	
	/**
	 * SearchFullResult
	 */
	public SearchFullResult() {
		table = new FlexTable();
		scrollPanel = new ScrollPanel(table);
		
		scrollPanel.setStyleName("okm-Input");
		
		initWidget(scrollPanel);
	}
	
	 /* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.UIObject#setPixelSize(int, int)
	 */
	public void setPixelSize(int width, int height) {
		table.setWidth("100%");
		scrollPanel.setPixelSize(width, height);
	}
	
	/**
	 * Adds a document to the panel
	 * 
	 * @param doc The doc to add
	 */
	public void addRow(GWTQueryResult gwtQueryResult) {
		if (gwtQueryResult.getDocument()!=null || gwtQueryResult.getAttachment()!=null) {
			addDocumentRow(gwtQueryResult, new Score(gwtQueryResult.getScore()));
		} else if (gwtQueryResult.getFolder()!=null) {
			addFolderRow(gwtQueryResult, new Score(gwtQueryResult.getScore()));
		} else if (gwtQueryResult.getMail()!=null) {
			addMailRow(gwtQueryResult, new Score(gwtQueryResult.getScore()));
		}
	}
	
	/**
	 * Adding document row
	 * 
	 * @param gwtQueryResult Query result
	 * @param score Document score
	 */
	private void addDocumentRow(GWTQueryResult gwtQueryResult, Score score) {
		int rows = table.getRowCount();
		
		GWTDocument doc = new GWTDocument();
		if (gwtQueryResult.getDocument()!=null) {
			doc = gwtQueryResult.getDocument();
		} else if (gwtQueryResult.getAttachment()!=null) {
			doc = gwtQueryResult.getAttachment();
		}
		
		// Document row
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setStyleName("okm-NoWrap");
		hPanel.add(new HTML(score.getHTML()));
		hPanel.add(Util.hSpace("5"));
		if(doc.isAttachment())  {
			hPanel.add(new HTML(Util.imageItemHTML("img/email_attach.gif")));
			hPanel.add(Util.hSpace("5"));
		} 
		hPanel.add(new HTML(Util.mimeImageHTML(doc.getMimeType())));
		hPanel.add(Util.hSpace("5"));
		Anchor anchor = new Anchor();
		anchor.setHTML(doc.getName());
		anchor.setStyleName("okm-Hyperlink");
		// On attachemt case must remove last folder path, because it's internal usage not for visualization
		if (doc.isAttachment()) {
			anchor.setTitle(doc.getParent().substring(0, doc.getParent().lastIndexOf("/")));
		} else {
			anchor.setTitle(doc.getParent());
		}
		final String docPath = doc.getPath();
		anchor.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				CommonUI.openAllFolderPath(docPath.substring(0,docPath.lastIndexOf("/")), docPath);
			}
		});
		hPanel.add(anchor);
		hPanel.add(Util.hSpace("5"));
		hPanel.add(new HTML(doc.getActualVersion().getName()));
		hPanel.add(Util.hSpace("5"));
		// Download
		Image gotoDocument = new Image(OKMBundleResources.INSTANCE.download());
		gotoDocument.addClickHandler(new ClickHandler() { 
			@Override
			public void onClick(ClickEvent event) {
				Util.downloadFile(docPath, "");
			}
			
		});
		gotoDocument.setTitle(Main.i18n("dashboard.keyword.goto.document"));
		gotoDocument.setStyleName("okm-KeyMap-ImageHover");
		hPanel.add(gotoDocument);
		table.setWidget(rows++, 0, hPanel);		
		
		// Folder row
		HorizontalPanel hPanel2 = new HorizontalPanel();
		hPanel2.setStyleName("okm-NoWrap");
		hPanel2.add(new HTML("<b>"+Main.i18n("document.folder")+":</b>&nbsp;"));
		hPanel2.add(drawFolder(doc.getParentId()));
		table.setWidget(rows++, 0, hPanel2);
		
		// Document detail
		HorizontalPanel hPanel4 = new HorizontalPanel();
		hPanel4.setStyleName("okm-NoWrap");
		hPanel4.add(new HTML("<b>"+Main.i18n("search.result.author")+":</b>&nbsp;"));
		hPanel4.add(new HTML(doc.getActualVersion().getAuthor()));
		hPanel4.add(Util.hSpace("33"));
		hPanel4.add(new HTML("<b>"+Main.i18n("search.result.size")+":</b>&nbsp;"));
		hPanel4.add(new HTML(Util.formatSize(doc.getActualVersion().getSize())));
		hPanel4.add(Util.hSpace("33"));
		hPanel4.add(new HTML("<b>"+Main.i18n("search.result.version")+":</b>&nbsp;"));
		hPanel4.add(new HTML(doc.getActualVersion().getName()));
		hPanel4.add(Util.hSpace("33"));
		hPanel4.add(new HTML("<b>"+Main.i18n("search.result.date.update")+":&nbsp;</b>"));
		DateTimeFormat dtf = DateTimeFormat.getFormat(Main.i18n("general.date.pattern"));
		hPanel4.add(new HTML(dtf.format(doc.getLastModified())));
		table.setWidget(rows++, 0, hPanel4);
		
		// Categories and tagcloud
		if (doc.getCategories().size()>0 || doc.getKeywords().size()>0) { 
			HorizontalPanel hPanel5 = new HorizontalPanel();
			hPanel5.setStyleName("okm-NoWrap");
			if (doc.getCategories().size()>0) {
				FlexTable tableSubscribedCategories = new FlexTable();
				tableSubscribedCategories.setStyleName("okm-DisableSelect");
				// Sets the document categories
				for (Iterator<GWTFolder> it = doc.getCategories().iterator(); it.hasNext();) {
					drawCategory(tableSubscribedCategories, it.next());
				}
				hPanel5.add(new HTML("<b>"+Main.i18n("document.categories")+"</b>"));
				hPanel5.add(Util.hSpace("5"));
				hPanel5.add(tableSubscribedCategories);
				hPanel5.add(Util.hSpace("33"));
			}
			if (doc.getKeywords().size()>0) {
				// Tag cloud
				TagCloud keywordsCloud = new TagCloud();
				keywordsCloud.setWidth("350");
				WidgetUtil.drawTagCloud(keywordsCloud, doc.getKeywords());
				hPanel5.add(new HTML("<b>"+Main.i18n("document.keywords.cloud")+"</b>"));
				hPanel5.add(Util.hSpace("5"));
				hPanel5.add(keywordsCloud);
			}
			table.setWidget(rows++, 0, hPanel5);
		}
		
		// Separator end line
		Image horizontalLine = new Image("img/transparent_pixel.gif");
		horizontalLine.setStyleName("okm-TopPanel-Line-Border");
		horizontalLine.setSize("100%", "2px");
		table.setWidget(rows, 0, horizontalLine);
		table.getFlexCellFormatter().setVerticalAlignment(rows, 0, HasAlignment.ALIGN_BOTTOM);
		table.getFlexCellFormatter().setHeight(rows, 0, "30");		
	}
	
	/**
	 * Adding folder
	 * 
	 * @param gwtQueryResult Query result
	 * @param score The folder score
	 */
	private void addFolderRow(GWTQueryResult gwtQueryResult, Score score) {
		int rows = table.getRowCount();
		
		final GWTFolder folder = gwtQueryResult.getFolder();
		
		// Folder row
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setStyleName("okm-NoWrap");
		hPanel.add(new HTML(score.getHTML()));
		hPanel.add(Util.hSpace("5"));
		// Looks if must change icon on parent if now has no childs and properties with user security atention
		if ( (folder.getPermissions() & GWTPermission.WRITE) == GWTPermission.WRITE) {
			if (folder.getHasChilds()) {
				hPanel.add(new HTML(Util.imageItemHTML("img/menuitem_childs.gif")));
			} else {
				hPanel.add(new HTML(Util.imageItemHTML("img/menuitem_empty.gif")));
			}
		} else {
			if (folder.getHasChilds()) {
				hPanel.add(new HTML(Util.imageItemHTML("img/menuitem_childs_ro.gif")));
			} else {
				hPanel.add(new HTML(Util.imageItemHTML("img/menuitem_empty_ro.gif")));
			}
		}
		Anchor anchor = new Anchor();
		anchor.setHTML(folder.getName());
		anchor.setTitle(folder.getParentPath());
		anchor.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				CommonUI.openAllFolderPath(folder.getPath(), "");
			}
		});
		anchor.setStyleName("okm-Hyperlink");
		hPanel.add(anchor);
		table.setWidget(rows++, 0, hPanel);	
		
		// Folder row
		HorizontalPanel hPanel2 = new HorizontalPanel();
		hPanel2.setStyleName("okm-NoWrap");
		hPanel2.add(new HTML("<b>"+Main.i18n("folder.parent")+":</b>&nbsp;"));
		hPanel2.add(drawFolder(folder.getParentPath()));
		table.setWidget(rows++, 0, hPanel2);
		
		// Folder detail
		HorizontalPanel hPanel3 = new HorizontalPanel();
		hPanel3.setStyleName("okm-NoWrap");
		hPanel3.add(new HTML("<b>"+Main.i18n("search.result.author")+":</b>&nbsp;"));
		hPanel3.add(new HTML(folder.getAuthor()));
		hPanel3.add(Util.hSpace("33"));
		hPanel3.add(new HTML("<b>"+Main.i18n("folder.created")+":&nbsp;</b>"));
		DateTimeFormat dtf = DateTimeFormat.getFormat(Main.i18n("general.date.pattern"));
		hPanel3.add(new HTML(dtf.format(folder.getCreated())));
		table.setWidget(rows++, 0, hPanel3);
		
		// Separator end line
		Image horizontalLine = new Image("img/transparent_pixel.gif");
		horizontalLine.setStyleName("okm-TopPanel-Line-Border");
		horizontalLine.setSize("100%", "2px");
		table.setWidget(rows, 0, horizontalLine);
		table.getFlexCellFormatter().setVerticalAlignment(rows, 0, HasAlignment.ALIGN_BOTTOM);
		table.getFlexCellFormatter().setHeight(rows, 0, "30");		
	}
	
	/**
	 * Adding mail
	 * 
	 * @param gwtQueryResult Query result
	 * @param score The mail score
	 */
	private void addMailRow(GWTQueryResult gwtQueryResult, Score score) {
		int rows = table.getRowCount();
		
		final GWTMail mail = gwtQueryResult.getMail();
		
		// Mail row
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setStyleName("okm-NoWrap");
		hPanel.add(new HTML(score.getHTML()));
		hPanel.add(Util.hSpace("5"));
		if (mail.getAttachments().size()>0) {
			hPanel.add(new HTML(Util.imageItemHTML("img/email_attach.gif")));
		} else {
			hPanel.add(new HTML(Util.imageItemHTML("img/email.gif")));
		}
		Anchor anchor = new Anchor();
		anchor.setHTML(mail.getSubject());
		anchor.setTitle(mail.getParent());
		anchor.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String docPath = mail.getPath();
				String path = docPath.substring(0,docPath.lastIndexOf("/"));
				CommonUI.openAllFolderPath(path, docPath);
			}
		});
		anchor.setStyleName("okm-Hyperlink");
		hPanel.add(anchor);
		table.setWidget(rows++, 0, hPanel);	
		
		// Mail Subject
		HorizontalPanel hPanel2 = new HorizontalPanel();
		hPanel2.setStyleName("okm-NoWrap");
		hPanel2.add(new HTML("<b>"+Main.i18n("mail.subject")+":</b>&nbsp;"));
		hPanel2.add(new HTML(mail.getSubject()));
		
		// Mail detail
		HorizontalPanel hPanel3 = new HorizontalPanel();
		hPanel3.setStyleName("okm-NoWrap");
		hPanel3.add(new HTML("<b>"+Main.i18n("mail.from")+":</b>&nbsp;"));
		hPanel3.add(new HTML(mail.getFrom()));
		table.setWidget(rows++, 0, hPanel3);
		
		// To panel
		if (mail.getTo().length>0) {
			HorizontalPanel hPanel4 = new HorizontalPanel();
			hPanel4.setStyleName("okm-NoWrap");
			VerticalPanel toPanel = new VerticalPanel();
			for (int i=0; i<mail.getTo().length; i++) {
				Anchor hTo = new Anchor();
				final String mailTo = mail.getTo()[i].contains("<")?mail.getTo()[i].substring(mail.getTo()[i].indexOf("<")+1, mail.getTo()[i].indexOf(">")):mail.getTo()[i];
				hTo.setHTML(mail.getTo()[i].replace("<", "&lt;").replace(">", "&gt;"));
				hTo.setTitle("mailto:"+mailTo);
				hTo.setStyleName("okm-Mail-Link");
				hTo.addStyleName("okm-NoWrap");
				hTo.addClickHandler(new ClickHandler() { 
					@Override
					public void onClick(ClickEvent event) {
						Window.open("mailto:" + mailTo, "_self", "");
					}
				});
				toPanel.add(hTo);
			}
			hPanel4.add(toPanel);
			table.setWidget(rows++, 0, hPanel4);
		}
		
		// Reply panel
		if (mail.getReply().length>0) {
			HorizontalPanel hPanel5 = new HorizontalPanel();
			hPanel5.setStyleName("okm-NoWrap");
			hPanel5.add(new HTML("<b>"+Main.i18n("mail.reply")+":</b>&nbsp;"));
			VerticalPanel replyPanel = new VerticalPanel();
			for (int i=0; i<mail.getReply().length; i++) {
				Anchor hReply = new Anchor();
				final String mailReply = mail.getReply()[i].contains("<")?mail.getReply()[i].substring(mail.getReply()[i].indexOf("<")+1, mail.getReply()[i].indexOf(">")):mail.getReply()[i];
				hReply.setHTML(mail.getReply()[i].replace("<", "&lt;").replace(">", "&gt;"));
				hReply.setTitle("mailto:"+mailReply);
				hReply.setStyleName("okm-Mail-Link");
				hReply.addStyleName("okm-NoWrap");
				hReply.addClickHandler(new ClickHandler() { 
					@Override
					public void onClick(ClickEvent event) {
						Window.open("mailto:" + mailReply, "_self", "");
					}
				});
				replyPanel.add(hReply);
			}
			hPanel5.add(replyPanel);
			table.setWidget(rows++, 0, hPanel5);
		}
	}
	
	/**
	 * drawCategory
	 * 
	 * @param category
	 */
	private void drawCategory(final FlexTable tableSubscribedCategories, final GWTFolder category) {
		int row = tableSubscribedCategories.getRowCount();
		Anchor anchor = new Anchor();
		// Looks if must change icon on parent if now has no childs and properties with user security atention
		String path = category.getPath().substring(16); // Removes /okm:categories
		if (category.getHasChilds()) {
			anchor.setHTML(Util.imageItemHTML("img/menuitem_childs.gif", path, "top"));
		} else {
			anchor.setHTML(Util.imageItemHTML("img/menuitem_empty.gif", path, "top"));
		}
		
		anchor.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				CommonUI.openAllFolderPath(category.getPath(), null);
			}
		});
		anchor.setStyleName("okm-KeyMap-ImageHover");
		tableSubscribedCategories.setWidget(row, 0, anchor);
	}
	
	/**
	 * drawFolder
	 * 
	 * @param folder
	 * @return
	 */
	private Anchor drawFolder(final String path) {
		Anchor anchor = new Anchor();
		anchor.setHTML(Util.imageItemHTML("img/menuitem_childs.gif", path, "top"));	
		anchor.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				CommonUI.openAllFolderPath(path, null);
			}
		});
		anchor.setStyleName("okm-KeyMap-ImageHover");
		return anchor;
	}
	
	/**
	 * removeAllRows
	 */
	public void removeAllRows() {
		table.removeAllRows();
	}
}