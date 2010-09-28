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

package com.openkm.util.impexp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.openkm.bean.Document;
import com.openkm.bean.Folder;
import com.openkm.core.AccessDeniedException;
import com.openkm.core.DatabaseException;
import com.openkm.core.FileSizeExceededException;
import com.openkm.core.ItemExistsException;
import com.openkm.core.PathNotFoundException;
import com.openkm.core.RepositoryException;
import com.openkm.core.UnsupportedMimeTypeException;
import com.openkm.core.UserQuotaExceededException;
import com.openkm.core.VirusDetectedException;
import com.openkm.module.DocumentModule;
import com.openkm.module.ModuleManager;

public class RepositoryImporter {
	private static Logger log = LoggerFactory.getLogger(RepositoryImporter.class);
		
	private RepositoryImporter() {}

	/**
	 * Import documents from filesystem into document repository.
	 */
	public static ImpExpStats importDocuments(File fs, String fldPath, boolean metadata, Writer out,
			InfoDecorator deco) throws PathNotFoundException, ItemExistsException, AccessDeniedException,
			RepositoryException, IOException, DatabaseException {
		log.debug("importDocuments({}, {}, {}, {}, {})", new Object[] { fs, fldPath, metadata, out, deco });
		ImpExpStats stats;
		
		try {
			if (fs.exists()) {
				stats = importDocumentsHelper(fs, fldPath, metadata, out, deco);
			} else  {
				throw new FileNotFoundException(fs.getPath());
			}
		} catch (PathNotFoundException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (ItemExistsException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (AccessDeniedException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (RepositoryException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (DatabaseException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
				
		log.debug("importDocuments: {}", stats);
		return stats;
	}
	
	/**
	 * Import documents from filesystem into document repository (recursive).
	 */
	private static ImpExpStats importDocumentsHelper(File fs, String fldPath, boolean metadata, Writer out,
			InfoDecorator deco) throws FileNotFoundException, PathNotFoundException, AccessDeniedException,
			ItemExistsException, RepositoryException, IOException, DatabaseException {
		log.debug("importDocumentsHelper({}, {}, {}, {}, {})", new Object[] { fs, fldPath, metadata, out, deco });
		File[] files = fs.listFiles();
		ImpExpStats stats = new ImpExpStats();
		
		for (int i=0; i<files.length; i++) {
			if (files[i].isDirectory()) {
				Folder fld = new Folder();
				fld.setPath(fldPath + "/" + files[i].getName());
				
				try {
					ModuleManager.getFolderModule().create(null, fld);
					ImpExpStats tmp = importDocumentsHelper(files[i], fld.getPath(), metadata, out, deco);
					
					// Stats
					stats.setOk(stats.isOk() && tmp.isOk());
					stats.setSize(stats.getSize() + tmp.getSize());
					stats.setDocuments(stats.getDocuments() + tmp.getDocuments());
					stats.setFolders(stats.getFolders() + tmp.getFolders() + 1);
				} catch (ItemExistsException e) {
					log.warn("ItemExistsException: {}", e.getMessage());
					out.write(deco.print(files[i].getPath(), files[i].length(), "ItemExists"));
					out.flush();
					stats.setOk(false);
				}
			} else {
				Document doc = new Document();
				doc.setPath(fldPath + "/" + files[i].getName());
				FileInputStream fisContent = new FileInputStream(files[i]);
				int size = fisContent.available();
				boolean docOk = true;
				
				try {
					DocumentModule dm = ModuleManager.getDocumentModule();
					dm.create(null, doc, fisContent);
					
					// Metadata
					Gson gson = new Gson();
					File jsFile = new File(files[i].getPath() + ".json");
					
					if (jsFile.exists() && jsFile.canRead()) {
						FileReader fr = new FileReader(jsFile);
						Document jsDoc = gson.fromJson(fr, Document.class);
						
						fr.close();
					}
					
					// Stats
					stats.setSize(stats.getSize() + size);
					stats.setDocuments(stats.getDocuments() + 1);
				} catch (UnsupportedMimeTypeException e) {
					log.warn("UnsupportedMimeTypeException: {}", e.getMessage());
					out.write(deco.print(files[i].getPath(), files[i].length(), "UnsupportedMimeType"));
					out.flush();
					stats.setOk(docOk = false);
				} catch (FileSizeExceededException e) {
					log.warn("FileSizeExceededException: {}", e.getMessage());
					out.write(deco.print(files[i].getPath(), files[i].length(), "FileSizeExceeded"));
					out.flush();
					stats.setOk(docOk = false);
				} catch (UserQuotaExceededException e) {
					log.warn("UserQuotaExceededException: {}", e.getMessage());
					out.write(deco.print(files[i].getPath(), files[i].length(), "UserQuotaExceeded"));
					out.flush();
					stats.setOk(docOk = false);
				} catch (VirusDetectedException e) {
					log.warn("VirusWarningException: {}", e.getMessage());
					out.write(deco.print(files[i].getPath(), files[i].length(), "VirusWarningException"));
					out.flush();
					stats.setOk(docOk = false);
				} catch (ItemExistsException e) {
					log.warn("ItemExistsException: {}", e.getMessage());
					out.write(deco.print(files[i].getPath(), files[i].length(), "ItemExists"));
					out.flush();
					stats.setOk(docOk = false);
				} finally {
					fisContent.close();					
				}

				if (docOk) {
					out.write(deco.print(files[i].getPath(), files[i].length(), null));
					out.flush();
				}
			}
		}
		
		log.debug("importDocumentsHelper: {}", stats);
		return stats;
	}
}
