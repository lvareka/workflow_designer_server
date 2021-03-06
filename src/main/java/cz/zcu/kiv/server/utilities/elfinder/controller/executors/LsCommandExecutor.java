package cz.zcu.kiv.server.utilities.elfinder.controller.executors;

import cz.zcu.kiv.server.utilities.elfinder.controller.ConnectorController;
import cz.zcu.kiv.server.utilities.elfinder.controller.executor.AbstractJsonCommandExecutor;
import cz.zcu.kiv.server.utilities.elfinder.controller.executor.CommandExecutor;
import cz.zcu.kiv.server.utilities.elfinder.controller.executor.FsItemEx;
import cz.zcu.kiv.server.utilities.elfinder.service.FsService;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LsCommandExecutor extends AbstractJsonCommandExecutor implements
        CommandExecutor
{
	@Override
	public void execute(FsService fsService, HttpServletRequest request,
			ServletContext servletContext, JSONObject json) throws Exception
	{
		String target = request.getParameter("target");
		String[] onlyMimes = request.getParameterValues("mimes[]");

		Map<String, FsItemEx> files = new HashMap<String, FsItemEx>();
		FsItemEx fsi = super.findItem(fsService, target);
		super.addChildren(files, fsi, onlyMimes,request.getHeader("email"));

		json.put("list", files2JsonArray(request, files.values()));
	}
}
