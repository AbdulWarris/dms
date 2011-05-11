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

package com.openkm.frontend.client.widget.searchin;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.openkm.frontend.client.Main;
import com.openkm.frontend.client.bean.GWTQueryParams;


/**
 * ControlSearchIn
 * 
 * @author jllort
 *
 */
public class ControlSearchIn extends Composite {
	private VerticalPanel controlPanel;
	private HTML textResults;
	private Image previous;
	private Image next;
	private FlexTable table;
	private int offset = 0;
	private int limit = 10;
	private boolean previousEnabled = false;	// Indicates if button is enabled
	private boolean nextEnabled = false; 		// Indicates if button is enabled
	private GWTQueryParams gwtParams;			// Actual search values
	private String statement;					// Simple search valeu
	private long total = 0;
	
	public ControlSearchIn(){
		controlPanel = new VerticalPanel();
		table = new FlexTable();
		textResults = new HTML(Main.i18n("search.results"));
		previous = new Image("img/icon/search/resultset_previous.gif");
		next = new Image("img/icon/search/resultset_next.gif");
		
		previous.addClickHandler(new ClickHandler() { 
			@Override
			public void onClick(ClickEvent event) {
				if (previousEnabled) {
					offset -= limit; 
					if (gwtParams!=null) {
						Main.get().mainPanel.search.searchBrowser.searchResult.findPaginated(gwtParams, offset, limit);
					} else {
						Main.get().mainPanel.search.searchBrowser.searchResult.findSimpleQueryPaginated(statement, offset, limit);
					}
				}
			}
		});
		
		next.addClickHandler(new ClickHandler() { 
			@Override
			public void onClick(ClickEvent event) {
				if (nextEnabled) {
					offset += limit; 
					if (gwtParams!=null) {
						Main.get().mainPanel.search.searchBrowser.searchResult.findPaginated(gwtParams, offset, limit);
					} else {
						Main.get().mainPanel.search.searchBrowser.searchResult.findSimpleQueryPaginated(statement, offset, limit);
					}
				}
			}
		});
		
		table.setWidget(0,0, textResults);
		table.setHTML(0,1, "&nbsp;&nbsp;&nbsp;");
		table.setWidget(0,2, previous);
		table.setHTML(0,3, "");
		table.setWidget(0,4, next);
		
		controlPanel.add(table);
		
		controlPanel.setVisible(false); // Initially not shows only when search has been executed
		controlPanel.setStyleName("okm-DisableSelect");
		
		initWidget(controlPanel);
	}
	
	/**
	 * Executes the search
	 */
	public void executeSearch(GWTQueryParams gwtParams, int limit) {		
		this.gwtParams = gwtParams;
		this.statement = null;
		this.limit = limit;
		offset = 0;
		Main.get().mainPanel.search.searchBrowser.searchResult.findPaginated(gwtParams, offset, limit);
	}
	
	/**
	 * Executes the search
	 */
	public void executeSearch(String statement, int limit) {	
		this.gwtParams = null;
		this.statement = statement;
		this.limit = limit;
		offset = 0;
		Main.get().mainPanel.search.searchBrowser.searchResult.findSimpleQueryPaginated(statement, offset, limit);
	}
	
	/**
	 * Refresh control values
	 * 
	 * @param total 
	 */
	public void refreshControl(long total){
		this.total = total;
		textResults.setHTML(Main.i18n("search.results") + " : " + total); 
		
		if (total == 0) {
			previousEnabled = false;
			nextEnabled = false;
			table.setHTML(0,3, "");
			setVisible(false);
		} else {

			if (offset == 0) {
				previousEnabled = false;
			} else {
				previousEnabled = true;
			}
			
			if ((offset + limit) >= total) {
				nextEnabled = false;
				table.setHTML(0,3, (offset+1) + "&nbsp;" + Main.i18n("search.to") + "&nbsp;" + total);
			} else {
				nextEnabled = true;
				table.setHTML(0,3, (offset+1) + "&nbsp;" + Main.i18n("search.to") + "&nbsp;" + (offset+limit));
			}
			setVisible(true);
		}
		
		evaluateIcons(); // Evaluates back / forward icons
	}
	
	/**
	 * Refreshing language
	 */
	public void langRefresh() {
		refreshControl(total);
	}
	
	/**
	 * Evaluate icons image
	 */
	public void evaluateIcons(){
		if (previousEnabled) {
			previous.setUrl("img/icon/search/resultset_previous.gif");
		} else {
			previous.setUrl("img/icon/search/resultset_previous_disabled.gif");
		}
		
		if (nextEnabled) {
			next.setUrl("img/icon/search/resultset_next.gif");
		} else {
			next.setUrl("img/icon/search/resultset_next_disabled.gif");
		}
	}
}