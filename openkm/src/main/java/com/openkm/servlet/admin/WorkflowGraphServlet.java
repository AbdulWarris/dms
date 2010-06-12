package com.openkm.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openkm.api.OKMWorkflow;
import com.openkm.core.DatabaseException;
import com.openkm.core.WorkflowException;

/**
 * Workflow graphic servlet
 */
public class WorkflowGraphServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(WorkflowGraphServlet.class);
		
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		request.setCharacterEncoding("UTF-8");
		long id = Long.parseLong(request.getParameter("id"));
		String node = request.getParameter("node");
		ServletOutputStream sos = response.getOutputStream();
		updateSessionManager(request);
		
		if (node != null && !node.equals("")) {
			node = new String(node.getBytes("ISO-8859-1"), "UTF-8");
		}
		
		try {
			// Get image
			byte[] data = OKMWorkflow.getInstance().getProcessDefinitionImage(id, node);
						
			if (data != null) {
				// Disable browser cache
				response.setHeader("Expires", "Sat, 6 May 1971 12:00:00 GMT");
				response.setHeader("Cache-Control", "max-age=0, must-revalidate");
				response.addHeader("Cache-Control", "post-check=0, pre-check=0");
				response.setHeader("Pragma", "no-cache");
				
				// Send data
				response.setContentType("image/jpeg");
				response.setContentLength(data.length);
				sos.write(data);
			} else {
				response.setContentType("text/plain");
				sos.write("Null process definition image".getBytes());
			}
		} catch (WorkflowException e) {
			log.error(e.getMessage(), e);
		} catch (DatabaseException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			sos.flush();
			sos.close();
		}
	}
}
