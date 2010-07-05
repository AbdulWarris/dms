/**
 *  OpenKM, Open Document Management System (http://www.openkm.com)
 *  Copyright (c) 2006-2010  Paco Avila & Josep Llort
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

package com.openkm.dao.bean;

import java.io.Serializable;

public class UserProfileMenuFile implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean createFolderVisible;
	private boolean findFolderVisible;
	private boolean goFolderVisible;
	private boolean downloadVisible;
	private boolean downloadPdfVisible;
	private boolean addDocumentVisible;
	private boolean addPropertyGroupVisible;
	private boolean removePropertyGroupVisible;
	private boolean startWorkflowVisible;
	private boolean addSubscriptionVisible;
	private boolean removeSubscriptionVisible;
	private boolean refreshVisible;
	private boolean goHomeVisible;
	private boolean setHomeVisible;
	private boolean scannerVisible;
	private boolean uploaderVisible;
	private boolean addBookmarkVisible;
	private boolean exportVisible;
	private boolean createFromTemplateVisible;
	private boolean purgeVisible;
	private boolean restoreVisible;
	private boolean purgeTrashVisible;
	private boolean sendDocumentLinkVisible;
	private boolean sendDocumentAttachmentVisible;
	private boolean changeSkinVisible;
	private boolean debugVisible;
	private boolean administrationVisible;
	private boolean manageBookmarksVisible;
	
	public boolean isCreateFolderVisible() {
		return createFolderVisible;
	}

	public void setCreateFolderVisible(boolean createFolderVisible) {
		this.createFolderVisible = createFolderVisible;
	}

	public boolean isFindFolderVisible() {
		return findFolderVisible;
	}

	public void setFindFolderVisible(boolean findFolderVisible) {
		this.findFolderVisible = findFolderVisible;
	}

	public boolean isGoFolderVisible() {
		return goFolderVisible;
	}

	public void setGoFolderVisible(boolean goFolderVisible) {
		this.goFolderVisible = goFolderVisible;
	}
	
	public boolean isDownloadVisible() {
		return downloadVisible;
	}

	public void setDownloadVisible(boolean downloadVisible) {
		this.downloadVisible = downloadVisible;
	}

	public boolean isDownloadPdfVisible() {
		return downloadPdfVisible;
	}

	public void setDownloadPdfVisible(boolean downloadPdfVisible) {
		this.downloadPdfVisible = downloadPdfVisible;
	}

	public boolean isAddDocumentVisible() {
		return addDocumentVisible;
	}

	public void setAddDocumentVisible(boolean addDocumentVisible) {
		this.addDocumentVisible = addDocumentVisible;
	}

	public boolean isAddPropertyGroupVisible() {
		return addPropertyGroupVisible;
	}

	public void setAddPropertyGroupVisible(boolean addPropertyGroupVisible) {
		this.addPropertyGroupVisible = addPropertyGroupVisible;
	}

	public boolean isRemovePropertyGroupVisible() {
		return removePropertyGroupVisible;
	}

	public void setRemovePropertyGroupVisible(boolean removePropertyGroupVisible) {
		this.removePropertyGroupVisible = removePropertyGroupVisible;
	}

	public boolean isStartWorkflowVisible() {
		return startWorkflowVisible;
	}

	public void setStartWorkflowVisible(boolean startWorkflowVisible) {
		this.startWorkflowVisible = startWorkflowVisible;
	}

	public boolean isAddSubscriptionVisible() {
		return addSubscriptionVisible;
	}

	public void setAddSubscriptionVisible(boolean addSubscriptionVisible) {
		this.addSubscriptionVisible = addSubscriptionVisible;
	}

	public boolean isRemoveSubscriptionVisible() {
		return removeSubscriptionVisible;
	}

	public void setRemoveSubscriptionVisible(boolean removeSubscriptionVisible) {
		this.removeSubscriptionVisible = removeSubscriptionVisible;
	}

	public boolean isRefreshVisible() {
		return refreshVisible;
	}

	public void setRefreshVisible(boolean refreshVisible) {
		this.refreshVisible = refreshVisible;
	}

	public boolean isGoHomeVisible() {
		return goHomeVisible;
	}

	public void setGoHomeVisible(boolean goHomeVisible) {
		this.goHomeVisible = goHomeVisible;
	}
	
	public boolean isSetHomeVisible() {
		return setHomeVisible;
	}

	public void setSetHomeVisible(boolean setHomeVisible) {
		this.setHomeVisible = setHomeVisible;
	}

	public boolean isScannerVisible() {
		return scannerVisible;
	}

	public void setScannerVisible(boolean scannerVisible) {
		this.scannerVisible = scannerVisible;
	}

	public boolean isUploaderVisible() {
		return uploaderVisible;
	}

	public void setUploaderVisible(boolean uploaderVisible) {
		this.uploaderVisible = uploaderVisible;
	}

	public boolean isAddBookmarkVisible() {
		return addBookmarkVisible;
	}

	public void setAddBookmarkVisible(boolean addBookmarkVisible) {
		this.addBookmarkVisible = addBookmarkVisible;
	}
	
	public boolean isExportVisible() {
		return exportVisible;
	}

	public void setExportVisible(boolean exportVisible) {
		this.exportVisible = exportVisible;
	}

	public boolean isCreateFromTemplateVisible() {
		return createFromTemplateVisible;
	}

	public void setCreateFromTemplateVisible(boolean createFromTemplateVisible) {
		this.createFromTemplateVisible = createFromTemplateVisible;
	}

	public boolean isPurgeVisible() {
		return purgeVisible;
	}

	public void setPurgeVisible(boolean purgeVisible) {
		this.purgeVisible = purgeVisible;
	}

	public boolean isRestoreVisible() {
		return restoreVisible;
	}

	public void setRestoreVisible(boolean restoreVisible) {
		this.restoreVisible = restoreVisible;
	}

	public boolean isPurgeTrashVisible() {
		return purgeTrashVisible;
	}

	public void setPurgeTrashVisible(boolean purgeTrashVisible) {
		this.purgeTrashVisible = purgeTrashVisible;
	}

	public boolean isSendDocumentLinkVisible() {
		return sendDocumentLinkVisible;
	}

	public void setSendDocumentLinkVisible(boolean sendDocumentLinkVisible) {
		this.sendDocumentLinkVisible = sendDocumentLinkVisible;
	}
	
	public boolean isSendDocumentAttachmentVisible() {
		return sendDocumentAttachmentVisible;
	}

	public void setSendDocumentAttachmentVisible(boolean sendDocumentAttachmentVisible) {
		this.sendDocumentAttachmentVisible = sendDocumentAttachmentVisible;
	}

	public boolean isChangeSkinVisible() {
		return changeSkinVisible;
	}

	public void setChangeSkinVisible(boolean changeSkinVisible) {
		this.changeSkinVisible = changeSkinVisible;
	}

	public boolean isDebugVisible() {
		return debugVisible;
	}

	public void setDebugVisible(boolean debugVisible) {
		this.debugVisible = debugVisible;
	}

	public boolean isAdministrationVisible() {
		return administrationVisible;
	}

	public void setAdministrationVisible(boolean administrationVisible) {
		this.administrationVisible = administrationVisible;
	}

	public boolean isManageBookmarksVisible() {
		return manageBookmarksVisible;
	}

	public void setManageBookmarksVisible(boolean manageBookmarksVisible) {
		this.manageBookmarksVisible = manageBookmarksVisible;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("createFolderVisible="); sb.append(createFolderVisible);
		sb.append(", findFolderVisible="); sb.append(findFolderVisible);
		sb.append(", goFolderVisible="); sb.append(goFolderVisible);
		sb.append(", downloadVisible="); sb.append(downloadVisible);
		sb.append(", downloadPdfVisible="); sb.append(downloadPdfVisible);;
		sb.append(", addDocumentVisible="); sb.append(addDocumentVisible);
		sb.append(", addPropertyGroupVisible="); sb.append(addPropertyGroupVisible);
		sb.append(", removePropertyGroupVisible="); sb.append(removePropertyGroupVisible);
		sb.append(", startWorkflowVisible="); sb.append(startWorkflowVisible);
		sb.append(", addSubscriptionVisible="); sb.append(addSubscriptionVisible);
		sb.append(", removeSubscriptionVisible="); sb.append(removeSubscriptionVisible);
		sb.append(", refreshVisible="); sb.append(refreshVisible);
		sb.append(", goHomeVisible="); sb.append(goHomeVisible);
		sb.append(", setHomeVisible="); sb.append(setHomeVisible);
		sb.append(", scannerVisible="); sb.append(scannerVisible);
		sb.append(", uploaderVisible="); sb.append(uploaderVisible);
		sb.append(", addBookmarkVisible="); sb.append(addBookmarkVisible);
		sb.append("}");
		return sb.toString();
	}
}
