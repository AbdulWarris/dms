/**
 *  OpenKM, Open Document Management System (http://www.openkm.com)
 *  Copyright (C) 2006-2010  Paco Avila & Josep Llort
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

package es.git.openkm.core;

public class VersionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3106668935554641947L;

	public VersionException() {
		super();
	}

	public VersionException(String arg0) {
		super(arg0);
	}

	public VersionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public VersionException(Throwable arg0) {
		super(arg0);
	}
}
