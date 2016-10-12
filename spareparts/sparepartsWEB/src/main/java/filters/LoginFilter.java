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

@WebFilter(filterName = "LoginFilter", urlPatterns = { "/login.xhtml" })
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		if (null == session || null == session.getAttribute(Constants.USERTYPE)) {
			chain.doFilter(request, response);
		} else {
			switch (session.getAttribute(Constants.USERTYPE).toString()) {
			case Constants.ADMIN: {
				resp.sendRedirect(req.getContextPath() + "/admin.xhtml");
				break;
			}
			case Constants.USER: {
				resp.sendRedirect(req.getContextPath() + "/user.xhtml");
				break;
			}
			case Constants.MANAGER: {
				resp.sendRedirect(req.getContextPath() + "/manager.xhtml");
				break;
			}
			default: {
				session.invalidate();
			}
			}
		}
	}

	@Override
	public void destroy() {

	}

}
