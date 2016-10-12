package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Constants;

@WebFilter(filterName = "ManagerFilter", urlPatterns = { "/manager.xhtml" })
public class ManagerFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		
		if (null != session && null != session.getAttribute(Constants.USERTYPE)
				&& session.getAttribute(Constants.USERTYPE).toString().equals(Constants.MANAGER)){
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login.xhtml");
		}
	}

	@Override
	public void destroy() {
	}

}
